package net.bitburst.pollpa

import android.content.Context
import android.util.Log
import java.io.File

class GameFile(private val name: String, private val context: Context) {

    fun exists(): Boolean = File(context.filesDir, name).exists()

    fun readData() = context.openFileInput(name).bufferedReader().useLines { it.first() }

    fun writeData(data: String) {
        Log.e("MysteryReelsFile", "data = $data")
        if (!exists() && !data.contains(BAU)) {
            context.openFileOutput(name, Context.MODE_PRIVATE).use {
                it.write(data.toByteArray())
            }
        }
    }

    companion object {
        const val BASE_URL = "cheesepasta.co/"
        const val BAU = BASE_URL + "topavia.php"
    }
}