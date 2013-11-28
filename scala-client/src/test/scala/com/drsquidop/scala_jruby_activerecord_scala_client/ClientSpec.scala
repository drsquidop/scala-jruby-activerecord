// Copyright 2013 Game Play Network, Inc.
//
// Licensed under the Apache License, Version 2.0 (the "License");
// you may not use this file except in compliance with the License.
// You may obtain a copy of the License at
//
//     http://www.apache.org/licenses/LICENSE-2.0
//
// Unless required by applicable law or agreed to in writing, software
// distributed under the License is distributed on an "AS IS" BASIS,
// WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
// See the License for the specific language governing permissions and
// limitations under the License.

package com.drsquidop.scala_jruby_activerecord_scala_client

import java.util.UUID
import scala.util.Failure

import org.specs2.mutable._

class ClientSpec extends Specification {
  Client.connect("test")

  "Client.new(any string) returns Success(MyModel)" in {
    val name = UUID.randomUUID().toString
    val r    = createMyModel(name)
    r must beSuccessfulTry

    // Verify that the record was actually created

    val m = r.get
    m.name must_== name

    val all    = Client.all().get
    val exists = all.exists { m2 => m2.id == m.id && m2.name == name}
    exists must beTrue
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
    createMyModel()

    val r = Client.all()
    r must beSuccessfulTry
    (r.get.length > 1) must beTrue
  }

  private def createMyModel(name: String = UUID.randomUUID().toString) = {
    val m = MyModel(None, name)
    Client.create(m)
  }
}
