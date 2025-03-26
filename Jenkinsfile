pipeline{

    agent{
        docker{
            image 'maven:3.8.3-openjdk-17'
        }
    }

    environment {
            IMAGE_NAME = "my-jenkins-app"
            CONTAINER_NAME = "my-app-jenkins"
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
        stage('Deploy'){
             agent {
                 docker {
                      image 'docker:24.0.7'
                      args '--privileged -v /var/run/docker.sock:/var/run/docker.sock'
                 }
             }
              steps {
                  sh '''
                      echo "===== Building Docker Image ====="
                      docker build -t $IMAGE_NAME .

                      echo "===== Stopping and Removing Existing Container ====="
                      docker stop $CONTAINER_NAME || true
                      docker rm $CONTAINER_NAME || true

                      echo "===== Running New Container ====="
                      docker run -d --name $CONTAINER_NAME -p 8080:8080 $IMAGE_NAME

                      echo "===== Deployment Complete ====="
                  '''
                         }
        }

    }
}