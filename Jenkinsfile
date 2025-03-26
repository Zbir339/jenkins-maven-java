pipeline{

    agent{
        docker{
            image 'maven:3.8.3-openjdk-17'
        }
    }
    stages{

        stage('Build'){
            steps{
                sh '''
                    echo "Bonjour ceci est inside le docker"
                    ls -la
                '''
            }


        }

    }
}