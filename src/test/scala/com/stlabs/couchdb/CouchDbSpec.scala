package com.stlabs.couchdb

import com.stlabs.couchdb.spec.SpecConfig
import org.specs2.mutable.Specification

class CouchDbSpec extends Specification {

  val couch = new CouchDb(SpecConfig.couchDbHost, SpecConfig.couchDbPort, SpecConfig.couchDbScheme)
  val db1   = "couchdb-scala-couchdb-spec1"
  val db2   = "couchdb-scala-couchdb-spec2"


  "User interface" >> {

    "get info about the db instance" >> {

    }

  }

}
