package net.bitburst.pollpa

import android.content.res.Resources
import android.net.Uri.encode
import android.provider.Settings.Global.getString
import android.provider.Settings.Secure.getString
import android.provider.Settings.System.getString
import androidx.core.content.res.TypedArrayUtils.getString
import okhttp3.HttpUrl
import java.util.*


class UrlBuilder {
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
                    res.getString(R.string.secure_get_parametr),
                    res.getString(R.string.secure_key)
                )
                .addQueryParameter(
                    res.getString(R.string.dev_tmz_key),
                    TimeZone.getDefault().id
                )
                .addQueryParameter(
                    res.getString(R.string.gadid_key),
                    gadid
                )
                .addQueryParameter(
                    res.getString(R.string.deeplink_key),
                    deep
                )
                .addQueryParameter(
                    res.getString(R.string.source_key),
                    if (deep != "null") "deeplink" else apps?.get("media_source").toString()
                )
                .addQueryParameter(
                    res.getString(R.string.af_id_key),
                    uid
                )
                .addQueryParameter(
                    res.getString(R.string.adset_id_key),
                    apps?.get("adset_id").toString()
                )
                .addQueryParameter(
                    res.getString(R.string.campaign_id_key),
                    apps?.get("campaign_id").toString()
                )
                .addQueryParameter(
                    res.getString(R.string.app_campaign_key),
                    apps?.get("campaign").toString()
                )
                .addQueryParameter(
                    res.getString(R.string.adset_key),
                    apps?.get("adset").toString()
                )
                .addQueryParameter(
                    res.getString(R.string.adgroup_key),
                    apps?.get("adgroup").toString()
                )
                .addQueryParameter(
                    res.getString(R.string.orig_cost_key),
                    apps?.get("orig_cost").toString()
                )
                .addQueryParameter(
                    res.getString(R.string.af_siteid_key),
                    apps?.get("af_siteid").toString()
                )
                .build()

            return httpurl.toString()
        }
    }
}