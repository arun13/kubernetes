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
		   	stage('Quality Gate'){
		   		steps {
		   		 timeout(time: 1, unit: 'MINUTES') { // timeout(time: 1, unit: 'HOURS') { // Just in case something goes wrong, pipeline will be killed after a timeout
		   			script{
		   				sleep(10)//Adding sleep to avoid some unexpected failure    	  
	    			def qg = waitForQualityGate() // Reuse taskId previously collected by withSonarQubeEnv
	    			if (qg.status != 'OK') {
	      				error "Pipeline aborted due to quality gate failure: ${qg.status}"
	    				}
 					  }
 					}
 				}
 			}
 			stage('Build') {
      			steps {
      				withMaven(maven:'Maven'){
        				sh 'mvn install'
        			}
      		}	
		  }
		  stage('Build Docker Image') {
     			steps {
      				sh "docker build -t account-app:${env.BUILD_ID} ."
	 		}
	 	}
	 	  
	 }
}