```
monesh@GOMO:~/Kubernetes$ vim deployment.yaml
monesh@GOMO:~/Kubernetes$ kubectl apply -f deployment.yaml
deployment.apps/nginx-deploy created
monesh@GOMO:~/Kubernetes$ kubectl get pods
NAME                               READY   STATUS    RESTARTS   AGE
nginx-deployment-cbdccf466-6txbn   1/1     Running   0          113s
nginx-deployment-cbdccf466-ct5z5   1/1     Running   0          113s
nginx-deployment-cbdccf466-vczmg   1/1     Running   0          113s
nginx-rs-nb7lb                     1/1     Running   0          113s
nginx-rs-pwp6f                     1/1     Running   0          113s
nginx-rs-tttds                     1/1     Running   0          113s
monesh@GOMO:~/Kubernetes$ kubectl describe nginx-deployment-cbdccf466-6txbn
error: the server doesn't have a resource type "nginx-deployment-cbdccf466-6txbn"
monesh@GOMO:~/Kubernetes$ kubectl describe pod  nginx-deployment-cbdccf466-6txbn
Name:             nginx-deployment-cbdccf466-6txbn
Namespace:        default
Priority:         0
Service Account:  default
Node:             test-cluster-control-plane/172.18.0.2
Start Time:       Thu, 11 Dec 2025 00:02:26 +0530
Labels:           app=nginx
                  pod-template-hash=cbdccf466
Annotations:      <none>
Status:           Running
IP:               10.244.0.26
IPs:
  IP:           10.244.0.26
Controlled By:  ReplicaSet/nginx-deployment-cbdccf466
Containers:
  nginx:
    Container ID:   containerd://b7e25d333a6c5ae81a0264942a3cc50cb28945ad996d91eb75d7e3206863aecb
    Image:          nginx:1.14.2
    Image ID:       docker.io/library/nginx@sha256:f7988fb6c02e0ce69257d9bd9cf37ae20a60f1df7563c3a2a6abe24160306b8d
    Port:           80/TCP
    Host Port:      0/TCP
    State:          Running
      Started:      Thu, 11 Dec 2025 00:02:27 +0530
    Ready:          True
    Restart Count:  0
    Environment:    <none>
    Mounts:
      /var/run/secrets/kubernetes.io/serviceaccount from kube-api-access-kh2r8 (ro)
Conditions:
  Type              Status
  Initialized       True
  Ready             True
  ContainersReady   True
  PodScheduled      True
Volumes:
  kube-api-access-kh2r8:
    Type:                    Projected (a volume that contains injected data from multiple sources)
    TokenExpirationSeconds:  3607
    ConfigMapName:           kube-root-ca.crt
    Optional:                false
    DownwardAPI:             true
QoS Class:                   BestEffort
Node-Selectors:              <none>
Tolerations:                 node.kubernetes.io/not-ready:NoExecute op=Exists for 300s
                             node.kubernetes.io/unreachable:NoExecute op=Exists for 300s
Events:
  Type    Reason     Age    From               Message
  ----    ------     ----   ----               -------
  Normal  Scheduled  2m22s  default-scheduler  Successfully assigned default/nginx-deployment-cbdccf466-6txbn to test-cluster-control-plane
  Normal  Pulled     2m21s  kubelet            Container image "nginx:1.14.2" already present on machine
  Normal  Created    2m21s  kubelet            Created container nginx
  Normal  Started    2m21s  kubelet            Started container nginx
monesh@GOMO:~/Kubernetes$ cat deployment.yaml
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
monesh@GOMO:~/Kubernetes$ kubectl get rc
No resources found in default namespace.
monesh@GOMO:~/Kubernetes$ kubectl get rs
NAME                         DESIRED   CURRENT   READY   AGE
nginx-deployment-cbdccf466   3         3         3       4m27s
nginx-rs                     3         3         3       35m
monesh@GOMO:~/Kubernetes$ kubectl get deploy
NAME               READY   UP-TO-DATE   AVAILABLE   AGE
nginx-deploy       3/3     3            3           107s
nginx-deployment   3/3     3            3           4m31s
```

## Use Full @16

