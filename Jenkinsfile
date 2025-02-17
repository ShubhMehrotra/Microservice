pipeline {
    agent any

    stages {
        stage('Build') {
            steps {
                cleanWs()
                echo 'Building a New Online Store'


            }
        }


    }
    post{
        success{
            archiveArtifacts artifacts:'build/**'
        }
        }

    }


