package org.goldenport.kestrel

import scala.language.implicitConversions

/*
 * http://stackoverflow.com/questions/9671620/how-to-keep-return-value-when-effectging-in-scala
 * 
 * @since   Sep.  6, 2014
 * @version Sep. 10, 2014
 * @author  ASAMI, Tomoharu
 */
object Kestrel {
//  def apply[A](x: A)(f: A => Unit): A = kestrel(x)(f)
  def kestrel[A](x: A)(f: A => Unit): A = { f(x); x }

  // implicit def stringToKestrel(
  //   label: String
  // )(implicit effect: KestrelEffect) = new {
  //   def @@@[T](exp: T): T = {
  //     effect.execute(label, exp)
  //     exp
  //   }
  // }

  implicit class KestralString[T](val label: String) extends AnyVal {
    def @@@[T](exp: T)(implicit effect: KestrelEffect): T = {
      effect.execute(label, exp)
      exp
    }
  }

  implicit class KestrelTap[T](val value: T) extends AnyVal {
    def tap(f: T => Unit): T = kestrel(value)(f)

    def @@(label: String)(implicit effect: KestrelEffect): T = {
      effect.execute(label, value)
      value
    }
 
    def @@(implicit effect: KestrelEffect): T = {
      effect.execute("MAP", value)
      value
    }

    def @@>[U](label: String)(f: T => U)(implicit effect: KestrelEffect): U = {
      val r = f(value)
      effect.execute(label, value, r)
      r
    }

    def @@>[U](f: T => U)(implicit effect: KestrelEffect): U = {
      val r = f(value)
      effect.execute("APPLY", value, r)
      r
    }

    def @@?(f: T => Boolean, g: String): T = {
      require (f(value), s"$g: $value")
      value
    }

    def @@??(g: T => String)(f: T => Boolean): T = {
      require (f(value), g)
      value
    }

    def @@@?: (f: T => Boolean, g: String): T = {
      assert (f(value), s"$g: $value")
      value
    }

    def @@@??: (g: T => String)(f: T => Boolean): T = {
      assert (f(value), s"$g: $value")
      value
    }

    // def @@@(label: String)(implicit effect: KestrelEffect) : T = {
    //   effect.execute(label, value)
    //   value
    // }

    // def @@@(f: T => String)(implicit effect: KestrelEffect) : T = {
    //   effect.execute(f(value), value)
    //   value
    // }

    // def |@=(label: String)(implicit effect: KestrelEffect): T = {
    //   effect.execute(label, value)
    //   value
    // }
 
    // def |@=(implicit effect: KestrelEffect): T = {
    //   effect.execute("MAP", value)
    //   value
    // }
  }
}
