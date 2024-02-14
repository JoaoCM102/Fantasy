package com.example.trabajo_firebase.Música
import android.content.ContentResolver
import android.content.Context
import android.content.res.Resources
import android.net.Uri
import androidx.annotation.AnyRes
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.media3.common.MediaItem
import androidx.media3.common.Player
import androidx.media3.exoplayer.ExoPlayer
import com.example.trabajo_firebase.R
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch

// Data class para representar una canción con su título, imagen y recurso de audio
data class Cancion(

    val rawResId: Int
)

// ViewModel para controlar el reproductor ExoPlayer
class ExoPlayerViewModel : ViewModel() {
    // Flujo mutable para almacenar el ExoPlayer actual
    private val _exoPlayer: MutableStateFlow<ExoPlayer?> = MutableStateFlow(null)
    // Flujo de solo lectura para el ExoPlayer
    val exoPlayer = _exoPlayer.asStateFlow()

    // Lista de canciones disponibles
    private val listaCanciones = listOf(

        Cancion(R.raw.uno),
        Cancion(R.raw.dos),
        Cancion(R.raw.tres),
        Cancion(R.raw.cuatro),
        Cancion(R.raw.cinco),
        Cancion(R.raw.seis),
        Cancion(R.raw.siete),
        Cancion(R.raw.ocho),
        Cancion(R.raw.nueve),
        Cancion(R.raw.diez),
        Cancion(R.raw.once),
        Cancion(R.raw.doce),
        Cancion(R.raw.trece),
        Cancion(R.raw.catorce),
        Cancion(R.raw.quince),
        Cancion(R.raw.dieciseis),
        Cancion(R.raw.diecisiete),
        Cancion(R.raw.dieciocho),
        Cancion(R.raw.diecinueve),
        Cancion(R.raw.veinte)

    )
    // Estado actual de la canción que se está reproduciendo
    private val _actual = MutableStateFlow(listaCanciones[0])
    val actual = _actual.asStateFlow()

    // Duración total de la canción actual
    private val _duracion = MutableStateFlow(0)
    val duracion = _duracion.asStateFlow()

    // Progreso de reproducción actual de la canción
    private val _progreso = MutableStateFlow(0)
    val progreso = _progreso.asStateFlow()

    // Estado de repetición de canciones
    private val _repetir = MutableStateFlow(false)
    val repetir = _repetir.asStateFlow()

    // Estado de reproducción aleatoria
    private val _random = MutableStateFlow(false)
    val random = _random.asStateFlow()

    // Índice de la canción actual en la lista de canciones
    private var currentSongIndex = 0

    // Método para crear el ExoPlayer
    fun crearExoPlayer(context: Context) {
        _exoPlayer.value = ExoPlayer.Builder(context).build()
        _exoPlayer.value!!.prepare()
        _exoPlayer.value!!.playWhenReady = true
    }
    fun cambiarVolumen(context: Context, volumen: Float){
        _exoPlayer.value?.volume = volumen
    }

    // Método para reproducir música
    fun hacerSonarMusica(context: Context) {
        val cancion = MediaItem.fromUri(obtenerRuta(context, _actual.value.rawResId))
        _exoPlayer.value!!.setMediaItem(cancion)
        _exoPlayer.value!!.playWhenReady = true

        _exoPlayer.value!!.addListener(object : Player.Listener {
            override fun onPlaybackStateChanged(playbackState: Int) {
                if (playbackState == Player.STATE_READY) {
                    _duracion.value = _exoPlayer.value!!.duration.toInt()

                    viewModelScope.launch {
                        while (isActive) {
                            _progreso.value = _exoPlayer.value!!.currentPosition.toInt()
                            delay(1000)
                        }
                    }
                } else if (playbackState == Player.STATE_ENDED) {
                    CambiarCancion(context)
                }
            }
        })
    }

    // Método para liberar recursos cuando se destruye el ViewModel
    override fun onCleared() {
        _exoPlayer.value!!.release()
        super.onCleared()
    }

    // Método para pausar o reanudar la reproducción de música
    fun PausarOSeguirMusica() {
        if (_exoPlayer.value!!.isPlaying) {
            _exoPlayer.value!!.pause()
        } else {
            _exoPlayer.value!!.play()
        }
    }

    // Método para cambiar la canción actual
    fun CambiarCancion(context: Context) {
        _exoPlayer.value?.stop()
        _exoPlayer.value?.clearMediaItems()

        if (repetir.value) {
            _exoPlayer.value?.setMediaItem(MediaItem.fromUri(obtenerRuta(context, _actual.value.rawResId)))
            _exoPlayer.value?.prepare()
            _exoPlayer.value?.playWhenReady = true
        } else {
            if (_random.value) {
                var randomIndex: Int
                do {
                    randomIndex = (listaCanciones.indices - currentSongIndex).random()
                } while (randomIndex == currentSongIndex)

                currentSongIndex = randomIndex
            } else {
                currentSongIndex = (currentSongIndex + 1) % listaCanciones.size
            }

            _actual.value = listaCanciones[currentSongIndex]

            _exoPlayer.value?.setMediaItem(MediaItem.fromUri(obtenerRuta(context, _actual.value.rawResId)))
            _exoPlayer.value?.prepare()
            _exoPlayer.value?.playWhenReady = true

            if (!repetir.value && currentSongIndex == listaCanciones.size - 1) {
                currentSongIndex = 0
            }
        }
    }

    // Método para alternar el modo de repetición de canciones
    fun toglearRepetir() {
        _repetir.value = !_repetir.value
    }

    // Método para alternar el modo de reproducción aleatoria
    fun toglearRandom() {
        _random.value = !_random.value
    }

    // Método para retroceder a la canción anterior
    fun retrocederCancion(context: Context) {
        _exoPlayer.value!!.stop()
        _exoPlayer.value!!.clearMediaItems()

        currentSongIndex = (currentSongIndex - 1 + listaCanciones.size) % listaCanciones.size
        _actual.value = listaCanciones[currentSongIndex]

        _exoPlayer.value!!.setMediaItem(MediaItem.fromUri(obtenerRuta(context, _actual.value.rawResId)))
        _exoPlayer.value!!.prepare()
        _exoPlayer.value!!.playWhenReady = true
    }

    // Método para ir a una posición específica de la canción actual
    fun irAPosicion(nuevaPosicion: Int) {
        _exoPlayer.value?.seekTo(nuevaPosicion.toLong())
    }

}

@Throws(Resources.NotFoundException::class)
fun obtenerRuta(context: Context, @AnyRes resId: Int): Uri {
    val res: Resources = context.resources
    return Uri.parse(
        ContentResolver.SCHEME_ANDROID_RESOURCE +
                "://" + res.getResourcePackageName(resId)
                + '/' + res.getResourceTypeName(resId)
                + '/' + res.getResourceEntryName(resId)
    )
}
