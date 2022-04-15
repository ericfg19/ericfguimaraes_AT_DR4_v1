package com.example.ericfguimaraes_at_dr4_v1

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.ericfguimaraes_at_dr4_v1.Model.Anotacao
import com.example.ericfguimaraes_at_dr4_v1.databinding.ActivityHomeBinding
import com.example.ericfguimaraes_at_dr4_v1.databinding.ActivityLoginBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.auth.User
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.firestore.ktx.toObject
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.activity_home.*


class HomeActivity : AppCompatActivity() {

    lateinit var auth: FirebaseAuth

    lateinit var binding: ActivityHomeBinding

    object MemoryDatabase {
        val anotacaoList  = arrayListOf<Anotacao>()

    }

    var adapter:ListaRcy? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        binding = ActivityHomeBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        auth = Firebase.auth
        configuracao()

        //recyclerview
        //adapter = ListaRcy(MemoryDatabase.anotacaoList)
        //rcyHome.adapter = adapter
        rcyHome.adapter = ListaRcy(MemoryDatabase.anotacaoList)



    }

    override fun onStart() {
        super.onStart()
        val currentUser = auth.currentUser
        if (currentUser != null) {


            val nomeCollection = "User"
            val db = Firebase.firestore
            val retorno = db.collection(nomeCollection)
                .document(auth.currentUser?.uid.toString()).get()
                .addOnSuccessListener {
                    var users = it["nome"]
                    binding.txtUsuario.setText(it["nome"].toString())
                }
        } else {
            var intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)

        }


    }

    fun configuracao() {
        configuraBotao()

    }

    private fun configuraBotao() {
        binding.btnLogout.setOnClickListener {
            Firebase.auth.signOut()
            var intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }

        binding.btnNovaAnotacao.setOnClickListener {

            var intent = Intent(this, AnotaActivity::class.java)
            startActivity(intent)

        }
    }

    override fun onResume() {
        super.onResume()
        //adapter?.notifyDataSetChanged()
    }
}