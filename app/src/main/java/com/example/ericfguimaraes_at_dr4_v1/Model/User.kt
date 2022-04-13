package com.example.ericfguimaraes_at_dr4_v1.Model

import com.google.firebase.firestore.Exclude
import com.google.firebase.firestore.IgnoreExtraProperties


@IgnoreExtraProperties
data class User(
    val nome: String? = null,
    val sobrenome: String? = null,
    val email: String? = null,
    val senha: String? = null
) {
    @Exclude
    fun toMap(): Map<String, Any?> {
        return mapOf(
            "nome" to nome,
            "sobrenome" to sobrenome,
            "email" to email,
            "senha" to senha,
        )
    }
}