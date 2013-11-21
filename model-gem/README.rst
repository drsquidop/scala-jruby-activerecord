Run ``rake -T`` to see all available tasks.

DB migration
------------

Config config/database.yml, then run:

* ``rake db:create`` to create DB
* ``rake db:migrate`` to create tables in the DB

Tests
-----

::

  rake spec

Build gem
---------

Create pkg/model-gem-1.0.gem:

::

  rake build

Check:

::

  gem unpack model-gem-1.0.gem

See:

* http://guides.rubygems.org/make-your-own-gem/
* `Using Bundler with Rubygem gemspecs <http://bundler.io/v1.5/rubygems.html>`_

Build JAR
---------

Collect model-gem and all its dependency libraries to a directory:

::

  gem install -i ./dir_to_be_jarred model-gem --no-rdoc --no-ri model-gem-1.0.gem

Build JAR file from the directory:

::

  jar cf model-gem-1.0-SNAPSHOT.jar -C dir_to_be_jarred .

Check:

::

  jar tf model-gem-1.0-SNAPSHOT.jar

You can also unzip it to check because it's only a ZIP file.

Publish to local machine
------------------------

Publish to local machine ivy directory, for `SBT <http://www.scala-sbt.org/>`_ to use:

::

  mkdir -p $HOME/.ivy2/local/com.drsquidop/scala-jruby-activerecord-model-gem/1.0-SNAPSHOT/jars
  cp model-gem-1.0-SNAPSHOT.jar $HOME/.ivy2/local/com.drsquidop/scala-jruby-activerecord-model-gem/1.0-SNAPSHOT/jars/scala-jruby-activerecord-model-gem.jar

  mkdir -p $HOME/.ivy2/local/com.drsquidop/scala-jruby-activerecord-model-gem/1.0-SNAPSHOT/ivys
  cp ivy.xml $HOME/.ivy2/local/com.drsquidop/scala-jruby-activerecord-model-gem/1.0-SNAPSHOT/ivys/ivy.xml

See:

* http://blog.nicksieger.com/articles/2009/01/10/jruby-1-1-6-gems-in-a-jar/

Use the gem from a Scala SBT project
------------------------------------

From an SBT project, you can now declare the dependency:

::

  libraryDependencies += "com.drsquidop" % "scala-jruby-activerecord-model-gem" % "1.0-SNAPSHOT"

To actually use it, you also need JRuby:

::

  libraryDependencies += "org.jruby" % "jruby-complete" % "1.7.6"

Sample Scala code that uses the gem:

::

  import javax.script.ScriptEngine
  import javax.script.ScriptEngineManager

  val engine = {
    System.setProperty("org.jruby.embed.compilemode",    "jit")
    System.setProperty("org.jruby.embed.compat.version", "JRuby1.9")

    val manager = new ScriptEngineManager
    manager.getEngineByName("jruby")
  }

  engine.eval("require 'model_gem'")

See:

* https://github.com/jruby/jruby/wiki/RedBridge
* http://stackoverflow.com/questions/9844668/how-can-i-use-gems-in-a-jar-with-embedded-jruby

Development tips
----------------

To try the code in ``irb`` during development:

::

  irb -Ilib
