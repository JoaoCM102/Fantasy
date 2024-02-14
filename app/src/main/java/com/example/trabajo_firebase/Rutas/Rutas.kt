package com.example.trabajo_firebase.Rutas

sealed class Rutas(val ruta: String) {
    /* TODO
        Crear e identificar las rutas (diferentes pantallas) de nuestra app
     */
    object MenuPrincipal: Rutas("MenuPrincipal")

    object MenuInicio: Rutas("MenuInicio")

    object Ajustes: Rutas("Ajustes")

    object InformacionJugador: Rutas("InformacionJugador")
    object CambiarContraseña: Rutas("CambiarContraseña")
    object Registro: Rutas("Registro")
    object Terminos:Rutas("Terminos")

}
