package com.gustavolins.wallet

import android.app.Application
import com.gustavolins.wallet.database.storage.StorageAccount
import com.gustavolins.wallet.database.storage.StorageOperation
import com.gustavolins.wallet.model.Operation
import dagger.hilt.android.HiltAndroidApp
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltAndroidApp
class Application : Application() {

    override fun onCreate( ) {
        super.onCreate()
    }

}