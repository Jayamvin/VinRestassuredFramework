pipeline 
{
    agent any
    
    tools{
    	maven 'maven'
        }

    stages 
    {
        stage('Build') 
        {
            steps
            {
                 git 'https://github.com/jglick/simple-maven-project-with-tests.git'
                 bat "mvn -Dmaven.test.failure.ignore=true clean package"
            }
            post 
            {
                success
                {
                    junit '**/target/surefire-reports/TEST-*.xml'
                    archiveArtifacts 'target/*.jar'
                }
            }
        }
        
        
        
        stage("Deploy to QA"){
            steps{
                echo("deploy to qa done")
            }
        }
                
        stage('Regression API Automation Test') {
            steps {
                catchError(buildResult: 'SUCCESS', stageResult: 'FAILURE') {
                    git 'https://github.com/Jayamvin/VinRestassuredFramework.git'
                    bat "mvn clean test -Dsurefire.suiteXmlFiles=src/test/resources/TestRunners/testng_regression.xml"
                    
                }
            }
        }
                
     
        //stage('Publish Allure Reports') {
        //  steps {
        //        script {
        //            allure([
        //                includeProperties: false,
        //               jdk: '',
        //                properties: [],
        //                reportBuildPolicy: 'ALWAYS',
        //                results: [[path: '/allure-results']]
        //            ])
        //        }
        //    }
        //  }
        
        
        stage('Publish Extent Report'){
            steps{
        //             publishHTML([allowMissing: false,
        //                          alwaysLinkToLastBuild: false, 
        //                          keepAll: false, 
                                  reportDir: 'Reports', 
                                  reportFiles: 'APIExecutionReport.html', 
                                  reportName: 'API HTML Extend Report', 
                                  reportTitles: ''])
            }
        }
        
        
         stage("Deploy to STAGE"){
            steps{
                echo("deploy to STAGE done")
            }
        }
        
        stage('Sanity Automation Test') {
            steps {
                catchError(buildResult: 'SUCCESS', stageResult: 'FAILURE') {
                    git 'https://github.com/Jayamvin/VinRestassuredFramework.git'
                    bat "mvn clean test -Dsurefire.suiteXmlFiles=src/test/resources/TestRunners/testng_regression.xml"
                    
                }
            }
        }
        
        
         stage('Publish Extent Report after sanity'){
            steps{
                     publishHTML([allowMissing: true,
                                  alwaysLinkToLastBuild: false, 
                                  keepAll: false, 
                                  reportDir: 'Reports', 
                                  reportFiles: 'APIExecutionReport.html', 
          //                        reportName: 'API HTML Extend Sanity Report', 
                                  reportTitles: ''])
            }
        }
        
        
        stage("Deploy to PROD"){
            steps{
                echo("deploy to PROD")
            }
        }
    }
}