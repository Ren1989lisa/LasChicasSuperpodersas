package com.example.saltasalta.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.saltasalta.data.api.ApiService
import com.example.saltasalta.data.models.ScoreRequest
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch

class GameViewModel(
    private val api: ApiService,
    private val userIdProvider: () -> Int
) : ViewModel() {

    // Referencia opcional al GameView para restart directo
    var gameViewRef: com.example.saltasalta.ui.game.GameView? = null

    private val _tiltFlow = MutableSharedFlow<Float>(extraBufferCapacity = 1)
    val tiltFlow = _tiltFlow.asSharedFlow()

    fun onTilt(value: Float) {
        _tiltFlow.tryEmit(value)
        gameViewRef?.setTilt(value)
    }

    fun sendScore(score: Int) {
        viewModelScope.launch {
            try {
                val req = ScoreRequest(
                    usuario_id = userIdProvider(),
                    puntuacion = score
                )
                api.guardarPuntuacion(req)
            } catch (_: Exception) {
                // silenciar error de red; podrías loguearlo
            }
        }
    }

    fun restartGame() {
        gameViewRef?.startGame()
    }
}

