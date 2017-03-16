package com.stlabs.couchdb.spec

import com.stlabs.couchdb.model.TypeMapping

trait Fixtures {

  case class FixPerson(name: String, age: Int)

//  val typeMapping = TypeMapping(classOf[FixPerson] -> "Person").toOption.get

}
