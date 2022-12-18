package net.bitburst.pollpa.view.loading

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.animation.AnimationUtils
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import net.bitburst.pollpa.MainIntent
import net.bitburst.pollpa.MainState
import net.bitburst.pollpa.R
import net.bitburst.pollpa.databinding.ActivityLoadingBinding
import net.bitburst.pollpa.view.G.GActivity
import net.bitburst.pollpa.view.spl.MainViewModel
import net.bitburst.pollpa.view.wv.WActivity
import org.koin.androidx.viewmodel.ext.android.viewModel


class LoadingActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoadingBinding
    private val viewModel: MainViewModel by viewModel()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoadingBinding.inflate(layoutInflater)
        setContentView(binding.root)

        animate()
        initMvi()
        viewModel.sendIntent(MainIntent.InitIntent(this))

    }

    private fun initMvi() {
        lifecycleScope.launch {
            viewModel.d.collectLatest { state ->
                when (state) {
                    is MainState.Loading -> {

                    }
                    is MainState.FBState -> {
                        showTitle(state.title)
                    }
                    is MainState.AppsFlyerState -> {
                        showTitle(state.title)
                    }
                    is MainState.NavigateToWeb -> {
                        navigateToWeb(state.url)
                    }
                    is MainState.NavigateToGame -> {
                        navigateToGame()
                    }
                }
            }
        }
    }


    private fun showTitle(title: String) {
//        binding.txtText.text = title
    }

    private fun navigateToWeb(url: String) {
        if (url.isEmpty()) return

        Log.e("NAVIGATE", "url = $url")

        val intet = Intent(this, WActivity::class.java)
        intet.putExtra("web_url", url)
        startActivity(intet)
        finish()
    }

    private fun navigateToGame() {
        val intet = Intent(this, GActivity::class.java)
        startActivity(intet)
        finish()
    }


    private fun animate() {
        val animation = AnimationUtils.loadAnimation(
            applicationContext, R.anim.right_left
        )
        lifecycleScope.launch {
            binding.imgViewFirst.startAnimation(animation)
            binding.imgViewFour.startAnimation(animation)
            delay(3000)
            binding.imgViewSecond.startAnimation(animation)
            binding.imgViewFirstFirst.startAnimation(animation)
            delay(3000)
            binding.imgViewTherd.startAnimation(animation)
            binding.imgViewFive.startAnimation(animation)

        }
    }
}