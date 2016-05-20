package com.avsystem.rpc

import com.avsystem.i18n.TranslationServer
import io.udash.i18n.RemoteTranslationRPC
import io.udash.rpc._

import scala.concurrent.Future
import scala.concurrent.ExecutionContext.Implicits.global

class ExposedRpcInterfaces(implicit clientId: ClientId) extends MainServerRPC {
  override def hello(name: String): Future[String] =
    Future.successful(s"Hello, $name!")

  override def pushMe(): Unit =
    ClientRPC(clientId).push(42)

  override def translations(): RemoteTranslationRPC =
    TranslationServer
}

       