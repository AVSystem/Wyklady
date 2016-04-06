#!/usr/bin/env bash
for ((a=1; a <= 30; a++)); do jstack $1 > jstack-$a; sleep 1; echo $a; done
