package com.avsystem.agh.appprof;

import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.BenchmarkMode;
import org.openjdk.jmh.annotations.Fork;
import org.openjdk.jmh.annotations.Measurement;
import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.annotations.OutputTimeUnit;
import org.openjdk.jmh.annotations.Param;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.Setup;
import org.openjdk.jmh.annotations.State;
import org.openjdk.jmh.annotations.Warmup;

import java.util.concurrent.TimeUnit;

@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.MICROSECONDS)
@State(Scope.Benchmark)
@Fork(1)
@Warmup(iterations = 5)
@Measurement(iterations = 5)
public class JavaBenchNonPredict {

    @Param({"1", "5", "10", "15", "20"})
    int lim;

    int startDiv;
    int startVal;

    @Setup
    public void setup() {
        startDiv = 2;
        startVal = 10;
    }

    private boolean isEvenlyDivisible(int val, int div, int lim) {
        if (div > lim)
            return true;
        else
            return (val % div == 0) && isEvenlyDivisible(val, div + 1, lim);
    }

    @Benchmark
    public int test() {
        int val = startVal;
        while (!isEvenlyDivisible(val, startDiv, lim))
            val += 2;
        return val;
    }
}