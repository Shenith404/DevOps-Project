pipeline {
    agent any
triggers{
    githubPush()
  }
    environment {
        REPO_URL = 'hhttps://github.com/Shenith404/DevOps-Project'
        BRANCH = 'main'
        DOCKER_REGISTRY = 'irajapaksha'
        APP_NAME = 'BookMyShoot'
    }

    stages {
        stage('Clone Repository') {
            steps {
                git branch: "${BRANCH}", url: "${REPO_URL}"
            }
        }

        stage('Build Docker Images') {
            steps {
                script {
                    sh 'docker-compose build'
                }
            }
        }

        stage('Push Docker Images') {
            steps {
                script {
                    sh '''
                    docker-compose push
                    '''
                }
            }
        }

        stage('Deploy Application') {
            steps {
                script {
                    sh 'docker-compose down'
                    sh 'docker-compose up -d'
                }
            }
        }
    }
}


