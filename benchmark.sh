#!/usr/bin/env bash

printf "Benchmark script compares ASM and Javassist code instrumentation performance\n\n"

# generate test classes
printf "\tGenerating test classes...\n"
mvn compile exec:java -Dexec.args="1000" -q &>/dev/null



# testing ASM
printf "\n\tMeasuring ASM performance...\n"
printf "\t[________]\n"
sed -i '' '13s/.*/inst.addTransformer(new ASMTransformer());/' src/main/java/pl/nadoba/jvm/instrumentation/benchmark/MyAgent.java

mvn clean compile -q &>/dev/null
printf "\t[##______]\n"

# run code instrumentation benchmark several times for warm up phase
for i in {1..10}
do
    mvn verify -q &>/dev/null
done
printf "\t[####____]\n"
for i in {1..10}
do
    mvn verify -q &>/dev/null
done
printf "\t[######__]\n"

measurements[0]=0

for i in {0..9}
do
    start=`curl -s 'http://www.timeapi.org/utc/now?\\s\\L'`
    mvn verify -q &>/dev/null
    end=`curl -s 'http://www.timeapi.org/utc/now?\\s\\L'`
    elapsed=$((end-start))
    measurements[i]=$elapsed
done

printf "\t[########]\n"

# remove the lowest measurement
lowest_index=0

for i in {0..9}
do
    if [ ${measurements[i]} -lt ${measurements[lowest_index]} ] ; then
        lowest_index=$i
    fi
done

measurements=(${measurements[@]:0:${lowest_index}} ${measurements[@]:$((${lowest_index} + 1))})

# then remove the highest measurement
highest_index=0

for i in {0..8}
do
    if [ ${measurements[i]} -gt ${measurements[highest_index]} ] ; then
        highest_index=$i
    fi
done

measurements=(${measurements[@]:0:${highest_index}} ${measurements[@]:$((${highest_index} + 1))})

# calculating average
asm_total=0
for i in {0..7}
do
    asm_total=$((asm_total+${measurements[i]}))
done



# testing Javassist
printf "\n\tMeasuring Javassist performance...\n"
printf "\t[________]\n"
sed -i '' '13s/.*/inst.addTransformer(new JavassistTransformer());/' src/main/java/pl/nadoba/jvm/instrumentation/benchmark/MyAgent.java

mvn clean compile -q &>/dev/null
printf "\t[##______]\n"

# run code instrumentation benchmark several times for warm up phase
for i in {1..10}
do
    mvn verify -q &>/dev/null
done
printf "\t[####____]\n"
for i in {1..10}
do
    mvn verify -q &>/dev/null
done
printf "\t[######__]\n"

measurements[0]=0

for i in {0..9}
do
    start=`curl -s 'http://www.timeapi.org/utc/now?\\s\\L'`
    mvn verify -q &>/dev/null
    end=`curl -s 'http://www.timeapi.org/utc/now?\\s\\L'`
    elapsed=$((end-start))
    measurements[i]=$elapsed
done

printf "\t[########]\n"

# remove the lowest measurement
lowest_index=0

for i in {0..9}
do
    if [ ${measurements[i]} -lt ${measurements[lowest_index]} ] ; then
        lowest_index=$i
    fi
done

measurements=(${measurements[@]:0:${lowest_index}} ${measurements[@]:$((${lowest_index} + 1))})

# then remove the highest measurement
highest_index=0

for i in {0..8}
do
    if [ ${measurements[i]} -gt ${measurements[highest_index]} ] ; then
        highest_index=$i
    fi
done

measurements=(${measurements[@]:0:${highest_index}} ${measurements[@]:$((${highest_index} + 1))})

# calculating average
javassist_total=0
for i in {0..7}
do
    javassist_total=$((javassist_total+${measurements[i]}))
done



# delete generated classes
printf "\n\tDeleting test classes...\n"
rm -rf src/main/java/pl/nadoba/jvm/instrumentation/benchmark/generated


# print the results
printf "\n------------------------------------------------\n"
printf "ASM transformer avg. execution time: $((asm_total/8)) ms\n"
printf "Javassist transformer avg. execution time: $((javassist_total/8)) ms"
printf "\n------------------------------------------------\n"
