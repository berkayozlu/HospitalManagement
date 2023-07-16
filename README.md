# Hospital Management Application with MySQL
#### This repository contains a Spring Boot application that uses MySQL as its database. The application can be deployed and run in two different environments - Docker or Kubernetes.

## Requirements

Docker installed
Docker Compose installed (for running with Docker)

Kubernetes cluster (for running with Kubernetes)

kubectl installed (for running with Kubernetes)

## Common Step - Building Docker Image

Before you can run the application with Docker or Kubernetes, you need to build the Docker image. Run the following command:

docker build -t hospital .

This will create a Docker image named hospital containing the application.

Running with Docker
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



