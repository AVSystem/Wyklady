package com.avsystem.rpc

import com.avsystem.commons.rpc.RPC
import io.udash.rpc._

@RPC
trait MainClientRPC {
  def push(number: Int): Unit
}
       