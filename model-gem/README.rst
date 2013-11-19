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

Development
-----------

To try the code in ``irb`` during development:

::

  irb -Ilib
