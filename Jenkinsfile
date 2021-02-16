pipeline{
    agent any
    stages{
        stage('Build Backend') {
            steps{
                bat 'mvn clean package -DskipTests=true'
            }
        }
        stage('Unit Tests') {
            steps{
                bat 'mvn test'
            }
        }
        stage('Sonar Analysis') {
            environment {
                scannerHome = tool 'SONAR_SCANNER'
            }
            steps{
                withSonarQubeEnv('SONAR_LOCAL') {
                    bat "${scannerHome}/bin/sonar-scanner -e -Dsonar.projectKey=DeployBack -Dsonar.host.url=http://localhost:9000 -Dsonar.login=16c8ec6b67fd343abb7ae6777359a6b0568d8604 -Dsonar.java.binaries=target -Dsonar.coverage.exclusions=**/.mvn/**,**/src/test/**,**/model/**,**Application.java"
                }                   
            }
        }
        stage ('Quality Gate') {
            steps{
                sleep(5)
                timeout(time: 1, unit: 'MINUTES') {
                    waitForQualityGate abortPipeline: true
                }
            }
        }
    }
}

