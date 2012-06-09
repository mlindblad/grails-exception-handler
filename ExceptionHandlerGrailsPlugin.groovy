import org.grails.plugins.exception.handler.ConfigurableExceptionResolver

class ExceptionHandlerGrailsPlugin {
    // the plugin version
    def version = "0.1"
    // the version or versions of Grails the plugin is designed for
    def grailsVersion = "1.3 > *"
    // the other plugins this plugin depends on
    //def dependsOn = [mail:"1.0 > *"]
    // resources that are excluded from plugin packaging
    def pluginExcludes = [
        "grails-app/views/error.gsp"
    ]
	
	def loadAfter = ['controllers']

    def title = "Exception Handler Plugin" // Headline display name of the plugin
    def author = "Konstantinos Kostarellis"
    def authorEmail = "kosta.grails@gmail.com"

//TODO: Get a nice description
    def description = '''\
Brief summary/description of the plugin.
'''

    // URL to the plugin's documentation
    def documentation = "http://grails.org/plugin/exception-handler"

    // Extra (optional) plugin metadata

    // License: one of 'APACHE', 'GPL2', 'GPL3'
    def license = "APACHE"

    // Details of company behind the plugin (if there is one)
    def organization = [ name: "Hackergarten", http: "http://gr8conf.eu/Presentations/Hackergarten-on-tour" ]

    // Any additional developers beyond the author specified above.
    def developers = [
            [ name: "Benoit Hediard", email: "ben@benorama.com" ],
            [ name: "Álvaro Sánchez-Mariscal", email: "alvaro.sanchez@salenda.es" ],
            [ name: "Alberto Vilches", email: "vilches@gmail.com" ],
            [ name: "Martin Lindblad"]]

    // Location of the plugin's issue tracker.
//    def issueManagement = [ system: "JIRA", url: "http://jira.grails.org/browse/GPMYPLUGIN" ]

    // Online location of the plugin's browseable source code.
    def scm = [ url: "https://github.com/mlindblad/grails-exception-handler" ]

    def doWithWebDescriptor = { xml ->
        // TODO Implement additions to web.xml (optional), this event occurs before
    }

    def doWithSpring = {
		exceptionHandler(ConfigurableExceptionResolver) {
            exceptionMappings = ['java.lang.Exception': '/error']
        }    
	}

    def doWithDynamicMethods = { ctx ->
        // TODO Implement registering dynamic methods to classes (optional)
    }

    def doWithApplicationContext = { applicationContext ->
        // TODO Implement post initialization spring config (optional)
    }

    def onChange = { event ->
        // TODO Implement code that is executed when any artefact that this plugin is
        // watching is modified and reloaded. The event contains: event.source,
        // event.application, event.manager, event.ctx, and event.plugin.
    }

    def onConfigChange = { event ->
        // TODO Implement code that is executed when the project configuration changes.
        // The event is the same as for 'onChange'.
    }

    def onShutdown = { event ->
        // TODO Implement code that is executed when the application shuts down (optional)
    }
}
