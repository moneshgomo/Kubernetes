``` bash

monesh@GOMO:~/Kubernetes$ kubectl create ns ns1
namespace/ns1 created
monesh@GOMO:~/Kubernetes$ kubeclt get ns

Command 'kubeclt' not found, did you mean:

  command 'kubectl' from snap kubectl (1.34.2)

See 'snap info <snapname>' for additional versions.

monesh@GOMO:~/Kubernetes$ kubectl get ns
NAME                 STATUS   AGE
default              Active   13d
demo                 Active   42m
kube-node-lease      Active   13d
kube-public          Active   13d
kube-system          Active   13d
local-path-storage   Active   13d
ns1                  Active   67s
ns2                  Active   71s
monesh@GOMO:~/Kubernetes$ k create deploy deploy-ns1 --image:nginx -n=ns1
error: unknown flag: --image:nginx
See 'kubectl create deployment --help' for usage.
monesh@GOMO:~/Kubernetes$ k create deploy --help
Create a deployment with the specified name.

Aliases:
deployment, deploy

Examples:
  # Create a deployment named my-dep that runs the busybox image
  kubectl create deployment my-dep --image=busybox

  # Create a deployment with a command
  kubectl create deployment my-dep --image=busybox -- date

  # Create a deployment named my-dep that runs the nginx image with 3 replicas
  kubectl create deployment my-dep --image=nginx --replicas=3

  # Create a deployment named my-dep that runs the busybox image and expose port
5701
  kubectl create deployment my-dep --image=busybox --port=5701

  # Create a deployment named my-dep that runs multiple containers
  kubectl create deployment my-dep --image=busybox:latest --image=ubuntu:latest
--image=nginx

Options:
    --allow-missing-template-keys=true:
        If true, ignore any errors in templates when a field or map key is
        missing in the template. Only applies to golang and jsonpath output
        formats.

    --dry-run='none':
        Must be "none", "server", or "client". If client strategy, only print
        the object that would be sent, without sending it. If server strategy,
        submit server-side request without persisting the resource.

    --field-manager='kubectl-create':
        Name of the manager used to track field ownership.

    --image=[]:
        Image names to run. A deployment can have multiple images set for
        multi-container pod.

    -o, --output='':
        Output format. One of: (json, yaml, name, go-template,
        go-template-file, template, templatefile, jsonpath, jsonpath-as-json,
        jsonpath-file).

    --port=-1:
        The containerPort that this deployment exposes.

    -r, --replicas=1:
        Number of replicas to create. Default is 1.

    --save-config=false:
        If true, the configuration of current object will be saved in its
        annotation. Otherwise, the annotation will be unchanged. This flag is
        useful when you want to perform kubectl apply on this object in the
        future.

    --show-managed-fields=false:
        If true, keep the managedFields when printing objects in JSON or YAML
        format.

    --template='':
        Template string or path to template file to use when -o=go-template,
        -o=go-template-file. The template format is golang templates
        [http://golang.org/pkg/text/template/#pkg-overview].

    --validate='strict':
        Must be one of: strict (or true), warn, ignore (or false). "true" or
        "strict" will use a schema to validate the input and fail the request
        if invalid. It will perform server side validation if
        ServerSideFieldValidation is enabled on the api-server, but will fall
        back to less reliable client-side validation if not. "warn" will warn
        about unknown or duplicate fields without blocking the request if
        server-side field validation is enabled on the API server, and behave
        as "ignore" otherwise. "false" or "ignore" will not perform any schema
        validation, silently dropping any unknown or duplicate fields.

Usage:
  kubectl create deployment NAME --image=image -- [COMMAND] [args...] [options]

Use "kubectl options" for a list of global command-line options (applies to all
commands).
monesh@GOMO:~/Kubernetes$ k create deploy deploy-ns1 --image:nginx -n=ns1
error: unknown flag: --image:nginx
See 'kubectl create deployment --help' for usage.
monesh@GOMO:~/Kubernetes$ k create deploy deploy-ns1 --image:nginx -n ns1
error: unknown flag: --image:nginx
See 'kubectl create deployment --help' for usage.
monesh@GOMO:~/Kubernetes$ k create deploy deploy-ns1 --image=nginx -n ns1
deployment.apps/deploy-ns1 created
monesh@GOMO:~/Kubernetes$ k get pods -n ns1
NAME                          READY   STATUS    RESTARTS   AGE
deploy-ns1-75dfbb79d7-sqxz6   1/1     Running   0          62s
monesh@GOMO:~/Kubernetes$ k get pods -n ns1 -o wide
NAME                          READY   STATUS    RESTARTS   AGE   IP            NODE                  NOMINATED NODE   READINESS GATES
deploy-ns1-75dfbb79d7-sqxz6   1/1     Running   0          82s   10.244.1.11   cka-cluster-worker2   <none>           <none>
monesh@GOMO:~/Kubernetes$ k exec deploy-ns1-75dfbb79d7-sqxz6 -n ns1 -- sh
monesh@GOMO:~/Kubernetes$ k exec deploy-ns1-75dfbb79d7-sqxz6 -n ns1 - sh
error: exec [POD] [COMMAND] is not supported anymore. Use exec [POD] -- [COMMAND] instead
See 'kubectl exec -h' for help and examples
monesh@GOMO:~/Kubernetes$ k exec deploy-ns1-75dfbb79d7-sqxz6 -n ns1 sh
error: exec [POD] [COMMAND] is not supported anymore. Use exec [POD] -- [COMMAND] instead
See 'kubectl exec -h' for help and examples
monesh@GOMO:~/Kubernetes$ k exec -it  deploy-ns1-75dfbb79d7-sqxz6 -n ns1 sh
error: exec [POD] [COMMAND] is not supported anymore. Use exec [POD] -- [COMMAND] instead
See 'kubectl exec -h' for help and examples
monesh@GOMO:~/Kubernetes$ k exec -it  deploy-ns1-75dfbb79d7-sqxz6 -n ns1 -- sh
# curl 10.244.2.13
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
# k scale replicas=2 deploy/deploy-ns1 -n ns1
sh: 2: k: not found
# exit
command terminated with exit code 127
monesh@GOMO:~/Kubernetes$  scale replicas=3 deploy/deploy-ns1 -n ns1

Command 'scale' not found, but can be installed with:

sudo apt install csound-utils

monesh@GOMO:~/Kubernetes$ k scale replicas=3 deploy/deploy-ns1 -n ns1
error: required flag(s) "replicas" not set
monesh@GOMO:~/Kubernetes$ k scale --replicas=3 deploy/deploy-ns1 -n ns1
deployment.apps/deploy-ns1 scaled
monesh@GOMO:~/Kubernetes$

monesh@GOMO:~/Kubernetes$ k get deploy -n ns1
NAME         READY   UP-TO-DATE   AVAILABLE   AGE
deploy-ns1   3/3     3            3           8m49s
monesh@GOMO:~/Kubernetes$ k expose deploy/deploy-ns1 --name=svc-ns1 --port=80 -n ns1
service/svc-ns1 exposed
monesh@GOMO:~/Kubernetes$ k get svc -n ns1
NAME      TYPE        CLUSTER-IP     EXTERNAL-IP   PORT(S)   AGE
svc-ns1   ClusterIP   10.96.97.171   <none>        80/TCP    74s
monesh@GOMO:~/Kubernetes$ k get svc -n ns1 -o wide
NAME      TYPE        CLUSTER-IP     EXTERNAL-IP   PORT(S)   AGE   SELECTOR
svc-ns1   ClusterIP   10.96.97.171   <none>        80/TCP    81s   app=deploy-ns1
monesh@GOMO:~/Kubernetes$ k get pods -n ns1
NAME                          READY   STATUS    RESTARTS   AGE
deploy-ns1-75dfbb79d7-q7cl8   1/1     Running   0          6m3s
deploy-ns1-75dfbb79d7-qfmc2   1/1     Running   0          6m3s
deploy-ns1-75dfbb79d7-sqxz6   1/1     Running   0          12m
monesh@GOMO:~/Kubernetes$ k exec -it deploy-ns1-75dfbb79d7-q7cl8 -n ns1 -- sh
# curl 10.96.153.101
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
# curl svc-ns2
curl: (6) Could not resolve host: svc-ns2
# exit
command terminated with exit code 6
monesh@GOMO:~/Kubernetes$ k exec -it deploy-ns1-75dfbb79d7-q7cl8 -n ns1 -- sh
# cat /etc/resolv.conf
search ns1.svc.cluster.local svc.cluster.local cluster.local lan
nameserver 10.96.0.10
options ndots:5
# <service-name>.<namespace>.svc.cluster.local
sh: 2: cannot open service-name: No such file
# curl svc-ns2.ns2.svc.cluster.local
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
monesh@GOMO:~/Kubernetes$ k delete ns/ns1
namespace "ns1" deleted
monesh@GOMO:~/Kubernetes$

