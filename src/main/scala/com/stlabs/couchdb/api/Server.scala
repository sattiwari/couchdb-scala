package com.stlabs.couchdb.api

import com.stlabs.couchdb.core.Client
import com.stlabs.couchdb.model.Res
import org.http4s.Status

import scalaz.concurrent.Task

class Server(client: Client) {

  def info: Task[Res.ServerInfo] = {
    client.get[Res.ServerInfo]("/", Status.Ok)
  }

}
