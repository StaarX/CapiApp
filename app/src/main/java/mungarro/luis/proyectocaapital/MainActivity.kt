package mungarro.luis.proyectocaapital

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import mungarro.luis.proyectocaapital.Entidades.Receta
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    var listaRecetas = ArrayList<Receta>()
    var mAuth: FirebaseAuth?=null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
mAuth= FirebaseAuth.getInstance()
        getWindow().setBackgroundDrawableResource(R.drawable.fondo)


        btn_Ingresar.setOnClickListener{
            ingresar()
        }

        btn_Registrar.setOnClickListener{
            val intent = Intent(this, Registrarse::class.java)
            startActivityForResult(intent, 123)
        }
    }
fun ingresar(){
    var correo=et_email.text.toString()
    var contra=et_pass.text.toString()
    if (!correo.isNullOrEmpty()&&!contra.isNullOrEmpty()){
        mAuth?.signInWithEmailAndPassword(correo,contra)
            ?.addOnCompleteListener { task->
               if (task.isSuccessful){
                   var intent=Intent(this,ListaRecetas::class.java)
                   intent.putExtra("usuario",correo)
                   startActivity(intent)
               }else{
                   Toast.makeText(this,"No pudo iniciarse la sesion", Toast.LENGTH_SHORT).show()
               }

            }
    }else{
        Toast.makeText(this,"Ingresa los datos por favor", Toast.LENGTH_SHORT).show()
    }
}

    override fun onStart() {
        super.onStart()
        var usuario=mAuth?.currentUser
        if (usuario!=null){
            var intent =Intent(this,ListaRecetas::class.java)
            intent.putExtra("usuario",usuario.email)
            startActivity(intent)
        }
    }

    override fun onRestart() {
        super.onRestart()
        mAuth?.signOut()
    }

}
