package com.drsquidop.scala_jruby_activerecord_scala_client

case class ValidationException(errors: Map[String, Seq[String]]) extends Exception
