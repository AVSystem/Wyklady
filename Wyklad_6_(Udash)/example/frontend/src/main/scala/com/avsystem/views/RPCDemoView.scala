package com.avsystem.views

import io.udash._
import com.avsystem.RPCDemoState
import org.scalajs.dom.Element
import com.avsystem.styles.DemoStyles
import scalacss.ScalatagsCss._

import scala.util.{Success, Failure}

case object RPCDemoViewPresenter extends DefaultViewPresenterFactory[RPCDemoState.type](() => {
  import com.avsystem.Context._

  val serverResponse = Property[String]("???")
  val input = Property[String]("")
  input.listen((value: String) => {
    serverRpc.hello(value).onComplete {
      case Success(resp) => serverResponse.set(resp)
      case Failure(_) => serverResponse.set("Error")
    }
  })

  serverRpc.pushMe()

  new RPCDemoView(input, serverResponse)
})

class RPCDemoView(input: Property[String], serverResponse: Property[String]) extends View {
  import scalatags.JsDom.all._

  private val content = div(
    TextInput(input, placeholder := "Type your name..."),
    p("Server response: ", bind(serverResponse))
  ).render

  override def getTemplate: Element = content

  override def renderChild(view: View): Unit = {}
}