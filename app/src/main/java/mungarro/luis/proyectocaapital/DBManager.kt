package mungarro.luis.proyectocaapital

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.database.sqlite.SQLiteQueryBuilder

class DBManager {
    var sqlDB: SQLiteDatabase?=null
    var dbName= "Recetas"
    var dbTabla="Receta"
    //Receta
    var colidReceta="idReceta"
    var colNombre="Nombre"
    var colDesc="Descripcion"
    var colTipo="Tipo"
    //Pasos
    var dbTabla3="Pasos"
    var colNumero="Numero"
    var colPaso="Paso"
    //Ingredientes
    var dbTabla2="Ingredientes"
    var colCantidad="Cantidad"
    var colWhat="Nombre"
    var dbVersion=1

    constructor(context: Context){
        var db=DataBaseHelperRecetas(context)
        sqlDB=db.writableDatabase
    }
    fun ingresarReceta(receta: ContentValues, ingredientes:ArrayList<ContentValues>, pasos:ArrayList<ContentValues>):Long{
        var id=sqlDB?.insert(dbTabla,null,receta)
        ingredientes.forEach {
            sqlDB?.insert(dbTabla3,null,it)
        }
        pasos.forEach {
            sqlDB?.insert(dbTabla2,null,it)
        }
        return id?:0
    }
    fun obtener(accion:Int,columnas:Array<String>,seleccion:String,seleccionArgs:Array<String>,ordenar:String): Cursor {
        val qb= SQLiteQueryBuilder()
        if (accion==1){
            qb.tables=dbTabla
            var cursor=qb.query(sqlDB,columnas,seleccion,seleccionArgs,null,null,ordenar)
            return cursor}
        if (accion==2){
            qb.tables=dbTabla2
            var cursor=qb.query(sqlDB,columnas,seleccion,seleccionArgs,null,null,ordenar)
            return cursor}
        if (accion==3){
            qb.tables=dbTabla3
            var cursor=qb.query(sqlDB,columnas,seleccion,seleccionArgs,null,null,ordenar)
            return cursor}
        var cursor: Cursor =qb.query(null,null,null,null,null,null,null)
        return cursor

    }
    fun eliminar(seleccion:String,seleccionArgs:Array<String>){
        sqlDB?.delete(dbTabla,seleccion,seleccionArgs)
    }
    inner class DataBaseHelperRecetas : SQLiteOpenHelper {
        var context : Context?=null
        constructor(context: Context):super(context,dbName,null,dbVersion){
            this.context=context
        }
        override fun onCreate(db: SQLiteDatabase?) {
            db?.execSQL("create table if not exists $dbTabla($colidReceta String,$colNombre TEXT, $colDesc TEXT,$colTipo TEXT);")
            db?.execSQL("create table if not exists $dbTabla3($colidReceta String,$colNumero INTEGER, $colPaso TEXT);")
            db?.execSQL("create table if not exists $dbTabla2($colidReceta String,$colWhat TEXT,$colCantidad TEXT );")

            var receta= ContentValues()
            var auxi= ContentValues()
            //Receta 1
            receta.put("idReceta","1")
            receta.put("Nombre","Pollo con verdura hervida")
            receta.put("Descripcion","Platillo tranquilon y facil de hacer")
            receta.put("Tipo","Rapido")
            db?.insert(dbTabla,null,receta)
            //Ingredientes 1
            auxi.put("idReceta","1")
            auxi.put("Nombre","Pechuga de pollo")
            auxi.put("Cantidad","1")
            db?.insert(dbTabla2,null,auxi)
            auxi.clear()
            auxi.put("idReceta","1")
            auxi.put("Nombre","Verduras")
            auxi.put("Cantidad","A eleccion")
            db?.insert(dbTabla2,null,auxi)
            auxi.clear()
            //Pasos 1
            auxi.put("idReceta","1")
            auxi.put("Numero",1)
            auxi.put("Paso","Desinfecta y hierve tus verduras")
            db?.insert(dbTabla3,null,auxi)
            auxi.clear()
            auxi.put("idReceta","1")
            auxi.put("Numero",2)
            auxi.put("Paso","Corta tu pollo en cubos y ponlo a freir en un poco de aceite por 4 minutos")
            db?.insert(dbTabla3,null,auxi)
            auxi.clear()
            auxi.put("idReceta","1")
            auxi.put("Numero",3)
            auxi.put("Paso","Agrega las verduras al sartén y cocinalo moviendo constantemete por 6 minutos a fuego medio")
            db?.insert(dbTabla3,null,auxi)
            auxi.clear()
            //Fin 1
            receta.clear()
            auxi.clear()


            //Receta 2
            receta.put("idReceta","2")
            receta.put("Nombre","Carne con brocoli y zanahoria")
            receta.put("Descripcion","Platillo nutritivo")
            receta.put("Tipo","Rapido")
            db?.insert(dbTabla,null,receta)
            //Ingredientes 2
            auxi.put("idReceta","2")
            auxi.put("Nombre","Carne de res")
            auxi.put("Cantidad","250 gr")
            db?.insert(dbTabla2,null,auxi)
            auxi.clear()
            auxi.put("idReceta","2")
            auxi.put("Nombre","Brocoli y zanahoria")
            auxi.put("Cantidad","A eleccion")
            db?.insert(dbTabla2,null,auxi)
            auxi.clear()
            //Pasos 2
            auxi.put("idReceta","2")
            auxi.put("Numero",1)
            auxi.put("Paso","Desinfecta y corta tu brocoli, zanahoria.")
            db?.insert(dbTabla3,null,auxi)
            auxi.clear()
            auxi.put("idReceta","2")
            auxi.put("Numero",2)
            auxi.put("Paso","Corta tu carne, brocoli y zanahorias.")
            db?.insert(dbTabla3,null,auxi)
            auxi.clear()
            auxi.put("idReceta","2")
            auxi.put("Numero",3)
            auxi.put("Paso","Mete todo en una sarte y revuelve ocasionalmente a fuego medio por 10 minutos.")
            db?.insert(dbTabla3,null,auxi)
            auxi.clear()
            //Fin 2
            receta.clear()
            auxi.clear()


            //Receta 3
            receta.put("idReceta","3")
            receta.put("Nombre","Sandwich de carne")
            receta.put("Descripcion","Platillo sencillo y rico para sacarte de un apuro")
            receta.put("Tipo","Rapido")
            db?.insert(dbTabla,null,receta)
            //Ingredientes 3
            auxi.put("idReceta","3")
            auxi.put("Nombre","Carne de res")
            auxi.put("Cantidad","250 gr")
            db?.insert(dbTabla2,null,auxi)
            auxi.clear()
            auxi.put("idReceta","3")
            auxi.put("Nombre","Papas")
            auxi.put("Cantidad","A eleccion")
            db?.insert(dbTabla2,null,auxi)
            auxi.clear()
            auxi.put("idReceta","3")
            auxi.put("Nombre","Pan molido y huevo")
            auxi.put("Cantidad","A eleccion")
            db?.insert(dbTabla2,null,auxi)
            auxi.clear()
            auxi.put("idReceta","3")
            auxi.put("Nombre","Aceite")
            auxi.put("Cantidad","A eleccion")
            db?.insert(dbTabla2,null,auxi)
            auxi.clear()
            //Pasos 3
            auxi.put("idReceta","3")
            auxi.put("Numero",1)
            auxi.put("Paso","Corta tu carne en piezas grandes y rompe bate huevo en un plato, también pon a calentar algo de aceite")
            db?.insert(dbTabla3,null,auxi)
            auxi.clear()
            auxi.put("idReceta","3")
            auxi.put("Numero",2)
            auxi.put("Paso","Una vez el aceite este caliente, pasa la carne por el huevo, luego al pan molido y friela por 5 minutos")
            db?.insert(dbTabla3,null,auxi)
            auxi.clear()
            auxi.put("idReceta","3")
            auxi.put("Numero",3)
            auxi.put("Paso","Una vez frita la carne, frié papas y pona la carne dentro de 2 panes.")
            db?.insert(dbTabla3,null,auxi)
            auxi.clear()
            //Fin 3
            receta.clear()
            auxi.clear()

//Receta 4
            receta.put("idReceta","4")
            receta.put("Nombre","Pollo con chipotle")
            receta.put("Descripcion","Plato creado por los dioses para el deleite de tu paladar")
            receta.put("Tipo","Estudiante")
            db?.insert(dbTabla,null,receta)
            //Ingredientes 4
            auxi.put("idReceta","4")
            auxi.put("Nombre","Pechuga de pollo")
            auxi.put("Cantidad","300 gr")
            db?.insert(dbTabla2,null,auxi)
            auxi.clear()
            auxi.put("idReceta","4")
            auxi.put("Nombre","Papas")
            auxi.put("Cantidad","A eleccion")
            db?.insert(dbTabla2,null,auxi)
            auxi.clear()
            auxi.put("idReceta","4")
            auxi.put("Nombre","Mantequilla")
            auxi.put("Cantidad","A eleccion")
            db?.insert(dbTabla2,null,auxi)
            auxi.clear()
            //Pasos 4
            auxi.put("idReceta","4")
            auxi.put("Numero",1)
            auxi.put("Paso","Licua el chipotle con un poco de media crema y pon a hervir las papas")
            db?.insert(dbTabla3,null,auxi)
            auxi.clear()
            auxi.put("idReceta","4")
            auxi.put("Numero",2)
            auxi.put("Paso","Pon el pollo a la plancha 4 minutos por lado y despues agrega la crema con chipotle, cocina por otros 5 minutos")
            db?.insert(dbTabla3,null,auxi)
            auxi.clear()
            auxi.put("idReceta","4")
            auxi.put("Numero",3)
            auxi.put("Paso","Smashea las papas, añadeles mantequilla y despues sirve con el pollo")
            db?.insert(dbTabla3,null,auxi)
            auxi.clear()
            //Fin 4
            receta.clear()
            auxi.clear()


            //Receta 5
            receta.put("idReceta","5")
            receta.put("Nombre","Pollo empanizado")
            receta.put("Descripcion","Como el que hace tu jefa")
            receta.put("Tipo","Estudiante")
            db?.insert(dbTabla,null,receta)
            //Ingredientes 5
            auxi.put("idReceta","5")
            auxi.put("Nombre","Pechuga de pollo")
            auxi.put("Cantidad","300 gr")
            db?.insert(dbTabla2,null,auxi)
            auxi.clear()
            auxi.put("idReceta","5")
            auxi.put("Nombre","Papas")
            auxi.put("Cantidad","A eleccion")
            db?.insert(dbTabla2,null,auxi)
            auxi.clear()
            auxi.put("idReceta","5")
            auxi.put("Nombre","Pan rayado, mantequilla y huevos")
            auxi.put("Cantidad","A eleccion")
            db?.insert(dbTabla2,null,auxi)
            auxi.clear()
            //Pasos 5
            auxi.put("idReceta","5")
            auxi.put("Numero",1)
            auxi.put("Paso","Pon a hervir las papas, bate un huevo y pon a calentar aceite")
            db?.insert(dbTabla3,null,auxi)
            auxi.clear()
            auxi.put("idReceta","5")
            auxi.put("Numero",2)
            auxi.put("Paso","Pasa la pechuga por el huevo, luego por el pan rayado y friela uniformemente por 5 minutos")
            db?.insert(dbTabla3,null,auxi)
            auxi.clear()
            auxi.put("idReceta","5")
            auxi.put("Numero",3)
            auxi.put("Paso","Smashea las papas, añadeles mantequilla y despues sirve con el pollo")
            db?.insert(dbTabla3,null,auxi)
            auxi.clear()
            //Fin 5
            receta.clear()
            auxi.clear()

            //Receta 6
            receta.put("idReceta","6")
            receta.put("Nombre","Spagetti")
            receta.put("Descripcion","Un insulto a los italianos")
            receta.put("Tipo","Estudiante")
            db?.insert(dbTabla,null,receta)
            //Ingredientes 6
            auxi.put("idReceta","6")
            auxi.put("Nombre","Spagetti de paquete")
            auxi.put("Cantidad","200 gr")
            db?.insert(dbTabla2,null,auxi)
            auxi.clear()
            auxi.put("idReceta","6")
            auxi.put("Nombre","Puré de tomate")
            auxi.put("Cantidad","400 gr")
            db?.insert(dbTabla2,null,auxi)
            auxi.clear()
            //Pasos 6
            auxi.put("idReceta","6")
            auxi.put("Numero",1)
            auxi.put("Paso","Pon los spagettis en agua hirviendo y cocinalos por 10 minutos a fuego medio")
            db?.insert(dbTabla3,null,auxi)
            auxi.clear()
            auxi.put("idReceta","6")
            auxi.put("Numero",2)
            auxi.put("Paso","En un sarten caliente vierte el pure de tomate y sazonalo con pimienta,sal y ajo")
            db?.insert(dbTabla3,null,auxi)
            auxi.clear()
            auxi.put("idReceta","6")
            auxi.put("Numero",3)
            auxi.put("Paso","Mezcla los spagettis cocinados con el pure y 'disfruta'")
            db?.insert(dbTabla3,null,auxi)
            auxi.clear()
            //Fin 6
            receta.clear()
            auxi.clear()

            //Receta 7
            receta.put("idReceta","7")
            receta.put("Nombre","Licuado con fresas")
            receta.put("Descripcion","Platillo smootheado por el mismisimo MJ")
            receta.put("Tipo","Postre")
            db?.insert(dbTabla,null,receta)
            //Ingredientes 7
            auxi.put("idReceta","7")
            auxi.put("Nombre","Fresas")
            auxi.put("Cantidad","Al gusto")
            db?.insert(dbTabla2,null,auxi)
            auxi.clear()
            auxi.put("idReceta","7")
            auxi.put("Nombre","Leche")
            auxi.put("Cantidad","300 ml")
            db?.insert(dbTabla2,null,auxi)
            auxi.clear()
            //Pasos 7
            auxi.put("idReceta","7")
            auxi.put("Numero",1)
            auxi.put("Paso","En una licuadora vierte leche, fresas al gusto y luego licualo xd")
            db?.insert(dbTabla3,null,auxi)
            //Fin 7
            receta.clear()
            auxi.clear()

            //Receta 8
            receta.put("idReceta","8")
            receta.put("Nombre","Pan tostado con mermelada")
            receta.put("Descripcion","Platillo by los sweets gods")
            receta.put("Tipo","Postre")
            db?.insert(dbTabla,null,receta)
            //Ingredientes 8
            auxi.put("idReceta","8")
            auxi.put("Nombre","Pan")
            auxi.put("Cantidad","Al gusto")
            db?.insert(dbTabla2,null,auxi)
            auxi.clear()
            auxi.put("idReceta","8")
            auxi.put("Nombre","Mermelada")
            auxi.put("Cantidad","Al gusto")
            db?.insert(dbTabla2,null,auxi)
            auxi.clear()
            //Pasos 8
            auxi.put("idReceta","8")
            auxi.put("Numero",1)
            auxi.put("Paso","Tuesta el pan y ponle mermelada xdd")
            db?.insert(dbTabla3,null,auxi)
            //Fin 8
            receta.clear()
            auxi.clear()

            //Receta 9
            receta.put("idReceta","9")
            receta.put("Nombre","Pan tostado con queso Cottage")
            receta.put("Descripcion","Platillo balanceado por la campana de Gauss")
            receta.put("Tipo","Postre")
            db?.insert(dbTabla,null,receta)
            //Ingredientes 9
            auxi.put("idReceta","9")
            auxi.put("Nombre","Pan")
            auxi.put("Cantidad","Al gusto")
            db?.insert(dbTabla2,null,auxi)
            auxi.clear()
            auxi.put("idReceta","9")
            auxi.put("Nombre","Queso cottage")
            auxi.put("Cantidad","Al gusto")
            db?.insert(dbTabla2,null,auxi)
            auxi.clear()
            //Pasos 9
            auxi.put("idReceta","9")
            auxi.put("Numero",1)
            auxi.put("Paso","Tuesta el pan y ponle queso <<deja vú>>")
            db?.insert(dbTabla3,null,auxi)
            //Fin 9
            receta.clear()
            auxi.clear()
        }

        override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
            db?.execSQL("drop table if exists $dbTabla")
            db?.execSQL("drop table if exists $dbTabla3")
            db?.execSQL("drop table if exists $dbTabla2")
        }

    }
}