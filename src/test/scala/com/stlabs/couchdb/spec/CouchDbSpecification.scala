package com.stlabs.couchdb.spec

import com.stlabs.couchdb.core.Client
import com.stlabs.couchdb.model.Config
import org.specs2.mutable.Specification
import org.specs2.scalaz.DisjunctionMatchers

import scalaz.\/
import scalaz.concurrent.Task

trait CouchDbSpecification extends Specification
                                   with DisjunctionMatchers {

  sequential

  val client = new Client(Config(SpecConfig.couchDbHost, SpecConfig.couchDbPort, SpecConfig.couchDbScheme))

  def await[T](future: Task[T]): Throwable \/ T = future.attemptRun

  def awaitRight[T](future: Task[T]): T = {
    val res = await(future)
    res must beRightDisjunction
    res.toOption.get
  }


}
