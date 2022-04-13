package com.example.ericfguimaraes_at_dr4_v1

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.viewModels
import androidx.core.widget.doAfterTextChanged
import androidx.lifecycle.ViewModel
import com.example.ericfguimaraes_at_dr4_v1.databinding.ActivityCadastrarBinding
import com.example.ericfguimaraes_at_dr4_v1.databinding.ActivityLoginBinding
import com.example.ericfguimaraes_at_dr4_v1.viewmodel.MainViewModel
import com.google.firebase.auth.FirebaseAuth
import java.util.*

class CadastrarActivity : AppCompatActivity() {

    lateinit var auth: FirebaseAuth

    lateinit var binding: ActivityCadastrarBinding

    val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cadastrar)

        binding = ActivityCadastrarBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        configuracao()
    }

    fun configuracao() {
        configuraBotao()

        validarTexto()

    }

    private fun validarTexto() {
        var retorno = false

        binding.editTextSenha.doAfterTextChanged { it ->
            if (!it.isNullOrEmpty() && !it.isBlank()) {
                Log.i("Nome", "${it}")
                viewModel.setNome(it.toString())
                retorno = true
            } else {
                viewModel.setNome("")
                retorno = false
            }
        }

        binding.editTextSobrenome.doAfterTextChanged { it ->
            if (!it.isNullOrEmpty() && !it.isBlank()) {
                Log.i("Sobrenome", "${it}")
                viewModel.setSobreNome(it.toString())
                retorno = true
            } else {
                viewModel.setSobreNome("")
                Log.i("Sobrenome", "Vazio")
                retorno = false
            }
        }

        binding.editTextEmail.doAfterTextChanged { it ->
            if (!it.isNullOrEmpty() && !it.isBlank()) {
                Log.i("Email", "${it}")
                viewModel.setEmail(it.toString())
                retorno = true
            } else {
                viewModel.setEmail("")
                Log.i("Email", "Vazio")
                retorno = false
            }
        }

        binding.editSenha1.doAfterTextChanged { it ->
            if (!it.isNullOrEmpty() && !it.isBlank()) {
                Log.i("Senha", "${it}")
                viewModel.setSenha(it.toString())
                retorno = true
            } else {
                viewModel.setSenha("")
                Log.i("Senha", "Vazio")
                retorno = false
            }
        }


        binding.editSenha2.doAfterTextChanged { it ->
            if (!it.isNullOrEmpty() && !it.isBlank()) {
                Log.i("Confirmar Senha", "${it}")
                viewModel.setConfirmaSenha(it.toString())
                retorno = true
            } else {
                viewModel.setConfirmaSenha("")
                Log.i("Confirmar Senha", "Vazio")
                retorno = false
            }
        }

    }

    private fun configuraBotao() {
        binding.btnRegistro.setOnClickListener {
            validarTexto()
            if (viewModel.verificacaoDadosEndereco())
                viewModel.cadastroFireBase()

            viewModel.cadastrado.observe(this, {
                if (it != null)
                    when (it) {
                        1 -> {
                            var intent = Intent(this, LoginActivity::class.java)
                            startActivity(intent)
                            Toast.makeText(
                                this, "Usuário Cadastrado com Sucesso!",
                                Toast.LENGTH_LONG
                            ).show()
                        }

                    }

            })

        }

    }
}