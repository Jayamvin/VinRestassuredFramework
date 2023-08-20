
//This Jenkinsfile is for taking docker image at the qa stage 

pipeline 
{
    agent any
    
    tools{
    	maven 'maven'
        }
        
    environment{
   
        BUILD_NUMBER = "${BUILD_NUMBER}"
   
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
             
             
                
                
        stage('Run Docker Image with Regression Tests') {
    steps {
        script {
        
        def exitCode = bat(script: "docker run --name APIAutomation Pipeline${BUILD_NUMBER} -e MAVEN_OPTS='-Dsurefire.suiteXmlFiles=.src/test/resources/TestRunners/testng_regression.xml' vnalluri2/vinapiautomation:latest", returnStatus: true)
            if (exitCode != 0) {
                currentBuild.result = 'FAILURE' // Mark the build as failed if tests fail
            }
            
            // Even if tests fail, copy the report (if present)
            bat "docker start APIAutomation Pipeline${BUILD_NUMBER}"
       	   // bat "sleep 60"
            bat "docker cp APIAutomation Pipeline${BUILD_NUMBER}:/app/Reports/APIExecutionReport.html ${WORKSPACE}/reports"
            bat "docker rm -f APIAutomation Pipeline${BUILD_NUMBER}"
       			 }
    		}
		}
		
		
		
		stage('Publish Regression Extent Report'){
            steps{
                     publishHTML([allowMissing: false,
                                  alwaysLinkToLastBuild: false, 
                                  keepAll: false, 
                                  reportDir: 'reports', 
                                  reportFiles: 'APIExecutionReport.html', 
                                  reportName: 'API HTML Regression Extent Report', 
                                  reportTitles: ''])
            }
        }
        
        
       

         
    }
}