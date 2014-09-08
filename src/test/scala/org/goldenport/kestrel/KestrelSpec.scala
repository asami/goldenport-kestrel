package org.goldenport.kestrel

import org.scalatest.WordSpec
import org.scalatest.Matchers
import org.scalatest.junit.JUnitRunner
import org.junit.runner.RunWith

/*
 * @since   Sep.  6, 2014
 * @version Sep.  8, 2014
 * @author  ASAMI, Tomoharu
 */
@RunWith(classOf[JUnitRunner])
class KestrelSpec extends WordSpec with Matchers {
  "Kestrel" should {
    "@@@" in {
      import Kestrel._
      implicit val effect = KestrelEffect.PrintEffect
      "int value" @@@ 1
    }
    "@@ with label" in {
      import Kestrel._
      implicit val effect = KestrelEffect.PrintEffect
      val r = Vector(1, 2, 3).@@("pre values").map(_ + 1).@@("post values")
      r should be (Vector(2, 3, 4))
    }
    "@@ without label" in {
      import Kestrel._
      implicit val effect = KestrelEffect.PrintEffect
      val r = Vector(1, 2, 3).@@.map(_ + 1).@@
      r should be (Vector(2, 3, 4))
    }
    "@@>" in {
      import scala.language.postfixOps
      import Kestrel._
      implicit val effect = KestrelEffect.PrintEffect
      def f(a: Int) = a + 1
      def g(b: Int) = b + 1
      val r = (f(1) @@> g _ @@)
      r should be (3)
    }
    "@@ in last witout period" in {
      import scala.language.postfixOps
      import Kestrel._
      implicit val effect = KestrelEffect.PrintEffect
      def f(a: Int) = a + 1
      (f(1) @@) should be (2)
    }
    "@@ in last with period" in {
      import Kestrel._
      implicit val effect = KestrelEffect.PrintEffect
      def f(a: Int) = a + 1
      (f(1).@@) should be (2)
    }

    "SnapshotEffect" which {
      "a" in {
        import Kestrel._
        implicit val effect = KestrelEffect.SnapshotEffect
        def f(a: Int) = a + 1
        (f(1).@@) should be (2)
        KestrelSnapshot.last should be(2)
      }
    }
  }
}
