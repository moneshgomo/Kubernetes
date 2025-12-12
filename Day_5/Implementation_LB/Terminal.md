``` bash

monesh@GOMO:~/Kubernetes$ ls
Cluster_IP.yaml  check-docker.sh  config.yml  deployment.yaml  kind.yaml  nodePort.yaml  pods  replicaSet.yaml  replicationController.yml  stop-docker.sh  task-deploy.yaml  task.yaml
monesh@GOMO:~/Kubernetes$ vim lb.yml
monesh@GOMO:~/Kubernetes$ cat lb.yml
apiVersion: v1
kind: Service
metadata:
  name: lb-svc
  labels:
    env: demo
spec:
  type: LoadBalancer
  selector:
    env: demo       # matches pods with label env=demo
  ports:
    - port: 80
monesh@GOMO:~/Kubernetes$ k8 apply -f lb.yml
service/lb-svc created
monesh@GOMO:~/Kubernetes$
monesh@GOMO:~/Kubernetes$ k8 get svc
NAME           TYPE           CLUSTER-IP      EXTERNAL-IP   PORT(S)        AGE
cluster-svc    ClusterIP      10.96.72.28     <none>        80/TCP         24m
kubernetes     ClusterIP      10.96.0.1       <none>        443/TCP        61m
lb-svc         LoadBalancer   10.96.109.251   <pending>     80:31275/TCP   75s
nodeport-svc   NodePort       10.96.169.211   <none>        80:30001/TCP   60m
monesh@GOMO:~/Kubernetes$
```

<br>

- The ```LB``` not having the external IP 
- Still now we jsut create the LB but not the actual LB Service , still now we not provision the loadbalancer thats why the LB not having the external-ip
- But now the lb is running on ```random nodePort```: ```3000
## Kind Documentation for LB : 

- [DOC](https://kind.sigs.k8s.io/docs/user/loadbalancer/)
