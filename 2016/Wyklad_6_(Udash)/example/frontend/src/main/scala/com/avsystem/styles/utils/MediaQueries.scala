package com.avsystem.styles.utils


import com.avsystem.styles.constants.StyleConstants

import scalacss.Defaults._
import scala.language.postfixOps

object MediaQueries extends StyleSheet.Inline {
  import dsl._

  def tabletLandscape(properties: StyleA) = style(
    media.screen.minWidth(1 px).maxWidth(StyleConstants.MediaQueriesBounds.TabletLandscapeMax px) (
      properties
    )
  )

  def tabletPortrait(properties: StyleA) = style(
    media.screen.minWidth(1 px).maxWidth(StyleConstants.MediaQueriesBounds.TabletMax px) (
      properties
    )
  )

  def phone(properties: StyleA) = style(
    media.screen.minWidth(1 px).maxWidth(StyleConstants.MediaQueriesBounds.PhoneMax px) (
      properties
    )
  )
}