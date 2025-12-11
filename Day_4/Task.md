## Task 8/40


In this exercise, you will create a Deployment with multiple replicas. After inspecting the Deployment, you will update its Pod template. You will also be able to use the rollout history to roll back to a previous revision.

> [!NOTE]
> If you do not already have a Kubernetes cluster, you can create a local Kubernetes cluster by following [Day06 Video](https://youtu.be/RORhczcOrWs)


## Replicaset
- Create a new Replicaset based on the nginx image with 3 replicas
- Update the replicas to 4 from the YAML
- Update the replicas to 6 from the command line

## Deployment
1. Create a Deployment named `nginx` with 3 replicas. The Pods should use the `nginx:1.23.0` image and the name `nginx`. The Deployment uses the label `tier=backend`. The Pod template should use the label `app=v1`.
2. List the Deployment and ensure the correct number of replicas is running.
3. Update the image to `nginx:1.23.4`.
4. Verify that the change has been rolled out to all replicas.
5. Assign the change cause "Pick up patch version" to the revision.
6. Scale the Deployment to 5 replicas.
7. Have a look at the Deployment rollout history.
8. Revert the Deployment to revision 1.
9. Ensure that the Pods use the image `nginx:1.23.0`.

## Troubleshooting the issue
1. Apply the below YAML and fix the issue with it

``` YAML
apiVersion: v1
kind:  Deployment
metadata:
  name: nginx-deploy
  labels:
    env: demo
spec:
  template:
    metadata:
      labels:
        env: demo
      name: nginx
    spec:
      containers:
      - image: nginx
        name: nginx
        ports:
        - containerPort: 80
  replicas: 3
  selector:
    matchLabels:
      env: demo
```

2. Apply the below YAML and fix the issue with it

``` YAML
apiVersion: v1
kind:  Deployment
metadata:
  name: nginx-deploy
  labels:
    env: demo
spec:
  template:
    metadata:
      labels:
        env: demo
      name: nginx
    spec:
      containers:
      - image: nginx
        name: nginx
        ports:
        - containerPort: 80
  replicas: 3
  selector:
    matchLabels:
      env: dev
```

---

## Task Completed

``` bash

monesh@GOMO:~/Kubernetes$ vim task.yaml
monesh@GOMO:~/Kubernetes$ kubectl run -f task.yaml
error: required flag(s) "image" not set
monesh@GOMO:~/Kubernetes$ kubectl apply -f task.yaml
replicaset.apps/task-nginx created
monesh@GOMO:~/Kubernetes$ kubectl get rs
NAME         DESIRED   CURRENT   READY   AGE
task-nginx   3         3         0       8s
monesh@GOMO:~/Kubernetes$ cat task.yaml
apiVersion: apps/v1
kind: ReplicaSet
metadata:
  name: task-nginx
  labels:
    env: task-app
spec:
  replicas: 3
  selector:
    matchLabels:
      env: task-app
  template:
    metadata:
      labels:
        env: task-app
    spec:
      containers:
        - name: nginx
          image: nginx

monesh@GOMO:~/Kubernetes$ kubectl edit rs ec/task-nginx
error: there is no need to specify a resource type as a separate argument when passing arguments in resource/name form (e.g. 'kubectl get resource/<resource_name>' instead of 'kubectl get resource resource/<resource_name>'
monesh@GOMO:~/Kubernetes$ kubectl edit rs/task-nginx
replicaset.apps/task-nginx edited
monesh@GOMO:~/Kubernetes$ kubectl get pods
NAME               READY   STATUS    RESTARTS   AGE
task-nginx-48ctr   1/1     Running   0          2m27s
task-nginx-bmshb   1/1     Running   0          19s
task-nginx-cgxzj   1/1     Running   0          2m27s
task-nginx-nhzkr   1/1     Running   0          2m27s
monesh@GOMO:~/Kubernetes$ kubectl scale --replicas=6  rs/task-nginx
replicaset.apps/task-nginx scaled
monesh@GOMO:~/Kubernetes$ kubectl get rs
NAME         DESIRED   CURRENT   READY   AGE
task-nginx   6         6         6       3m57s
monesh@GOMO:~/Kubernetes$ kubectl get pods
NAME               READY   STATUS    RESTARTS   AGE
task-nginx-48ctr   1/1     Running   0          4m2s
task-nginx-bmshb   1/1     Running   0          114s
task-nginx-cgxzj   1/1     Running   0          4m2s
task-nginx-dgnrl   1/1     Running   0          12s
task-nginx-mvpgs   1/1     Running   0          12s
task-nginx-nhzkr   1/1     Running   0          4m2s
monesh@GOMO:~/Kubernetes$
```

---

## Deployment Task

``` bash

monesh@GOMO:~/Kubernetes$ ls
check-docker.sh  deployment.yaml  replicaSet.yaml            stop-docker.sh
config.yml       pods             replicationController.yml  task.yaml
monesh@GOMO:~/Kubernetes$ vim task-deploy.yaml
monesh@GOMO:~/Kubernetes$ kubectl apply -f task-deploy.yaml
deployment.apps/nginx created
monesh@GOMO:~/Kubernetes$ cat task-deploy.yaml
apiVersion: apps/v1
kind: Deployment
metadata:
  name: nginx
  labels:
    tier: backend
spec:
  replicas: 3
  selector:
    matchLabels:
      app: v1
  template:
    metadata:
      labels:
        app: v1
    spec:
      containers:
        - name: nginx
          image: nginx:1.23.0
monesh@GOMO:~/Kubernetes$ kubectl get deploy
NAME    READY   UP-TO-DATE   AVAILABLE   AGE
nginx   0/3     3            0           22s
monesh@GOMO:~/Kubernetes$ kubectl get pods
NAME                     READY   STATUS              RESTARTS   AGE
nginx-5d44b9f45b-chrxg   0/1     ContainerCreating   0          27s
nginx-5d44b9f45b-kq897   0/1     ContainerCreating   0          27s
nginx-5d44b9f45b-wvpz7   0/1     ContainerCreating   0          27s
task-nginx-48ctr         1/1     Running             0          20m
task-nginx-bmshb         1/1     Running             0          18m
task-nginx-cgxzj         1/1     Running             0          20m
task-nginx-dgnrl         1/1     Running             0          17m
task-nginx-mvpgs         1/1     Running             0          17m
task-nginx-nhzkr         1/1     Running             0          20m
```


## Annotating the commit message like ```git```

``` bash
monesh@GOMO:~/Kubernetes$ kubectl annotate deployment/nginx kubernetes.io/change-cause="Pick up patch version"
deployment.apps/nginx annotated
monesh@GOMO:~/Kubernetes$ kubectl history deploy/nginx
error: unknown command "history" for "kubectl"
monesh@GOMO:~/Kubernetes$ kubectl rollout history deployment/nginx
deployment.apps/nginx
REVISION  CHANGE-CAUSE
1         <none>
2         Pick up patch version

monesh@GOMO:~/Kubernetes$ kubectl scale --replicas=5 deploy/nginx
deployment.apps/nginx scaled
monesh@GOMO:~/Kubernetes$ kubectl get deploy
NAME    READY   UP-TO-DATE   AVAILABLE   AGE
nginx   5/5     5            5           24m
monesh@GOMO:~/Kubernetes$
monesh@GOMO:~/Kubernetes$ kubectl explain pod deploy/nginx
error: We accept only this format: explain RESOURCE
monesh@GOMO:~/Kubernetes$ kubectl explain deployment
GROUP:      apps
KIND:       Deployment
VERSION:    v1

DESCRIPTION:
    Deployment enables declarative updates for Pods and ReplicaSets.

FIELDS:
  apiVersion    <string>
    APIVersion defines the versioned schema of this representation of an object.
    Servers should convert recognized schemas to the latest internal value, and
    may reject unrecognized values. More info:
    https://git.k8s.io/community/contributors/devel/sig-architecture/api-conventions.md#resources

  kind  <string>
    Kind is a string value representing the REST resource this object
    represents. Servers may infer this from the endpoint the client submits
    requests to. Cannot be updated. In CamelCase. More info:
    https://git.k8s.io/community/contributors/devel/sig-architecture/api-conventions.md#types-kinds

  metadata      <ObjectMeta>
    Standard object's metadata. More info:
    https://git.k8s.io/community/contributors/devel/sig-architecture/api-conventions.md#metadata

  spec  <DeploymentSpec>
    Specification of the desired behavior of the Deployment.

  status        <DeploymentStatus>
    Most recently observed status of the Deployment.


monesh@GOMO:~/Kubernetes$ kubectl get deploy nginx
NAME    READY   UP-TO-DATE   AVAILABLE   AGE
nginx   5/5     5            5           29m
monesh@GOMO:~/Kubernetes$ kubectl describe deploy nginx
Name:                   nginx
Namespace:              default
CreationTimestamp:      Thu, 11 Dec 2025 17:41:41 +0530
Labels:                 tier=backend
Annotations:            deployment.kubernetes.io/revision: 3
Selector:               app=v1
Replicas:               5 desired | 5 updated | 5 total | 5 available | 0 unavailable
StrategyType:           RollingUpdate
MinReadySeconds:        0
RollingUpdateStrategy:  25% max unavailable, 25% max surge
Pod Template:
  Labels:  app=v1
  Containers:
   nginx:
    Image:         nginx:1.23.0
    Port:          <none>
    Host Port:     <none>
    Environment:   <none>
    Mounts:        <none>
  Volumes:         <none>
  Node-Selectors:  <none>
  Tolerations:     <none>
Conditions:
  Type           Status  Reason
  ----           ------  ------
  Available      True    MinimumReplicasAvailable
  Progressing    True    NewReplicaSetAvailable
OldReplicaSets:  nginx-5484b6d87b (0/0 replicas created)
NewReplicaSet:   nginx-5d44b9f45b (5/5 replicas created)
Events:
  Type    Reason             Age    From                   Message
  ----    ------             ----   ----                   -------
  Normal  ScalingReplicaSet  30m    deployment-controller  Scaled up replica set nginx-5d44b9f45b to 3
  Normal  ScalingReplicaSet  26m    deployment-controller  Scaled up replica set nginx-5484b6d87b to 1
  Normal  ScalingReplicaSet  26m    deployment-controller  Scaled down replica set nginx-5d44b9f45b to 2 from 3
  Normal  ScalingReplicaSet  26m    deployment-controller  Scaled up replica set nginx-5484b6d87b to 2 from 1
  Normal  ScalingReplicaSet  26m    deployment-controller  Scaled down replica set nginx-5d44b9f45b to 1 from 2
  Normal  ScalingReplicaSet  26m    deployment-controller  Scaled up replica set nginx-5484b6d87b to 3 from 2
  Normal  ScalingReplicaSet  26m    deployment-controller  Scaled down replica set nginx-5d44b9f45b to 0 from 1
  Normal  ScalingReplicaSet  5m31s  deployment-controller  Scaled up replica set nginx-5484b6d87b to 5 from 3
  Normal  ScalingReplicaSet  3m29s  deployment-controller  Scaled up replica set nginx-5d44b9f45b to 2 from 0
  Normal  ScalingReplicaSet  3m29s  deployment-controller  Scaled down replica set nginx-5484b6d87b to 4 from 5
  Normal  ScalingReplicaSet  3m29s  deployment-controller  Scaled up replica set nginx-5d44b9f45b to 3 from 2
  Normal  ScalingReplicaSet  3m27s  deployment-controller  Scaled down replica set nginx-5484b6d87b to 3 from 4
  Normal  ScalingReplicaSet  3m27s  deployment-controller  Scaled up replica set nginx-5d44b9f45b to 4 from 3
  Normal  ScalingReplicaSet  3m27s  deployment-controller  Scaled down replica set nginx-5484b6d87b to 1 from 3
  Normal  ScalingReplicaSet  3m27s  deployment-controller  Scaled up replica set nginx-5d44b9f45b to 5 from 4
  Normal  ScalingReplicaSet  3m26s  deployment-controller  Scaled down replica set nginx-5484b6d87b to 0 from 1
monesh@GOMO:~/Kubernetes$
```
---

## TroubleShooting Task

-  Given Deploy.yaml
  
``` yml
apiVersion: v1
kind:  Deployment
metadata:
  name: nginx-deploy
  labels:
    env: demo
spec:
  template:
    metadata:
      labels:
        env: demo
      name: nginx
    spec:
      containers:
      - image: nginx
        name: nginx
        ports:
        - containerPort: 80
  replicas: 3
  selector:
    matchLabels:
      env: demo
```

- Bug fixed yaml

``` yaml
apiVersion: apps/v1
kind: Deployment
metadata:
  name: nginx-deploy
  labels:
    env: demo
spec:
  replicas: 3
  selector:
    matchLabels:
      env: demo
  template:
    metadata:
      labels:
        env: demo
    spec:
      containers:
      - name: nginx
        image: nginx
        ports:
        - containerPort: 80
```

- 2nd deploy.yaml with issues

``` yaml
apiVersion: v1
kind:  Deployment
metadata:
  name: nginx-deploy
  labels:
    env: demo
spec:
  template:
    metadata:
      labels:
        env: demo
      name: nginx
    spec:
      containers:
      - image: nginx
        name: nginx
        ports:
        - containerPort: 80
  replicas: 3
  selector:
    matchLabels:
      env: dev
```

- Fixed deploy.yaml

``` yaml
apiVersion: apps/v1
kind: Deployment
metadata:
  name: nginx-deploy
  labels:
    env: demo
spec:
  replicas: 3
  selector:
    matchLabels:
      env: demo
  template:
    metadata:
      labels:
        env: demo
    spec:
      containers:
      - name: nginx
        image: nginx
        ports:
        - containerPort: 80
```
