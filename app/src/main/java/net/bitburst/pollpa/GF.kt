package net.bitburst.pollpa

import android.content.Context
import android.util.Log
import java.io.File

class GF(private val name: String, private val context: Context) {

    fun exists(): Boolean = File(context.filesDir, name).exists()

    fun readData() = context.openFileInput(name).bufferedReader().useLines { it.first() }

    fun wD(data: String) {
        if (!exists() && !data.contains(BAU)) {
            context.openFileOutput(name, Context.MODE_PRIVATE).use {
                it.write(data.toByteArray())
            }
        }
    }

    companion object {
        const val BU = "cheesepasta.co/"
        const val BAU = BU + "topavia.php"
    }
}