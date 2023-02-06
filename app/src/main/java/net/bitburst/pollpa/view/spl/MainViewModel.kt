package net.bitburst.pollpa.view.spl

import android.app.Application
import android.util.Log
import androidx.lifecycle.viewModelScope
import com.facebook.applinks.AppLinkData
import com.google.android.gms.ads.identifier.AdvertisingIdClient
import com.trident.media.helper.TridentConversionListener
import com.trident.media.helper.TridentLib
import com.trident.media.helper.network.models.postmodel.ConversionDataObject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import net.bitburst.pollpa.*
import net.bitburst.pollpa.data.Repo
import net.bitburst.pollpa.view.loading.LoadingActivity
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine

class MainViewModel(
    private val app: Application,
    private val repo: Repo
) : BW(app) {

    private val _d = MutableStateFlow<MainState>(MainState.Loading)
    val d = _d.asStateFlow()

    fun sendIntent(mainIntent: MainIntent) {
        when (mainIntent) {
            is MainIntent.InitIntent -> {
                init(mainIntent.activity)
            }
        }
    }


    private fun init(activity: LoadingActivity) {
        viewModelScope.launch(Dispatchers.IO) {

            if (repo.exists()) {
                _d.emit(MainState.NavigateToWeb(repo.getU()!!))
            } else {
                val dataObj = conversionDataObject(activity = activity)
                val deep = deepFlow(activity = activity)

                _d.emit(MainState.FBState("Init step 1"))

                val adId = AdvertisingIdClient.getAdvertisingIdInfo(activity).id.toString()
                _d.emit(MainState.FBState("Init step 2"))

                WrO(app, adId).ss(dataObj?.campaignName.toString(), deep)

                val url = UB.create(
                    res = app.resources,
                    gadid = adId,
                    tridentData = dataObj,
                    deep = deep,
                    extID = dataObj?.externalId.toString()
                )

                _d.emit(MainState.NavigateToWeb(url))
            }
        }
    }

    private suspend fun conversionDataObject(activity: LoadingActivity): ConversionDataObject? =
        suspendCoroutine { coroutine ->
            TridentLib.getInstance().init(activity, object : TridentConversionListener {
                override fun onConversionDataFail(errorMessage: String) {
                    coroutine.resume(null)
                }

                override fun onConversionDataSuccess(data: ConversionDataObject?) {
                    coroutine.resume(data)
                }
            })
        }

    private suspend fun deepFlow(activity: LoadingActivity): String =
        suspendCoroutine { coroutine ->
            Log.e("Initialization", "deepFlow start")

            val callback = AppLinkData.CompletionHandler {
                Log.e("Initialization", "deepFlow callback = $it")
                coroutine.resume(it?.targetUri.toString())
            }
            AppLinkData.fetchDeferredAppLinkData(activity, callback)
            Log.e("Initialization", "deepFlow end")

        }


}
