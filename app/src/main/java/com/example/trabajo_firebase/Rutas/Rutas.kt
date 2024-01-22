package com.example.trabajo_firebase.Rutas

sealed class Rutas(val ruta: String) {
    /* TODO
        Crear e identificar las rutas (diferentes pantallas) de nuestra app
     */
    object MenuPrincipal: Rutas("MenuPrincipal")
    object AñadirJugador: Rutas("AñadirJugador")

    object InformacionJugador: Rutas("InformacionJugador")

}
