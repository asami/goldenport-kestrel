package org.goldenport.kestrel

/*
 * @since   Sep.  6, 2014
 * @version Sep.  8, 2014
 * @author  ASAMI, Tomoharu
 */
class KestrelSnapshot() {
  import KestrelSnapshot._
  var snapshots = Vector.empty[Snapshot]

  def add(label: String, value: Any) = {
    snapshots = snapshots :+ Snapshot(label, value, None)
  }

  def add(label: String, value: Any, value1: Any) = {
    snapshots = snapshots :+ Snapshot(label, value, Some(value1))
  }

  def last: Any = {
    val r = snapshots.lastOption.map(_.value) getOrElse {
      throw new IllegalStateException("No entry in snapshot.")
    }
    r // for debugger hook
  }

  def lastSnapshot: Option[Snapshot] = {
    val r = snapshots.lastOption
    r // for debugger hook
  }

  def lines: Seq[String] = {
    val r = snapshots.map { x =>
      x.value1 match {
        case Some(s) => s"${x.label}: ${x.value} => ${s}"
        case None => s"${x.label}: ${x.value}"
      }
    }
    r // for debugger hook
  }
}

object KestrelSnapshot {
  case class Snapshot(
    label: String,
    value: Any,
    value1: Option[Any]
  )

  val main = new KestrelSnapshot()

  def lastSnapshot: Option[Snapshot] = {
    val r = main.lastSnapshot
    r // for debugger hook
  }

  def last: Any = {
    val r = main.last
    r // for debugger hook
  }

  def lines: Seq[String] = {
    val r = main.lines
    r // for debugger hook
  }
}
