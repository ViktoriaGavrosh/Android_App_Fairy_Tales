package com.viktoriagavrosh.home.fake

import com.viktoriagavrosh.repositories.ShelfRepository
import com.viktoriagavrosh.repositories.model.Retaining
import com.viktoriagavrosh.repositories.utils.RequestResult
import com.viktoriagavrosh.repositories.utils.ShelfGenre
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class FakeShelfRepository : ShelfRepository {

    private val fakeTales = FakeSource.fakeTales.toMutableList()
    private val fakeFolks = FakeSource.fakeFolks.toMutableList()
    private val fakeRiddles = FakeSource.fakeRiddles.toMutableList()

    override fun getItems(genre: ShelfGenre): Flow<RequestResult<List<Retaining>>> {
        val items = when (genre) {
            ShelfGenre.Tales.Animal -> fakeTales.filter { it.genre == ShelfGenre.Tales.Animal }
            ShelfGenre.Tales.Fairy -> fakeTales.filter { it.genre == ShelfGenre.Tales.Fairy }
            ShelfGenre.Tales.People -> fakeTales.filter { it.genre == ShelfGenre.Tales.People }
            ShelfGenre.Nights -> fakeTales.filter { it.isNight }
            ShelfGenre.Favorites -> fakeTales.filter { it.isFavorite }
            ShelfGenre.Folks.Poem -> fakeFolks.filter { it.genre == ShelfGenre.Folks.Poem }
            ShelfGenre.Folks.Counting -> fakeFolks.filter { it.genre == ShelfGenre.Folks.Counting }
            ShelfGenre.Folks.Lullaby -> fakeFolks.filter { it.genre == ShelfGenre.Folks.Lullaby }
            ShelfGenre.Riddles -> fakeRiddles
        }
        return flow { emit(RequestResult.Success(items)) }
    }

    override suspend fun updateFavoriteTale(id: Int, isFavorite: Boolean) {
        fakeTales[id] = fakeTales[id].copy(isFavorite = isFavorite)
    }
}
