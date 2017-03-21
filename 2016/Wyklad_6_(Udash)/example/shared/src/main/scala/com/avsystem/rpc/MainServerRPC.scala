package com.avsystem.rpc

import com.avsystem.commons.rpc.RPC
import io.udash.i18n.RemoteTranslationRPC
import io.udash.rpc._

import scala.concurrent.Future

@RPC
trait MainServerRPC {
  def hello(name: String): Future[String]
  def pushMe(): Unit

  def translations(): RemoteTranslationRPC
}

       