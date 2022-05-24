package com.example.catapp.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.catapp.model.CatImage
import com.example.catapp.repository.CatRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class CatDetailViewModel(private val repository: CatRepository, id: String) : ViewModel() {
    private var _catResponse = MutableStateFlow<CatImage>(CatImage("", ""))
    val catResponse = _catResponse.asStateFlow()
    private var _errorMessage = MutableStateFlow("")
    val errorMessage = _errorMessage.asStateFlow()

    init {

        getCat(id)
    }

    private fun getCat(id : String) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val cat = repository.loadItemById(id)
                _catResponse.value = cat
            }
            catch (e: Exception) {
                _errorMessage.value = e.toString()
            }
        }
    }
}