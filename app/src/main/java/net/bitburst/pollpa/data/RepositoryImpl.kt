package net.bitburst.pollpa.data

import android.app.Application
import net.bitburst.pollpa.GameFile

class RepositoryImpl (private val app: Application) : Repository {

    private var gameFile: GameFile = GameFile("game.data", app)

    override fun getUrl(): String {
        return gameFile.readData()
    }

    override fun saveUrl(url: String) {
        if (url.isNotEmpty() && !url.contains(GameFile.BASE_URL) && !gameFile.exists()) {
            gameFile.writeData(url)
        }
    }

    override fun exists(): Boolean {
        return gameFile.exists()
    }
}