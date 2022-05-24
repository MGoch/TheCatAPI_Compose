package com.example.catapp

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.catapp.model.CatImage
import com.example.catapp.ui.theme.CatAppTheme
import com.example.catapp.viewmodel.CatDetailViewModel
import com.example.catapp.viewmodel.CatDetailViewModelFactory

class CatDetailActivity : ComponentActivity() {

    private val catDetailViewModel by viewModels<CatDetailViewModel>(factoryProducer = {
        val id = intent.getStringExtra("id") ?: throw IllegalArgumentException("Id not available")
        CatDetailViewModelFactory(application, id)
    })

    private val test by lazy{
        "Test"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            CatAppTheme {
                // A surface container using the 'background' color from the theme
                Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colors.background) {
                    val cat = catDetailViewModel.catResponse.collectAsState()
                    CatCard(cat = cat.value)
                }
            }
        }
    }
}

@Composable
fun CatCard(cat: CatImage) {
    Card(modifier = Modifier
        .padding(32.dp)
        ) {

        AsyncImage(model = cat.url, contentDescription = cat.id, modifier = Modifier.fillMaxSize())
    }
}
