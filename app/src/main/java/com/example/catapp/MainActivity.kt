package com.example.catapp

import android.app.ActivityOptions
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.Card
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import coil.annotation.ExperimentalCoilApi
import coil.compose.AsyncImage
import com.example.catapp.model.CatImage
import com.example.catapp.ui.theme.CatAppTheme
import com.example.catapp.viewmodel.CatOverViewViewModelFactory
import com.example.catapp.viewmodel.CatOverviewViewModel

class MainActivity : ComponentActivity() {
    private val catOverviewViewModel by viewModels<CatOverviewViewModel>(factoryProducer = { CatOverViewViewModelFactory(application) })

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
           CatAppTheme {
                // A surface container using the 'background' color from the theme
                Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colors.background) {
                    val catList = catOverviewViewModel.catListResponse.collectAsState()
                    CatList(catList = catList.value) { id ->
                        sendId(id)
                    }


                }
            }
        }
    }

    private fun sendId(id: String) {
        val intent = Intent(this, CatDetailActivity::class.java)
        intent.putExtra("id", id)
        startActivity(intent, ActivityOptions.makeSceneTransitionAnimation(this).toBundle())
    }

}

@Composable
fun CatList(catList: List<CatImage>, onCardPressed: (String) -> Unit) {
    LazyColumn {
        itemsIndexed(items = catList) { _, item ->
            CatItem(cat = item, onCardPressed)
        }
    }
}

@OptIn(ExperimentalCoilApi::class, ExperimentalMaterialApi::class)
@Composable
fun CatItem(cat: CatImage, onCardPressed: (String) -> Unit) {

    Card(onClick = {
        onCardPressed(cat.id)
    }, modifier = Modifier
        .padding(32.dp)
        .size(300.dp)) {

        AsyncImage(model = cat.url, contentDescription = cat.id, modifier = Modifier.fillMaxSize())
    }
}











