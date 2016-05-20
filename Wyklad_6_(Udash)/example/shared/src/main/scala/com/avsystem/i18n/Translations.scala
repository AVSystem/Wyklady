package com.avsystem.i18n

import io.udash.i18n.TranslationKey

object Translations {
  import TranslationKey._

  object demo {
    val helloWorld = key("demo.helloWorld")

    object typed {
      val hello = key1[String]("demo.hello")
    }

    object untyped {
      val helloWithNumber = keyX("demo.helloWithNumber")
    }
  }
}
