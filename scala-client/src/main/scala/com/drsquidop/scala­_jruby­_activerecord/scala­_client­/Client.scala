package com.drsquidop.scala­_jruby­_activerecord.scala_client

import scala.util.Try

object Client {
  def create(myModel: MyModel): Try[MyModel] = ???
  
  def get(id: Int): Try[MyModel] = ???
  
  def all(): Try[Seq[MyModel]] = ???
}
