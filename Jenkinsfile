pipeline{
    agent any
    	stages{
    	    stage('Compile'){
    	        steps{
		   	        withMaven(maven:'Maven'){
		   	         sh 'mvn clean compile'
    				}            
    	        }
    	    }
       	    stage('Unit Testing'){
    	        steps{
		   	        withMaven(maven:'Maven'){
		   	         sh 'mvn test'
    				}            
    	        }
    	    }
    	    stage('SonarQube analysis'){
    	    steps{
    			    withSonarQubeEnv('sonarqube') {
				    // requires SonarQube Scanner for Maven 3.2+
				    sh 'mvn org.sonarsource.scanner.maven:sonar-maven-plugin:3.2:sonar'
    				}    
    	    	}
		   	}	
	}
}