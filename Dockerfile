version: 0.2

phases:
  pre_build:
    commands:
      - mvn clean install
      - echo Logging in to Amazon ECR...
      - aws --version
      - REPOSITORY_URI=471112602050.dkr.ecr.us-west-1.amazonaws.com/suryatechie
      - aws ecr get-login-password --region us-west-1 | docker login --username AWS --password-stdin $REPOSITORY_URI
      - COMMIT_HASH=$(echo $CODEBUILD_RESOLVED_SOURCE_VERSION | cut -c 1-7)
      - IMAGE_TAG=build-$(echo $CODEBUILD_BUILD_ID | awk -F":" '{print $2}')
  build:
    commands:
      - echo Build started on `date`
      - echo Building the Docker image...
      - docker build -t $REPOSITORY_URI:latest .
      - docker tag $REPOSITORY_URI:latest $REPOSITORY_URI:$IMAGE_TAG
  post_build:
    commands:
      - echo Build completed on `date`
      - echo Pushing the Docker images...
      - docker push $REPOSITORY_URI:latest
      - docker push $REPOSITORY_URI:$IMAGE_TAG
      - echo Writing image definitions file...
      - printf '[{"name":"course-service","imageUri":"%s"}]' $REPOSITORY_URI:$IMAGE_TAG > imagedefinitions.json
      - echo Writing image definitions file...
      # add your container name
      - DOCKER_CONTAINER_NAME=suryatechie
      - printf '[{"name":"%s","imageUri":"%s"}]' $DOCKER_CONTAINER_NAME $REPOSITORY_URI:$IMAGE_TAG > imagedefinitions.json
      - echo $DOCKER_CONTAINER_NAME
      - echo printing imagedefinitions.json
      - cat imagedefinitions.json

artifacts:
  files:
    - imagedefinitions.json
    - target/course-service.jar
