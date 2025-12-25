``` bash

monesh@GOMO:~/Kubernetes$ kubectl create ns ns2
namespace/ns2 created
monesh@GOMO:~/Kubernetes$ kubectl get ns
NAME                 STATUS   AGE
default              Active   13d
demo                 Active   42m
kube-node-lease      Active   13d
kube-public          Active   13d
kube-system          Active   13d
local-path-storage   Active   13d
ns1                  Active   78s
ns2                  Active   82s
monesh@GOMO:~/Kubernetes$ k create deploy deploy-ns2 --image=nginx -n ns2
deployment.apps/deploy-ns2 created
monesh@GOMO:~/Kubernetes$ k get pods -n ns2
NAME                          READY   STATUS    RESTARTS   AGE
deploy-ns2-6bd45dd884-xnmm4   1/1     Running   0          56s
monesh@GOMO:~/Kubernetes$ k get pods -n ns2 -o wide
NAME                          READY   STATUS    RESTARTS   AGE   IP            NODE                 NOMINATED NODE   READINESS GATES
deploy-ns2-6bd45dd884-xnmm4   1/1     Running   0          71s   10.244.2.13   cka-cluster-worker   <none>           <none>
monesh@GOMO:~/Kubernetes$ k  exec -it deploy-ns2-6bd45dd884-xnmm4 -n ns2 -- sh
# curl 10.244.1.11
<!DOCTYPE html>
<html>
<head>
<title>Welcome to nginx!</title>
<style>
html { color-scheme: light dark; }
body { width: 35em; margin: 0 auto;
font-family: Tahoma, Verdana, Arial, sans-serif; }
</style>
</head>
<body>
<h1>Welcome to nginx!</h1>
<p>If you see this page, the nginx web server is successfully installed and
working. Further configuration is required.</p>

<p>For online documentation and support please refer to
<a href="http://nginx.org/">nginx.org</a>.<br/>
Commercial support is available at
<a href="http://nginx.com/">nginx.com</a>.</p>

<p><em>Thank you for using nginx.</em></p>
</body>
</html>
# exit
monesh@GOMO:~/Kubernetes$ k scale --replicas=3 deploy/deploy-ns2 -n ns2
deployment.apps/deploy-ns2 scaled
monesh@GOMO:~/Kubernetes$ k get deploy -n ns2
NAME         READY   UP-TO-DATE   AVAILABLE   AGE
deploy-ns2   3/3     3            3           10m
monesh@GOMO:~/Kubernetes$ k expose deploy/deploy-ns2 --name=svc-ns2 --port=80 -n ns2
service/svc-ns2 exposed
monesh@GOMO:~/Kubernetes$ k get svc -n ns2
NAME      TYPE        CLUSTER-IP      EXTERNAL-IP   PORT(S)   AGE
svc-ns2   ClusterIP   10.96.153.101   <none>        80/TCP    58s
monesh@GOMO:~/Kubernetes$ k get pods -n ns2
NAME                          READY   STATUS    RESTARTS   AGE
deploy-ns2-6bd45dd884-bshfp   1/1     Running   0          6m28s
deploy-ns2-6bd45dd884-f8b5q   1/1     Running   0          6m28s
deploy-ns2-6bd45dd884-xnmm4   1/1     Running   0          13m
monesh@GOMO:~/Kubernetes$ k exec -it deploy-ns2-6bd45dd884-f8b5q -n ns2 -- sh
# curl 10.96.97.171
<!DOCTYPE html>
<html>
<head>
<title>Welcome to nginx!</title>
<style>
html { color-scheme: light dark; }
body { width: 35em; margin: 0 auto;
font-family: Tahoma, Verdana, Arial, sans-serif; }
</style>
</head>
<body>
<h1>Welcome to nginx!</h1>
<p>If you see this page, the nginx web server is successfully installed and
working. Further configuration is required.</p>

<p>For online documentation and support please refer to
<a href="http://nginx.org/">nginx.org</a>.<br/>
Commercial support is available at
<a href="http://nginx.com/">nginx.com</a>.</p>

<p><em>Thank you for using nginx.</em></p>
</body>
</html>
# curl svc-ns1
curl: (6) Could not resolve host: svc-ns1
# exit
command terminated with exit code 6
monesh@GOMO:~/Kubernetes$ k exec -it deploy-ns2-6bd45dd884-f8b5q -n ns2 -- sh
# curl  ns1.svc.cluster.local
curl: (6) Could not resolve host: ns1.svc.cluster.local
# curl  svc.cluster.local
curl: (6) Could not resolve host: svc.cluster.local
# curl  cluster.local lan
curl: (6) Could not resolve host: cluster.local
curl: (6) Could not resolve host: lan
# curl svc-ns1.ns1.svc.cluster.local
<!DOCTYPE html>
<html>
<head>
<title>Welcome to nginx!</title>
<style>
html { color-scheme: light dark; }
body { width: 35em; margin: 0 auto;
font-family: Tahoma, Verdana, Arial, sans-serif; }
</style>
</head>
<body>
<h1>Welcome to nginx!</h1>
<p>If you see this page, the nginx web server is successfully installed and
working. Further configuration is required.</p>

<p>For online documentation and support please refer to
<a href="http://nginx.org/">nginx.org</a>.<br/>
Commercial support is available at
<a href="http://nginx.com/">nginx.com</a>.</p>

<p><em>Thank you for using nginx.</em></p>
</body>
</html>
# exit
monesh@GOMO:~/Kubernetes$ k delete ns/ns2
namespace "ns2" deleted
monesh@GOMO:~/Kubernetes$

