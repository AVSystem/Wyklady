package com.avsystem.styles

import com.avsystem.styles.constants.StyleConstants
import com.avsystem.styles.fonts.{FontStyle, FontWeight, UdashFonts}
import com.avsystem.styles.utils.{MediaQueries, StyleUtils}

import scala.language.postfixOps
import scalacss.Attr
import scalacss.Defaults._

object GlobalStyles extends StyleSheet.Inline {
  import dsl._

  val reset = style(scalacss.ext.CssReset.meyer)

  val global = style(
    unsafeRoot("#application") (
      height(100 %%)
    ),

    unsafeRoot("html") (
      UdashFonts.acumin(),
      position.relative,
      height(100 %%),
      fontSize(62.5 %%),
      overflowY.scroll
    ),

    unsafeRoot("body") (
      position.relative,
      height(100 %%),
      fontSize(1.6 rem)
    ),

    unsafeRoot("p")(
      marginTop(1 rem),
      fontSize(1.6 rem),
      lineHeight(1.3),

      &.firstChild (
        marginTop(`0`)
      )
    ),

    unsafeRoot("li")(
      fontSize.inherit,
      lineHeight(1.3)
    ),

    unsafeRoot("h1") (
      position.relative,
      UdashFonts.acumin(FontWeight.SemiBold),
      paddingTop(7 rem),
      paddingBottom(4.5 rem),
      lineHeight(1.2),
      fontSize(4.8 rem),

      &.after (
        content := "\"\u2014\"",
        position.absolute,
        left(`0`),
        bottom(`0`),
        fontSize(3.6 rem)
      ),

      MediaQueries.phone(
        style(
          fontSize(3.2 rem)
        )
      )
    ),

    unsafeRoot("h2") (
      UdashFonts.acumin(FontWeight.Regular),
      marginBottom(2 rem),
      lineHeight(1.2),
      fontSize(2.5 rem),
      wordWrap.breakWord,

      MediaQueries.phone(
        style(
          fontSize(2.8 rem)
        )
      )
    ),

    unsafeRoot("h3") (
      UdashFonts.acumin(FontWeight.ExtraLight),
      marginTop(4.5 rem),
      marginBottom(1.5 rem),
      lineHeight(1.2),
      fontSize(2.2 rem),

      MediaQueries.phone(
        style(
          fontSize(2.6 rem)
        )
      )
    ),

    unsafeRoot("h4") (
      UdashFonts.acumin(FontWeight.ExtraLight),
      marginTop(3.5 rem),
      marginBottom(1.5 rem),
      lineHeight(1.2),
      fontSize(2.4 rem),

      MediaQueries.phone(
        style(
          fontSize(2 rem)
        )
      )
    ),

    unsafeRoot("blockquote") (
      UdashFonts.acumin(FontWeight.ExtraLight, FontStyle.Italic),
      position.relative,
      margin(4 rem, `0`, 5 rem, 4.5 rem),
      padding(1.5 rem, 3 rem),
      fontSize(3.2 rem),
      color(StyleConstants.Colors.Grey),

      &.before(
        StyleUtils.border(StyleConstants.Colors.Red, .3 rem),
        content := "\" \"",
        position.absolute,
        top(`0`),
        left(`0`),
        height(100 %%)
      ),

      MediaQueries.phone(
        style(
          fontSize(2.4 rem)
        )
      )
    ),

    unsafeRoot("a") (
      textDecoration := "none",
      outline(`0`).important,

      &.link(
        textDecoration := "none"
      ),

      &.hover (
        textDecoration := "none"
      ),

      &.visited (
        color.inherit
      ),

      &.hover (
        textDecoration := "underline"
      )
    ),

    unsafeRoot("img")(
      maxWidth(100 %%),
      height.auto
    ),

    unsafeRoot("svg") (
      display.block
    ),

    unsafeRoot("object[type='image/svg+xml']") (
      display.block,
      pointerEvents := "none"
    ),

    unsafeRoot("input") (
      padding(.5 rem, 1 rem),
      borderWidth(1 px),
      borderStyle.solid,
      borderColor(c"#cccccc"),
      borderRadius(.3 rem),

      &.focus (
        outline.none
      )
    ),

    unsafeRoot("input::-webkit-outer-spin-button")(
      Attr.real("-webkit-appearance") := "none",
      margin(`0`)
    ),

    unsafeRoot("input::-webkit-inner-spin-button")(
      Attr.real("-webkit-appearance") := "none",
      margin(`0`)
    ),

    unsafeRoot("textarea") (
      resize.none
    ),

    unsafeRoot("strong")(
      fontWeight.bolder
    ),

    unsafeRoot("b")(
      fontWeight.bold
    ),

    unsafeRoot("i")(
      fontStyle.italic,
      fontWeight._600
    ),

    unsafeRoot("*") (
      boxSizing.borderBox,

      &.before (
        boxSizing.borderBox
      ),

      &.after (
        boxSizing.borderBox
      )
    )
  )

  val clearfix = style(
    &.before (
      content := "\" \"",
      display.table
    ),

    &.after (
      content := "\" \"",
      display.table,
      clear.both
    )
  )

  val col = style(
    position.relative,
    display.inlineBlock,
    verticalAlign.top
  )

  val body = style(
    position.relative,
    width(StyleConstants.Sizes.BodyWidth px),
    height(100 %%),
    margin(0 px, auto),

    MediaQueries.tabletLandscape(
      style(
        width(100 %%),
        paddingLeft(2 rem),
        paddingRight(2 rem)
      )
    ),

    MediaQueries.phone(
      style(
        paddingLeft(3 %%),
        paddingRight(3 %%)
      )
    )
  )

  val main = style(
    position.relative,
    minHeight :=! s"calc(100vh - ${StyleConstants.Sizes.HeaderHeight}px - ${StyleConstants.Sizes.FooterHeight}px)",
    paddingBottom(10 rem)
  )

  val block = style(
    display.block
  )

  val inline = style(
    display.inline
  )

  val hidden = style(
    visibility.hidden
  )

  val noMargin = style(
    margin(`0`).important
  )

  val red = style(
    color(StyleConstants.Colors.Red).important
  )

  val grey = style(
    color(StyleConstants.Colors.Grey).important
  )
}