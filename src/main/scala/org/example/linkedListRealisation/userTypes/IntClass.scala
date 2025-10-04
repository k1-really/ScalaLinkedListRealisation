package org.example.linkedListRealisation.userTypes

import org.example.linkedListRealisation.Interfaces.UserTypeInterface
import org.example.linkedListRealisation.comparators.IntComparator

import java.util.Comparator

class IntClass extends UserTypeInterface {
  override def typeName = "Integer"

  //override def create: Int = (Math.random * 100).toInt - 50

  override def clone: AnyRef = try super.clone.asInstanceOf[IntClass]
  catch {
    case e: CloneNotSupportedException =>
      throw new RuntimeException(e)
  }

  override def readValueSer(intValue: AnyRef): String = intValue.toString

  override def parseValueDeser(str: String): Integer = str.toInt

  override def getTypeComparator():Comparator[_] = new IntComparator


}
