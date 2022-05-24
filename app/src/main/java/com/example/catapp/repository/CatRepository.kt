package com.example.catapp.repository

import com.example.catapp.model.CatImage
import com.example.catapp.service.CatAPI
import database.CatImageDao
import kotlinx.coroutines.flow.flow

class CatRepository (

    private val catImageDao: CatImageDao,
    private val webservice: CatAPI
) {
    suspend fun loadItems(): List<CatImage> {
        // get from network
        val getFromNetwork = webservice.getCats()

        // save into local
        catImageDao.insertAll(getFromNetwork)

        //load from local
        return catImageDao.getAll()
    }

    suspend fun loadItemById(id : String): CatImage {

        return catImageDao.loadCatById(id)
    }

    fun loadNewItems() = flow {
        // get from network
        val getFromNetwork = webservice.getCats()

        // save into local
        catImageDao.insertAll(getFromNetwork)

        //load from local
        emit(catImageDao.getAll())
    }
}

