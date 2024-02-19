package com.example.trabajo_firebase.firebase.clases

data class Users(
    var id: String?,
    var userId: String,
    var displayName: String,
    var avatarUrl: String,
    var numNoticias: Int,
    var valoracion: Float,
){
    constructor() : this(null, "", "", "", 0, 0.0f)
    fun toMap(): MutableMap<String,Any>{
        return mutableMapOf(
            "user_id" to this.userId,
            "displayName" to this.displayName,
            "avatarUrl" to this.avatarUrl,
            "numNoticias" to this.numNoticias,
            "valoracion" to this.valoracion
        )
    }
}
