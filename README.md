jvm-instr-benchmark
=================

Benchmark project for measuring the instrumentation performance in ASM and Javassist.
First, we do generate a number of very simple classes with one method. Each class implements given interface.
Then, we initialize each of this classes, perform some instrumentation on them and call the interface.
The whole operation is measured to compare ASM and Javassist performance.

My results are available in `results.pdf`

### benchmark phases
- warm up - a few short runs to let JVM focus on this little benchmark,
- taking measurements - long run taking actual results (processed properly to be meaningful).

## running the project

`sh benchmark.sh` 
Be patient!
