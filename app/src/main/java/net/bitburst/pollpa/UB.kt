package net.bitburst.pollpa

import android.content.res.Resources
import com.trident.media.helper.network.models.postmodel.ConversionDataObject
import okhttp3.HttpUrl
import java.util.*


class UB {
    companion object {
        fun create(
            res: Resources,
            gadid: String,
            tridentData: ConversionDataObject?,
            deep: String,
            extID: String
        ): String {
            var httpurl: HttpUrl = HttpUrl.Builder()
                .scheme("https")
                .host("cheesepasta.co")
                .addPathSegment("topavia.php")
                .addQueryParameter(res.getString(R.string.agv), res.getString(R.string.bdhdf))
                .addQueryParameter(res.getString(R.string.kfo), TimeZone.getDefault().id)
                .addQueryParameter(res.getString(R.string.dada), gadid)
                .addQueryParameter(res.getString(R.string.tata), deep)
                .addQueryParameter(res.getString(R.string.rere),
                    if (deep != "null") "deeplink" else tridentData?.source
                )
                .addQueryParameter(res.getString(R.string.fefe), extID)
                .addQueryParameter(res.getString(R.string.tetet), tridentData?.adEventId.toString())
                .addQueryParameter(res.getString(R.string.dvd), tridentData?.campaignId.toString())
                .addQueryParameter(res.getString(R.string.gagd), tridentData?.campaignName.toString())
                .addQueryParameter(res.getString(R.string.kflv), tridentData?.adType.toString())
                .addQueryParameter(res.getString(R.string.rwgd), tridentData?.adGroupName.toString())
                .addQueryParameter(res.getString(R.string.qewgc), "null")
                .addQueryParameter(res.getString(R.string.fvdc), tridentData?.networkType.toString())
                .build()

            return httpurl.toString()
        }
    }
}