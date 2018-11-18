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
		   stage('Build Account Service Docker Image') {
     			steps {
     				script{
		     				 withEnv(['DOCKER_CONFIG=${WORKSPACE}/.docker',"DOCKER_REGISTRY=${dockerRegistry}"]) {
		     				 def customImage = docker.build("artaneja13/kubernetes:account","./account/")	
		      				 withCredentials([string(credentialsId: 'docker-hub', variable: 'dockerHubPassword')]) {
		      				 sh "docker login -u ar.taneja@gmail.com -p ${dockerHubPassword}"
		      				 customImage.push()
				 	       		}
     			    //def browsers = ['chrome', 'firefox']
                    //for (int i = 0; i < browsers.size(); ++i) {
                    //}
      			    //sh "docker build -f ./account/Dockerfile -t artaneja13/kubernetes:account ./account"
      			    		}
      			    	}
	 		}
	 	  }
 	  
	 	   stage('Build Account Deposit Service Docker Image') {
     			steps {
     			echo "testing1"
     			//	sh "docker build -f ./account-deposit/Dockerfile -t artaneja13/kubernetes:account-deposit ./account-deposit"
	 		}
	 	  }
	 	   stage('Build Account Withdrawel Service Docker Image') {
     			steps {
     			echo "testing2"
      			//	sh "docker build -f ./account-withdrawel/Dockerfile -t artaneja13/kubernetes:account-withdrawel ./account-withdrawel"
	 		}
	 	  }
	 	  
	 	  
	 }
}