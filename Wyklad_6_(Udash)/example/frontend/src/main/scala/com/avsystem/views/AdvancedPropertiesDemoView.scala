package com.avsystem.views

import com.avsystem._
import io.udash._
import org.scalajs.dom.{Element, Event}

import scala.util.Random

object AdvancedPropertiesDemoViewPresenter extends ViewPresenter[AdvancedPropertiesDemoState.type] {
  import Context._

  override def create(): (View, Presenter[AdvancedPropertiesDemoState.type]) = {
    val model = SeqProperty[Double](Seq(1.3, 4.5, 6.7))

    val presenter = new AdvancedPropertiesDemoPresenter(model)
    val view = new AdvancedPropertiesDemoView(model, presenter)
    (view, presenter)
  }
}

class AdvancedPropertiesDemoPresenter(doubles: SeqProperty[Double]) extends Presenter[AdvancedPropertiesDemoState.type] {
  override def handleState(state: AdvancedPropertiesDemoState.type): Unit = ()

  private val ints: SeqProperty[Int] = doubles.transform(_.toInt, _.toDouble)

  def addNextDouble(): Unit =
    doubles.append(Random.nextDouble() * 10)

  def addNextInt(): Unit =
    ints.append(Random.nextInt(10))
}

class AdvancedPropertiesDemoView(doubles: SeqProperty[Double], presenter: AdvancedPropertiesDemoPresenter) extends View {
  import com.avsystem.Context._

  import scalatags.JsDom.all._

  private val ints: SeqProperty[Int] = doubles.transform(_.toInt, _.toDouble)
  private val evens: ReadableSeqProperty[Int] = ints.filter(_ % 2 == 0)

  private val content = div(
    h2("Advanced properties demo"),
    ul(
      li("Doubles: ", repeat(doubles)((p: Property[Double]) => span(s"${p.get}, ").render)),
      li("Ints: ", repeat(ints)((p: Property[Int]) => span(s"${p.get}, ").render)),
      li("Events: ", repeat(evens)((p: ReadableProperty[Int]) => span(s"${p.get}, ").render))
    ),
    button(onclick := ((ev: Event) => presenter.addNextDouble()))("Add double"),
    button(onclick := ((ev: Event) => presenter.addNextInt()))("Add int"),
    h3("Validation result"),
    bindValidation(doubles,
      _ => p("Validation in progress").render,
      {
        case Valid => p("Model is valid").render
        case Invalid(errors) => p("Errors: ", ul(errors.map(error => li(error).render))).render
      },
      execError => p(execError.getMessage).render
    )

  ).render

  override def getTemplate: Element = content

  override def renderChild(view: View): Unit = {}
}