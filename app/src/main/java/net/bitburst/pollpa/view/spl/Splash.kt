package net.bitburst.pollpa.view.spl

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.Settings
import net.bitburst.pollpa.databinding.ActivityMainBinding
import net.bitburst.pollpa.view.G.GActivity
import net.bitburst.pollpa.view.loading.LoadingActivity
import net.bitburst.pollpa.view.wv.WActivity

class Splash : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        if (!iNA()) {
            val intet = Intent(this, GActivity::class.java)
            startActivity(intet)
            finish()
        } else {
            val intet = Intent(this, LoadingActivity::class.java)
            startActivity(intet)
            finish()
        }


    }

    fun iNA(): Boolean =
        Settings.Global.getString(
            this.contentResolver,
            Settings.Global.ADB_ENABLED
        ) != "1"


}