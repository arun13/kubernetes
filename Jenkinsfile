pipeline {
    agent any
    stages {
        stage('Compile') {
            steps {
                withMaven(maven: 'maven') {
                    sh 'mvn clean compile -DskipTests=true'
                }
            }
        }
        stage('Unit Testing') {
            steps {
                withMaven(maven: 'maven') {
                    sh 'mvn test'
                }
            }
        }
        stage('SonarQube analysis') {
            steps {
                withSonarQubeEnv('sonarqube') {
                    // requires SonarQube Scanner for Maven 3.2+
                    sh 'mvn org.sonarsource.scanner.maven:sonar-maven-plugin:3.2:sonar'
                }
            }
        }
        stage('Quality Gate') {
            steps {
                timeout(time: 1, unit: 'MINUTES') {
                    // timeout(time: 1, unit: 'HOURS') { // Just in case something goes wrong, pipeline will be killed after a timeout
                    script {
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
                withMaven(maven: 'maven') {
                    sh 'mvn install -DskipTests=true'
                }
            }
        }
        stage('Build Account Service Docker Image') {
            steps {
                script {
                    def dockerRegistry = "https://hub.docker.com"
                    withEnv(["DOCKER_REGISTRY=${dockerRegistry}"]) {
                        def customImage = docker.build("artaneja13/account:latest", "./account/")
                        sh "docker login -u artaneja13 -p arun1982"
                        sh "docker push artaneja13/account:latest"
                        //customImage.push()
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
                script {
                    def dockerRegistry = "https://hub.docker.com"
                    withEnv(["DOCKER_REGISTRY=${dockerRegistry}"]) {
                        docker.build("artaneja13/account-deposit:latest", "./account-deposit/")
                        sh "docker login -u artaneja13 -p arun1982"
                        sh "docker push artaneja13/account-deposit:latest"
                    }
                }
            }
        }
        stage('Build Account Withdrawal Service Docker Image') {
            steps {
                script {
                    def dockerRegistry = "https://hub.docker.com"
                    withEnv(["DOCKER_REGISTRY=${dockerRegistry}"]) {
                        def customImage = docker.build("artaneja13/account-withdrawal:latest", "./account-withdrawal/")
                        sh "docker login -u artaneja13 -p arun1982"
                        sh "docker push artaneja13/account-withdrawal:latest"
                    }
                }
            }
        }

        stage('Deploy account service to kubernetes') {
            steps {
                script {
                    sh "./account/k8s/deploy.sh"
                }
            }
        }

        stage('Deploy  account deposit service to kubernetes') {
            steps {
                script {
                    sh "./account-deposit/k8s/deploy.sh"
                }
            }
        }

        stage('Deploy  account withdrawal to kubernetes') {
            steps {
                script {
                    sh "./account-withdrawal/k8s/deploy.sh"
                }
            }
        }
        stage('Deploy  account-app ingress controller to kubernetes') {
            steps {
                script {
                    sh "./k8s/deploy.sh"
                }
            }
        }
    }
}