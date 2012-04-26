package models

import org.squeryl._
import org.squeryl.Schema


class BarDbObject extends KeyedEntity[Long] {
  val id: Long = 0
}

case class Bar(val name: String) extends BarDbObject {
  //override def toString = { "\"name\":\"%s\"".format(name) }
}

object BarDb extends Schema {
  val bars = table[Bar]("BAR")

}