```text
monesh@GOMO:~/Kubernetes$ kubectl get deploy
NAME               READY   UP-TO-DATE   AVAILABLE   AGE
nginx-deploy       3/3     3            3           4m11s
nginx-deployment   3/3     3            3           6m55s
monesh@GOMO:~/Kubernetes$ kubectl get pods
NAME                               READY   STATUS    RESTARTS   AGE
nginx-deployment-cbdccf466-6txbn   1/1     Running   0          6m3s
nginx-deployment-cbdccf466-ct5z5   1/1     Running   0          6m3s
nginx-deployment-cbdccf466-vczmg   1/1     Running   0          6m3s
nginx-rs-nb7lb                     1/1     Running   0          6m3s
nginx-rs-pwp6f                     1/1     Running   0          6m3s
nginx-rs-tttds                     1/1     Running   0          6m3s
monesh@GOMO:~/Kubernetes$ kubectl delete pod nginx-rs
Error from server (NotFound): pods "nginx-rs" not found
monesh@GOMO:~/Kubernetes$ kubectl delete rs nginx-rs
replicaset.apps "nginx-rs" deleted from default namespace
monesh@GOMO:~/Kubernetes$ kubectl get pods
NAME                               READY   STATUS              RESTARTS   AGE
nginx-deploy-84568f8674-6x9gc      0/1     ContainerCreating   0          5s
nginx-deploy-84568f8674-nb9rm      0/1     ContainerCreating   0          5s
nginx-deploy-84568f8674-pbsj7      0/1     ContainerCreating   0          5s
nginx-deployment-cbdccf466-6txbn   1/1     Running             0          7m15s
nginx-deployment-cbdccf466-ct5z5   1/1     Running             0          7m15s
nginx-deployment-cbdccf466-vczmg   1/1     Running             0          7m15s
monesh@GOMO:~/Kubernetes$ kubectl get deploy
NAME               READY   UP-TO-DATE   AVAILABLE   AGE
nginx-deploy       3/3     3            3           5m45s
nginx-deployment   3/3     3            3           8m29s
monesh@GOMO:~/Kubernetes$ kubectl delete deploy nginx-deployment
deployment.apps "nginx-deployment" deleted from default namespace
monesh@GOMO:~/Kubernetes$ kubectl get deploy
NAME           READY   UP-TO-DATE   AVAILABLE   AGE
nginx-deploy   3/3     3            3           5m58s
monesh@GOMO:~/Kubernetes$
```

## important ( usefull) 

```text
monesh@GOMO:~/Kubernetes$ kubectl get all
NAME                                READY   STATUS    RESTARTS   AGE
pod/nginx-deploy-84568f8674-6x9gc   1/1     Running   0          84s
pod/nginx-deploy-84568f8674-nb9rm   1/1     Running   0          84s
pod/nginx-deploy-84568f8674-pbsj7   1/1     Running   0          84s

NAME                 TYPE        CLUSTER-IP   EXTERNAL-IP   PORT(S)   AGE
service/kubernetes   ClusterIP   10.96.0.1    <none>        443/TCP   2d9h

NAME                           READY   UP-TO-DATE   AVAILABLE   AGE
deployment.apps/nginx-deploy   3/3     3            3           6m50s

NAME                                      DESIRED   CURRENT   READY   AGE
replicaset.apps/nginx-deploy-84568f8674   3         3         3       84s
monesh@GOMO:~/Kubernetes$


```

- Service is default in the cluster
---
## Updating the image in deploy pod

