package database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.catapp.model.CatImage

@Database(entities = [CatImage::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun catImageDao(): CatImageDao
}