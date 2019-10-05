package mungarro.luis.proyectocaapital

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_registrarse.*
import com.google.firebase.auth.FirebaseAuth


class Registrarse : AppCompatActivity() {
    var mAuth: FirebaseAuth? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registrarse)
        mAuth= FirebaseAuth.getInstance()

    btn_Registro.setOnClickListener{
        registrar()
    }

    }
    fun registrar(){
        var correo=et_correo.text.toString()
        var contra1=et_contra.text.toString()
        var contra2=et_contra2.text.toString()

        if (!correo.isNullOrEmpty()&&!contra1.isNullOrEmpty()&&!contra2.isNullOrEmpty()){
if (contra1==contra2){
firebaseSignup(correo,contra1)
}else{
    Toast.makeText(this,"Las contraseñas no son iguales", Toast.LENGTH_SHORT).show()
}
        }else{
            Toast.makeText(this,"Ingresa los datos por favor", Toast.LENGTH_SHORT).show()
        }
    }
    fun firebaseSignup(correo:String,contra:String){
        mAuth?.createUserWithEmailAndPassword(correo, contra)
            ?.addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    val user = mAuth?.getCurrentUser()
                    Toast.makeText(this, "${user?.email} se registró correctamente", Toast.LENGTH_SHORT).show()
                    finish()
                } else {

                    Toast.makeText(this, "Error en el registro", Toast.LENGTH_SHORT).show()
                }

                // ...
            }
    }
}
