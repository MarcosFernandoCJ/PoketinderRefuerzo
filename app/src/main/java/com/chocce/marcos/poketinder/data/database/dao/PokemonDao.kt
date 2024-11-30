package com.chocce.marcos.poketinder.data.database.dao
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import com.chocce.marcos.poketinder.data.database.entities.MyPokemonEntity

@Dao
interface PokemonDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(pokemonEntity: MyPokemonEntity)

    @androidx.room.Query("SELECT * FROM pokemon_table")
    suspend fun getAllPokemons(): List<MyPokemonEntity>

    @androidx.room.Query("DELETE FROM pokemon_table")
    suspend fun deleteTable()
}
