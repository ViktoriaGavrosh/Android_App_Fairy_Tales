package com.viktoriagavrosh.ui.viewmodel

/*  TODO
class FakeFolkWorkRepository : FolkWorkRepository {

    private val fakeListFolkWork = FakeSource().fakeListFolkWork

    override fun getAllWorks(genre: String): Flow<List<Tale>> = flow {
        emit(fakeListFolkWork.filter { it.genre == genre })
    }

    override fun getAllFavoriteWorks(genre: String): Flow<List<Tale>> = flow {
        emit(fakeListFolkWork.filter { it.genre == genre && it.isFavorite })
    }

    override fun getWorkById(id: Int): Flow<Tale> = flow {
        emit(fakeListFolkWork.first { it.id == id })
    }

    override suspend fun updateFavoriteWork(id: Int, isFavorite: Boolean) {
        fakeListFolkWork[id] = fakeListFolkWork[id]
            .copy(isFavorite = isFavorite)
    }
}


 */
