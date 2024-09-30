package com.viktoriagavrosh.repositories.fake

import com.viktoriagavrosh.database.TaleAppDatabase
import com.viktoriagavrosh.database.dao.FolkDao
import com.viktoriagavrosh.database.dao.RiddleDao
import com.viktoriagavrosh.database.dao.TaleDao

internal class FakeDb : TaleAppDatabase {
    override val folkDao: FolkDao = FakeFolkDao()
    override val taleDao: TaleDao = FakeTaleDao()
    override val riddleDao: RiddleDao = FakeRiddleDao()
}

