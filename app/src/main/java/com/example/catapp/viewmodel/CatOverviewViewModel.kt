package com.example.catapp.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.catapp.model.CatImage
import com.example.catapp.repository.CatRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch

class CatOverviewViewModel(private val repository: CatRepository) : ViewModel() {
    private val _catListResponse = MutableStateFlow<List<CatImage>>(listOf())
    val catListResponse = _catListResponse.asStateFlow()
    private val _errorMessage = MutableStateFlow("")
    val errorMessage = _errorMessage.asStateFlow()

    init {
        getCatList()
    }

    private fun getCatList() {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                repository.loadNewItems().map {
                    it.size
                }.collect{

                }

            }
            catch (e: Exception) {
                _errorMessage.value = e.toString()
            }
        }
    }
}