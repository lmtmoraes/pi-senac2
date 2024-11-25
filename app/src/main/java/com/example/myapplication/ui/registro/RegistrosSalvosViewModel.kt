package com.example.myapplication.ui.registro

import android.app.Application
import com.example.myapplication.ui.utils.BaseViewModel
import com.hadilq.liveevent.LiveEvent

class RegistrosSalvosViewModel (application: Application) : BaseViewModel(application) {

    val editClicked = LiveEvent<Unit>()
    val removeClicked = LiveEvent<Unit>()

    fun onEditClicked() {
        editClicked.postValue(Unit)
    }

    fun onRemoveClicked(){
        removeClicked.postValue(Unit)

    }
}