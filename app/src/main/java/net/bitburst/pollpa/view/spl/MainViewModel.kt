package net.bitburst.pollpa.view.spl

import android.app.Activity
import android.app.Application
import android.util.Log
import androidx.lifecycle.viewModelScope
import com.appsflyer.AppsFlyerLib
import com.facebook.applinks.AppLinkData
import com.google.android.gms.ads.identifier.AdvertisingIdClient
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import net.bitburst.pollpa.*
import net.bitburst.pollpa.data.Repository
import net.bitburst.pollpa.data.RepositoryImpl
import net.bitburst.pollpa.view.loading.LoadingActivity
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine

class MainViewModel(
    private val app: Application,
    private val repository: Repository
) : BaseViewModel(app) {

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

            if (repository.exists()) {
                _d.emit(MainState.NavigateToWeb(repository.getUrl()!!))
            } else {
                val apps = gAF(activity = activity)
                val deep = deepFlow(activity = activity)

                _d.emit(MainState.FBState("Init step 1"))

                val adId = AdvertisingIdClient.getAdvertisingIdInfo(activity).id.toString()
                val uId = AppsFlyerLib.getInstance().getAppsFlyerUID(activity)!!
                _d.emit(MainState.FBState("Init step 2"))

                WrO(app, adId).send(apps?.get("campaign").toString(), deep)

                val url = UrlBuilder.create(
                    res = app.resources,
                    gadid = adId,
                    apps = if (deep == "null") apps else null,
                    deep = deep,
                    uid = if (deep == "null") uId else null
                )

                Log.e("URLSSS", "$url")
                _d.emit(MainState.NavigateToWeb(url))
            }
        }
    }


    private suspend fun gAF(activity: LoadingActivity): MutableMap<String, Any>? =
        suspendCoroutine { coroutine ->

            val callback = object : AW {

                override fun onConversionDataSuccess(convData: MutableMap<String, Any>?) {
                    coroutine.resume(convData)
                }

                override fun onConversionDataFail(p0: String?) {
                    coroutine.resume(null)
                }
            }
            AppsFlyerLib.getInstance().init("dLTnhryimVfxMsvHLrwsu6", callback, activity)
            AppsFlyerLib.getInstance().start(activity)
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
