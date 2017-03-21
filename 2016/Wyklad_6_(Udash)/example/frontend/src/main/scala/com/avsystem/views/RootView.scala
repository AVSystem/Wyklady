package com.avsystem.views

import io.udash._
import com.avsystem.RootState
import org.scalajs.dom.Element
import scalatags.JsDom.tags2.main
import com.avsystem.views.components.{Footer, Header}
import com.avsystem.styles.{DemoStyles, GlobalStyles}
import scalacss.ScalatagsCss._

object RootViewPresenter extends DefaultViewPresenterFactory[RootState.type](() => new RootView)

class RootView extends View {
  import com.avsystem.Context._
  import scalatags.JsDom.all._

  private var child: Element = div().render

  private val content = div(
    Header.getTemplate,
    main(GlobalStyles.main)(
      div(GlobalStyles.body)(
        h1("agh-demo"),
        child
      )
    )
    ,Footer.getTemplate
  ).render

  override def getTemplate: Element = content

  override def renderChild(view: View): Unit = {
    import io.udash.wrappers.jquery._
    val newChild = view.getTemplate
    jQ(child).replaceWith(newChild)
    child = newChild
  }
}