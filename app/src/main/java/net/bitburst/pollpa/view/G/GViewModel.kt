package net.bitburst.pollpa.view.G

import android.util.Log
import androidx.annotation.DrawableRes
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import net.bitburst.pollpa.GItem
import net.bitburst.pollpa.R

class GViewModel : ViewModel() {

    var positionFirst = MutableLiveData<Int>(0)
    var positionSecond = MutableLiveData<Int>(0)
    var positionTherd = MutableLiveData<Int>(0)

    var topImages = MutableLiveData(TopImages())


    fun addPosition() {
        positionFirst.value = positionFirst.value?.plus(3)
        positionSecond.value = positionSecond.value?.plus(4)
        positionTherd.value = positionTherd.value?.plus(2)

    }

    fun setInitData(): MutableList<GItem> {
        val allStates = mutableListOf<GItem>()

        allStates.add(GItem(R.drawable.q1, 1))
        allStates.add(GItem(R.drawable.q2, 2))
        allStates.add(GItem(R.drawable.q3, 3))
        allStates.add(GItem(R.drawable.q4, 4))
        allStates.add(GItem(R.drawable.q5, 5))

        allStates.shuffle()

        return allStates
    }

    fun imgClicked(item: GItem, position: Int) {
        when (position) {
            1 -> {
                topImages.value = topImages.value?.copy(firstDrawable = item.resId)
            }
            2 -> {
                topImages.value = topImages.value?.copy(secondDrawable = item.resId)
            }
            3 -> {
                topImages.value = topImages.value?.copy(therdDrawable = item.resId)
            }
        }
         topImages.value?.let {
             if (it.firstDrawable == it.secondDrawable &&
                     it.secondDrawable == it.therdDrawable) {
                 Log.e("WIIIIIN", "Ypu Win")
             }
         }
    }

    data class TopImages(
        @DrawableRes
        val firstDrawable: Int = R.drawable.start,

        @DrawableRes
        val secondDrawable: Int = R.drawable.start,

        @DrawableRes
        val therdDrawable: Int = R.drawable.start,
    )


}