Code from single chapter of book [Netty in Action](http://manning.com/maurer) that is available [here](https://github.com/normanmaurer/netty-in-action).

This example code is meant to show that one can get misleading data from samplers/profilers like VisualVM that operate only on JVM stacks. 
When `EchoServer` is executed one of the threads has name like `nioEventLoopGroup-2-1` and it is reported as `RUNNABLE` almost all the time. 
Example stack trace of this thread looks like

```java
"nioEventLoopGroup-2-1" #11 prio=10 os_prio=0 tid=0x00007ff5882a4800 nid=0x1be2 runnable [0x00007ff568a27000]
   java.lang.Thread.State: RUNNABLE
	at sun.nio.ch.EPollArrayWrapper.epollWait(Native Method)
	at sun.nio.ch.EPollArrayWrapper.poll(EPollArrayWrapper.java:269)
	at sun.nio.ch.EPollSelectorImpl.doSelect(EPollSelectorImpl.java:93)
	at sun.nio.ch.SelectorImpl.lockAndDoSelect(SelectorImpl.java:86)
	- locked <0x000000078802d8e0> (a io.netty.channel.nio.SelectedSelectionKeySet)
	- locked <0x00000007880269b8> (a java.util.Collections$UnmodifiableSet)
	- locked <0x0000000788025640> (a sun.nio.ch.EPollSelectorImpl)
	at sun.nio.ch.SelectorImpl.select(SelectorImpl.java:97)
	at io.netty.channel.nio.NioEventLoop.select(NioEventLoop.java:622)
	at io.netty.channel.nio.NioEventLoop.run(NioEventLoop.java:310)
	at io.netty.util.concurrent.SingleThreadEventExecutor$2.run(SingleThreadEventExecutor.java:116)
	at io.netty.util.concurrent.DefaultThreadFactory$DefaultRunnableDecorator.run(DefaultThreadFactory.java:137)
	at java.lang.Thread.run(Thread.java:745)
```

but the truth is that native thread is really just waiting in the native code (`epollWait`). It is easy to check by looking up 
`/proc/<jvm-pid/tasks/<native-thread-id>/state`, e.g.

```java
 cat /proc/7105/task/7138/status
Name:    java
State:    S (sleeping)
```
even so if one enables sampling/profiling in VisualVM the thread that executes `epollWait` behaves like non-RUNNABLE. This is because in
the sources of Netbeans profiler there is something like 

```java
public class StackTraceSnapshotBuilder {
    
    static final char NAME_SIG_SPLITTER = '|';
    private static final StackTraceElement[] NO_STACK_TRACE = new StackTraceElement[0];
    private static final boolean COLLECT_TWO_TIMESTAMPS = true;
    private static final List<MethodInfo> knownBLockingMethods = Arrays.asList(new MethodInfo[] {
        new MethodInfo("java.net.PlainSocketImpl", "socketAccept[native]"), // NOI18N
        new MethodInfo("java.net.PlainSocketImpl", "socketAccept[native](java.net.SocketImpl) : void"), // NOI18N
        new MethodInfo("sun.awt.windows.WToolkit", "eventLoop[native]"), // NOI18N
        new MethodInfo("sun.awt.windows.WToolkit", "eventLoop[native]() : void"), // NOI18N
        new MethodInfo("java.lang.UNIXProcess", "waitForProcessExit[native]"), // NOI18N
        new MethodInfo("java.lang.UNIXProcess", "waitForProcessExit[native](int) : int"), // NOI18N
        new MethodInfo("sun.awt.X11.XToolkit", "waitForEvents[native]"), // NOI18N
        new MethodInfo("sun.awt.X11.XToolkit", "waitForEvents[native](long) : void"), // NOI18N
        new MethodInfo("apple.awt.CToolkit", "doAWTRunLoop[native]"), // NOI18N
        new MethodInfo("apple.awt.CToolkit", "doAWTRunLoop[native](long, boolean, boolean) : void"), // NOI18N
        new MethodInfo("java.lang.Object", "wait[native]"), // NOI18N
        new MethodInfo("java.lang.Object", "wait[native](long) : void"), // NOI18N
        new MethodInfo("java.lang.Thread", "sleep[native]"), // NOI18N
        new MethodInfo("java.lang.Thread", "sleep[native](long) : void"), // NOI18N
        new MethodInfo("sun.net.dns.ResolverConfigurationImpl","notifyAddrChange0[native]"), // NOI18N
        new MethodInfo("sun.net.dns.ResolverConfigurationImpl","notifyAddrChange0[native]() : int"), // NOI18N
        new MethodInfo("java.lang.ProcessImpl","waitFor[native]"), // NOI18N
        new MethodInfo("java.lang.ProcessImpl","waitFor[native]() : int"), // NOI18N
        new MethodInfo("sun.nio.ch.EPollArrayWrapper","epollWait[native]"), // NOI18N
        new MethodInfo("sun.nio.ch.EPollArrayWrapper","epollWait[native](long, int, long, int) : int"), // NOI18N
        new MethodInfo("java.net.DualStackPlainSocketImpl","accept0[native]"), // NOI18N
        new MethodInfo("java.net.DualStackPlainSocketImpl","accept0[native](int, java.net.InetSocketAddress[]) : int"), // NOI18N
        new MethodInfo("java.lang.ProcessImpl","waitForInterruptibly[native]"), // NOI18N
        new MethodInfo("java.lang.ProcessImpl","waitForInterruptibly[native](long) : void"), // NOI18N
        new MethodInfo("sun.print.Win32PrintServiceLookup","notifyPrinterChange[native]"), // NOI18N
        new MethodInfo("sun.print.Win32PrintServiceLookup","notifyPrinterChange[native](long) : int"), // NOI18N
        new MethodInfo("java.net.DualStackPlainSocketImpl","waitForConnect[native]"), // NOI18N
        new MethodInfo("java.net.DualStackPlainSocketImpl","waitForConnect[native](int, int) : void"), // NOI18N
        new MethodInfo("sun.nio.ch.KQueueArrayWrapper","kevent0[native]"), // NOI18N
        new MethodInfo("sun.nio.ch.KQueueArrayWrapper","kevent0[native](int, long, int, long) : int"), // NOI18N
        new MethodInfo("sun.nio.ch.WindowsSelectorImpl$SubSelector","poll0[native]"), // NOI18N
        new MethodInfo("sun.nio.ch.WindowsSelectorImpl$SubSelector","poll0[native](long, int, int[], int[], int[], long) : int"), // NOI18N
    });

    private static boolean containsKnownBlockingMethod(StackTraceElement[] stackTrace) {
         if (stackTrace.length > 0) {
             MethodInfo firstFrame = new MethodInfo(stackTrace[0]);
             return knownBLockingMethods.contains(firstFrame);
         }
         return false;
     }
     SampledThreadInfo(String tn, long tid, Thread.State ts, StackTraceElement[] st, InstrumentationFilter filter) {
         threadName = tn;
         threadId = tid;
         state = ts;
         stackTrace = st;
         if (state == Thread.State.RUNNABLE && containsKnownBlockingMethod(st)) { // known blocking method -> change state to waiting
             state = Thread.State.WAITING;
         }
         if (filter != null) {
             int i;
             
             for (i=0; i<st.length; i++) {
                 StackTraceElement frame = st[i];
                 if (filter.passesFilter(frame.getClassName().replace('.','/'))) {
                     if (i>1) {
                         stackTrace = new StackTraceElement[st.length-i+1];
                         System.arraycopy(st,i-1,stackTrace,0,stackTrace.length);
                     }
                     break;
                 }
             }
             if (i==st.length) {
                 stackTrace = NO_STACK_TRACE;
             }
         }
     }
}
```
so some of known native-blockin methods are blacklisted but you are out of luck if one of the libraries your software uses also have native-blocking
methods. 