```text
monesh@GOMO:~/Kubernetes$ kubectl get deploy
NAME           READY   UP-TO-DATE   AVAILABLE   AGE
nginx-deploy   3/3     3            3           8m40s
monesh@GOMO:~/Kubernetes$ kubectl describe pod nginx-deploy
Name:             nginx-deploy-84568f8674-6x9gc
Namespace:        default
Priority:         0
Service Account:  default
Node:             test-cluster-control-plane/172.18.0.2
Start Time:       Thu, 11 Dec 2025 00:09:36 +0530
Labels:           env=demo
                  pod-template-hash=84568f8674
Annotations:      <none>
Status:           Running
IP:               10.244.0.36
IPs:
  IP:           10.244.0.36
Controlled By:  ReplicaSet/nginx-deploy-84568f8674
Containers:
  nginx:
    Container ID:   containerd://b7ca5844d1ec14308deff16718d0e73c1ad44ad7a403855e1c8f539d6eddc07b
    Image:          nginx
    Image ID:       docker.io/library/nginx@sha256:325b00a35073d9aa1d3df16da8afbbae1ac7d824c505f7490cd5cdbb79d60f6d
    Port:           <none>
    Host Port:      <none>
    State:          Running
      Started:      Thu, 11 Dec 2025 00:09:47 +0530
    Ready:          True
    Restart Count:  0
    Environment:    <none>
    Mounts:
      /var/run/secrets/kubernetes.io/serviceaccount from kube-api-access-hchbq (ro)
Conditions:
  Type              Status
  Initialized       True
  Ready             True
  ContainersReady   True
  PodScheduled      True
Volumes:
  kube-api-access-hchbq:
    Type:                    Projected (a volume that contains injected data from multiple sources)
    TokenExpirationSeconds:  3607
    ConfigMapName:           kube-root-ca.crt
    Optional:                false
    DownwardAPI:             true
QoS Class:                   BestEffort
Node-Selectors:              <none>
Tolerations:                 node.kubernetes.io/not-ready:NoExecute op=Exists for 300s
                             node.kubernetes.io/unreachable:NoExecute op=Exists for 300s
Events:
  Type    Reason     Age    From               Message
  ----    ------     ----   ----               -------
  Normal  Scheduled  3m27s  default-scheduler  Successfully assigned default/nginx-deploy-84568f8674-6x9gc to test-cluster-control-plane
  Normal  Pulling    3m27s  kubelet            Pulling image "nginx"
  Normal  Pulled     3m17s  kubelet            Successfully pulled image "nginx" in 2.424582227s (10.149840488s including waiting)
  Normal  Created    3m17s  kubelet            Created container nginx
  Normal  Started    3m17s  kubelet            Started container nginx

Name:             nginx-deploy-84568f8674-nb9rm
Namespace:        default
Priority:         0
Service Account:  default
Node:             test-cluster-control-plane/172.18.0.2
Start Time:       Thu, 11 Dec 2025 00:09:36 +0530
Labels:           env=demo
                  pod-template-hash=84568f8674
Annotations:      <none>
Status:           Running
IP:               10.244.0.38
IPs:
  IP:           10.244.0.38
Controlled By:  ReplicaSet/nginx-deploy-84568f8674
Containers:
  nginx:
    Container ID:   containerd://12f76a0987f04c2a4569dd953d782f109177637c5a457ce2887e207c3fceefcd
    Image:          nginx
    Image ID:       docker.io/library/nginx@sha256:325b00a35073d9aa1d3df16da8afbbae1ac7d824c505f7490cd5cdbb79d60f6d
    Port:           <none>
    Host Port:      <none>
    State:          Running
      Started:      Thu, 11 Dec 2025 00:09:45 +0530
    Ready:          True
    Restart Count:  0
    Environment:    <none>
    Mounts:
      /var/run/secrets/kubernetes.io/serviceaccount from kube-api-access-m9h7j (ro)
Conditions:
  Type              Status
  Initialized       True
  Ready             True
  ContainersReady   True
  PodScheduled      True
Volumes:
  kube-api-access-m9h7j:
    Type:                    Projected (a volume that contains injected data from multiple sources)
    TokenExpirationSeconds:  3607
    ConfigMapName:           kube-root-ca.crt
    Optional:                false
    DownwardAPI:             true
QoS Class:                   BestEffort
Node-Selectors:              <none>
Tolerations:                 node.kubernetes.io/not-ready:NoExecute op=Exists for 300s
                             node.kubernetes.io/unreachable:NoExecute op=Exists for 300s
Events:
  Type    Reason     Age    From               Message
  ----    ------     ----   ----               -------
  Normal  Scheduled  3m27s  default-scheduler  Successfully assigned default/nginx-deploy-84568f8674-nb9rm to test-cluster-control-plane
  Normal  Pulling    3m27s  kubelet            Pulling image "nginx"
  Normal  Pulled     3m19s  kubelet            Successfully pulled image "nginx" in 3.434618749s (7.730031439s including waiting)
  Normal  Created    3m19s  kubelet            Created container nginx
  Normal  Started    3m19s  kubelet            Started container nginx

Name:             nginx-deploy-84568f8674-pbsj7
Namespace:        default
Priority:         0
Service Account:  default
Node:             test-cluster-control-plane/172.18.0.2
Start Time:       Thu, 11 Dec 2025 00:09:36 +0530
Labels:           env=demo
                  pod-template-hash=84568f8674
Annotations:      <none>
Status:           Running
IP:               10.244.0.37
IPs:
  IP:           10.244.0.37
Controlled By:  ReplicaSet/nginx-deploy-84568f8674
Containers:
  nginx:
    Container ID:   containerd://780e5a198398d0b0dfab03d1f5d8056626f43aec5cb1070ff908e19455dbd2e7
    Image:          nginx
    Image ID:       docker.io/library/nginx@sha256:325b00a35073d9aa1d3df16da8afbbae1ac7d824c505f7490cd5cdbb79d60f6d
    Port:           <none>
    Host Port:      <none>
    State:          Running
      Started:      Thu, 11 Dec 2025 00:09:42 +0530
    Ready:          True
    Restart Count:  0
    Environment:    <none>
    Mounts:
      /var/run/secrets/kubernetes.io/serviceaccount from kube-api-access-l5qv5 (ro)
Conditions:
  Type              Status
  Initialized       True
  Ready             True
  ContainersReady   True
  PodScheduled      True
Volumes:
  kube-api-access-l5qv5:
    Type:                    Projected (a volume that contains injected data from multiple sources)
    TokenExpirationSeconds:  3607
    ConfigMapName:           kube-root-ca.crt
    Optional:                false
    DownwardAPI:             true
QoS Class:                   BestEffort
Node-Selectors:              <none>
Tolerations:                 node.kubernetes.io/not-ready:NoExecute op=Exists for 300s
                             node.kubernetes.io/unreachable:NoExecute op=Exists for 300s
Events:
  Type    Reason     Age    From               Message
  ----    ------     ----   ----               -------
  Normal  Scheduled  3m27s  default-scheduler  Successfully assigned default/nginx-deploy-84568f8674-pbsj7 to test-cluster-control-plane
  Normal  Pulling    3m27s  kubelet            Pulling image "nginx"
  Normal  Pulled     3m23s  kubelet            Successfully pulled image "nginx" in 4.297292711s (4.297446141s including waiting)
  Normal  Created    3m23s  kubelet            Created container nginx
  Normal  Started    3m22s  kubelet            Started container nginx

monesh@GOMO:~/Kubernetes$ kubectl set image deploy/nginx-deploy \
> nginx=nginx:1.9.1
deployment.apps/nginx-deploy image updated
monesh@GOMO:~/Kubernetes$ kubectl describe pod nginx-deploy
Name:             nginx-deploy-84568f8674-6x9gc
Namespace:        default
Priority:         0
Service Account:  default
Node:             test-cluster-control-plane/172.18.0.2
Start Time:       Thu, 11 Dec 2025 00:09:36 +0530
Labels:           env=demo
                  pod-template-hash=84568f8674
Annotations:      <none>
Status:           Running
IP:               10.244.0.36
IPs:
  IP:           10.244.0.36
Controlled By:  ReplicaSet/nginx-deploy-84568f8674
Containers:
  nginx:
    Container ID:   containerd://b7ca5844d1ec14308deff16718d0e73c1ad44ad7a403855e1c8f539d6eddc07b
    Image:          nginx
    Image ID:       docker.io/library/nginx@sha256:325b00a35073d9aa1d3df16da8afbbae1ac7d824c505f7490cd5cdbb79d60f6d
    Port:           <none>
    Host Port:      <none>
    State:          Running
      Started:      Thu, 11 Dec 2025 00:09:47 +0530
    Ready:          True
    Restart Count:  0
    Environment:    <none>
    Mounts:
      /var/run/secrets/kubernetes.io/serviceaccount from kube-api-access-hchbq (ro)
Conditions:
  Type              Status
  Initialized       True
  Ready             True
  ContainersReady   True
  PodScheduled      True
Volumes:
  kube-api-access-hchbq:
    Type:                    Projected (a volume that contains injected data from multiple sources)
    TokenExpirationSeconds:  3607
    ConfigMapName:           kube-root-ca.crt
    Optional:                false
    DownwardAPI:             true
QoS Class:                   BestEffort
Node-Selectors:              <none>
Tolerations:                 node.kubernetes.io/not-ready:NoExecute op=Exists for 300s
                             node.kubernetes.io/unreachable:NoExecute op=Exists for 300s
Events:
  Type    Reason     Age    From               Message
  ----    ------     ----   ----               -------
  Normal  Scheduled  4m50s  default-scheduler  Successfully assigned default/nginx-deploy-84568f8674-6x9gc to test-cluster-control-plane
  Normal  Pulling    4m50s  kubelet            Pulling image "nginx"
  Normal  Pulled     4m40s  kubelet            Successfully pulled image "nginx" in 2.424582227s (10.149840488s including waiting)
  Normal  Created    4m40s  kubelet            Created container nginx
  Normal  Started    4m40s  kubelet            Started container nginx

Name:             nginx-deploy-84568f8674-nb9rm
Namespace:        default
Priority:         0
Service Account:  default
Node:             test-cluster-control-plane/172.18.0.2
Start Time:       Thu, 11 Dec 2025 00:09:36 +0530
Labels:           env=demo
                  pod-template-hash=84568f8674
Annotations:      <none>
Status:           Running
IP:               10.244.0.38
IPs:
  IP:           10.244.0.38
Controlled By:  ReplicaSet/nginx-deploy-84568f8674
Containers:
  nginx:
    Container ID:   containerd://12f76a0987f04c2a4569dd953d782f109177637c5a457ce2887e207c3fceefcd
    Image:          nginx
    Image ID:       docker.io/library/nginx@sha256:325b00a35073d9aa1d3df16da8afbbae1ac7d824c505f7490cd5cdbb79d60f6d
    Port:           <none>
    Host Port:      <none>
    State:          Running
      Started:      Thu, 11 Dec 2025 00:09:45 +0530
    Ready:          True
    Restart Count:  0
    Environment:    <none>
    Mounts:
      /var/run/secrets/kubernetes.io/serviceaccount from kube-api-access-m9h7j (ro)
Conditions:
  Type              Status
  Initialized       True
  Ready             True
  ContainersReady   True
  PodScheduled      True
Volumes:
  kube-api-access-m9h7j:
    Type:                    Projected (a volume that contains injected data from multiple sources)
    TokenExpirationSeconds:  3607
    ConfigMapName:           kube-root-ca.crt
    Optional:                false
    DownwardAPI:             true
QoS Class:                   BestEffort
Node-Selectors:              <none>
Tolerations:                 node.kubernetes.io/not-ready:NoExecute op=Exists for 300s
                             node.kubernetes.io/unreachable:NoExecute op=Exists for 300s
Events:
  Type    Reason     Age    From               Message
  ----    ------     ----   ----               -------
  Normal  Scheduled  4m50s  default-scheduler  Successfully assigned default/nginx-deploy-84568f8674-nb9rm to test-cluster-control-plane
  Normal  Pulling    4m50s  kubelet            Pulling image "nginx"
  Normal  Pulled     4m42s  kubelet            Successfully pulled image "nginx" in 3.434618749s (7.730031439s including waiting)
  Normal  Created    4m42s  kubelet            Created container nginx
  Normal  Started    4m42s  kubelet            Started container nginx

Name:             nginx-deploy-84568f8674-pbsj7
Namespace:        default
Priority:         0
Service Account:  default
Node:             test-cluster-control-plane/172.18.0.2
Start Time:       Thu, 11 Dec 2025 00:09:36 +0530
Labels:           env=demo
                  pod-template-hash=84568f8674
Annotations:      <none>
Status:           Running
IP:               10.244.0.37
IPs:
  IP:           10.244.0.37
Controlled By:  ReplicaSet/nginx-deploy-84568f8674
Containers:
  nginx:
    Container ID:   containerd://780e5a198398d0b0dfab03d1f5d8056626f43aec5cb1070ff908e19455dbd2e7
    Image:          nginx
    Image ID:       docker.io/library/nginx@sha256:325b00a35073d9aa1d3df16da8afbbae1ac7d824c505f7490cd5cdbb79d60f6d
    Port:           <none>
    Host Port:      <none>
    State:          Running
      Started:      Thu, 11 Dec 2025 00:09:42 +0530
    Ready:          True
    Restart Count:  0
    Environment:    <none>
    Mounts:
      /var/run/secrets/kubernetes.io/serviceaccount from kube-api-access-l5qv5 (ro)
Conditions:
  Type              Status
  Initialized       True
  Ready             True
  ContainersReady   True
  PodScheduled      True
Volumes:
  kube-api-access-l5qv5:
    Type:                    Projected (a volume that contains injected data from multiple sources)
    TokenExpirationSeconds:  3607
    ConfigMapName:           kube-root-ca.crt
    Optional:                false
    DownwardAPI:             true
QoS Class:                   BestEffort
Node-Selectors:              <none>
Tolerations:                 node.kubernetes.io/not-ready:NoExecute op=Exists for 300s
                             node.kubernetes.io/unreachable:NoExecute op=Exists for 300s
Events:
  Type    Reason     Age    From               Message
  ----    ------     ----   ----               -------
  Normal  Scheduled  4m51s  default-scheduler  Successfully assigned default/nginx-deploy-84568f8674-pbsj7 to test-cluster-control-plane
  Normal  Pulling    4m50s  kubelet            Pulling image "nginx"
  Normal  Pulled     4m46s  kubelet            Successfully pulled image "nginx" in 4.297292711s (4.297446141s including waiting)
  Normal  Created    4m46s  kubelet            Created container nginx
  Normal  Started    4m45s  kubelet            Started container nginx

Name:             nginx-deploy-84f5f9795c-459tr
Namespace:        default
Priority:         0
Service Account:  default
Node:             test-cluster-control-plane/172.18.0.2
Start Time:       Thu, 11 Dec 2025 00:14:00 +0530
Labels:           env=demo
                  pod-template-hash=84f5f9795c
Annotations:      <none>
Status:           Pending
IP:
IPs:              <none>
Controlled By:    ReplicaSet/nginx-deploy-84f5f9795c
Containers:
  nginx:
    Container ID:
    Image:          nginx:1.9.1
    Image ID:
    Port:           <none>
    Host Port:      <none>
    State:          Waiting
      Reason:       ContainerCreating
    Ready:          False
    Restart Count:  0
    Environment:    <none>
    Mounts:
      /var/run/secrets/kubernetes.io/serviceaccount from kube-api-access-99qs6 (ro)
Conditions:
  Type              Status
  Initialized       True
  Ready             False
  ContainersReady   False
  PodScheduled      True
Volumes:
  kube-api-access-99qs6:
    Type:                    Projected (a volume that contains injected data from multiple sources)
    TokenExpirationSeconds:  3607
    ConfigMapName:           kube-root-ca.crt
    Optional:                false
    DownwardAPI:             true
QoS Class:                   BestEffort
Node-Selectors:              <none>
Tolerations:                 node.kubernetes.io/not-ready:NoExecute op=Exists for 300s
                             node.kubernetes.io/unreachable:NoExecute op=Exists for 300s
Events:
  Type    Reason     Age   From               Message
  ----    ------     ----  ----               -------
  Normal  Scheduled  27s   default-scheduler  Successfully assigned default/nginx-deploy-84f5f9795c-459tr to test-cluster-control-plane
  Normal  Pulling    26s   kubelet            Pulling image "nginx:1.9.1"

monesh@GOMO:~/Kubernetes$

```
## Version control in Kubernetes 

```text
monesh@GOMO:~/Kubernetes$ kubectl get deploy
NAME           READY   UP-TO-DATE   AVAILABLE   AGE
nginx-deploy   3/3     3            3           11m
monesh@GOMO:~/Kubernetes$ kubectl rollout history deploy/nginx-deploy \
>
deployment.apps/nginx-deploy
REVISION  CHANGE-CAUSE
1         <none>
2         <none>

monesh@GOMO:~/Kubernetes$

```

- 1st change is creation of a new pod ( 2nd is we made the change in live object but it remains same in that deployment.yml file )