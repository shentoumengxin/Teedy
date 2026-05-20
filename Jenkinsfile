pipeline {
    agent any

    environment {
        DOCKER_IMAGE = 'zhangziha/teedy-app'   // ⚠️ 改成你的 Docker Hub 用户名
        DOCKER_TAG   = 'v1.0'
    }

    stages {
        stage('Checkout SCM') {
            steps { checkout scm }
        }

        stage('Clean') {
            steps { sh 'mvn clean' }
        }

        stage('Compile') {
            steps { sh 'mvn install -DskipTests -Dpmd.skip=true -U' }
        }

        stage('Test') {
            steps { sh 'mvn test -Dmaven.test.failure.ignore=true' }
        }

        stage('PMD') {
            steps { sh 'mvn pmd:pmd' }
        }

        stage('JaCoCo') {
            steps { sh 'mvn jacoco:report' }
        }

        stage('Javadoc') {
            steps {
                sh 'mvn javadoc:javadoc -Dadditionalparam=-Xdoclint:none -Dmaven.javadoc.failOnError=false'
            }
        }

        stage('Site') {
            steps { sh 'mvn site' }
        }

        stage('Package') {
            steps { sh 'mvn package -DskipTests -Dpmd.skip=true' }
        }

        // ===== Docker 阶段 =====

        stage('Building image') {
            steps {
                script {
                    docker.build("${env.DOCKER_IMAGE}:${env.DOCKER_TAG}")
                }
            }
        }

        stage('Upload image') {
            steps {
                script {
                    docker.withRegistry('https://registry.hub.docker.com', 'dockerhub_credentials') {
                        docker.image("${env.DOCKER_IMAGE}:${env.DOCKER_TAG}").push()
                        docker.image("${env.DOCKER_IMAGE}:${env.DOCKER_TAG}").push('latest')
                    }
                }
            }
        }

        stage('Run containers') {
            steps {
                script {
                    // 清理旧容器(失败也继续)
                    sh '''
                        docker rm -f teedy-container-8081 || true
                        docker rm -f teedy-container-8082 || true
                        docker rm -f teedy-container-8083 || true
                    '''

                    // 跑三个容器
                    docker.image("${env.DOCKER_IMAGE}:${env.DOCKER_TAG}").run('--name teedy-container-8081 -d -p 8081:8080')
                    docker.image("${env.DOCKER_IMAGE}:${env.DOCKER_TAG}").run('--name teedy-container-8082 -d -p 8082:8080')
                    docker.image("${env.DOCKER_IMAGE}:${env.DOCKER_TAG}").run('--name teedy-container-8083 -d -p 8083:8080')

                    sh 'docker ps --filter "name=teedy-container"'
                }
            }
        }
    }

    post {
        always {
            archiveArtifacts artifacts: '**/target/site/**/*.*', fingerprint: true
            archiveArtifacts artifacts: '**/target/**/*.jar',   fingerprint: true
            archiveArtifacts artifacts: '**/target/**/*.war',   fingerprint: true
            junit '**/target/surefire-reports/*.xml'
        }
    }
}