package com.dontbelievethebyte.binaryclock

import java.text.SimpleDateFormat
import java.util.Calendar

import android.graphics.Typeface
import android.os.Handler
import android.view.{ViewGroup, Gravity}
import android.widget.{ImageView, LinearLayout}
import com.dontbelievethebyte.binaryclock.R
import com.dontbelievethebyte.binaryclock.R.drawable
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

  //Time placeholders
  lazy val s0text : STextView = new STextView("S")
  lazy val s1text : STextView = new STextView("S")
  lazy val m0text : STextView = new STextView("M")
  lazy val m1text : STextView = new STextView("M")
  lazy val h0text : STextView = new STextView("H")
  lazy val h1text : STextView = new STextView("H")

  //Drawables
  lazy val ledOffDrawable = getResources.getDrawable(drawable.led_off)
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
      this += h0Led8
      this += h0Led4
      this += h0Led2
      this += h0Led1
      this += h0text
    } gravity ledColumnGravity layoutParams ledColumnLayoutParams

    val hours1 = new SVerticalLayout {
      style {
        case t: STextView => styleTimePlaceHolder(t)
        case i: SImageView => styleLedDrawable(i)
      }
      this += h1Led2
      this += h1Led1
      this += h1text
    } gravity ledColumnGravity layoutParams ledColumnLayoutParams

    val minutes0 = new SVerticalLayout {
      style {
        case t: STextView => styleTimePlaceHolder(t)
        case i: SImageView => styleLedDrawable(i)
      }
      this += m0Led8
      this += m0Led4
      this += m0Led2
      this += m0Led1
      this += m0text
    } gravity ledColumnGravity layoutParams ledColumnLayoutParams

    val minutes1 = new SVerticalLayout {
      style {
        case t: STextView => styleTimePlaceHolder(t)
        case i: SImageView => styleLedDrawable(i)
      }
      this += m1Led8
      this += m1Led4
      this += m1Led2
      this += m1Led1
      this += m1text
    } gravity ledColumnGravity layoutParams ledColumnLayoutParams

    val seconds0 = new SVerticalLayout {
      style {
        case t: STextView => styleTimePlaceHolder(t)
        case i: SImageView => styleLedDrawable(i)
      }
      this += s0Led8
      this += s0Led4
      this += s0Led2
      this += s0Led1
      this += s0text
    } gravity ledColumnGravity layoutParams ledColumnLayoutParams

    val seconds1 = new SVerticalLayout {
      style {
        case t: STextView => styleTimePlaceHolder(t)
        case i: SImageView => styleLedDrawable(i)
      }
      this += s1Led8
      this += s1Led4
      this += s1Led2
      this += s1Led1
      this += s1text
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

  val handler = new Handler()

  val runnable = new Runnable() {

    def run() {
      try{
        showTime()
        handler.postDelayed(this, 1000)
      }
      catch {
        case e: Exception => handler.postDelayed(this, 1000)
      }
    }
  }
  
  onResume {
    handler.postDelayed(runnable, 1000)
  }

  onPause {
    handler.removeCallbacks(runnable)
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

  val hoursFormat = new SimpleDateFormat("HH")
  val minutesFormat = new SimpleDateFormat("mm")
  val secondsFormat = new SimpleDateFormat("ss")

  def showTime() = {
    val time = Calendar.getInstance().getTime
    setHours(hoursFormat.format(time))
    setMinutes(minutesFormat.format(time))
    setSeconds(secondsFormat.format(time))
  }

  def toInt(s: String):Int = {
    try {
      s.toInt
    } catch {
      case e:Exception => 0
    }
  }

  def setSeconds(seconds: String): Unit = {
    s0text text seconds.substring(seconds.length() - 1)
    s1text text seconds.substring(0,1)
    setLeds(List(s0Led1, s0Led2, s0Led4, s0Led8), seconds.substring(seconds.length() - 1).toInt)
    setLeds(List(s1Led1, s1Led2, s1Led4, s1Led8), seconds.substring(0,1).toInt)
  }

  def setMinutes(minutes: String): Unit = {
    m0text text minutes.substring(minutes.length() - 1)
    m1text text minutes.substring(0,1)
    setLeds(List(m0Led1, m0Led2, m0Led4, m0Led8), minutes.substring(minutes.length() - 1).toInt)
    setLeds(List(m1Led1, m1Led2, m1Led4, m1Led8), minutes.substring(0, 1).toInt)
  }

  def setHours(hours: String): Unit = {
    h0text text hours.substring(hours.length() - 1)
    h1text text hours.substring(0,1)
    setLeds(List(h0Led1, h0Led2), hours.substring(hours.length() - 1).toInt)
    setLeds(List(h1Led1, h1Led2), hours.substring(0, 1).toInt)
  }

  def setLeds(leds : List[SImageView], value : Int ): Unit = {
    leds match {
      case List(_, _, _, _) => value match  {
        case 0 =>
          leds(0) imageDrawable ledOffDrawable
          leds(1) imageDrawable ledOffDrawable
          leds(2) imageDrawable ledOffDrawable
          leds(3) imageDrawable ledOffDrawable
        case 1 =>
          leds(0) imageDrawable ledOnDrawable
          leds(1) imageDrawable ledOffDrawable
          leds(2) imageDrawable ledOffDrawable
          leds(3) imageDrawable ledOffDrawable
        case 2 =>
          leds(0) imageDrawable ledOffDrawable
          leds(1) imageDrawable ledOnDrawable
          leds(3) imageDrawable ledOffDrawable
          leds(3) imageDrawable ledOffDrawable
        case 3 =>
          leds(0) imageDrawable ledOnDrawable
          leds(1) imageDrawable ledOnDrawable
          leds(2) imageDrawable ledOffDrawable
          leds(3) imageDrawable ledOffDrawable
        case 4 =>
          leds(0) imageDrawable ledOffDrawable
          leds(1) imageDrawable ledOffDrawable
          leds(2) imageDrawable ledOnDrawable
          leds(3) imageDrawable ledOffDrawable
        case 5 =>
          leds(0) imageDrawable ledOnDrawable
          leds(1) imageDrawable ledOffDrawable
          leds(2) imageDrawable ledOnDrawable
          leds(3) imageDrawable ledOffDrawable
        case 6 =>
          leds(0) imageDrawable ledOffDrawable
          leds(1) imageDrawable ledOnDrawable
          leds(2) imageDrawable ledOnDrawable
          leds(3) imageDrawable ledOffDrawable
        case 7 =>
          leds(0) imageDrawable ledOnDrawable
          leds(1) imageDrawable ledOnDrawable
          leds(2) imageDrawable ledOnDrawable
          leds(3) imageDrawable ledOffDrawable
        case 8 =>
          leds(0) imageDrawable ledOffDrawable
          leds(1) imageDrawable ledOffDrawable
          leds(2) imageDrawable ledOffDrawable
          leds(3) imageDrawable ledOnDrawable
        case 9 =>
          leds(0) imageDrawable ledOnDrawable
          leds(1) imageDrawable ledOffDrawable
          leds(2) imageDrawable ledOffDrawable
          leds(3) imageDrawable ledOnDrawable
      }
      case List(_, _) => value match {
        case 0 =>
          leds(0) imageDrawable ledOffDrawable
          leds(1) imageDrawable ledOffDrawable
        case 1 =>
          leds(0) imageDrawable ledOnDrawable
          leds(1) imageDrawable ledOffDrawable
        case 2 =>
          leds(0) imageDrawable ledOffDrawable
          leds(1) imageDrawable ledOnDrawable
      }
    }
  }
}
