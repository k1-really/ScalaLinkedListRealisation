package org.example.linkedListRealisation.comparators

import org.example.linkedListRealisation.models.Point

import java.util.Comparator

class PointComparator extends Comparator[Point] {

  override def compare(o1: Point, o2: Point): Int = {
    val doubleValue1: Double = Math.sqrt(Math.pow(o1.getX, 2).toInt + Math.pow(o1.getY, 2).toInt);
    val doubleValue2: Double = Math.sqrt(Math.pow(o2.getX, 2).toInt + Math.pow(o2.getY, 2).toInt);
    val intValue: Integer = doubleValue1.compare(doubleValue2);
    intValue
  };
}
