Data structures in Scala
========================

.. image:: https://circleci.com/gh/pavlov99/scalastructures/tree/master.svg?style=shield
    :alt: CircleCI build
    :target: https://circleci.com/gh/pavlov99/scalastructures/tree/master

Data Structures written in scala

Development
===========
Test code

.. code-block:: bash

   sbt test

Style check:

.. code-block:: bash

   sbt scalastyle
   sbt test:scalastyle

Generate documentation (stored in target/scala-2.11/api)

.. code-block:: bash

   sbt doc

Notes about styleguide:

* Scala stylistic guidelines  http://docs.scala-lang.org/style/
* Scala cheatsheet http://docs.scala-lang.org/cheatsheets/
* Databricks guide https://github.com/databricks/scala-style-guide
* [Purely Functional Data Structures by Chris Okasaki](http://www.amazon.com/Purely-Functional-Structures-Chris-Okasaki/dp/0521663504)
* [What's new in Purely Functional DS since Okasaki](http://cstheory.stackexchange.com/questions/1539/whats-new-in-purely-functional-data-structures-since-okasaki)

Code documentation guidelines: http://docs.scala-lang.org/overviews/scaladoc/for-library-authors.html
