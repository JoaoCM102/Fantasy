package com.example.trabajo_firebase.firebase.clases

data class Noticias(
    var id: String?,
    var idNoticia: String,
    var encabezado: String,
    var autor : String,
    var texto : String,
    var titulo : String
) {
    constructor() : this(null,"","", "", "", "")

    // Opcional: Función para convertir a un Map
    fun toMap(): Map<String, Any> {
        return mapOf(
            "autor" to autor,
            "idNoticia" to idNoticia,
            "encabezado" to encabezado,
            "texto" to texto,
            "titulo" to titulo,
        )
    }
}
