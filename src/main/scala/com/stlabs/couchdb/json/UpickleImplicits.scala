package com.stlabs.couchdb.json

import org.http4s.Status
import upickle.{Js, Reader, Types, Writer}

trait UpickleImplicits extends Types {

  implicit val statusW: Writer[Status] = Writer[Status] {
    x => Js.Num(x.code.toDouble)
  }

  implicit val statusR: Reader[Status] = Reader[Status] {
    case json: Js.Num => Status.fromInt(json.value.toInt).toOption.get
  }

}
