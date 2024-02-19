package com.example.trabajo_firebase.firebase.clases

data class Noticias(
    var id: String?,
    var idNoticia: String,
    var autor : String,
    var texto : String,
    var titulo : String,
    var comentario : Map<String,String>
) {
    constructor() : this(null,"", "", "", "", emptyMap())

    // Opcional: Función para convertir a un Map
    fun toMap(): Map<String, Any> {
        return mapOf(
            "autor" to autor,
            "idNoticia" to idNoticia,
            "texto" to texto,
            "titulo" to titulo,
            "comentario" to comentario
        )
    }
}
