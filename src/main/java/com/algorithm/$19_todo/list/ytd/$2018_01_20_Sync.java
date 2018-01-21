package com.algorithm.$19_todo.list.ytd;

import com.algorithm.$8_annotation.single.ann.SearchKeyWord;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.AbstractQueuedSynchronizer;

/**
 * @author:v_fanhaibo on 2018/1/11
 * @version:v1.0
 */

@SearchKeyWord(" jdk1.7 forkJoinPool ")
@SearchKeyWord(" 重排序,JMM ")

public class $2018_01_20_Sync<V> extends AbstractQueuedSynchronizer {
    //semantics	英[sɪˈmæntɪks]美[sɪˈmæntɪks]n.	语义学; 词义学;
    //Returns the current value of synchronization state.
    //This operation has memory semantics of a volatile read.

    private V resutl;

//    public void innerSet(V v) {
//        while (true) {
//            int state = getState();
//            ranOrCancelled(s);
//        }
//    }
//
//    public V innerGet() {
//
//
//        return resutl;
//    }
    /**
     *

     Provides a framework for implementing blocking locks and related synchronizers (semaphores, events, etc) that rely on first-in-first-out (FIFO) wait queues. This class is designed to be a useful basis for most kinds of synchronizers that rely on a single atomic int value to represent state. Subclasses must define the protected methods that change this state, and which define what that state means in terms of this object being acquired or released. Given these, the other methods in this class carry out all queuing and blocking mechanics. Subclasses can maintain other state fields, but only the atomically updated int value manipulated using methods getState, setState and compareAndSetState is tracked with respect to synchronization.
     Subclasses should be defined as non-public internal helper classes that are used to implement the synchronization properties of their enclosing class. Class AbstractQueuedSynchronizer does not implement any synchronization interface. Instead it defines methods such as acquireInterruptibly that can be invoked as appropriate by concrete locks and related synchronizers to implement their public methods.
     This class supports either or both a default exclusive mode and a shared mode. When acquired in exclusive mode, attempted acquires by other threads cannot succeed. Shared mode acquires by multiple threads may (but need not) succeed. This class does not "understand" these differences except in the mechanical sense that when a shared mode acquire succeeds, the next waiting thread (if one exists) must also determine whether it can acquire as well. Threads waiting in the different modes share the same FIFO queue. Usually, implementation subclasses support only one of these modes, but both can come into play for example in a ReadWriteLock. Subclasses that support only exclusive or only shared modes need not define the methods supporting the unused mode.
     This class defines a nested AbstractQueuedSynchronizer.ConditionObject class that can be used as a Condition implementation by subclasses supporting exclusive mode for which method isHeldExclusively reports whether synchronization is exclusively held with respect to the current thread, method release invoked with the current getState value fully releases this object, and acquire, given this saved state value, eventually restores this object to its previous acquired state. No AbstractQueuedSynchronizer method otherwise creates such a condition, so if this constraint cannot be met, do not use it. The behavior of AbstractQueuedSynchronizer.ConditionObject depends of course on the semantics of its synchronizer implementation.
     This class provides inspection, instrumentation, and monitoring methods for the internal queue, as well as similar methods for condition objects. These can be exported as desired into classes using an AbstractQueuedSynchronizer for their synchronization mechanics.
     Serialization of this class stores only the underlying atomic integer maintaining state, so deserialized objects have empty thread queues. Typical subclasses requiring serializability will define a readObject method that restores this to a known initial state upon deserialization.
     Usage
     To use this class as the basis of a synchronizer, redefine the following methods, as applicable, by inspecting and/or modifying the synchronization state using getState, setState and/or compareAndSetState:
     tryAcquire
     tryRelease
     tryAcquireShared
     tryReleaseShared
     isHeldExclusively
     Each of these methods by default throws UnsupportedOperationException. Implementations of these methods must be internally thread-safe, and should in general be short and not block. Defining these methods is the only supported means of using this class. All other methods are declared final because they cannot be independently varied.
     You may also find the inherited methods from AbstractOwnableSynchronizer useful to keep track of the thread owning an exclusive synchronizer. You are encouraged to use them -- this enables monitoring and diagnostic tools to assist users in determining which threads hold locks.
     Even though this class is based on an internal FIFO queue, it does not automatically enforce FIFO acquisition policies. The core of exclusive synchronization takes the form:
     Acquire:
     while (!tryAcquire(arg)) {
     enqueue thread if it is not already queued;
     possibly block current thread;
     }

     Release:
     if (tryRelease(arg))
     unblock the first queued thread;

     (Shared mode is similar but may involve cascading signals.)
     Because checks in acquire are invoked before enqueuing, a newly acquiring thread may barge ahead of others that are blocked and queued. However, you can, if desired, define tryAcquire and/or tryAcquireShared to disable barging by internally invoking one or more of the inspection methods, thereby providing a fair FIFO acquisition order. In particular, most fair synchronizers can define tryAcquire to return false if hasQueuedPredecessors (a method specifically designed to be used by fair synchronizers) returns true. Other variations are possible.
     Throughput and scalability are generally highest for the default barging (also known as greedy, renouncement, and convoy-avoidance) strategy. While this is not guaranteed to be fair or starvation-free, earlier queued threads are allowed to recontend before later queued threads, and each recontention has an unbiased chance to succeed against incoming threads. Also, while acquires do not "spin" in the usual sense, they may perform multiple invocations of tryAcquire interspersed with other computations before blocking. This gives most of the benefits of spins when exclusive synchronization is only briefly held, without most of the liabilities when it isn't. If so desired, you can augment this by preceding calls to acquire methods with "fast-path" checks, possibly prechecking hasContended and/or hasQueuedThreads to only do so if the synchronizer is likely not to be contended.
     This class provides an efficient and scalable basis for synchronization in part by specializing its range of use to synchronizers that can rely on int state, acquire, and release parameters, and an internal FIFO wait queue. When this does not suffice, you can build synchronizers from a lower level using atomic classes, your own custom java.util.Queue classes, and LockSupport blocking support.
     Usage Examples
     Here is a non-reentrant mutual exclusion lock class that uses the value zero to represent the unlocked state, and one to represent the locked state. While a non-reentrant lock does not strictly require recording of the current owner thread, this class does so anyway to make usage easier to monitor. It also supports conditions and exposes one of the instrumentation methods:
     class Mutex implements Lock, java.io.Serializable {

     // Our internal helper class
     private static class Sync extends AbstractQueuedSynchronizer {
     // Reports whether in locked state
     protected boolean isHeldExclusively() {
     return getState() == 1;
     }

     // Acquires the lock if state is zero
     public boolean tryAcquire(int acquires) {
     assert acquires == 1; // Otherwise unused
     if (compareAndSetState(0, 1)) {
     setExclusiveOwnerThread(Thread.currentThread());
     return true;
     }
     return false;
     }

     // Releases the lock by setting state to zero
     protected boolean tryRelease(int releases) {
     assert releases == 1; // Otherwise unused
     if (getState() == 0) throw new IllegalMonitorStateException();
     setExclusiveOwnerThread(null);
     setState(0);
     return true;
     }

     // Provides a Condition
     Condition newCondition() { return new ConditionObject(); }

     // Deserializes properly
     private void readObject(ObjectInputStream s)
     throws IOException, ClassNotFoundException {
     s.defaultReadObject();
     setState(0); // reset to unlocked state
     }
     }

     // The sync object does all the hard work. We just forward to it.
     private final Sync sync = new Sync();

     public void lock()                { sync.acquire(1); }
     public boolean tryLock()          { return sync.tryAcquire(1); }
     public void unlock()              { sync.release(1); }
     public Condition newCondition()   { return sync.newCondition(); }
     public boolean isLocked()         { return sync.isHeldExclusively(); }
     public boolean hasQueuedThreads() { return sync.hasQueuedThreads(); }
     public void lockInterruptibly() throws InterruptedException {
     sync.acquireInterruptibly(1);
     }
     public boolean tryLock(long timeout, TimeUnit unit)
     throws InterruptedException {
     return sync.tryAcquireNanos(1, unit.toNanos(timeout));
     }
     }
     Here is a latch class that is like a CountDownLatch except that it only requires a single signal to fire. Because a latch is non-exclusive, it uses the shared acquire and release methods.
     class BooleanLatch {

     private static class Sync extends AbstractQueuedSynchronizer {
     boolean isSignalled() { return getState() != 0; }

     protected int tryAcquireShared(int ignore) {
     return isSignalled() ? 1 : -1;
     }

     protected boolean tryReleaseShared(int ignore) {
     setState(1);
     return true;
     }
     }

     private final Sync sync = new Sync();
     public boolean isSignalled() { return sync.isSignalled(); }
     public void signal()         { sync.releaseShared(1); }
     public void await() throws InterruptedException {
     sync.acquireSharedInterruptibly(1);
     }
     }
     Since:
     1.5     *
     */

}
