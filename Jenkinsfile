pipeline{

    agent{
        docker{
            image 'maven:3.8.3-openjdk-17'
        }
    }

    environment {
        GIT_BRANCH = "main"
        GIT_TOKEN = credentials('github-token')
    }

    stages{

        stage('Build'){
            steps{
                // tester l'existence de maven
                // clean if there is an existing target folder then package the whole project
                sh '''
                    echo "=====  Build  ====="
                    ls -la
                    mvn --version
                    mvn clean package -DskipTests
                    echo "==== Fin Build ===="
                '''
            }
        }
        stage('Test'){
            steps{
                // tester l'existence du folder target et son fichier jar ou war
                // et lancer les tests unitaire
                sh '''
                    echo "Phase ==== Test ===="
                    ls -la
                    mvn test
                    echo "==== Fin Test ===="
                '''
            }

             post {
             // afficher un diag des test emisent
                   always {
                        junit 'target/surefire-reports/*.xml'
                   }
             }
        }
        stage('Push to Deployment Repo') {
                    steps {
                            sh '''
                            git config user.name "Jenkins"
                            git config user.email "jenkins@example.com"
                            git add -f target/*.jar Dockerfile
                            git commit -m "Deploy new version"
                            git remote add deploy https://$GIT_TOKEN@github.com/Zbir339/railway-consumed-jar.git
                            git branch -M $GIT_BRANCH
                            git push -u deploy $GIT_BRANCH
                            '''
                    }
                }

//         stage('Setup Railway') {
//              steps {
//                   sh '''
//                        echo "Installing Railway CLI..."
//                        curl -fsSL https://railway.app/install.sh | sh
//                        echo "Railway CLI Installed."
//                   '''
//              }
//         }
//
//         stage('Deploy to Railway') {
//               steps {
//                    sh '''
//                         railway login --token $RAILWAY_TOKEN
//                         railway up
//                    '''
//               }
//         }
    }
}