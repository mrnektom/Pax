package org.pax.messenger.service

import app.cash.sqldelight.coroutines.asFlow
import app.cash.sqldelight.coroutines.mapToOneOrNull
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import org.koin.core.annotation.Single
import org.pax.messenger.Account
import org.pax.messenger.Database

@Single
class AccountsService(
    database: Database,

    ) {
    @OptIn(DelicateCoroutinesApi::class)
    val activeAccount = database.accountQueries.findAll()
        .asFlow()
        .mapToOneOrNull(Dispatchers.Default)
        .stateIn(GlobalScope, SharingStarted.Lazily, null)

    @OptIn(DelicateCoroutinesApi::class)
    val isAuthorized = activeAccount.map { it != null }.stateIn(GlobalScope, SharingStarted.Lazily, true)



    suspend fun addAccount(account: Account) {

    }
}