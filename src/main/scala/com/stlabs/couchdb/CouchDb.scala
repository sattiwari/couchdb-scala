package com.stlabs.couchdb

import com.stlabs.couchdb.api._
import com.stlabs.couchdb.core.Client
import com.stlabs.couchdb.model.{Config, TypeMapping}

import scalaz.Memo

case class CouchDbApi(name: String, docs: Documents, design: Design, query: Query)

class CouchDb(host: String, port: Int, scheme: String) {

  val client = new Client(Config(host, port, scheme))
  val server = new Server(client)
  val dbs = new Databases(client)

  private val memo = Memo.mutableHashMapMemo[(String, TypeMapping), CouchDbApi] {
    case (db, types) => CouchDbApi(db, new Documents(client, db, types), new Design(client, db), new Query(client, db))
  }


}

object CouchDb {

  def apply(host: String, port: Int, scheme: String = "http"): CouchDb = {
    new CouchDb(host, port, scheme)
  }

}
