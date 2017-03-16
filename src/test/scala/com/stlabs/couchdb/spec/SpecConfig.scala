package com.stlabs.couchdb.spec

object SpecConfig {
  val couchDbHost   = System.getProperty("couchDbHost", "127.0.0.1")
  val couchDbPort   = System.getProperty("couchDbPort", "5984").toInt
  val couchDbScheme = System.getProperty("couchDbScheme", "http")

  private val log = org.log4s.getLogger

  log.info("----------------------")
  log.info(s"couchDbHost: $couchDbHost")
  log.info(s"couchDbPort: $couchDbPort")
  log.info("----------------------")
}
