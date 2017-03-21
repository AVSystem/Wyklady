package com.avsystem.agh.appprof

import java.util.concurrent.TimeUnit

import org.openjdk.jmh.annotations._

import scala.annotation.tailrec

@BenchmarkMode(Array(Mode.AverageTime))
@OutputTimeUnit(TimeUnit.MICROSECONDS)
@State(Scope.Benchmark)
@Fork(1)
@Warmup(iterations = 5)
@Measurement(iterations = 5)
class ScalaBenchNoTailrec {

  @Param(Array("1", "5", "10", "15", "20"))
  var lim: Int = _

  def isEvenlyDivisible(v: Int, div: Int, lim: Int): Boolean = {
    if (div > lim) true
    else (v % div == 0) && isEvenlyDivisible(v, div + 1, lim)
  }

  @Benchmark
  def test(): Int = {
    var v = 10
    while(!isEvenlyDivisible(v, 2, lim))
      v += 2
    v
  }

}
