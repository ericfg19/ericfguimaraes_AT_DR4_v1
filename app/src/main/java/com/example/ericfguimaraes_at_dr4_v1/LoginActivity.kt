package com.example.ericfguimaraes_at_dr4_v1

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.example.ericfguimaraes_at_dr4_v1.databinding.ActivityLoginBinding
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class LoginActivity : AppCompatActivity() {

    lateinit var auth: FirebaseAuth

    lateinit var binding: ActivityLoginBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        binding = ActivityLoginBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        auth = Firebase.auth
        configuracao()
    }

    override fun onStart() {
        super.onStart()
        val currentUser = auth.currentUser
        if (currentUser != null) {
            Log.i("Login", "usuario logado")
            Toast.makeText(
                this, "Usuário logado com Sucesso!",
                Toast.LENGTH_LONG
            ).show()
            var intent = Intent(this, HomeActivity::class.java)
            startActivity(intent)

        } else {
            Log.i("Login", "usuario não logado")
            Toast.makeText(
                this, "Usuário não logado!",
                Toast.LENGTH_LONG
            ).show()
        }

    }

    fun configuracao() {
        configuraBotao()

    }

    private fun configuraBotao() {
        binding.btnLogin.setOnClickListener {

            var auth = Firebase.auth
            auth.signInWithEmailAndPassword(
                binding.editTextEmail.text.toString(),
                binding.editTextSenha.text.toString()
            )
                .addOnCompleteListener(this){ task ->
                    if(task.isSuccessful){

                        //mensagem de sucesso
                    }
                    else{
                        Log.i("Login", task.exception.toString())

                    }
                }
        }


    }

    //renomear para onClickCadastro
    fun PaginaCadastro(view: View) {
        var intent = Intent(this, CadastrarActivity::class.java)
        startActivity(intent)
    }

}