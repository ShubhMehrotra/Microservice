pipeline {

    options{
        timestamps()
        disableConcurrentBuilds()
        buildDiscarder(logRotator(numToKeepStr: '5'))

    }
    agent any

    stages {
        stage('Clean Workspace') {
            steps {
                cleanWs()
                echo 'Cleaning Workspace...'
                echo 'Building a New Online Store'
            }
        }
         stage('Checkout Code') {
            steps {
                script {
                    echo 'Checking out code from Git...'
                    checkout scm
                }
            }
        }
        stage('Build Microservice'){
            steps {
                script {
                    sh 'cd ServiceRegistry && mvn clean package'

                }
        }
        }

        stage('Build and Push ServiceRegistry'){
            steps {
                script {
                    withCredentials([usernamePassword(credentialsId: 'DOCKER_CREDENTIALS', usernameVariable: 'DOCKER_USER', passwordVariable: 'DOCKER_PASS')]) {
                        sh "echo \$DOCKER_PASS | docker login -u \$DOCKER_USER --password-stdin"
                        sh "docker build -t \$DOCKER_USER/serviceregistry:latest ."
                        sh "docker push \$DOCKER_USER/serviceregistry:latest"
                }
            }
        }

 }





    }
}

