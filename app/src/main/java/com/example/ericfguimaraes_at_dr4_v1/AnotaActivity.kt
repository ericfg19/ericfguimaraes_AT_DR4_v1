package com.example.ericfguimaraes_at_dr4_v1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.ericfguimaraes_at_dr4_v1.Model.Anotacao
import com.example.ericfguimaraes_at_dr4_v1.databinding.ActivityAnotaBinding
import com.example.ericfguimaraes_at_dr4_v1.databinding.ActivityHomeBinding
import kotlinx.android.synthetic.main.activity_anota.*
import java.util.*

class AnotaActivity : AppCompatActivity() {

    lateinit var binding: ActivityAnotaBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_anota)

        btnSaveNote.setOnClickListener {
            HomeActivity.MemoryDatabase.anotacaoList.add(

                Anotacao(
                    data = Date(),
                    titulo = edtTitulo.text.toString(),
                    anotacao = edtAnota.text.toString()

                )
            )
            finish()


        }


    }
}