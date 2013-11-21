package com.drsquidop.scala_jruby_activerecord_scala_client

import java.util.UUID
import scala.util.Failure

import org.specs2.mutable._

class ClientSpec extends Specification {
  private val ymlConfigs = """
development:
  adapter: jdbcpostgresql
  database: scala-jruby-activerecord-dev
  host: localhost

# Warning: The database defined as "test" will be erased and
# re-generated from your development database when you run "rake".
# Do not set this db to the same as development or production.
test:
  adapter: jdbcpostgresql
  database: scala-jruby-activerecord-test
  host: localhost

production:
  adapter: jdbcpostgresql
  database: scala-jruby-activerecord
  host: localhost
"""

  Client.connect(ymlConfigs, "test")

  "Client.new(any string) returns Success(MyModel)" in {
    val r = createMyModel()
    r must beSuccessfulTry
  }

  "Client.new(empty string) returns Failure(ValidationException) with ActiveRecord validation error map" in {
    val r = createMyModel("")
    r must beFailedTry

    val errors = r.asInstanceOf[Failure[MyModel]].exception.asInstanceOf[ValidationException].errors
    errors must haveKey("name")
  }

  "Client.get(valid id) returns Success(MyModel)" in {
    val m    = createMyModel().get
    val id   = m.id
    val name = m.name

    val r = Client.get(id.get)
    r          must    beSuccessfulTry
    r.get.id   must_== id
    r.get.name must_== name
  }

  """Client.get(invalid id) returns Failure(Exception("MyModel not found"))""" in {
    val r = Client.get(-1)
    r must beFailedTry
  }

  "Client.all returns Success(List[MyModel]) with several records" in {
    createMyModel()

    val r = Client.all()
    r must beSuccessfulTry
    r.get.nonEmpty must beTrue
  }

  private def createMyModel(name: String = UUID.randomUUID().toString) = {
    val m = MyModel(None, name)
    Client.create(m)
  }
}
