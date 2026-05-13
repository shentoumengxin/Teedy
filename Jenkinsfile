pipeline {
    agent any
    
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
            steps { sh 'mvn javadoc:javadoc' }
        }
        
        stage('Site') {
            steps { sh 'mvn site' }
        }
        
        stage('Package') {
            steps { sh 'mvn package -DskipTests -Dpmd.skip=true' }
        }
    }
    
    post {
        always {
            archiveArtifacts artifacts: '**/target/site/**/*.*', fingerprint: true
            archiveArtifacts artifacts: '**/target/**/*.jar', fingerprint: true
            archiveArtifacts artifacts: '**/target/**/*.war', fingerprint: true
            junit '**/target/surefire-reports/*.xml'
        }
    }
}