package org.example.linkedListRealisation.comparators

import java.util.Comparator

class IntComparator extends Comparator[Int] {
  override def compare(o1: Int, o2: Int): Int = o1.compare(o2)
}
