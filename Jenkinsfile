pipeline {
    agent any

    stages {
        stage('Build') {
            steps {
                cleanWs()
                echo 'Building a New Online Store'
                sh'mkdir -p New Online Store'
                sh'touch New Online Store/API.txt'
                sh'echo "Online Store" >> New Online Store/API.txt'
                sh'cat New Online Store/API.txt'


            }
        }


    }
    post{
        success{
            archiveArtifacts artifacts:'build/**'
        }
        }

    }


