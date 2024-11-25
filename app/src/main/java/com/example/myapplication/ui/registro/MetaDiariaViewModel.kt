package com.example.myapplication.ui.registro

import android.app.Application
import com.example.myapplication.ui.utils.BaseViewModel
import com.hadilq.liveevent.LiveEvent

class MetaViewModel(application: Application) : BaseViewModel(application) {

    val salvarRegistroClicked = LiveEvent<Unit>()
    val quantidadeIngeridaClicked = LiveEvent<Unit>()
    val editarMetaClicked = LiveEvent<Unit>()

    fun onSalvarRegistroClicked() {
        salvarRegistroClicked.postValue(Unit)
    }

    fun onQuantidadeIngeridaClicked() {
        quantidadeIngeridaClicked.postValue(Unit)
    }

    fun onEditarMetaClicked() {
        editarMetaClicked.postValue(Unit)
    }
}