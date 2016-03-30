package com.avsystem.agh.appprof;

import org.openjdk.jmh.annotations.*;
import java.util.concurrent.TimeUnit;

@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.MICROSECONDS)
@State(Scope.Benchmark)
@Fork(1)
@Warmup(iterations = 5)
@Measurement(iterations = 5)
public class JavaBench {

    @Param({"1", "5", "10", "15", "20"})
    int lim;

    private boolean isEvenlyDivisible(int val, int div, int lim) {
        if (div > lim)
            return true;
        else
            return (val % div == 0) && isEvenlyDivisible(val, div + 1, lim);
    }

    @Benchmark
    public int test() {
        int val = 10;
        while(!isEvenlyDivisible(val, 2, lim))
            val += 2;
        return val;
    }

}
