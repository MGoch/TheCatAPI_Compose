package database

import androidx.room.*
import com.example.catapp.model.CatImage

@Dao
interface CatImageDao {

    @Query("SELECT * FROM cat_image_table")
    suspend fun getAll(): List<CatImage>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(cats: List<CatImage>)

    @Delete
    suspend fun delete(cat: CatImage)

    @Query("SELECT * FROM cat_image_table WHERE id=:id ")
    suspend fun loadCatById(id: String): CatImage
}