package com.avsystem

import io.udash._
import com.avsystem.views._

class StatesToViewPresenterDef extends ViewPresenterRegistry[RoutingState] {
  def matchStateToResolver(state: RoutingState): ViewPresenter[_ <: RoutingState] = state match {
    case RootState => RootViewPresenter
    case IndexState => IndexViewPresenter
    case PropertiesDemoState => PropertiesDemoViewPresenter
    case AdvancedPropertiesDemoState => AdvancedPropertiesDemoViewPresenter
    case BindingDemoState(urlArg) => BindingDemoViewPresenter(urlArg)
    case RPCDemoState => RPCDemoViewPresenter
    case TranslationDemoState => TranslationDemoViewPresenter
    case _ => ErrorViewPresenter
  }
}