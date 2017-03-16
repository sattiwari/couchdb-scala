package com.stlabs.couchdb

import com.stlabs.couchdb.spec.{CouchDbSpecification, SpecConfig}

class CouchDbSpec extends CouchDbSpecification {

  val couch = new CouchDb(SpecConfig.couchDbHost, SpecConfig.couchDbPort, SpecConfig.couchDbScheme)
  val db1   = "couchdb-scala-couchdb-spec1"
  val db2   = "couchdb-scala-couchdb-spec2"


  "User interface" >> {

    "get info about the db instance" >> {
      awaitRight(couch.server.info).couchdb mustEqual "Welcome"
    }

  }

}
