package com.example.trabajo_firebase.firebase.clases

data class Comentarios(
    var idComentario : String?,
    var comentario: String,
    var idAutor: String,
    var idNoticia: String,
    var autor : Int
){
    constructor() : this("","","","",0)
}
