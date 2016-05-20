package com.avsystem.views

import io.udash._
import com.avsystem._
import org.scalajs.dom.Element
import com.avsystem.styles.{DemoStyles, GlobalStyles}
import scalacss.ScalatagsCss._

object IndexViewPresenter extends DefaultViewPresenterFactory[IndexState.type](() => new IndexView)

class IndexView extends View {
  import com.avsystem.Context._
  import scalatags.JsDom.all._

  private val content = div(
    h2("Thank you for choosing Udash! Take a look at following demo pages:"),
    ul(DemoStyles.stepsList)(
      li(a(DemoStyles.underlineLinkBlack, href := PropertiesDemoState.url)("Properties demo")),
      li(a(DemoStyles.underlineLinkBlack, href := AdvancedPropertiesDemoState.url)("Advanced properties demo")),
      li(a(DemoStyles.underlineLinkBlack, href := BindingDemoState().url)("Binding demo")),
      li(a(DemoStyles.underlineLinkBlack, href := BindingDemoState("url argument").url)("Binding demo with URL argument")),
      li(a(DemoStyles.underlineLinkBlack, href := RPCDemoState.url)("RPC demo")),
      li(a(DemoStyles.underlineLinkBlack, href := TranslationDemoState.url)("Translation demo"))
    ),
    h3("Read more"),
    ul(
      li(
        a(DemoStyles.underlineLinkBlack, href := "http://udash.io/", target := "_blank")("Visit Udash Homepage.")
      ),
      li(
        a(DemoStyles.underlineLinkBlack, href := "http://guide.udash.io/", target := "_blank")("Read more in Udash Guide.")
      ),
      li(
        a(DemoStyles.underlineLinkBlack, href := "https://www.scala-js.org/", target := "_blank")("Read more about Scala.js.")
      ),
      li(
        a(DemoStyles.underlineLinkBlack, href := "https://japgolly.github.io/scalacss/book/", target := "_blank")("Read more about ScalaCSS")
      ),
      li(
        a(DemoStyles.underlineLinkBlack, href := "http://www.lihaoyi.com/scalatags/", target := "_blank")("Read more about ScalaTags")
      )
    )
  ).render

  override def getTemplate: Element = content

  override def renderChild(view: View): Unit = {}
}