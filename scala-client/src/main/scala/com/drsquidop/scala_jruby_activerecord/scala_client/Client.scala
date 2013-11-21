package com.drsquidop.scala_jruby_activerecord_scala_client

import java.util.{List => JList, Map => JMap}
import javax.script.{Invocable, ScriptEngine, ScriptEngineManager}

import scala.collection.JavaConverters._
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

    val valid = invocable.invokeMethod(m, "valid?").asInstanceOf[Boolean]

    if (valid) {
      toScalaMyModel(m)
    } else {
      val errors   = invocable.invokeMethod(m, "errors")
      val messages = invocable.invokeMethod(errors, "messages").asInstanceOf[JMap[Any, JList[String]]].asScala

      // messages is map of Symbol -> JList
      // Convert it to map of String -> List
      val messages2 = messages.map { case (k, v) => (k.toString, v.asScala) }
      throw ValidationException(messages2.toMap)
    }
  }

  def get(id: Long): Try[MyModel] = Try {
    val m = invocable.invokeMethod(RMyModel, "find", Long.box(id))
    toScalaMyModel(m)
  }

  def all(): Try[Seq[MyModel]] = Try {
    val activeRecordRelation = invocable.invokeMethod(RMyModel, "all")
    val ms                   = invocable.invokeMethod(activeRecordRelation, "to_a").asInstanceOf[JList[Any]].asScala
    ms.map(toScalaMyModel _)
  }

  private def toScalaMyModel(rubyMyModel: Any) = {
    val id   = invocable.invokeMethod(rubyMyModel, "id").asInstanceOf[Long]
    val name = invocable.invokeMethod(rubyMyModel, "name").asInstanceOf[String]
    MyModel(Some(id), name)
  }
}
