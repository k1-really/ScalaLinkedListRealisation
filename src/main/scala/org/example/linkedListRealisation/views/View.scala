package org.example.linkedListRealisation.views
import org.example.linkedListRealisation.Interfaces.UserTypeInterface
import org.example.linkedListRealisation.UserFactory
import org.example.linkedListRealisation.dataStructures.LinkedListRealisation
import scalafx.Includes._
import scalafx.event.ActionEvent
import scalafx.scene.control.{Button, Label, Menu, MenuBar, MenuItem, ScrollPane, Separator, TextField}
import scalafx.scene.layout.{AnchorPane, HBox, VBox}
import scalafx.scene.text.{Font, FontWeight}

import java.beans.EventHandler
import java.io.{File, FileInputStream, FileOutputStream, FileWriter, ObjectInputStream, ObjectOutputStream}
import java.lang.Exception
import scala.collection.mutable.ArrayBuffer
import scala.io.Source
import scala.util.control.Breaks.break
import scala.util.control.Exception

class View() extends AnchorPane {

  val userFactory:UserFactory = new UserFactory
  var currentUserType:UserTypeInterface = null
  var linkedListRealisation: LinkedListRealisation[AnyRef] = null
  var loadedType:String = null

  val menuBar = new MenuBar
  val typeMenu = new Menu("Type")


  menuBar.getMenus.add(typeMenu)
  // Buttons
  val saveBtn = new Button("Save") {
    layoutX = 500
    layoutY = 50
  }
  val loadBtn = new Button("Load") {
    layoutX = 500
    layoutY = 80
  }
  val sortBtn = new Button("Sort"){
    layoutX = 60
    layoutY = 80
  }
  val addToEndBtn = new Button("AddToEnd") {
    layoutX = 44
    layoutY = 355
  }
  val addByIndexBtn = new Button("AddByIndex") {
    layoutX = 200
    layoutY = 355
  }
  val removeByIndexBtn = new Button("RemoveByIndex") {
    layoutX = 338
    layoutY = 355
  }
  val findByIndexBtn = new Button("FindByIndex") {
    layoutX = 488
    layoutY = 355
  }

  // Text Fields
  val addToEndInput = new TextField {
    layoutX = 14
    layoutY = 301
    prefHeight = 26
    prefWidth = 132
  }
  val addByIndexInput = new TextField {
    layoutX = 174
    layoutY = 301
    prefHeight = 26
    prefWidth = 132
  }
  val addId = new TextField {
    layoutX = 257
    layoutY = 266
    prefHeight = 26
    prefWidth = 42
  }
  val removeId = new TextField {
    layoutX = 367
    layoutY = 266
    prefHeight = 26
    prefWidth = 42
  }
  val findId = new TextField {
    layoutX = 507
    layoutY = 266
    prefHeight = 26
    prefWidth = 42
  }

  // Labels
  val addToEndErrorLbl = new Label {
    layoutX = 20
    layoutY = 332
    prefHeight = 18
    prefWidth = 118
  }
  val addByIndexErrorLbl = new Label {
    layoutX = 181
    layoutY = 332
    prefHeight = 18
    prefWidth = 118
  }
  val removeByIndexErrorLbl = new Label {
    layoutX = 329
    layoutY = 332
    prefHeight = 18
    prefWidth = 118
  }
  val errorLoading = new Label("Невозможно прочитать данные из файла!") {
    layoutX = 180
    layoutY = 210
    prefHeight = 18
    prefWidth = 250
  }
  val findByIndexErrorLbl = new Label {
    layoutX = 469
    layoutY = 332
    prefHeight = 18
    prefWidth = 118
  }
  val valueForFind = new Label {
    layoutX = 477
    layoutY = 301
    prefHeight = 18
    prefWidth = 101
    visible = false
  }
  val vBox = new VBox {
         prefHeight = 150
         prefWidth = 444
       }

       children = List(vBox)
  // Scroll Pane
  val scrollPane = new ScrollPane {
    layoutX = 102
    layoutY = 58
    prefHeight = 150
    prefWidth = 393

    val contentPane = new AnchorPane {
      prefHeight = 150
      prefWidth = 376

      children = List(vBox)
    }
    content = contentPane
  }

  val separator1 = new Separator {
    layoutX = 163
    layoutY = 262
    orientation = scalafx.geometry.Orientation.VERTICAL
    prefHeight = 140
    prefWidth = 4
  }
  val separator2 = new Separator {
    layoutX = 313
    layoutY = 257
    orientation = scalafx.geometry.Orientation.VERTICAL
    prefHeight = 140
    prefWidth = 4
  }
  val separator3 = new Separator {
    layoutX = 457
    layoutY = 262
    orientation = scalafx.geometry.Orientation.VERTICAL
    prefHeight = 140
    prefWidth = 4
  }


  val clearBtn = new Button("Clear!") {
    layoutX = 252
    layoutY = 27
  }


  addToEndBtn.onAction = (e: ActionEvent) => {
    disableErrorLabels()
    val valueToAdd = addToEndInput.getText
    if (valueToAdd.isEmpty) {
      addToEndErrorLbl.setVisible(true)
      addToEndErrorLbl.setText("Wrong input!")
    }
    else {
      try{
        val obj = currentUserType.parseValueDeser(valueToAdd)

        if (obj != null) {
          linkedListRealisation.add(obj)
          drawList()
        }
      }
      catch{
        case x: Exception => {
          addToEndErrorLbl.setVisible(true)
          addToEndErrorLbl.setText("Неверный формат!")

          println("Wrong Type Format")
        }


      }

    }
  }

