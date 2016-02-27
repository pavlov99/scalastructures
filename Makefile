.PHONY: help
# target: help - Display callable targets
help:
	@egrep "^# target:" [Mm]akefile | sed -e 's/^# target: //g' | sort


.PHONY: test
# target: test - Test functionality and code style
test:
	sbt test scalastyle test:scalastyle


.PHONY: build
# target: build - build jar from source code.
build:
	@sbt package


.PHONY: doc
# target: doc - generate documentation.
doc:
	@sbt doc
