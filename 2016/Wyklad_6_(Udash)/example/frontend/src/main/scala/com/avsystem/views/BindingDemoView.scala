package com.avsystem.views

import io.udash._
import com.avsystem.BindingDemoState
import org.scalajs.dom.Element
import com.avsystem.styles.DemoStyles
import scalacss.ScalatagsCss._

case class BindingDemoViewPresenter(urlArg: String) extends DefaultViewPresenterFactory[BindingDemoState](() => {
  import com.avsystem.Context._

  val model = Property[String](urlArg)
  new BindingDemoView(model)
})

class BindingDemoView(model: Property[String]) extends View {
  import scalatags.JsDom.all._

  private val content = div(
    h2("Binding demo"),
    h3("Example"),
    TextInput(model, placeholder := "Type your name..."),
    p("Hello, ", bind(model), "!")
  ).render

  override def getTemplate: Element = content

  override def renderChild(view: View): Unit = {}
}