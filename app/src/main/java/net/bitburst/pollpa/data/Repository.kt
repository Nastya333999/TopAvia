package net.bitburst.pollpa.data

interface Repository {

    fun getUrl():String?

    fun saveUrl(url: String)

    fun exists(): Boolean
}