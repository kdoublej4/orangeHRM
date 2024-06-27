pipeline {
    agent any

    environment {
        JAVA_HOME = ''
        MAVEN_HOME= ''
    }

    parameters {
        choice(
            name: 'Project'
            choices: ['OrangeHRM']
            description: 'Choose project'
        )
        choice(
            name: 'Test_suite'
            choices: ['Smoke', 'Regression', 'E2E']
            description: 'Test Suite'
        )
    }

    stages {
        stage('Checkout') {
            steps {
                echo 'Checking out the code...'
                git 'giturl'
            }
        }

        stage('Build') {
            steps {
                echo 'Building the application'
                sh "${MAVEN_HOME}/bin/mvn clean package"
            }
        }

        stage('Test') {
            steps {
                script {
                    def project = params.Project ?: 'OrangeHRM'
                    def testSuite = params.Test_suite ?: 'Smoke'

                    echo "Running tests for project: ${project}, test suite: ${testSuite}"
                    sh "${MAVEN_HOME}/bin/mvn clean test -P${testSuite} -DtestCaseId=${project} -DfailIfNoTests=false"
                }
            }

            post {
                always {
                    echo 'Publishing Allure results...'
                    allure([
                        includeProperties: false,
                        jdk: 17.0.3,
                        properties: [],
                        reportBuildPolicy: 'ALWAYS',
                        results: [[path: 'target/allure-results']]
                    ])
                }
            }
        }
    }
}