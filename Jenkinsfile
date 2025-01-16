pipeline {
    agent any
    
    environment {
        DOCKER_REGISTRY = 'docker.io'
        DOCKER_USERNAME = 'subba1234'
        DOCKER_PASSWORD = 'dckr_pat_832suA0yUcKuiImPK6lmIpQJ3vs'
        DOCKER_IMAGE_NAME = 'bankingapp'
        DOCKER_TAG = 'latest'
        DOCKER_REPO = 'subba1234/bankingapp'
    }


    stages {
        stage('Download Git SCM') {
            steps {
                checkout scmGit(branches: [[name: '*/main']], extensions: [], userRemoteConfigs: [[credentialsId: 'githubtoken', url: 'https://github.com/paramesh12345-crypto/banking.git']])
            }
        }
        
        stage('Clean and Build') {
            steps {
                script {
                    // Change directory to the path where your code is located
                    dir('/var/lib/jenkins/workspace/webhook_test') {
                        // Ensure gradlew is executable
                        sh 'chmod +x gradlew'
                        
                        // Run gradle clean build
                        sh './gradlew clean build'
                    }
                }
            }
        }
        
        stage('Docker Build') {
            steps {
                script {
                    // Build Docker image from the Dockerfile
                    dir('/var/lib/jenkins/workspace/webhook_test') {
                        // Build the image and tag it
                        sh "docker build -t ${DOCKER_REPO}:${DOCKER_TAG} ."
                    }
                }
            }
        }

        stage('Login to Docker Registry') {
            steps {
                script {
                    // Login to Docker Hub (Docker registry)
                    sh "echo $DOCKER_PASSWORD | docker login -u $DOCKER_USERNAME --password-stdin"
                }
            }
        }

        stage('Push Docker Image') {
            steps {
                script {
                    // Push the Docker image to the repository
                    sh "docker push ${DOCKER_REPO}:${DOCKER_TAG}"
                }
            }
        }
    }
    
    post {
        always {
            sh 'docker system prune -f'
        }
    }
}
