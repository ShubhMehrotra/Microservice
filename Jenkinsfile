pipeline {

    options {
        timestamps()
        disableConcurrentBuilds()
        buildDiscarder(logRotator(numToKeepStr: '5'))
    }

    agent any

    environment {
        DOCKER_REGISTRY = 'shubhmehrotra'
    }

    stages {

        stage('Clean Workspace') {
            steps {
                cleanWs()
                echo 'Cleaning Workspace...'
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

        stage('Start Kafka & Zookeeper') {
            steps {
                script {
                    echo 'Stopping any running containers...'
                    sh 'docker compose down || true'
                    echo 'Starting Kafka and Zookeeper...'
                    sh 'docker compose up -d'




                }
            }
        }

        stage('Build Microservices') {
            parallel {
                stage('Build Service Registry') {
                    steps {
                        sh 'cd ServiceRegistry && mvn clean package'
                    }
                }
                stage('Build API Gateway') {
                    steps {
                        sh 'cd ApiGateway && mvn clean package'
                    }
                }
                stage('Build Customer Service') {
                    steps {
                        sh 'cd Customer && mvn clean package'
                    }
                }
                stage('Build Cart Service') {
                    steps {
                        sh 'cd Cart && mvn clean package'
                    }
                }
                stage('Build Shipping Service') {
                    steps {
                        sh 'cd Shipping && mvn clean package'
                    }
                }
            }
        }

        stage('Build Docker Images') {
            steps {
                script {
                    sh '''
                    docker build -t $DOCKER_REGISTRY/serviceregistry:latest -f ServiceRegistry/Dockerfile ServiceRegistry
                    docker build -t $DOCKER_REGISTRY/apigateway:latest -f ApiGateway/Dockerfile ApiGateway
                    docker build -t $DOCKER_REGISTRY/customer:latest -f Customer/Dockerfile Customer
                    docker build -t $DOCKER_REGISTRY/cart:latest -f Cart/Dockerfile Cart
                    docker build -t $DOCKER_REGISTRY/shipping:latest -f Shipping/Dockerfile Shipping
                    '''
                }
            }
        }

        stage('Push Docker Images') {
            steps {
                script {
                    withCredentials([usernamePassword(credentialsId: 'DOCKER_CREDENTIALS', usernameVariable: 'DOCKER_USER', passwordVariable: 'DOCKER_PASS')]) {
                        sh '''
                        echo $DOCKER_PASS | docker login -u $DOCKER_USER --password-stdin
                        docker push $DOCKER_REGISTRY/serviceregistry:latest
                        docker push $DOCKER_REGISTRY/apigateway:latest
                        docker push $DOCKER_REGISTRY/customer:latest
                        docker push $DOCKER_REGISTRY/cart:latest
                        docker push $DOCKER_REGISTRY/shipping:latest
                        '''
                    }
                }
            }
        }

        stage('Deploy Services') {
            steps {
                script {
                    echo 'Deploying all services...'
                    sh 'docker compose up -d'
                }
            }
        }

    }

    post {
        success {
            echo 'Build and deployment completed successfully!'
        }
        failure {
            echo 'Build failed. Please check the logs.'
        }
    }
}
