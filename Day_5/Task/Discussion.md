![alt text](image-5.png)

``` yaml
apiVersion: v1
kind: Pod
metadata:
  name: nginx-pod
  labels:
    app: myapp
spec:
  containers:
    - name: nginx
      image: nginx:1.23.4-alpine
      ports:
        - containerPort: 80
```
<br>

``` yaml
apiVersion: v1
kind: Service
metadata:
  name: myapp-svc
spec:
  type: ClusterIP
  selector:
    app: myapp
  ports:
    - port: 80
      targetPort: 80
```

![alt text](image-6.png)

![alt text](image-7.png)

---

Discuss: Under what condition would you use the service types LoadBalancer, node port, clusterIP, and external?

![alt text](image-8.png)

![alt text](image-9.png)


| Service Type | Accessible Where    | Use Case                                      |
| ------------ | ------------------- | --------------------------------------------- |
| ClusterIP    | Inside cluster only | Internal microservices, DBs                   |
| NodePort     | NodeIP:Port         | Dev/test external access                      |
| LoadBalancer | Public cloud IP     | Production external access, HA                |
| ExternalName | DNS outside cluster | Map external services to a Kubernetes Service |


