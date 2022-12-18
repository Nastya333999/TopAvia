package net.bitburst.pollpa

import android.content.res.Resources
import okhttp3.HttpUrl
import java.util.*


class UB {
    companion object {
        fun create(
            res: Resources,
            gadid: String,
            apps: MutableMap<String, Any>?,
            deep: String,
            uid: String?
        ): String {
            var httpurl: HttpUrl = HttpUrl.Builder()
                .scheme("https")
                .host("cheesepasta.co")
                .addPathSegment("topavia.php")
                .addQueryParameter(
                    res.getString(R.string.agv),
                    res.getString(R.string.bdhdf)
                )
                .addQueryParameter(
                    res.getString(R.string.kfo),
                    TimeZone.getDefault().id
                )
                .addQueryParameter(
                    res.getString(R.string.dada),
                    gadid
                )
                .addQueryParameter(
                    res.getString(R.string.tata),
                    deep
                )
                .addQueryParameter(
                    res.getString(R.string.rere),
                    if (deep != "null") "deeplink" else apps?.get("media_source").toString()
                )
                .addQueryParameter(
                    res.getString(R.string.fefe),
                    uid
                )
                .addQueryParameter(
                    res.getString(R.string.tetet),
                    apps?.get("adset_id").toString()
                )
                .addQueryParameter(
                    res.getString(R.string.dvd),
                    apps?.get("campaign_id").toString()
                )
                .addQueryParameter(
                    res.getString(R.string.gagd),
                    apps?.get("campaign").toString()
                )
                .addQueryParameter(
                    res.getString(R.string.kflv),
                    apps?.get("adset").toString()
                )
                .addQueryParameter(
                    res.getString(R.string.rwgd),
                    apps?.get("adgroup").toString()
                )
                .addQueryParameter(
                    res.getString(R.string.qewgc),
                    apps?.get("orig_cost").toString()
                )
                .addQueryParameter(
                    res.getString(R.string.fvdc),
                    apps?.get("af_siteid").toString()
                )
                .build()

            return httpurl.toString()
        }
    }
}