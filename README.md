# Java Snippets
Snippets of useful/interesting Java code

## Technology
+ Java: 1.7.0_10-b18

## Ideas
+ Sorting (plus searching / binary search)
+ Divide-and-conquer
+ Dynamic programming / memoization
+ Greediness
+ Recursion
+ Stack
+ Queue
+ Hashset
+ Hashtable
+ Binary tree
+ Heap
+ Graph
+ Heaps, list, queues, stacks, btree, db algorithms
+ Algorithm complexity
+ Tress - binary, n-ary, trie, balanced binary tree such as red/black tree, sply, AVL
+ Tree traversal - BFS, DFS, inorder/postorder/preorder
+ Graphs - objects, points, matrix, Djkstra, A*
+ NP complete problems - travelling salesman, knapsack problem
+ Maths - combinatarics, probabilty, n-choose-k problems, discrete maths
+ Selection sort, heapsort, mergesort, quicksort, binary search
+ Adjaceny matrix, adjacncy list
+ Mutex, semaphore, deadlock, livelock, lock/monitor

# Garbage Collection Tuning

Some of my favorite articles on the subject include (if you are new to GC tuning you should read these first):

+ [Java SE 6 HotSpot Virtual Machine Garbage Collection Tuning](http://www.oracle.com/technetwork/java/javase/gc-tuning-6-140523.html)
+ [How to Tune Java Garbage Collection](http://architects.dzone.com/articles/how-tune-java-garbage)
+ [Garbage Collectors Available In JDK 1.7.0_04](http://www.fasterj.com/articles/oraclecollectors1.shtml)
+ [1.4.1 Garbage collection algorithms](http://www.javaperformancetuning.com/news/qotm026.shtml)
+ [Understanding CMS GC Logs](https://blogs.oracle.com/poonam/entry/understanding_cms_gc_logs)

The young and old generation use different types of algorithms for garbage collection. The young generation uses a copying collection algorithm that moves all the live object from one area to another, leaving the dead objects behind. The old generation uses a mark-and-sweep collection algorithm. Copy collection time is roughly proportional to the number of live objects, mark-and-sweep collection is roughly proportional to the size of the heap. This is why the young heap is small and collected frequently and the old heap is big and collected less frequently.

There are two major aspects to play with: the algorithm used (on both the young and old generation) and the amount of memory allocated. 

### Algorithm

On a multicore machine the choice is between the parallel algorithm and the concurrent algorithm. Parallel means that during the stop-the-world pause the collector uses multiple threads to complete the job. Concurrent means that some of the work can be done whilst the application is running, therefore reducing the length of the stop-the-world pause. The important difference is:

+ Parallel: use when optimizing for throughput, therefore your system will be able to process more requests, but stop-the-world pauses will be more noticeable.
+ Concurrent: use when optimizing for latency, therefore your system will produce more consistent, but slower results.

On all the systems I've worked on, consistency is more important so I normally use the concurrent algorithm. The concurrent algorithm only works on the old generation, but the parallel algorithm will be defaulted for the new generation. To enable the concurrent algorithm use the flag:

```
-XX:+UseConcMarkSweepGC
```

To enable the parallel algorithm I use the flags:

```
-XX:+UseParallelGC -XX:+UseParallelOldGC -XX:+UseAdaptiveSizePolicy
```

### Memory

Increasing the memory allocated will cause longer GC, but also mean less frequent runs. I usually find that allocating the minimum amount is usually the best strategy, because even though more GC collections will occur, they will be much faster. The start size and max size should be set to the same size:

```
-Xmx512m -Xms512m
```

### Debug Flags

I like to set the following:

```
-XX:+PrintGCDetails -XX:+PrintGCTimeStamps -XX:+PrintGCDateStamps -Xloggc:/home/user/gc.log
```

To get even more details I add:

```
-XX:+PrintTenuringDistribution -XX:+PrintHeapAtGC
```

To check if a flag is set by default:

```
java -XX:+PrintFlagsFinal | grep FLAG
```
