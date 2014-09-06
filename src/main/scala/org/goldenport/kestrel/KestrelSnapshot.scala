package org.goldenport.kestrel

/*
 * @since   Sep.  6, 2014
 * @version Sep.  6, 2014
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

  def last: Option[Snapshot] = {
    val r = snapshots.lastOption
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
}
