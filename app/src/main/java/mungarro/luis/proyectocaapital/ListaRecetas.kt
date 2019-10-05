package mungarro.luis.proyectocaapital

import android.app.Activity
import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.Toast
import mungarro.luis.proyectocaapital.Entidades.Ingrediente
import mungarro.luis.proyectocaapital.Entidades.Pasos
import mungarro.luis.proyectocaapital.Entidades.Receta
import kotlinx.android.synthetic.main.activity_lista_recetas.*
import kotlinx.android.synthetic.main.recetas_view.view.*

class ListaRecetas : AppCompatActivity() {
    val PERMISO_CAMARA=234
    val CODIGO_CAMARA=222
    val PERMISO_STORAGE=111
    val CODIGO_STORAGE=110
    var quick = ArrayList<Receta>()
    var estudiante = ArrayList<Receta>()
    var postres = ArrayList<Receta>()



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lista_recetas)

llenarRecetas("%")



        var adaptador = AdaptadorReceta(this, quick)
        vistaListaOriginal.adapter = adaptador



        btn_platillosEstudiambre.setOnClickListener {



            var adaptador = AdaptadorReceta(this, estudiante)
            vistaListaOriginal.adapter = adaptador
            val transaccion = supportFragmentManager.beginTransaction()
            val fragmentoEstudiambre = Comida2()


            transaccion.replace(R.id.contenedor, fragmentoEstudiambre)

            transaccion.addToBackStack(null)

            transaccion.commit()
        }

        btn_platillosR.setOnClickListener {

            var adaptador = AdaptadorReceta(this, quick)
            vistaListaOriginal.adapter = adaptador
            val transaccion = supportFragmentManager.beginTransaction()
            val fragmentoComRapida = Comida1()


            transaccion.replace(R.id.contenedor, fragmentoComRapida)

            transaccion.addToBackStack(null)

            transaccion.commit()
        }

        btn_postres.setOnClickListener {


            var adaptador = AdaptadorReceta(this, postres)
            vistaListaOriginal.adapter = adaptador
            val transaccion = supportFragmentManager.beginTransaction()
            val fragmentoPostre = Comida3()


            transaccion.replace(R.id.contenedor, fragmentoPostre)

            transaccion.addToBackStack(null)

            transaccion.commit()
        }

        btn_cerrarSesion.setOnClickListener {
            finish()
        }


    }
    fun tomar_foto(){
        var camara = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        startActivityForResult(camara,CODIGO_CAMARA)
    }
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode==Activity.MODE_APPEND){
            val builder = AlertDialog.Builder(this)
            builder.setTitle("Foto")
            builder.setMessage("¿Deseas tomar una foto de lo que acabas de cocinar?")
            //builder.setPositiveButton("OK", DialogInterface.OnClickListener(function = x))

            builder.setPositiveButton(android.R.string.yes) { dialog, which ->
                if(Build.VERSION.SDK_INT>= Build.VERSION_CODES.M){
                    if (checkSelfPermission(android.Manifest.permission.CAMERA)== PackageManager.PERMISSION_DENIED){
                        //Permisos denegados
                        val permiso=arrayOf(android.Manifest.permission.CAMERA)
                        requestPermissions(permiso, PERMISO_CAMARA)

                    }else{
                        //Si tengo los permisos
                        tomar_foto()
                    }

                }else{
                    //La version es menor
                    tomar_foto()
                }
            }

            builder.setNegativeButton(android.R.string.no) { dialog, which ->
                Toast.makeText(applicationContext,
                    ":(", Toast.LENGTH_SHORT).show()
            }
            builder.show()
        }

    }
    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        when(requestCode){
            PERMISO_CAMARA->{
                if (grantResults.size>0 && grantResults[0]==PackageManager.PERMISSION_GRANTED){
                    tomar_foto()
                }else{
                    //No acepto los permisos
                    Toast.makeText(this, "Permisos denegados",Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
    fun llenarRecetas(titulo:String){
var db=DBManager(this)
        this.postres.clear()
        this.quick.clear()
        this.estudiante.clear()


        var columnas=arrayOf("idReceta","Nombre","Descripcion","Tipo")
        var columnasing=arrayOf("idReceta","Nombre","Cantidad")
        var columnaspas=arrayOf("idReceta","Numero","Paso")
        var seleccion=arrayOf(titulo)
        var cursor=db.obtener(1,columnas,"Nombre like ?",seleccion,"idReceta")


            if (cursor.moveToFirst()){
                do {
                    var pasos=ArrayList<Pasos>()
                    var ingredientes=ArrayList<Ingrediente>()
                    var idReceta=cursor.getString(cursor.getColumnIndex("idReceta"))
                    var nombre=cursor.getString(cursor.getColumnIndex("Nombre"))
                    var desc=cursor.getString(cursor.getColumnIndex("Descripcion"))
                    var tipo=cursor.getString(cursor.getColumnIndex("Tipo"))
                    var imagen:Int=0
                    if (idReceta.equals("1")){
                      imagen=R.drawable.pollo_verdura
                    }
                    if (idReceta.equals("2")){
                        imagen=R.drawable.carne_brocolizan
                    }
                    if (idReceta.equals("3")){
                        imagen=R.drawable.sanguich_carnita
                    }
                    if (idReceta.equals("4")){
                        imagen=R.drawable.pollo_chipotle
                    }
                    if (idReceta.equals("5")){
                        imagen=R.drawable.pollo_empanizado
                    }
                    if (idReceta.equals("6")){
                        imagen=R.drawable.spaguetti
                    }
                    if (idReceta.equals("7")){
                        imagen=R.drawable.licuado_fresa
                    }
                    if (idReceta.equals("8")){
                        imagen=R.drawable.pan_fresa
                    }
                    if (idReceta.equals("9")){
                        imagen=R.drawable.pan_quesito
                    }


                    var cursoring=db.obtener(2,columnasing,"idReceta = ?", arrayOf(idReceta),"idReceta")
                    var cursorpas=db.obtener(3,columnaspas,"idReceta = ?",arrayOf(idReceta),"idReceta")
                    if (cursorpas.moveToFirst()){
                        do {
                            pasos.add(Pasos(cursorpas.getInt(cursorpas.getColumnIndex("Numero")),cursorpas.getString(cursorpas.getColumnIndex("Paso"))))
                        }while (cursorpas.moveToNext())
                    }
                    if (cursoring.moveToFirst()){
                        do {
                            ingredientes.add(Ingrediente(cursoring.getString(cursoring.getColumnIndex("Nombre")),cursoring.getString(cursoring.getColumnIndex("Cantidad"))))
                        }while (cursoring.moveToNext())
                    }


                    if (tipo.equals("Postre")){
                        postres.add(Receta(nombre,desc,tipo,pasos,ingredientes,imagen))
                    }
                    if (tipo.equals("Estudiante")){
                        estudiante.add(Receta(nombre,desc,tipo,pasos,ingredientes,imagen))
                    }
                    if (tipo.equals("Rapido")){
                        quick.add(Receta(nombre,desc,tipo,pasos,ingredientes,imagen))
                    }
                }while (cursor.moveToNext())
            }


    }





    private class AdaptadorReceta : BaseAdapter {
        var context: Context? = null
        var recetas: ArrayList<Receta>? = null


        constructor(context: Context, recetas: ArrayList<Receta>) {
            this.context = context
            this.recetas = recetas
        }

        override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
            var layout = LayoutInflater.from(context)
            var vista = layout?.inflate(R.layout.recetas_view, null)!!
            var rec = recetas!![position]
            if (vista != null) {
                vista.tv_nombre.text = rec.nombre
                vista.tv_desc.text = rec.desc
                vista.comidaImagen.setImageResource(rec.imagen)
                vista.comidaImagen.setTag(Integer.valueOf(rec.imagen))
            }

            vista.setOnClickListener {
                val intent = Intent(context, Ingredientes::class.java)
                intent.putExtra("id", vista.comidaImagen.getTag() as Integer)
                intent.putExtra("Receta",recetas!![position])

                (context as Activity).startActivityForResult(intent, 123)
            }
            return vista
        }

        override fun getItem(position: Int): Any {
            return recetas?.get(position) ?: "Error"
        }

        override fun getItemId(position: Int): Long {
            return position.toLong()
        }

        override fun getCount(): Int {
            return recetas?.size ?: 0
        }


    }
}