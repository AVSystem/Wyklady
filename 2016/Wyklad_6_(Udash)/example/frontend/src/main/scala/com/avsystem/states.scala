package com.avsystem

import io.udash._

sealed abstract class RoutingState(val parentState: RoutingState) extends State {
  def url(implicit application: Application[RoutingState]): String = s"#${application.matchState(this).value}"
}

case object RootState extends RoutingState(null)

case object ErrorState extends RoutingState(RootState)

case object IndexState extends RoutingState(RootState)

case object PropertiesDemoState extends RoutingState(RootState)

case object AdvancedPropertiesDemoState extends RoutingState(RootState)

case class BindingDemoState(urlArg: String = "") extends RoutingState(RootState)

case object RPCDemoState extends RoutingState(RootState)

case object TranslationDemoState extends RoutingState(RootState)