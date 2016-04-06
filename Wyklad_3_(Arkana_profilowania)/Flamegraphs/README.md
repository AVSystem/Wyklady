# Overview
This directory contains Flamegraphs example from http://www.brendangregg.com/flamegraphs.html and taken from sample application.

**flamegraph-12756-1.svg** and **flamegraph-12756-2.svg** were generated based on recorded perf events with stacktraces from JVM with **-XX:+PreserveFramePointer** enabled. Use **generate-flamegraph.sh <JVM-PID>** script to generate new flamegraph. To work this script requires both **attach-main.jar** and **libperfmap.so** - instructions how to obtain the files is [here](https://github.com/jrudolph/perf-map-agent).

**flamegraph-jstack-12756.svg** contains flamegraph generated based on stack gathered using **jstack** utility. Use **loop-stack.sh <JVM-PID>** to obtain 30 thread dumps from given JVM, then invoke 

```bash
cat jstack-* > jstack-all # concatenate all stacks
./stackcollapse-jstack.pl jstack-all > collapsed # collapse all stacks so they are readable by flamegraph.pl script
cat collapsed | ./flamegraph.pl --color=java > flamegraph-jstack.svg # generate flamegraph 
```