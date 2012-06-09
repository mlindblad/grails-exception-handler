package org.grails.plugins.exception.handler

import javax.servlet.http.HttpServletRequest

import org.codehaus.groovy.grails.web.errors.GrailsExceptionResolver


class ConfigurableExceptionResolver extends GrailsExceptionResolver {

    @Override
    void logStackTrace(Exception exception, HttpServletRequest request) {
        LOG.error(getRequestLogMessage(exception, request), exception);

        def root = getRootCause(exception)
        def exceptionLog = new ExceptionLog(className: root?.getClass()?.name ?: exception.getClass().name,
                                            causedBy: root && root.message != exception.message? root.message: null,
                                            message: exception.message,
                                            stackTrace: exception.stackTrace.toString(),
                                            uri: request.forwardURI ?: request.'javax.servlet.error.request_uri')
        def hash = exceptionLog.hash
        exceptionLog.id = hash

        ExceptionLog.withTransaction {status ->
            //try {
                def existing = ExceptionLog.read(hash)
                if (!existing) {
                    exceptionLog.save(failOnError: true, flush: true)
                } else {
                    exceptionLog = existing
                }

                def exceptionIncident = new ExceptionIncident(exceptionLog: existing,
                                                              params: request.parameterMap.toString(),
                                                              requestHeaders: request.headers.toString(),
                                                              remoteIp: request.remoteAddr,
                                                              userAgent: request.getHeader('User-Agent')).save(failOnError: true)
            /*} catch (e) {
                // Fails silently
                log.error("Could save exception: ${e.message}")
            } */

        }
    }

}
