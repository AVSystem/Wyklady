package com.avsystem.views

import com.avsystem.{PropertiesDemoState, _}
import io.udash._
import org.scalajs.dom.{Element, Event}

import scala.concurrent.{ExecutionContext, Future}
import scala.util.Random

trait NumbersInRange {
  def minimum: Int
  def maximum: Int
  def numbers: Seq[Int]
}

object NumbersInRangeValidator extends Validator[NumbersInRange] {
  override def apply(element: NumbersInRange)(implicit ec: ExecutionContext): Future[ValidationResult] = Future {
    def inBounds(i: Int): Boolean =
      i >= element.minimum && i <= element.maximum

    if (element.numbers.forall(inBounds)) Valid
    else Invalid(Seq(s"Numbers ${element.numbers.filter(i => !inBounds(i))} are not in <${element.minimum}, ${element.maximum}>"))
  }
}

object PropertiesDemoViewPresenter extends ViewPresenter[PropertiesDemoState.type] {
  import Context._

  override def create(): (View, Presenter[PropertiesDemoState.type]) = {
    val model = ModelProperty[NumbersInRange]
    model.subProp(_.minimum).set(0)
    model.subProp(_.maximum).set(42)
    model.subSeq(_.numbers).set(Seq(4, 23, 41))

    model.addValidator(NumbersInRangeValidator)

    val presenter = new PropertiesDemoPresenter(model)
    val view = new PropertiesDemoView(model, presenter)
    (view, presenter)
  }
}

class PropertiesDemoPresenter(model: ModelProperty[NumbersInRange]) extends Presenter[PropertiesDemoState.type] {
  private val minimum: Property[Int] = model.subProp(_.minimum)
  private val maximum: Property[Int] = model.subProp(_.maximum)
  private val numbers: SeqProperty[Int] = model.subSeq(_.numbers)

  override def handleState(state: PropertiesDemoState.type): Unit = ()

  def randomize(): Unit = {
    minimum.set(Random.nextInt(20))
    maximum.set(minimum.get + Random.nextInt(80))
    numbers.set(Seq.fill(3)(Random.nextInt(100)))
  }
}

class PropertiesDemoView(model: ModelProperty[NumbersInRange], presenter: PropertiesDemoPresenter) extends View {
  import com.avsystem.Context._

  import scalatags.JsDom.all._

  private val minimum: Property[Int] = model.subProp(_.minimum)
  private val maximum: Property[Int] = model.subProp(_.maximum)
  private val numbers: SeqProperty[Int] = model.subSeq(_.numbers)

  private val content = div(
    h2("Properties demo"),
    ul(
      li("Minimum: ", bind(minimum)),
      li("Maximum: ", produce(maximum)(value => if (value % 2 == 0) i(value).render else b(value).render)),
      li("Numbers: ", repeat(numbers)((p: Property[Int]) => span(s"${p.get}, ").render))
    ),
    button(onclick := ((ev: Event) => presenter.randomize()))("Randomize"),
    h3("Validation result"),
    bindValidation(model,
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