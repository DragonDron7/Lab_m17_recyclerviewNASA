package com.dronios777.nasa_photos.presentation

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dronios777.nasa_photos.data.FotoModel
import com.dronios777.nasa_photos.data.Repository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class MainViewModel : ViewModel() {

    private val repository = Repository()

    private val _photo = MutableStateFlow<List<FotoModel>>(emptyList()) //загруженный список фото
    val photo = _photo.asStateFlow()

    private val _isLoading = MutableStateFlow(false)// хранится состояние загрузки
    val isLoading = _isLoading.asStateFlow()


    init {
        loadPhoto()
    }

    private fun loadPhoto() {
        viewModelScope.launch(Dispatchers.IO) {
            kotlin.runCatching {
                _isLoading.value = true //идёт загрузка
                repository.getPhoto("2022-12-01")
            }.fold(
                onSuccess = { _photo.value = it.photos },
                onFailure = { Log.d("ttt", it.message ?: "") }
            )
            _isLoading.value = false //окончена загрузка

        }
    }

    fun refresh() {
        loadPhoto()
    }


}

