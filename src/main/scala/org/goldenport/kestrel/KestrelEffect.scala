package org.goldenport.kestrel

/*
 * @since   Sep.  6, 2014
 * @version Sep.  6, 2014
 * @author  ASAMI, Tomoharu
 */
trait KestrelEffect {
  def execute(label: String, value: Any): Unit
  def execute(label: String, value1: Any, value2: Any): Unit
}

object KestrelEffect {
  object EmptyEffect extends KestrelEffect {
    def execute(label: String, value: Any) = Unit
    def execute(label: String, value1: Any, value2: Any) = Unit
  }

  object PrintEffect extends KestrelEffect {
    def execute(label: String, value: Any) {
      println(s"$label: $value")
    }

    def execute(label: String, value: Any, value1: Any) {
      println(s"$label: $value => $value1")
    }
  }

  object SnapshotEffect extends KestrelEffect {
    def execute(label: String, value: Any) = {
      KestrelSnapshot.main.add(label, value)
    }

    def execute(label: String, value: Any, value1: Any) = {
      KestrelSnapshot.main.add(label, value, value1)
    }
  }
}
