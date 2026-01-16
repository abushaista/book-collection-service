pipeline {
	agent any

	environment {
		DOCKER_REGISTRY = 'docker.io'
		DOCKER_REPO = 'yourusername/book-collection-service'
		IMAGE_TAG = "${env.BUILD_NUMBER}"
		AZURE_APP_NAME = 'your-app-service-name'
		AZURE_RESOURCE_GROUP = 'your-resource-group'
		HEALTHCHECK_URL = "https://${AZURE_APP_NAME}.azurewebsites.net/actuator/health"
	}

	stages {
		stage('Checkout') {
			steps {
				echo "Checkout source code"
				checkout scm
			}
		}

		stage('Build') {
			steps {
				echo "Building Spring Boot app"
				sh 'mvn clean package -DskipTests'
			}
		}

		stage('Build Docker Image') {
			steps {
				echo "Building Docker image: ${DOCKER_REPO}:${IMAGE_TAG}"
				sh "docker build -t ${DOCKER_REPO}:${IMAGE_TAG} ."
			}
		}

		stage('Push Docker Image') {
			steps {
				echo "Pushing Docker image to ${DOCKER_REGISTRY}"
				withCredentials([usernamePassword(credentialsId: 'docker-hub-credentials',
					usernameVariable: 'DOCKER_USER',
					passwordVariable: 'DOCKER_PASS')]) {
					sh """
                        echo $DOCKER_PASS | docker login ${DOCKER_REGISTRY} -u $DOCKER_USER --password-stdin
                        docker push ${DOCKER_REPO}:${IMAGE_TAG}
                    """
				}
			}
		}

		stage('Deploy to Azure App Service') {
			steps {
				echo "Deploying Docker image to Azure App Service"
				withCredentials([azureServicePrincipal('azure-sp-credentials')]) {
					sh """
                        # Set Azure subscription (optional)
                        az account set --subscription \$AZURE_SUBSCRIPTION_ID

                        # Update App Service container
                        az webapp config container set \\
                            --name ${AZURE_APP_NAME} \\
                            --resource-group ${AZURE_RESOURCE_GROUP} \\
                            --docker-custom-image-name ${DOCKER_REPO}:${IMAGE_TAG} \\
                            --docker-registry-server-url https://${DOCKER_REGISTRY}

                        # Restart App Service to apply new image
                        az webapp restart --name ${AZURE_APP_NAME} --resource-group ${AZURE_RESOURCE_GROUP}
                    """
				}
			}
		}

		stage('Health Check') {
			steps {
				echo "Checking App Service health"
				script {
					retry(5) { // coba 5 kali dengan delay jika App Service butuh waktu start
						sleep 10
						def response = sh(script: "curl -s -o /dev/null -w '%{http_code}' ${HEALTHCHECK_URL}", returnStdout: true).trim()
						if (response != '200') {
							error "Health check failed: ${response}"
						} else {
							echo "App Service is healthy!"
						}
					}
				}
			}
		}
	}

	post {
		success {
			echo "Deployment succeeded! Image: ${DOCKER_REPO}:${IMAGE_TAG}"
		}
		failure {
			echo "Deployment failed!"
		}
	}
}
