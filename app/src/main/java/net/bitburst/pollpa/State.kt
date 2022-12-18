package net.bitburst.pollpa

import androidx.constraintlayout.motion.utils.ViewState
import net.bitburst.pollpa.view.loading.LoadingActivity

sealed class MainState : ViewState() {
    object Loading : MainState()
    data class AppsFlyerState(val title: String) : MainState()
    data class FBState(val title: String) : MainState()
    data class NavigateToWeb(val url: String) : MainState()
    object NavigateToGame : MainState()
}

sealed class MainIntent {
    data class InitIntent(val activity: LoadingActivity) : MainIntent()
}
