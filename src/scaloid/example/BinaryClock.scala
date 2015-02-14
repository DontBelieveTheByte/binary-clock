package com.dontbelievethebyte.binaryclock

import android.graphics.Typeface
import android.view.{ViewGroup, Gravity}
import android.widget.{ImageView, LinearLayout}
import scala.language.postfixOps
import org.scaloid.common._

class BinaryClock extends SActivity{
  //Seconds first column LEDs
  lazy val s0Led1 : SImageView = new SImageView()
  lazy val s0Led2 : SImageView = new SImageView()
  lazy val s0Led4 : SImageView = new SImageView()
  lazy val s0Led8 : SImageView = new SImageView()

  //Seconds second column LEDs
  lazy val s1Led1 : SImageView = new SImageView()
  lazy val s1Led2 : SImageView = new SImageView()
  lazy val s1Led4 : SImageView = new SImageView()
  lazy val s1Led8 : SImageView = new SImageView()

  //Minutes first column LEDs
  lazy val m0Led1 : SImageView = new SImageView()
  lazy val m0Led2 : SImageView = new SImageView()
  lazy val m0Led4 : SImageView = new SImageView()
  lazy val m0Led8 : SImageView = new SImageView()

  //Minutes first column LEDs
  lazy val m1Led1 : SImageView = new SImageView()
  lazy val m1Led2 : SImageView = new SImageView()
  lazy val m1Led4 : SImageView = new SImageView()
  lazy val m1Led8 : SImageView = new SImageView()

  //Hours first column LEDs
  lazy val h0Led1 : SImageView = new SImageView()
  lazy val h0Led2 : SImageView = new SImageView()
  lazy val h0Led4 : SImageView = new SImageView()
  lazy val h0Led8 : SImageView = new SImageView()

  //Hours first column LEDs
  lazy val h1Led1 : SImageView = new SImageView()
  lazy val h1Led2 : SImageView = new SImageView()

  //Drawables
  lazy val ledOffDrawable = getResources.getDrawable(R.drawable.led_off)
  lazy val ledOnDrawable = getResources.getDrawable(R.drawable.led_on)

  //Layout params definitions
  lazy val ledColumnLayoutParams = new LinearLayout.LayoutParams(40 dip, 200.dip)
  lazy val legendColumnLayoutParams = new LinearLayout.LayoutParams(20 dip, 200.dip)
  lazy val separatorColumnLayoutParams = new LinearLayout.LayoutParams(8 dip, 200 dip)
  lazy val squareElementLayoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 40 dip)

  //Columns gravity definitions
  val ledColumnGravity = Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL
  val legendColumnGravity = Gravity.TOP | Gravity.CENTER_HORIZONTAL
  val separatorColumnGravity = Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL
  val squareElementGravity = Gravity.CENTER | Gravity.CENTER_HORIZONTAL

  onCreate {

    //Linear layouts that will make all the different the columns
    val binaryLegendLeft = new SVerticalLayout {
      style {
        case t: STextView => styleBinaryPlaceHolder(t)
      }
      STextView("8")
      STextView("4")
      STextView("2")
      STextView("1")
    } gravity legendColumnGravity layoutParams legendColumnLayoutParams

    val binaryLegendRight = new SVerticalLayout {
      style {
        case t: STextView => styleBinaryPlaceHolder(t)
      }
      STextView("8")
      STextView("4")
      STextView("2")
      STextView("1")
    } gravity legendColumnGravity layoutParams legendColumnLayoutParams

    val hours0 = new SVerticalLayout {
      style {
        case t: STextView => styleTimePlaceHolder(t)
        case i: SImageView => styleLedDrawable(i)
      }
      this += h0Led1
      this += h0Led2
      this += h0Led4
      this += h0Led8
      STextView("H")
    } gravity ledColumnGravity layoutParams ledColumnLayoutParams

    val hours1 = new SVerticalLayout {
      style {
        case t: STextView => styleTimePlaceHolder(t)
        case i: SImageView => styleLedDrawable(i)
      }
      this += h1Led1
      this += h1Led2
      STextView("H")
    } gravity ledColumnGravity layoutParams ledColumnLayoutParams

    val minutes0 = new SVerticalLayout {
      style {
        case t: STextView => styleTimePlaceHolder(t)
        case i: SImageView => styleLedDrawable(i)
      }
      this += m0Led1
      this += m0Led2
      this += m0Led4
      this += m0Led8
      STextView("M")
    } gravity ledColumnGravity layoutParams ledColumnLayoutParams

    val minutes1 = new SVerticalLayout {
      style {
        case t: STextView => styleTimePlaceHolder(t)
        case i: SImageView => styleLedDrawable(i)
      }
      this += m1Led1
      this += m1Led2
      this += m1Led4
      this += m1Led8
      STextView("M")
    } gravity ledColumnGravity layoutParams ledColumnLayoutParams

    val seconds0 = new SVerticalLayout {
      style {
        case t: STextView => styleTimePlaceHolder(t)
        case i: SImageView => styleLedDrawable(i)
      }
      this += s0Led1
      this += s0Led2
      this += s0Led4
      this += s0Led8
      STextView("S")
    } gravity ledColumnGravity layoutParams ledColumnLayoutParams

    val seconds1 = new SVerticalLayout {
      style {
        case t: STextView => styleTimePlaceHolder(t)
        case i: SImageView => styleLedDrawable(i)
      }
      this += s1Led1
      this += s1Led2
      this += s1Led4
      this += s1Led8
      STextView("S")
    } gravity ledColumnGravity layoutParams ledColumnLayoutParams

    val separatorLeft = new SVerticalLayout {
      style {
        case t: STextView => styleTimePlaceHolder(t)
      }
      STextView(":")
    } gravity separatorColumnGravity layoutParams separatorColumnLayoutParams

    val separatorRight = new SVerticalLayout {
      style {
        case t: STextView => styleTimePlaceHolder(t)
      }
      STextView(":")
    } gravity separatorColumnGravity layoutParams separatorColumnLayoutParams

    //This is where we set the content view
    contentView = new SLinearLayout {
      this += binaryLegendLeft
      this += hours1
      this += hours0
      this += separatorLeft
      this += minutes1
      this += minutes0
      this += separatorRight
      this += seconds1
      this += seconds0
      this += binaryLegendRight
    }.gravity(Gravity.CENTER)
     .orientation(LinearLayout.HORIZONTAL)
     .backgroundColor(getResources.getColor(R.color.background))
     .layoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT))
  }

  def styleBinaryPlaceHolder (t: STextView): STextView = {
    t height 40.dip  typeface Typeface.MONOSPACE textSize 10.dip gravity Gravity.CENTER textColor getResources.getColor(R.color.label)
  }

  def styleTimePlaceHolder (t: STextView): STextView = {
    t height 40.dip  typeface Typeface.MONOSPACE textSize 15.dip gravity Gravity.CENTER textColor getResources.getColor(R.color.label)
  }

  def styleLedDrawable (i: SImageView): SImageView = {
    i backgroundColor android.R.color.transparent imageDrawable ledOffDrawable layoutParams squareElementLayoutParams scaleType ImageView.ScaleType.FIT_CENTER
  }
}
