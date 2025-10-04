package org.example.linkedListRealisation.dataStructures
import org.example.linkedListRealisation.Interfaces.LinkedListInterface
import scalafx.scene.input.KeyCombination.ModifierValue.Any

import java.util.Comparator
import java.util.function.Consumer
import scala.reflect.ClassTag

class LinkedListRealisation[T:ClassTag] extends LinkedListInterface[T] with Serializable {
  private class Node(var prev: Node, var next: Node, val value: T) extends Serializable

  private var head: Node = null
  private var tail: Node = null
  private  var size:Int = 0



  def clear():Unit = {
    head = null;
    tail = null;
    size = 0;
  }
  def getSize:Int = {
    size
  }
  def add(item: T): Boolean = {
    val newNode = new Node(null, null, item)
    if (head == null) {
      head = newNode
      tail = newNode
    } else {
      tail.next = newNode
      newNode.prev = tail
      tail = newNode
    }
    size+=1;
    true
  }

  def addAtIndex(item: T, index: Int): Boolean = {
    val newNode = new Node(null, null, item)
    if (index == 0) {
      newNode.next = head
      head.prev = newNode
      head = newNode
    } else {
      var current = head
      var i = 0
      while (i < index && current != null) {
        current = current.next
        i += 1
      }
      if (current != null) {
        newNode.next = current
        newNode.prev = current.prev
        current.prev.next = newNode
        current.prev = newNode
      } else {
        tail.next = newNode
        newNode.prev = tail
        tail = newNode
      }
    }
    size +=1
    true
  }

  def remove(item: T): Boolean = {
    var current = head
    while (current != null) {
      if (current.value == item) {
        if (current == head) {
          head = current.next
          if (head != null) head.prev = null
        }
        if (current == tail) {
          tail = current.prev
          if (tail != null) tail.next = null
        }
        if (current.prev != null) current.prev.next = current.next
        if (current.next != null) current.next.prev = current.prev
        size -=1
        return true
      }
      current = current.next
    }
    false
  }

  def removeAtIndex(index: Int): Boolean = {
    if (index == 0) {
      if (head != null) {
        head = head.next
        if (head != null) head.prev = null
      }
      size -=1
      true
    } else {
      var current = head
      var i = 0
      while (i < index && current != null) {
        current = current.next
        i += 1
      }
      if (current != null) {
        if (current == tail) {
          tail = current.prev
          if (tail != null) tail.next = null
        } else {
          current.prev.next = current.next
          current.next.prev = current.prev
        }
        size -=1
        true
      } else {
        false
      }
    }
  }

  def findById(id: Int): Option[T] = {
    var current = head
    var i = 0
    while (i < id && current != null) {
      current = current.next
      i += 1
    }
    if (current != null) {
      Some(current.value)
    } else {
      None
    }
  }

  def printList(): Unit = {
    var current = head
    while (current != null) {
      println(current.value)
      current = current.next
    }
  }

  def forEach(consumer: Consumer[T]): Unit = {
    var current = this.head
    while (current != null) {
      consumer.accept(current.value)
      current = current.next
    }
  }

  def foo[T:ClassTag](count: Int, value: T): Array[T] = Array.fill[T](count)(value)

  def toList(): List[T] = {
    var current = head
    var array:Array[T] = Array[T]()

    while (current != null) {
        array :+= current.value

      current = current.next
    }
    val list:List[T] = array.toList
    list
  }

  def convertListToLinkedListRealisation(list: List[T]):LinkedListRealisation[T] ={
    val linkedListRealisation:LinkedListRealisation[T] = new LinkedListRealisation[T]
    for(i<-list.indices){
      linkedListRealisation.add(list(i))
    }
    linkedListRealisation
  }


  def sort(comparator: Comparator[_]):LinkedListRealisation[T] ={
    val comparator2: Comparator[T] = comparator.asInstanceOf[Comparator[T]]
    var list:List[T] = toList()
    val sortedList:List[T]  = mergeSort(list:List[T], comparator2:Comparator[T])
    val linkedListRealisation:LinkedListRealisation[T] = convertListToLinkedListRealisation(sortedList)
    linkedListRealisation
  }


  private def merge(left: List[T], right: List[T],comparator: Comparator[T]): List[T] = (left, right) match {
    case (Nil, right) => right
    case (left, Nil) => left
    case (leftHead :: leftTail, rightHead :: rightTail) =>
      if (comparator.compare(leftHead, rightHead) == -1)
        leftHead :: merge(leftTail, right,comparator)
      else
        rightHead :: merge(left, rightTail,comparator)
  }

  private def mergeSort(arr: List[T],comparator: Comparator[T]): List[T] = {
    val n = arr.length
    if (n <= 1) arr
    else {
      val mid = n / 2
      val (left, right) = arr.splitAt(mid)
      merge(mergeSort(left,comparator), mergeSort(right,comparator),comparator)
    }
  }
}





