package com.avsystem.views

import com.avsystem.i18n.Translations
import com.avsystem.{Context, TranslationDemoState}
import io.udash._
import io.udash.i18n._
import org.scalajs.dom.{Element, Event}
import org.scalajs.dom.ext.LocalStorage

import scala.concurrent.duration.DurationInt
import scala.language.postfixOps

case object TranslationDemoViewPresenter extends DefaultViewPresenterFactory[TranslationDemoState.type](() => new TranslationDemoView)

class TranslationDemoView extends View {
  import Context._
  import scalatags.JsDom.all._

  implicit val language: Property[Lang] = Property(Lang("en"))
  implicit val translationProvider = new RemoteTranslationProvider(
    serverRpc.translations(), Some(LocalStorage), 15 seconds)

  private val content = div(
    button(onclick := ((_: Event) => language.set(Lang("en"))))("EN"),
    button(onclick := ((_: Event) => language.set(Lang("pl"))))("PL"),
    p("Without arguments: ", translatedDynamic(Translations.demo.helloWorld)(_.apply())),
    p("Typed arguments: ", translatedDynamic(Translations.demo.typed.hello)(_.apply("AGH"))),
    p("Untyped arguments: ", translatedDynamic(Translations.demo.untyped.helloWithNumber)(_.apply("AGH", 123, "sth")))
  ).render

  override def getTemplate: Element = content

  override def renderChild(view: View): Unit = {}
}