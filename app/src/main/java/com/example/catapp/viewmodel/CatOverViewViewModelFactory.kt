package com.example.catapp.viewmodel

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.room.Room
import com.example.catapp.repository.CatRepository
import com.example.catapp.service.CatAPI
import database.AppDatabase

class CatOverViewViewModelFactory(private val app: Application) : ViewModelProvider.AndroidViewModelFactory(app) {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        val webService = CatAPI.getInstance()

        val db = Room.databaseBuilder(
            app,
            AppDatabase::class.java, "cat-database"
        ).build()

        val repository = CatRepository(db.catImageDao(),webService)

        return CatOverviewViewModel(repository = repository) as T
    }
}