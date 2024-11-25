package com.example.myapplication.ui.menu

import android.app.Application
import androidx.lifecycle.ViewModel
import com.example.myapplication.ui.utils.BaseViewModel
import com.hadilq.liveevent.LiveEvent

class MenuViewModel(application: Application) : BaseViewModel(application) {

    // LiveData para cliques nos botões

    val consultarClicked = LiveEvent<Unit>()
    val registroDoDiaClicked = LiveEvent<Unit>()
    val atualizarFrequenciaClicked = LiveEvent<Unit>()


    // Métodos para tratar cliques dos botões
    fun onConsultarRegistrosClicked() {
        consultarClicked.postValue(Unit)
    }

    fun onRegistroDoDiaClicked() {
        registroDoDiaClicked.postValue(Unit)
    }

    fun onAtualizarFrequenciaClicked() {
        atualizarFrequenciaClicked.postValue(Unit)
    }
}
