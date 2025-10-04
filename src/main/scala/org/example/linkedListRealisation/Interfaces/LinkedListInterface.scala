package org.example.linkedListRealisation.Interfaces

import scala.reflect.ClassTag

trait LinkedListInterface[T] {
  def add(value: T): Boolean
  def addAtIndex(value: T, id: Int): Boolean
  def remove(value: T): Boolean
  def removeAtIndex(id: Int): Boolean
  def findById(id: Int): Option[T]
  def printList(): Unit
}
