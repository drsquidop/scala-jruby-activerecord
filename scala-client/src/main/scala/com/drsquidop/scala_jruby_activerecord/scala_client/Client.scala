package com.drsquidop.scala_jruby_activerecord_scala_client

import javax.script.{Invocable, ScriptEngine, ScriptEngineManager}
import scala.util.Try

object Client {
  // This is a demo about JSR 223, thus JRuby native APIs are not used.
  // We only use things in package javax.script:
  // http://docs.oracle.com/javase/6/docs/api/javax/script/package-summary.html

  private val engine = {
    System.setProperty("org.jruby.embed.compilemode",    "jit")
    System.setProperty("org.jruby.embed.compat.version", "JRuby1.9")

    val manager = new ScriptEngineManager
    manager.getEngineByName("jruby")
  }

  private val invocable = engine.asInstanceOf[Invocable]

  engine.eval("require 'model_gem'")

  private val RModelGem = engine.eval("ModelGem")
  private val RMyModel  = engine.eval("ModelGem::MyModel")

  def connect(ymlConfigs: String, env: String) {
    invocable.invokeMethod(RModelGem, "connect", ymlConfigs, env)
  }

  def create(myModel: MyModel): Try[MyModel] = Try {
    val h = engine.eval("{}")
    invocable.invokeMethod(h, "store", "name", myModel.name)
    val m = invocable.invokeMethod(RMyModel, "create", h)
    toScalaMyModel(m)
  }

  def get(id: Long): Try[MyModel] = Try {
    val m = invocable.invokeMethod(RMyModel, "find", Long.box(id))
    toScalaMyModel(m)
  }

  def all(): Try[Seq[MyModel]] = Try {
    val activeRecordRelation = invocable.invokeMethod(RMyModel, "all")
    val rubyArray            = invocable.invokeMethod(activeRecordRelation, "to_a")
    val javaArray            = invocable.invokeMethod(rubyArray, "to_java").asInstanceOf[Array[Any]]
    javaArray.map(toScalaMyModel _)
  }

  private def toScalaMyModel(rubyMyModel: Any) = {
    val id   = invocable.invokeMethod(rubyMyModel, "id").asInstanceOf[Long]
    val name = invocable.invokeMethod(rubyMyModel, "name").asInstanceOf[String]
    MyModel(Some(id), name)
  }
}
