package com.linchausen.netssdkproguardbug

import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.activity.result.ActivityResultLauncher
import eu.nets.pia.CardProcessError
import eu.nets.pia.PiaInterfaceConfiguration
import eu.nets.pia.PiaSDK
import eu.nets.pia.PiaSDK.Environment
import eu.nets.pia.ProcessResult
import eu.nets.pia.card.CardProcessActivityLauncherInput
import eu.nets.pia.card.CardProcessActivityResultContract
import eu.nets.pia.card.CardScheme
import eu.nets.pia.card.CardTokenizationRegistration
import eu.nets.pia.card.TransactionCallback
import eu.nets.pia.data.model.PiaLanguage
import eu.nets.pia.wallets.PaymentProcess
import java.util.Locale

class MainActivity : AppCompatActivity() {
    companion object {
        const val TAG = "MainActivity"
        val REDIRECT_URL: Uri = Uri.parse("https://svippr.no")

    }

    private val cardStorageActivityLauncher: ActivityResultLauncher<CardProcessActivityLauncherInput> =
        registerForActivityResult(
            CardProcessActivityResultContract.invoke(),
            this::transactionCompleteResult
        )

    private fun transactionCompleteResult(result: ProcessResult<CardProcessError>) {
        when (result) {
            is ProcessResult.Success -> {
                Log.i(TAG, "Success: ${result.transactionID}")
            }

            is ProcessResult.Cancellation -> {
                Log.i(TAG, "Cancellation")
            }

            is ProcessResult.Failure -> {
                Log.i(TAG, "Result from NETS SDK was not successful: ${result.processError}")
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        findViewById<Button>(R.id.button)?.setOnClickListener {

            // set the SDK configuration related to CardIo at runtime
            PiaInterfaceConfiguration.getInstance().apply {
                isDisableSaveCardOption = true
                piaLanguage = PiaLanguage.ENGLISH
            }

            val cardRegistration = PaymentProcess.cardTokenization(
                android.util.Pair(BuildConfig.NETAXEPT_MERCHANT_ID, Environment.TEST),
                emptySet(),
                object : CardTokenizationRegistration {
                    override fun registerPayment(callbackWithTransaction: TransactionCallback) {
                        callbackWithTransaction.successWithTransactionIDAndRedirectURL(
                            BuildConfig.TRANSACTION_ID,
                            REDIRECT_URL
                        )
                    }
                }
            )

            PiaSDK.startCardProcessActivity(
                cardStorageActivityLauncher,
                cardRegistration, /* isCVCRequired */
                true
            )
        }
    }
}