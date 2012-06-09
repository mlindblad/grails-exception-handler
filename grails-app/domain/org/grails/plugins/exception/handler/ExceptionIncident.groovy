package org.grails.plugins.exception.handler

class ExceptionIncident {

	Date dateCreated
	String userDescription
	String remoteIp
	String userAgent
	String params
	String requestHeaders
	
	static belongsTo = [exceptionLog: ExceptionLog]

	static mapping = {
		params type:'text'
		requestHeaders type:'text'
	}
	
    static constraints = {
		userDescription nullable:true, blank:false
		userAgent nullable:true, blank:false
		params nullable:true, blank:false
    }
}
