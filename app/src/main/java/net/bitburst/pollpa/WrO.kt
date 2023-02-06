package net.bitburst.pollpa

import android.content.Context
import com.onesignal.OneSignal

class WrO(context: Context, uid: String) {
    init {
        OneSignal.initWithContext(context)
        OneSignal.setAppId("345e5776-f30a-433b-8165-4e7e9dc1a125")
        OneSignal.setExternalUserId(uid)
    }

    fun ss(campaignName: String, deep: String) {
        if (deep != "null") {
            deep.replace("myapp://", "").substringBefore("/")
        } else if (campaignName != "null") {
            OneSignal.sendTag("key2", campaignName.substringBefore("_"))
        } else {
            OneSignal.sendTag("key2", "organic")
        }
    }
}