  addByIndexBtn.onAction = (e: ActionEvent) => {
    disableErrorLabels()
    val valueToAdd = addByIndexInput.getText
    val index = addId.getText
    if (valueToAdd.isEmpty || index.isEmpty) {
      addByIndexErrorLbl.setVisible(true)
      addByIndexErrorLbl.setText("Empty input!")
    }
    else {
      val obj = currentUserType.parseValueDeser(valueToAdd)
      linkedListRealisation.addAtIndex(obj,index.toInt)
      drawList()
      addByIndexErrorLbl.setVisible(false)
    }
  }

  removeByIndexBtn.onAction = (e: ActionEvent) => {
    disableErrorLabels()
    val indexToRemove = removeId.getText
    if (indexToRemove.isEmpty) {
      removeByIndexErrorLbl.setVisible(true)
      removeByIndexErrorLbl.setText("Empty input!")
    }
    else {
      linkedListRealisation.removeAtIndex(indexToRemove.toInt)
      drawList()
      addToEndErrorLbl.setVisible(false)
    }
  }

  findByIndexBtn.onAction = (e: ActionEvent) => {
    disableErrorLabels()
    val indexToFind = findId.getText
    if (indexToFind.isEmpty) {
      findByIndexErrorLbl.setVisible(true)
      findByIndexErrorLbl.setText("Empty input!")
    }
    else {
      val str = linkedListRealisation.findById(indexToFind.toInt).toString
      valueForFind.setVisible(true)
      valueForFind.setText(str)
      findByIndexErrorLbl.setVisible(false)
    }
  }

  clearBtn.onAction = (e: ActionEvent) => {
    this.linkedListRealisation.clear()
    drawList()
  }

  saveBtn.onAction = (e: ActionEvent) => {
    saveListToFile()
  }

  loadBtn.onAction = (e: ActionEvent) => {
    loadListFromFile()
  }

  sortBtn.onAction = (e: ActionEvent) => {
    this.linkedListRealisation = linkedListRealisation.sort(currentUserType.getTypeComparator);
    drawList()
  }

  children = List(
    menuBar,
    addToEndBtn,
    addByIndexBtn,
    removeByIndexBtn,
    findByIndexBtn,
    addToEndInput,
    addByIndexInput,
    addId,
    removeId,
    findId,
    addToEndErrorLbl,
    addByIndexErrorLbl,
    removeByIndexErrorLbl,
    findByIndexErrorLbl,
    valueForFind,
    scrollPane,
    separator1,
    separator2,
    separator3,
    clearBtn,
    saveBtn,
    loadBtn,
    errorLoading,
    sortBtn
  )

  def initialize(): Unit = {
    disableManagingBtn()
    showTypes()
    disableErrorLbl()

  }

  def drawList(): Unit = {
    vBox.getChildren.clear()
    val size = linkedListRealisation.getSize
    /*for (i <- 0 until size) {
      val str = currentUserType.readValueSer(linkedListRealisation.findById(i).get)+" "

      val label = new Label(str)
      label.setFont(Font.font("Roboto", FontWeight.EXTRA_BOLD, 24))
      vBox.getChildren.add(label)
    }*/

    linkedListRealisation.forEach(elem =>{
      val str = currentUserType.readValueSer(elem) + " "

      val label = new Label(str)
      label.setFont(Font.font("Roboto", FontWeight.EXTRA_BOLD, 24))
      vBox.getChildren.add(label)
    })
  }







  private def showTypes(): Unit = {
    var i = 0

    for (s <- userFactory.getTypeNameList) {
      val itemToAdd = new MenuItem(s)
      itemToAdd.setOnAction((event:ActionEvent)=>{
        linkedListRealisation = new LinkedListRealisation[AnyRef]
        //drawList()
        currentUserType = userFactory.getBuilderByName(itemToAdd.getText)


        typeMenu.getItems.stream().forEach(menuItem => menuItem.setDisable(false))

        itemToAdd.setDisable(true)
        enableManagingBtn()
      })
      itemToAdd.setId("item" + i)
      typeMenu.getItems.add(itemToAdd)
      i += 1
    }
  }

        def disableManagingBtn(): Unit = {
    addToEndBtn.setDisable(true)
    addByIndexBtn.setDisable(true)
    removeByIndexBtn.setDisable(true)
    findByIndexBtn.setDisable(true)
  }

  private def enableManagingBtn(): Unit = {
    addToEndBtn.setDisable(false)
    addByIndexBtn.setDisable(false)
    removeByIndexBtn.setDisable(false)
    findByIndexBtn.setDisable(false)
  }
  private def disableErrorLbl():Unit={
    errorLoading.setVisible(false)
  }

  private def disableErrorLabels(): Unit = {
    addToEndErrorLbl.setVisible(false)
    addByIndexErrorLbl.setVisible(false)
    removeByIndexErrorLbl.setVisible(false)
    findByIndexErrorLbl.setVisible(false)
  }



  def saveListToFile(): Unit = {

    val fileWriter = new FileWriter(new File("save.txt"))
    fileWriter.write(currentUserType.typeName()+"\n")
    for (i <- 0 until linkedListRealisation.getSize) {
      fileWriter.write(linkedListRealisation.findById(i).get.toString +"\n")
    }
    fileWriter.close()


  }

  def loadListFromFile(): Unit = {
    linkedListRealisation.clear();
  errorLoading.setVisible(false)
    val fileName = "save.txt"

    var i: Integer = 0
    var userType:UserTypeInterface = null
    for (line <- Source.fromFile(fileName).getLines()) {
      if(i==0) {
        loadedType = line
        if(loadedType != currentUserType.typeName()){
          errorLoading.setVisible(true)
          linkedListRealisation.clear()
          break()
        }
        userType = userFactory.getBuilderByName(loadedType)
      }
      else {
         linkedListRealisation.add(userType.parseValueDeser(line))
      }
      i += 1
    }

    drawList()
  }

  initialize()
}


