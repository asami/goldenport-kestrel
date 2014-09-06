package org.goldenport.kestrel

import org.scalatest.WordSpec
import org.scalatest.Matchers
import org.scalatest.junit.JUnitRunner
import org.junit.runner.RunWith

/*
 * @since   Sep.  6, 2014
 * @version Sep.  6, 2014
 * @author  ASAMI, Tomoharu
 */
@RunWith(classOf[JUnitRunner])
class KestrelSpec extends WordSpec with Matchers {
  "Kestrel" should {
    "a" in {
      import Kestrel._
      implicit val effect = KestrelEffect.PrintEffect
      "int value" @@@ 1
    }
    "b" in {
      import Kestrel._
      implicit val effect = KestrelEffect.PrintEffect
      val r = Vector(1, 2, 3).@@("pre values").map(_ + 1).@@("post values")
      r should be (Vector(2, 3, 4))
    }
    "b2" in {
      import Kestrel._
      implicit val effect = KestrelEffect.PrintEffect
      val r = Vector(1, 2, 3).@@.map(_ + 1).@@
      r should be (Vector(2, 3, 4))
    }
    "c" in {
      import scala.language.postfixOps
      import Kestrel._
      implicit val effect = KestrelEffect.PrintEffect
      def f(a: Int) = a + 1
      def g(b: Int) = b + 1
      val r = (f(1) |@> g _ @@)
      r should be (3)
    }
    "d" in {
      import scala.language.postfixOps
      import Kestrel._
      implicit val effect = KestrelEffect.PrintEffect
      def f(a: Int) = a + 1
      (f(1) @@) should be (2)
    }
    "d2" in {
      import Kestrel._
      implicit val effect = KestrelEffect.PrintEffect
      def f(a: Int) = a + 1
      (f(1).@@) should be (2)
    }
  }
}
