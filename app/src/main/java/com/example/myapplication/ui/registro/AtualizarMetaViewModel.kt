package com.example.myapplication.ui.registro

import android.app.Application
import com.example.myapplication.ui.utils.BaseViewModel
import com.hadilq.liveevent.LiveEvent

class AtualizarMetaViewModel(application: Application) : BaseViewModel(application) {

    val atualizaMetaClicked = LiveEvent<Unit>()

    fun onAtualizaMetaClicked() {
        atualizaMetaClicked.postValue(Unit)
    }
}