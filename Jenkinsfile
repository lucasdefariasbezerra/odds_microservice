pipeline {
    //agent { docker { image 'maven:3.3.3' } }
    agent any
    triggers {
       githubPush()
    }

    stages {
        stage('build') {
          steps {
              sshagent(['1db8cc9b-65c6-4edb-93fb-67125fcdf43f']) {
                sh "ssh -o StrictHostKeyChecking=no ec2-user@${env.ODDS_ENV} odds-micro.sh BUILD"
             }
          }
        }

        stage('deployment') {
            steps {
               sshagent(['1db8cc9b-65c6-4edb-93fb-67125fcdf43f']) {
                  sh "ssh -o StrictHostKeyChecking=no ec2-user@${env.ODDS_ENV} nohup odds-micro.sh DEPLOY &"
               }
            }
        }
    }
}