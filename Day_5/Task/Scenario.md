![alt text](image-10.png)


``` yaml
apiVersion: v1
kind: Service
metadata:
  name: db-service
spec:
  type: ClusterIP
  selector:
    app: postgres
  ports:
    - port: 5432
      targetPort: 5432
```

![alt text](image-11.png)

``` yaml
apiVersion: v1
kind: Service
metadata:
  name: frontend-nodeport
spec:
  type: NodePort
  selector:
    app: frontend
  ports:
    - port: 80
      targetPort: 80
      nodePort: 30001
````

![alt text](image-12.png)

``` yaml
apiVersion: v1
kind: Service
metadata:
  name: frontend-lb
spec:
  type: LoadBalancer
  selector:
    app: frontend
  ports:
    - port: 80
      targetPort: 80
```
![alt text](image-13.png)

``` yaml
apiVersion: v1
kind: Service
metadata:
  name: payment-gateway
spec:
  type: ExternalName
  externalName: api.stripe.com
  ports:
    - port: 443
```
![alt text](image-14.png)