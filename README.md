#grails-funky-spock

Grails plugin to add spock test type to functional test phase.

##Installation

For grails-2 add the following line to the plugins section of your BuildConfig.groovy

    test ":funky-spock:0.1"

Follow the Spock Plugin [instructions](http://grails.org/plugin/spock) to install it and its dependencies.

For example, if using Grails 2.2:
```
dependencies {
  test "org.spockframework:spock-grails-support:0.7-groovy-2.0"
}

plugins {
  test(":spock:0.7") {
    exclude "spock-grails-support"
}

test ":funky-spock:0.1"
```

##Running tests

    grails test-app functional:spock

