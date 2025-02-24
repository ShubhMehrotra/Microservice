pipeline {

    options{
        timestamps()
        ansiColor('xterm')
        disableConcurrentBuilds()
        buildDiscarder(logRotator(numToKeepStr: '5'))

    }
    agent any

    stages {
        stage('Build') {
            steps {
                cleanWs()
                echo 'Building a New Online Store'


            }
        }


    }


    }


