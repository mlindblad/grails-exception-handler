package org.grails.plugins.exceptionHandler

import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

import org.codehaus.groovy.grails.web.errors.GrailsExceptionResolver
import org.springframework.web.servlet.ModelAndView

import exception.handler.ExceptionIncident
import exception.handler.ExceptionLog

class ConfigurableExceptionResolver extends GrailsExceptionResolver {

	@Override
	public ModelAndView resolveException(HttpServletRequest request,
			HttpServletResponse response, Object handler, Exception ex) {
			
		def root = getRootCause(ex)
		
		def exceptionLog = new ExceptionLog(stackTrace: ex.stackTrace.toString(),
											 uri: request.forwardURI ?: request.'javax.servlet.error.request_uri',
											 className: root?.getClass()?.name ?: ex.getClass().name,
											 message: ex.message,
											 causedBy: root && root.message != ex.message? root.message: null)
		def hash = exceptionLog.hash
		
		exceptionLog.id = hash
		
		def existing = ExceptionLog.read(hash)
		
		ExceptionLog.withTransaction {status ->
			if (!existing) {
				exceptionLog.save(failOnError: true, flush: true)
			} else {
				exceptionLog = existing 
			}
			
			def exceptionIncident = new ExceptionIncident(remoteIp: request.remoteAddr,
														   userAgent: request.getHeader('User-Agent'),
														   params: request.parameterMap.toString(),
														   requestHeaders: request.headers.toString(),
														   exceptionLog: existing).save(failOnError: true)
			
		}
		
	}
	
	

}
