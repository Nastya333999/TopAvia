package net.bitburst.pollpa.data

interface Repo {

    fun getU():String?

    fun saveU(url: String)

    fun exists(): Boolean
}