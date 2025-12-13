``` bash

PS C:\Users\ASUS\OneDrive\Documents\Desktop\Kubernetes> docker run --name busy-box busybox sh
Unable to find image 'busybox:latest' locally
latest: Pulling from library/busybox
e59838ecfec5: Pull complete
Digest: sha256:d80cd694d3e9467884fcb94b8ca1e20437d8a501096cdf367a5a1918a34fc2fd
Status: Downloaded newer image for busybox:latest
PS C:\Users\ASUS\OneDrive\Documents\Desktop\Kubernetes>  docker run -it busybox sh
/ # ls version
ls: version: No such file or directory
/ # ls --version
ls: unrecognized option '--version'
BusyBox v1.37.0 (2024-09-26 21:31:42 UTC) multi-call binary.

Usage: ls [-1AaCxdLHRFplinshrSXvctu] [-w WIDTH] [FILE]...

List directory contents

        -1      One column output
        -a      Include names starting with .
        -A      Like -a, but exclude . and ..
        -x      List by lines
        -d      List directory names, not contents
        -L      Follow symlinks
        -H      Follow symlinks on command line
        -R      Recurse
        -p      Append / to directory names
        -F      Append indicator (one of */=@|) to names
        -l      Long format
        -i      List inode numbers
        -n      List numeric UIDs and GIDs instead of names
        -s      List allocated blocks
        -lc     List ctime
        -lu     List atime
        --full-time     List full date/time
        -h      Human readable sizes (1K 243M 2G)
        --group-directories-first
        -S      Sort by size
        -X      Sort by extension
        -v      Sort by version
        -t      Sort by mtime
        -tc     Sort by ctime
        -tu     Sort by atime
        -r      Reverse sort order
        -w N    Format N columns wide
        --color[={always,never,auto}]
/ # systemctl
sh: systemctl: not found
/ # apt
sh: apt: not found
/ # bash
sh: bash: not found
/ # ls /
bin    etc    lib    proc   sys    usr
dev    home   lib64  root   tmp    var
/ # ls
bin    etc    lib    proc   sys    usr
dev    home   lib64  root   tmp    var
/ # cd lib
/lib # ls
ld-linux-x86-64.so.2  libnss_compat.so.2    libnss_hesiod.so.2
libc.so.6             libnss_dns.so.2       libpthread.so.0
libm.so.6             libnss_files.so.2     libresolv.so.2
/lib # cd ..
/ # ps
PID   USER     TIME  COMMAND
    1 root      0:00 sh
   12 root      0:00 ps
/ # exit
```