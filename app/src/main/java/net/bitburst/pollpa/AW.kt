package net.bitburst.pollpa

import com.appsflyer.AppsFlyerConversionListener

interface AW : AppsFlyerConversionListener {
    override fun onAppOpenAttribution(p0: MutableMap<String, String>?) {}
    override fun onAttributionFailure(p0: String?) {}
}
