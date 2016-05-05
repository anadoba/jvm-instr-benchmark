jvm-instr-benchmark
=================

Benchmark project for measuring the instrumentation performance in ASM and Javassist.
First, we do generate a number of very simple classes with one method (and end up with 10000 various .java files). Each class implements given interface.
Then, we initialize each of this classes, perform some instrumentation on them and call the interface.
The whole operation is measured to compare ASM and Javassist performance.

My results (chart) are available under
[chart.png](chart.png) 


### benchmark phases
- generating test classes (10000 java files),
- setting the code transformer to ASM,
- warm up - a few short runs to let JVM focus on this little benchmark,
- taking measurements - long run taking actual results (processed properly to be meaningful),
- setting the code transformer to Javassist,
- warming up again,
- taking measurements,
- printing out the results,
- deleting test classes.

## running the project

`sh benchmark.sh` 
Please be patient as whole operation takes about 8 minutes on a mobile Core i7 machine.
