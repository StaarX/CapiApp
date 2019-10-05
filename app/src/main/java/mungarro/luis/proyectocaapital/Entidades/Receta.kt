package mungarro.luis.proyectocaapital.Entidades

import java.io.Serializable

data class Receta(var nombre: String,
                  var desc: String?= null,
                  var tipo: String?=null,
                  var pasos:ArrayList<Pasos>?=null,
                  var ingredientes:ArrayList<Ingrediente>?=null,
                  val imagen: Int):Serializable{
    constructor():this("",null,null,null,null,0)

}