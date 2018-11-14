pipeline{
    agent{
    	stages{
    	    stage('Compile'){
    	        steps{
		   	        withMaven(maven:'Maven'){
		   	        'mvn clean compile'
    				}            
    	        }
    	    }
    	}
	}
}
