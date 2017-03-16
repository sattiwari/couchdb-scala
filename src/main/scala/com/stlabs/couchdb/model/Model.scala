package com.stlabs.couchdb.model

case class CouchException[T](content: T) extends Throwable {
  override def toString: String = "CouchException: " + content
}
