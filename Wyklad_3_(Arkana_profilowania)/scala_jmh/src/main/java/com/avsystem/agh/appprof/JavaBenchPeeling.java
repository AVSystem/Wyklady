package com.avsystem.agh.appprof;

import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.BenchmarkMode;
import org.openjdk.jmh.annotations.Fork;
import org.openjdk.jmh.annotations.Measurement;
import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.annotations.OutputTimeUnit;
import org.openjdk.jmh.annotations.Param;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.State;
import org.openjdk.jmh.annotations.Warmup;

import java.util.concurrent.TimeUnit;

@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.MICROSECONDS)
@State(Scope.Benchmark)
@Fork(1)
@Warmup(iterations = 5)
@Measurement(iterations = 5)
public class JavaBenchPeeling {

    @Param({"1", "5", "10", "15", "20"})
    int lim;

    @Benchmark
    public int test_02() {
        forval: for (int val = 10; ; val += 2) {
            for (int div = 2; div < lim; div++) {
                if (val % div != 0) continue forval;
            }
            return val;
        }
    }

    @Benchmark
    public int test_03() {
        forval: for (int val = 10; ; val += 2) {
            if (val % 2 != 0) continue;
            for (int div = 3; div < lim; div++) {
                if (val % div != 0) continue forval;
            }
            return val;
        }
    }

    @Benchmark
    public int test_04() {
        forval: for (int val = 10; ; val += 2) {
            if (val % 2 != 0) continue;
            if (val % 3 != 0) continue;
            for (int div = 4; div < lim; div++) {
                if (val % div != 0) continue forval;
            }
            return val;
        }
    }

    @Benchmark
    public int test_05() {
        forval: for (int val = 10; ; val += 2) {
            if (val % 2 != 0) continue;
            if (val % 3 != 0) continue;
            if (val % 4 != 0) continue;
            for (int div = 5; div < lim; div++) {
                if (val % div != 0) continue forval;
            }
            return val;
       }
    }

    @Benchmark
    public int test_06() {
        forval: for (int val = 10; ; val += 2) {
            if (val % 2 != 0) continue;
            if (val % 3 != 0) continue;
            if (val % 4 != 0) continue;
            if (val % 5 != 0) continue;
            for (int div = 6; div < lim; div++) {
                if (val % div != 0) continue forval;
            }
            return val;
       }
    }

    @Benchmark
    public int test_07() {
        forval: for (int val = 10; ; val += 2) {
            if (val % 2 != 0) continue;
            if (val % 3 != 0) continue;
            if (val % 4 != 0) continue;
            if (val % 5 != 0) continue;
            if (val % 6 != 0) continue;
            for (int div = 7; div < lim; div++) {
                if (val % div != 0) continue forval;
            }
            return val;
       }
    }

    @Benchmark
    public int test_08() {
        forval: for (int val = 10; ; val += 2) {
            if (val % 2 != 0) continue;
            if (val % 3 != 0) continue;
            if (val % 4 != 0) continue;
            if (val % 5 != 0) continue;
            if (val % 6 != 0) continue;
            if (val % 7 != 0) continue;
            for (int div = 8; div < lim; div++) {
                if (val % div != 0) continue forval;
            }
            return val;
       }
    }

    @Benchmark
    public int test_09() {
        forval: for (int val = 10; ; val += 2) {
            if (val % 2 != 0) continue;
            if (val % 3 != 0) continue;
            if (val % 4 != 0) continue;
            if (val % 5 != 0) continue;
            if (val % 6 != 0) continue;
            if (val % 7 != 0) continue;
            if (val % 8 != 0) continue;
            for (int div = 9; div < lim; div++) {
                if (val % div != 0) continue forval;
            }
            return val;
       }
    }

    @Benchmark
    public int test_10() {
        forval: for (int val = 10; ; val += 2) {
            if (val % 2 != 0) continue;
            if (val % 3 != 0) continue;
            if (val % 4 != 0) continue;
            if (val % 5 != 0) continue;
            if (val % 6 != 0) continue;
            if (val % 7 != 0) continue;
            if (val % 8 != 0) continue;
            if (val % 9 != 0) continue;
            for (int div = 10; div < lim; div++) {
                if (val % div != 0) continue forval;
            }
            return val;
       }
    }

    @Benchmark
    public int test_11() {
        forval: for (int val = 10; ; val += 2) {
            if (val % 2 != 0) continue;
            if (val % 3 != 0) continue;
            if (val % 4 != 0) continue;
            if (val % 5 != 0) continue;
            if (val % 6 != 0) continue;
            if (val % 7 != 0) continue;
            if (val % 8 != 0) continue;
            if (val % 9 != 0) continue;
            if (val % 10 != 0) continue;
            for (int div = 11; div < lim; div++) {
                if (val % div != 0) continue forval;
            }
            return val;
       }
    }

    @Benchmark
    public int test_11_hand() {
        forval: for (int val = 10; ; val += 2) {
            if (val % 2 != 0) continue;
            if (val % 3 != 0) continue;
            if (val % 4 != 0) continue;
            if (val % 5 != 0) continue;
//            if (val % 6 != 0) continue;  // contains (2*3)
            if (val % 7 != 0) continue;
            if (val % 8 != 0) continue;
            if (val % 9 != 0) continue;
//            if (val % 10 != 0) continue;  // contains (2*5)
            for (int div = 11; div < lim; div++) {
                if (val % div != 0) continue forval;
            }
            return val;
       }
    }

}
