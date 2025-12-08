# Kubernetes (K8s)

Kubernetes (K8s) is an open-source platform for automating the deployment, scaling, and management of containerized applications, originally from Google and now maintained by the CNCF. It acts as an orchestrator, grouping containers into "pods," managing services, load balancing, self-healing (restarting failed containers), and automating updates and rollbacks, making it ideal for complex, microservices-based systems across various environments.

## Core Functions

- **Automation**: Automates deployment, scaling, and management tasks.
- **Orchestration**: Schedules containerized workloads across a cluster of machines.
- **Self-Healing**: Restarts failed containers, replaces them, and kills containers that don't respond to health checks.
- **Service Discovery & Load Balancing**: Automatically exposes containers to the internet or other services and distributes traffic.
- **Automated Rollouts & Rollbacks**: Manages application updates, ensuring smooth transitions and easy reversion if issues arise.
- **Resource Management**: Efficiently allocates CPU, memory, and other resources.

## Key Concepts

- **Container**: A standard package for software, like Docker.
- **Pod**: The smallest deployable unit in Kubernetes, a group of one or more containers sharing resources.
- **Cluster**: A set of nodes (VMs/servers) managed by Kubernetes.
- **kubectl**: The command-line tool for interacting with Kubernetes clusters.

## Why It's Used

- Manages complex microservices architectures.
- Enables hybrid and multi-cloud deployments (on-premise, AWS, Azure, GCP).
- Simplifies scaling applications to meet demand.
- Provides robust management for stateful workloads like databases, IoT, and Machine Learning. 