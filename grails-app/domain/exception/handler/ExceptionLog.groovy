package exception.handler

class ExceptionLog {
	
	String id
	String stackTrace
	String uri
	String className
	String message
	String causedBy
	
	Date dateCreated
	
	def beforeInsert() {
		id = getHash() 
	}
	
	def getHash() {
		(stackTrace + uri + className + message + causedBy).encodeAsMD5()
	}
	
	static mapping = {
		stackTrace type:'text'
		id generator:'assigned'
	}

    static constraints = {
    }
}
