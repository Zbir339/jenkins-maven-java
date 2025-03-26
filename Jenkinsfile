pipeline{

    agent{
        docker{
            image 'maven:3.8.3-openjdk-17'
        }
    }
    stages{

        stage('Build'){
            steps{
                // tester l'existence de maven
                sh '''
                    echo "=====  Build  ====="
                    ls -la
                    mvn --version
                    mvn clean package // clean if there is an existing target folder then package the whole project
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
                    echo "==== Fin Test ===="
                '''
            }
        }

    }
}