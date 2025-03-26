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
                    echo "Bonjour ceci est inside le docker"
                    ls -la
                    mvn --version
                '''
            }

        }

    }
}