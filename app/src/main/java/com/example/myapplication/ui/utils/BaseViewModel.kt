package com.example.myapplication.ui.utils

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

open class BaseViewModel(application: Application) : AndroidViewModel(application) {

    private val _valorSalvo =
        MutableLiveData("0") // Valor carregado das SharedPreferences
    val valorSalvo: LiveData<String?> = _valorSalvo

    private val _valorIngerido =
        MutableLiveData("0") // Valor carregado das SharedPreferences
    val valorIngerido: LiveData<String?> = _valorIngerido

    private val _data =
        MutableLiveData("") // Valor carregado das SharedPreferences
    val data: LiveData<String?> = _data

    fun salvarValor(data: String, valor: String, valorIngerido: String) {
        viewModelScope.launch {  // Use viewModelScope para garantir que a operação seja cancelada se a ViewModel for destruída
            val sharedPrefs = getApplication<Application>().getSharedPreferences(
                "MinhasPreferencias",
                Context.MODE_PRIVATE
            )
            with(sharedPrefs.edit()) {
                putString("valor", valor)
                putString("data", data)
                putString("valorIngerido", valorIngerido)
                apply()
            }
            _data.value = data
            _valorSalvo.value = valor
            _valorIngerido.value = valorIngerido
        }
    }


    protected val sharedPreferences: SharedPreferences = getApplication<Application>().getSharedPreferences(
        "MinhasPreferencias",
        Context.MODE_PRIVATE
    )

    protected fun saveValue(key: String, value: String) {
        with(sharedPreferences.edit()) {
            putString(key, value)
            apply()
        }
    }

    protected fun loadValue(key: String): String? {
        return sharedPreferences.getString(key, null)
    }

    fun carregarValorSalvo() {
        val sharedPrefs = getApplication<Application>().getSharedPreferences(
            "MinhasPreferencias",
            Context.MODE_PRIVATE
        )
        _data.value =
            sharedPrefs.getString("data", null)
        _valorSalvo.value =
            sharedPrefs.getString("valor", null)
        _valorIngerido.value =
            sharedPrefs.getString("valorIngerido", null)
    }
}