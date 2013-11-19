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

::

  gem build model_gem.gemspec

model-gem-1.0-SNAPSHOT.gem will be created.

To unpack to check:

::

  gem unpack model-gem-1.0-SNAPSHOT.gem

See:

* http://guides.rubygems.org/make-your-own-gem/
* `Using Bundler with Rubygem gemspecs <http://bundler.io/v1.5/rubygems.html>`_

Build JAR
---------

::

  gem install -i ./dir_to_be_jarred model-gem --no-rdoc --no-ri model-gem-1.0-SNAPSHOT.gem
  jar cf model-gem-1.0-SNAPSHOT.jar -C dir_to_be_jarred .

Copy to local ivy directory:

::

  mkdir -p $HOME/.ivy2/local/com.drsquidop/scala-jruby-activerecord-model-gem/1.0-SNAPSHOT/jars
  cp model-gem-1.0-SNAPSHOT.jar $HOME/.ivy2/local/com.drsquidop/scala-jruby-activerecord-model-gem/1.0-SNAPSHOT/jars/scala-jruby-activerecord-model-gem.jar

  mkdir -p $HOME/.ivy2/local/com.drsquidop/scala-jruby-activerecord-model-gem/1.0-SNAPSHOT/ivys
  cp ivy.xml $HOME/.ivy2/local/com.drsquidop/scala-jruby-activerecord-model-gem/1.0-SNAPSHOT/ivys/ivy.xml

See:

* http://blog.nicksieger.com/articles/2009/01/10/jruby-1-1-6-gems-in-a-jar/
* http://stackoverflow.com/questions/9844668/how-can-i-use-gems-in-a-jar-with-embedded-jruby

Development tips
----------------

To try the code in ``irb`` during development:

::

  irb -Ilib
