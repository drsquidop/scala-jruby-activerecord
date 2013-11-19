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

model-gem-<version>.gem will be created.

To unpack to check:

::

  gem unpack model-gem-<version>.gem

See `make-your-own-gem <http://guides.rubygems.org/make-your-own-gem/>`_.

Build JAR
---------

FIXME

::

  bundle install --deployment --standalone

Development tips
----------------

To try the code in ``irb`` during development:

::

  irb -Ilib
