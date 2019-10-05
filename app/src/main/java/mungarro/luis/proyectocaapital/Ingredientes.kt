package mungarro.luis.proyectocaapital

import android.app.Activity
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.CheckBox
import android.widget.LinearLayout
import mungarro.luis.proyectocaapital.Entidades.Receta
import kotlinx.android.synthetic.main.activity_ingredientes.*

class Ingredientes : AppCompatActivity() {
var rec=Receta()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ingredientes)


        var bundle = intent.extras
        var id: Int = bundle.getInt("id")
        rec=bundle.getSerializable("Receta") as Receta
        txt_nombre.text = rec.nombre.toString()
        txt_descripcion.text =rec.desc.toString()
        ingredientesImagen.setImageResource(id)
        sugeridosView.removeAllViews()
        ingredienteView.removeViewAt(3)
        ingredienteView.removeViewAt(2)
        ingredienteView.removeViewAt(1)
        rec.ingredientes?.forEach {
            var chk=CheckBox(this)
            chk.layoutParams= LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT)
            chk.text=it.nombre.toString()+" - "+it.cantidad
            ingredienteView.addView(chk)
        }





        btn_empezarReceta.setOnClickListener{
            val intent = Intent(this, EmpezarReceta::class.java)
            intent.putExtra("Receta",rec)
            startActivityForResult(intent, 123)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode== Activity.RESULT_OK){
            setResult(Activity.MODE_APPEND)
            finish()
        }
    }
}
