# Hospital Management Application with MySQL
#### This repository contains a Spring Boot application that uses MySQL as its database. The application can be deployed and run in two different environments - Docker or Kubernetes.

## Requirements

Docker installed
Docker Compose installed (for running with Docker)

Kubernetes cluster (for running with Kubernetes)

kubectl installed (for running with Kubernetes)

## Initial Setup

Before building the Docker image, we need to pull the MySQL image (if you don't have it already) and run it with the following command:

docker run --detach --env MYSQL_ROOT_PASSWORD=test --env MYSQL_USER=notroot --env MYSQL_PASSWORD=test --env MYSQL_DATABASE=hospital --name mysql --publish 3306:3306 mysql:8-oracle

Now, run your Spring Boot application in your IDE.

Once the application is running, create a JAR file with the following Maven command:

mvn install

## Common Step - Building Docker Image

Before you can run the application with Docker or Kubernetes, you need to build the Docker image. Run the following command:

docker build -t hospital .

This will create a Docker image named hospital containing the application.

## Running with Docker
Firstly, ensure that Docker is running. Then, use Docker Compose to start the application:

docker-compose up

The application will be available at http://localhost:8080

## Running with Kubernetes
Firstly, ensure that your Kubernetes cluster is running and that you've configured kubectl correctly.

Apply the Kubernetes configuration from the deploy.yaml file:

kubectl apply -f deploy.yaml

This will create a service and a deployment in your cluster.

Confirm that your service and deployment are running correctly:

kubectl get  pods -A -w

You can check the logs of the server pod to ensure it's running as expected:

kubectl logs <server-name>

Next, set up port forwarding so that the service is accessible:

kubectl port-forward svc/server 8080:8080

The application will be available at http://localhost:8080.

## Stopping the application

If you're running with Docker, you can stop the application by pressing Ctrl+C in the terminal where docker-compose up is running.

If you're running with Kubernetes, you can stop the application by deleting the service and deployment:

kubectl delete -f deploy.yaml



