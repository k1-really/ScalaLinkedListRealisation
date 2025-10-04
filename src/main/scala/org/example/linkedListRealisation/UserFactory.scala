package org.example.linkedListRealisation

import org.example.linkedListRealisation.Interfaces.UserTypeInterface
import org.example.linkedListRealisation.userTypes.{IntClass, PointClass}

import scala.Console.in





class UserFactory {
  private val list: List[UserTypeInterface] = List(new IntClass,new PointClass)


  def getTypeNameList: List[String] = {
    val typeNameList = list.map(x => x.typeName()).toList
    typeNameList
  }

  def getBuilderByName(name: String): UserTypeInterface = list.find(userType => userType.typeName().equals(name)).orNull
}
