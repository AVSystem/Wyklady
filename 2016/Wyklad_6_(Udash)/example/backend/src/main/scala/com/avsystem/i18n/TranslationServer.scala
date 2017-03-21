package com.avsystem.i18n

import io.udash.i18n._
import java.{util => ju}

import scala.concurrent.ExecutionContext.Implicits.global

object TranslationServer extends TranslationRPCEndpoint(
  new ResourceBundlesTranslationTemplatesProvider(
    TranslationServerBundles.langs
      .map(lang =>
        Lang(lang) -> TranslationServerBundles.bundlesNames
          .map(name =>
            ju.ResourceBundle.getBundle(name, new ju.Locale(lang))
          )
      ).toMap
  )
)

object TranslationServerBundles {
  val langs = Seq("en", "pl")
  val bundlesNames = Seq("demo_translations")
}