name := "scala_jmh"

version := "1.0"

organization := "com.avsystem"

scalaVersion := "2.11.8"

enablePlugins(JmhPlugin)

cancelable in Global := true

javaOptions += "-XX:+UnlockDiagnosticVMOptions"
javaOptions += "-XX:PrintAssemblyOptions=intel"