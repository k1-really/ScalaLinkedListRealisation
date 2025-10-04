package org.example.linkedListRealisation.userTypes

import org.example.linkedListRealisation.Interfaces.UserTypeInterface
import org.example.linkedListRealisation.comparators.{IntComparator, PointComparator}
import org.example.linkedListRealisation.models.Point

import java.util.Comparator

class PointClass extends UserTypeInterface with Cloneable {
  override def typeName = "Point"

 // override def create = new Point((Math.random * 100).toInt - 50, (Math.random * 100).toInt - 50)

  override def clone: AnyRef = try super.clone.asInstanceOf[PointClass]
  catch {
    case e: CloneNotSupportedException =>
      throw new RuntimeException(e)
  }

  override def readValueSer(point: AnyRef): String = {
    val pointToSer = point.asInstanceOf[Point]
    pointToSer.toString
  }

  override def parseValueDeser(str: String): AnyRef = {
    val len:Integer = str.length
    val substr:String = str.substring(1,len-1)
    val parameterOfPoint = substr.split(", ")
    if (parameterOfPoint.length == 2) {

      val x = parameterOfPoint(0).toInt
      val y = parameterOfPoint(1).toInt

      new Point(x, y)
    }
    else null
  }

  override def getTypeComparator():Comparator[_] = new PointComparator
}
