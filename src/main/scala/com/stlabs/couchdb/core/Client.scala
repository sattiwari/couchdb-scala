package com.stlabs.couchdb.core

import com.stlabs.couchdb.json.UpickleImplicits
import com.stlabs.couchdb.model._
import org.http4s._
import org.http4s.Method._
import org.http4s.client.blaze.PooledHttp1Client
import org.http4s.util.CaseInsensitiveString

import scalaz.concurrent.Task
import scalaz.Scalaz._

class Client(config: Config) extends UpickleImplicits {

  type R[T] = upickle.Reader[T]
  type W[T] = upickle.Writer[T]

  private val log = org.log4s.getLogger

  val client = PooledHttp1Client()

  val uriBase = Uri(
    scheme = CaseInsensitiveString(config.scheme).some,
    authority = Uri.Authority(
      host = Uri.IPv4(address = config.host),
      port = config.port.some
    ).some)

  def url(resource: String, params: Seq[(String, String)] = Seq.empty[(String, String)]): Uri = {
    uriBase.copy(path = resource).setQueryParams(
      params.map(x => (x._1, Seq(x._2))).toMap
    )
  }

  def req(request: Request, expectedStatus: Status): Task[Response] = {
    log.debug(s"Making request $request")
    client(request) flatMap { response =>
      log.debug(s"Received response $response")
      if(response.status == expectedStatus) {
        Task.now(response)
      } else {
        log.warn(s"unexpected response status ${response.status}, expected $expectedStatus")
        for {
          responseBody <- response.as[String]
          requestBody <- EntityDecoder.decodeString(request)
          errorRaw = upickle.read[Res.Error](responseBody)
          error = errorRaw.copy(
            status = response.status,
            request = request.toString,
            requestBody = requestBody
          )
          _ = log.warn(s"Request error $error")
          fail <- Task.fail(CouchException[Res.Error](error))
        } yield fail
      }
    }
  }

  def reqAndRead[T: R](request: Request, expectedStatus: Status): Task[T] = {
    for {
      response <- req(request, expectedStatus)
      asString <- response.as[String]
    } yield upickle.read[T](asString)
  }

  def get[T: R](resource: String, expectedStatus: Status, params: Seq[(String, String)] = Seq.empty[(String, String)]): Task[T] = {
    val request = Request(method = GET, uri = url(resource, params), headers = Headers(Header("Accept", "application/json")))
    reqAndRead[T](request, expectedStatus)
  }

}
