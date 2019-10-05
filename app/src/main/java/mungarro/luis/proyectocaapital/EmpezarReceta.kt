package mungarro.luis.proyectocaapital

import android.app.Activity
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.LinearLayout
import android.widget.RadioButton
import kotlinx.android.synthetic.main.activity_empezar_receta.*
import android.widget.Toast
import mungarro.luis.proyectocaapital.Entidades.Receta


class EmpezarReceta : AppCompatActivity() {
var rec= Receta()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_empezar_receta)
        var bundle = intent.extras
        var id: Int = bundle.getInt("id")
        rec=bundle.getSerializable("Receta") as Receta

        RadiosView.removeAllViews()
        rec.pasos?.forEach {
         var radio=RadioButton(this)
            radio.layoutParams=LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT)
            radio.text="Paso "+it.numero+": "+it.what
            radio.textAlignment=LinearLayout.TEXT_ALIGNMENT_TEXT_START
            radio.layout(20,30,20,30)
            radio.setBackgroundResource(R.drawable.radio_botones)
            radio.setButtonDrawable(R.drawable.radioboton)
            radio.id=it.numero!!.toInt()
            RadiosView.addView(radio)
        }

        btn_terminarReceta.setOnClickListener{

            setResult(Activity.RESULT_OK)
            finish()
            val toast1 = Toast.makeText(
                applicationContext,
                "Buen provecho!!", Toast.LENGTH_SHORT
            )

            toast1.show()

        }

        btn_volverRecetas.setOnClickListener{
            setResult(Activity.RESULT_CANCELED)
            finish()
        }

    }
}
