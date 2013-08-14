/*
 * Copyright 2013 Shane Riddell
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
 package grails.plugin.funky.spock

import grails.plugin.spock.test.GrailsSpecTestType
import spock.lang.Specification

class LoadSpec extends Specification {
    private Map binding

    def setup() {
        binding = [variables: [functionalTests:[]]]
    }

    def "Test no type added without functional phase"() {
        when:
        Loader.addTestTypeIfNeeded(['unit'], binding, GrailsSpecTestType.class)

        then:
        0 == binding.variables.functionalTests.size()
    }


    def "Test type is added in functional phase"() {
        when:
        Loader.addTestTypeIfNeeded(['functional'], binding, GrailsSpecTestType.class)

        then:
        1                        == binding.variables.functionalTests.size()
        'spock'                  == binding.variables.functionalTests[0].name
        'functional'             == binding.variables.functionalTests[0].relativeSourcePath
        GrailsSpecTestType.class == binding.variables.functionalTests[0].getClass()
    }

    def "Test type is only added once"() {
        when:
        Loader.addTestTypeIfNeeded(['functional'], binding, GrailsSpecTestType.class)
        Loader.addTestTypeIfNeeded(['functional'], binding, GrailsSpecTestType.class)

        then:
        1                        == binding.variables.functionalTests.size()
        'spock'                  == binding.variables.functionalTests[0].name
        'functional'             == binding.variables.functionalTests[0].relativeSourcePath
        GrailsSpecTestType.class == binding.variables.functionalTests[0].getClass()
    }

    def "Test that other GrailsSpecTestType instances can be used"() {
        given:
        binding.variables = [functionalTests:[new GrailsSpecTestType('spock-somethingelse','somethingelse')]]

        when:
        Loader.addTestTypeIfNeeded(['functional'], binding, GrailsSpecTestType.class)

        then:
        2                        == binding.variables.functionalTests.size()
        'spock'                  == binding.variables.functionalTests[1].name
        'functional'             == binding.variables.functionalTests[1].relativeSourcePath
        GrailsSpecTestType.class == binding.variables.functionalTests[1].getClass()
    }
}
