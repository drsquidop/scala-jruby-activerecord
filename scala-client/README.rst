JSR 223 demo that uses a JRuby gem, which in turn uses ActiveRecord.

Run tests
---------

Create test DB (see model-gem), and run:

::

  sbt test

Development
-----------

Generate Eclipse project: ``sbt eclipse``.

Generate IntelliJ project: ``sbt gen-idea``.

Reference:

* `Embedding JRuby <https://github.com/jruby/jruby/wiki/RedBridge>`_
* JSR 223 - http://www.oracle.com/technetwork/articles/dsl/jruby-141877.html#ej
* https://github.com/jruby/jruby/wiki/JRubyAndJavaCodeExamples
* https://kenai.com/projects/jruby/pages/WalkthroughsAndTutorials
* http://java.ociweb.com/mark/programming/ActiveRecord.html

Notes:

* Only JSR 223 standard APIs are used. JRuby native APIs are not used.
* Convenient libraries are not used. Ex: https://github.com/mcamou/scuby
