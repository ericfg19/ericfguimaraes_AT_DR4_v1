package com.example.ericfguimaraes_at_dr4_v1.viewmodel

import android.content.Intent
import android.util.Log
import androidx.core.content.ContextCompat.startActivity
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.ericfguimaraes_at_dr4_v1.HomeActivity
import com.example.ericfguimaraes_at_dr4_v1.Model.User
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
// renomear para loginViewModel
class MainViewModel:ViewModel() {
    private val _cadastrado = MutableLiveData<Int>(null)
    var cadastrado: LiveData<Int> = _cadastrado
    fun setCadastrado(cadastrado: Int){
        _cadastrado.postValue(cadastrado)
    }

    private var uid: String = ""
    private val _nome = MutableLiveData("")
    var nome: LiveData<String> = _nome
    fun setNome(nome: String) {
        _nome.postValue(nome)
    }

    private val _sobreNome = MutableLiveData("")
    var sobreNome: LiveData<String> = _sobreNome
    fun setSobreNome(sobreNome: String) {
        _sobreNome.postValue(sobreNome)
    }


    private val _email = MutableLiveData("")
    var email: LiveData<String> = _email
    fun setEmail(email: String) {
        _email.postValue(email)
    }

    private val _senha = MutableLiveData("")
    var senha: LiveData<String> = _senha
    fun setSenha(senha: String) {
        _senha.postValue(senha)
    }

    private val _confirmaSenha = MutableLiveData("")
    var confirmaSenha: LiveData<String> = _confirmaSenha
    fun setConfirmaSenha(confirmaSenha: String) {
        _confirmaSenha.postValue(confirmaSenha)
    }

    fun cadastroFireBase() {

        var auth = Firebase.auth
        auth.createUserWithEmailAndPassword(
            email.value.toString(),
            senha.value.toString()
        )
            .addOnCompleteListener() { task ->
                if (task.isSuccessful) {
                    val user = auth.currentUser
                    uid = user?.uid!!
                    cadastroFirestore()

                    Log.i("cadastro", "Sucesso ao cadastrar", task.exception)

                } else
                    Log.i("cadastro", "Falha ao cadastrar", task.exception)

            }
    }

    fun cadastroFirestore() {
        var registro = User(
            nome.value.toString(),
            sobreNome.value.toString(),
            email.value.toString(),
            senha.value.toString()

        )
        val nomeCollection = "User"
        val db = Firebase.firestore

        db.collection(nomeCollection)
            .document(uid)
            .set(registro)
            .addOnSuccessListener { document ->
                Log.i("Upload", "Document: Id: ${uid}")
                setCadastrado(1)
            }.addOnFailureListener {
                Log.i("Upload", it.message.toString())

            }
    }

    fun verificacaoDadosEndereco(): Boolean {
        return (nome.value?.length!! > 3 &&
                sobreNome.value?.length!! >= 2 &&
                email.value?.length!! > 2 &&
                senha.value?.length!! > 2 &&
                confirmaSenha.value?.length!! > 2
                )
    }
}