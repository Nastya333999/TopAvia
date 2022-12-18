package net.bitburst.pollpa.data

import android.app.Application
import net.bitburst.pollpa.GF

class RepoImpl (private val app: Application) : Repo {

    private var gameFile: GF = GF("game.data", app)

    override fun getU(): String {
        return gameFile.readData()
    }

    override fun saveU(url: String) {
        if (url.isNotEmpty() && !url.contains(GF.BU) && !gameFile.exists()) {
            gameFile.wD(url)
        }
    }

    override fun exists(): Boolean {
        return gameFile.exists()
    }
}