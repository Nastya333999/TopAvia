package net.bitburst.pollpa.view.G

import android.os.Bundle
import android.os.CountDownTimer
import android.widget.ImageView
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import net.bitburst.pollpa.ItemAdapter
import net.bitburst.pollpa.R
import net.bitburst.pollpa.databinding.ActivityGactivityBinding


class GActivity : AppCompatActivity() {
    private lateinit var binding: ActivityGactivityBinding
    private val viewModel: GViewModel by viewModels()
    private lateinit var firstImg: ImageView
    private lateinit var secondImg: ImageView
    private lateinit var theardImg: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityGactivityBinding.inflate(layoutInflater)
        setContentView(binding.root)

        firstImg = findViewById(R.id.imgFirst)
        secondImg = findViewById(R.id.imgSecond)
        theardImg = findViewById(R.id.imgThird)



        initAdapter()
        startTmr()
    }

    private fun initAdapter() {

        binding.itemsRVFirst.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        binding.itemsRVFirst.adapter = ItemAdapter(value = viewModel.setInitData(), onClick = {
            viewModel.imgClicked(it, 1)
        })

        viewModel.positionFirst.observe(this) { positionInt ->
            binding.itemsRVFirst.smoothScrollToPosition(positionInt)
        }

        binding.itemsRVSecond.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        binding.itemsRVSecond.adapter = ItemAdapter(value = viewModel.setInitData(), onClick = {
            viewModel.imgClicked(it, 2)
        })

        viewModel.positionSecond.observe(this) { positionInt ->
            binding.itemsRVSecond.smoothScrollToPosition(positionInt)
        }

        binding.itemsRVTherd.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        binding.itemsRVTherd.adapter = ItemAdapter(value = viewModel.setInitData(), onClick = {

            viewModel.imgClicked(it, 3)
        })

        viewModel.positionTherd.observe(this) { positionInt ->
            binding.itemsRVTherd.smoothScrollToPosition(positionInt)
        }


        viewModel.topImages.observe(this) {
            firstImg.setImageResource(it.firstDrawable)
            secondImg.setImageResource(it.secondDrawable)
            theardImg.setImageResource(it.therdDrawable)

            viewModel.topImages.value?.let {
                if (it.firstDrawable == it.secondDrawable &&
                    it.secondDrawable == it.therdDrawable) {
                    lifecycleScope.launch {
                        binding.txtText.text = "You win!!!"
                        delay(2000)
                        binding.txtText.text = "Click!!!"
                    }
                }
            }
        }

    }

    private fun startTmr() {
        object : CountDownTimer(5000000000, 500) {

            override fun onTick(millisUntilFinished: Long) {
                viewModel.addPosition()
            }

            override fun onFinish() {}
        }.start()
    }


}