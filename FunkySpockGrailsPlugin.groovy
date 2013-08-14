class FunkySpockGrailsPlugin {
    // the plugin version
    def version = "0.1.0"
    // the version or versions of Grails the plugin is designed for
    def grailsVersion = "2.0 > *"
    // resources that are excluded from plugin packaging
    def pluginExcludes = [
        "**/.gitignore",
        "grails-app/**",
        "src/java/**",
        "web-app/**",
        "lib/spock-*"
    ]

    def loadAfter = ['spock']

    // TODO Fill in these fields
    def title = "Funky Spock Plugin" // Headline display name of the plugin
    def author = "Shane Riddell"
    def authorEmail = "riddell.shane@gmail.com"
    def description = '''Adds spock test type to functional testing phase.'''

    // URL to the plugin's documentation
    def documentation = "http://grails.org/plugin/grails-funky-spock"

    def license = "APACHE"

    def scm = [ url: "https://github.com/sriddell/grails-funky-spock" ]

}