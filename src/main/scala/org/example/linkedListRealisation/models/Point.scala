package org.example.linkedListRealisation.models

class Point(var x:Integer, var y:Integer) extends Serializable {



  def setX(_x: Int) {
    x = _x;
  }

  def getX(): Int = x

  def setY(_y: Int) {
    y = _y;
  }

  def getY(): Int = y
  override def toString: String = "("+ x + ", " + y+")"

}

