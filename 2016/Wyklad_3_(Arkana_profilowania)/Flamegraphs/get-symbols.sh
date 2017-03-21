#!/usr/bin/env bash
java -cp attach-main.jar:/usr/lib/jvm/java-8-oracle/lib/tools.jar  net.virtualvoid.perf.AttachOnce $1
sudo chown root /tmp/perf-$1.map
