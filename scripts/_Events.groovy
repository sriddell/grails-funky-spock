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
import org.codehaus.groovy.grails.compiler.GrailsProjectCompiler

projectCompiler = new GrailsProjectCompiler(pluginSettings)
projectCompiler.configureClasspath()

eventTestPhasesStart = { phases ->
    //look for the test type in grails 2.3 first, then for the one in the spock plugin
    types = ['org.codehaus.groovy.grails.test.spock.GrailsSpecTestType','grails.plugin.spock.test.GrailsSpecTestType']
    def testClazz = null
    for (String className:types) {
        testClazz = loadClass(className)
        if (testClazz != null) break
    }
    if (null == testClazz) {
        throw new Exception("funky-spock could not find any spock test types; looked for " + types)
    }
    def loaderClazz = loadClass('grails.plugin.funky.spock.Loader')
    loaderClazz.addTestTypeIfNeeded(phases, binding, testClazz)
}


// soft load classes
// http://jira.grails.org/browse/GRAILS-6453
// http://grails.1312388.n4.nabble.com/plugin-classes-not-included-in-classpath-for-plugin-scripts-td2271962.html
loadClass = { className ->
    def load = { name ->
        classLoader.loadClass(name)
    }
    try {
        load(className)
    } catch (ClassNotFoundException ignored) {
        projectCompiler.compileAll()
        try {
            load(className)
        } catch (ClassNotFoundException e) {
        }
    }
}

