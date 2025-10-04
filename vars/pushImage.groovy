def call(String IMAGE_NAME, String IMAGE_TAG, String DOCKER_HUB_USER) {
    withCredentials([usernamePassword(
        credentialsId: 'github-credentials', 
        passwordVariable: 'DOCKERHUB_PASSWORD', 
        usernameVariable: 'DOCKERHUB_USER'
    )]) {
        sh '''
            docker tag ${IMAGE_NAME}:${IMAGE_TAG} ${DOCKER_HUB_USER}/${IMAGE_NAME}:${IMAGE_TAG}
            docker login -u $DOCKERHUB_USER -p $DOCKERHUB_PASSWORD
            docker push ${DOCKER_HUB_USER}/${IMAGE_NAME}:${IMAGE_TAG}
        '''
    }
}