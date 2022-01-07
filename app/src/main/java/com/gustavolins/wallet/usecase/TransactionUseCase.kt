package com.gustavolins.wallet.usecase

import android.text.Editable
import androidx.room.IMultiInstanceInvalidationCallback
import com.gustavolins.wallet.database.storage.*
import com.gustavolins.wallet.model.AccountAndBalance
import com.gustavolins.wallet.model.Coin
import com.gustavolins.wallet.model.Transaction
import com.gustavolins.wallet.model.TransactionAndCoin
import com.gustavolins.wallet.repository.AppRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class TransactionUseCase @Inject
constructor(val storageAccount: StorageAccount,
            val storageBalance: StorageBalance,
            val storageTransaction: StorageTransaction,
            val storageCoin: StorageCoin,
            val appRepository: AppRepository
)  {


    suspend fun convertCoin(transactionAndCoin: TransactionAndCoin) {
        val fromCoin = transactionAndCoin.fromCoin!!
        val toCoin = transactionAndCoin.toCoin
        val fromValueToConvert = transactionAndCoin.transaction.value!!
        val valuefromCoin = (1 / fromCoin.currentQuote)
        val valuetoCoin = (1 / toCoin!!.currentQuote)
        var valueToConvert = (fromValueToConvert * valuetoCoin) / valuefromCoin

        storageBalance.updateBalance(fromCoin.coinId!!, -fromValueToConvert)
        storageBalance.updateBalance(toCoin?.coinId!!, valueToConvert)
        transactionAndCoin.transaction.valueTo = valueToConvert
        storageTransaction.inserTransaction(transactionAndCoin.transaction)

    }
     suspend fun prepareOperation(coin: Coin, operation: Long) : TransactionAndCoin{
        lateinit var transaction: Transaction
        lateinit var transactionAndBalance: TransactionAndCoin
        val account = storageAccount.getAccountAndBalance()
        if (operation == 1L){
            transaction = Transaction(null,account.account.accountId, operation, null, coin.coinId, account.account.coinDefaultId, null )
            transactionAndBalance = TransactionAndCoin(transaction, coin, account.coinDefault)
        }
        else if (operation == 2L){
            transaction = Transaction(null,account.account.accountId, operation, null, account.account.coinDefaultId, coin.coinId, null )
            transactionAndBalance = TransactionAndCoin(transaction, account.coinDefault, coin)
        }
        else  {
            transaction = Transaction(null,account.account.accountId, operation, null, coin.coinId, null, null )
            transactionAndBalance = TransactionAndCoin(transaction,  coin, null)
        }
        return transactionAndBalance
     }

    suspend fun runTransaction(transactionAndCoin: TransactionAndCoin) {
        convertCoin(transactionAndCoin)
    }

    suspend fun updateQuoteCoin(coin : Coin?) : Double? {
        if (coin?.coinId == 2L) {
            return updateQuoteBitcoin(coin)
        }
        if (coin?.coinId == 3L) {
            return updateQuoteBritas(coin)
        }
        return null
    }

    suspend fun updateQuoteBitcoin(coin : Coin?) : Double? {
        val response = appRepository.getBitcoinQuote()
        if (response.isSuccessful) {
            try {
                val quote = response.body()?.ticker?.last?.toDouble()
                storageCoin.update(quote , coin?.coinId)
                return quote
            } catch (nfe: NumberFormatException) {
                return null
            }
        }
        return null
    }

    suspend fun updateQuoteBritas(coin : Coin?) : Double? {
        val response = appRepository.getBritasQuote()
        if (response.isSuccessful) {
            try {
                val quote = response.body()?.usdbrl?.ask?.toDouble()
                storageCoin.update(quote , coin?.coinId)
                return quote
            } catch (nfe: NumberFormatException) {
                return null
            }
        }
        return null
    }

    fun validateValue(editable: Editable, fromCoinBalance : Double) : Boolean {
        try {
            val parsedDouble = editable.toString().toDouble()
            return !(editable.toString().isEmpty()
                    || parsedDouble > fromCoinBalance
                    || parsedDouble ==0.0)
        } catch (nfe: NumberFormatException) {
            return  false
        }
    }
}