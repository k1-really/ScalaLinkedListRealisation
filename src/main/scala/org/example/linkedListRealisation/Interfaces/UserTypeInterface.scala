package org.example.linkedListRealisation.Interfaces

import java.util.Comparator

trait UserTypeInterface {
  def typeName(): String

  def readValueSer(obj: AnyRef): String

  def parseValueDeser(ss: String): AnyRef

  def getTypeComparator: Comparator[_]
}

