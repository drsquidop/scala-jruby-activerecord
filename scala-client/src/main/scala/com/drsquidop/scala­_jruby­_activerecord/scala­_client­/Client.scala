package com.drsquidop.scala­_jruby­_activerecord.scala_client

import javax.script.ScriptEngine
import javax.script.ScriptEngineManager

import scala.util.Try

object Client {
  // https://github.com/jruby/jruby/wiki/RedBridge
  private val engine = {
    System.setProperty("org.jruby.embed.compilemode",    "jit")
    System.setProperty("org.jruby.embed.compat.version", "JRuby1.9")

    val manager = new ScriptEngineManager
    manager.getEngineByName("jruby")
  }

  engine.eval("puts 'Hello World!'")

  def create(myModel: MyModel): Try[MyModel] = ???

  def get(id: Int): Try[MyModel] = ???

  def all(): Try[Seq[MyModel]] = ???
}
