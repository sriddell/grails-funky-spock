class FunkySpockGrailsPlugin {
    def version = "0.2.1"
    def grailsVersion = "2.0 > *"
    def pluginExcludes = [
        "grails-app/**",
        "src/java/**",
        "web-app/**",
        "lib/spock-*"
    ]

    def loadAfter = ['spock']

    def title = "Funky Spock Plugin"
    def author = "Shane Riddell"
    def authorEmail = "riddell.shane@gmail.com"
    def description = 'Adds spock test type to functional testing phase.'
    def documentation = "http://grails.org/plugin/grails-funky-spock"

    def license = "APACHE"
    def scm = [url: "https://github.com/sriddell/grails-funky-spock"]
    def issueManagement = [ system: "GITHUB", url: "https://github.com/sriddell/grails-funky-spock/issues" ]
}
