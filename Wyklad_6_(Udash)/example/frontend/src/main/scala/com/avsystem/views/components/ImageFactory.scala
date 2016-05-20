package com.avsystem.views.components
import org.scalajs.dom
import scalatags.JsDom

class ImageFactory(prefix: String) {
  import scalatags.JsDom.all._
  def apply(name: String, altText: String, xs: Modifier*): JsDom.TypedTag[dom.html.Image] = {
    img(src := s"$prefix/$name", alt := altText, xs)
  }
}

object Image extends ImageFactory("assets/images")