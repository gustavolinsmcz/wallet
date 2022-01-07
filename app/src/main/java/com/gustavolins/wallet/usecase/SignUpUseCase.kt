package com.gustavolins.wallet.usecase

import androidx.lifecycle.viewModelScope
import com.gustavolins.wallet.database.storage.*
import com.gustavolins.wallet.model.*
import kotlinx.coroutines.launch
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class SignUpUseCase @Inject
    constructor(val storageClient: StorageClient,
    val storageAccount: StorageAccount,
    val storageCoin: StorageCoin,
    val storageBalance: StorageBalance,
    val storageOperation: StorageOperation
)   {

    suspend fun signUp(client: Client) {
            val idNewClient = storageClient.inserClient(client)

            storageOperation.inserOperation(Operation(1, "Vender"));
            storageOperation.inserOperation(Operation(2, "Comprar"));
            storageOperation.inserOperation(Operation(3, "Converter"));

            var idNewDefault = storageCoin.insertCoin(Coin(1, "REAL", 1.0))
            val idNewAccount = storageAccount.insertAccount(Account(1, "1253-X", idNewClient, idNewDefault))
            storageBalance.inserBalance(Balance(null, idNewAccount, idNewDefault, 100000.00 ))

            var idNewCoin =  storageCoin.insertCoin(Coin(2, "BITCOIN", 260000.00))
            storageBalance.inserBalance(Balance(null, idNewAccount, idNewCoin, 0.0 ))

            idNewCoin =  storageCoin.insertCoin(Coin(3, "BRITAS", 5.45))
            storageBalance.inserBalance(Balance(null, idNewAccount, idNewCoin, 0.0 ))
    }

}