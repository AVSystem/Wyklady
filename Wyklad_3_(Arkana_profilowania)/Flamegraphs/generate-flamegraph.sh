#!/usr/bin/env bash
sudo perf record -F 99 -g -p $1 -- sleep 15
./get-symbols.sh $1
sudo perf script | ./stackcollapse-perf.pl > stack-collapsed-$1 
chown adebski stack-collapsed-$1
cat stack-collapsed-$1 | ./flamegraph.pl --color=java > flamegraph-$1.svg
