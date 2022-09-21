package com.sintel.cantiquesenmafa

import android.app.SearchManager
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.SearchView
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        toolbar.setTitle(R.string.mafa_name)
        setSupportActionBar(toolbar)

        val fragmentAdapter= PageAdapter(supportFragmentManager)
        viewpager.adapter=fragmentAdapter

        tableyout.setupWithViewPager(viewpager)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu,menu)

        //associate searchable configuration with searchview
        val searchManager = getSystemService(Context.SEARCH_SERVICE) as SearchManager
        (menu?.findItem(R.id.search)?.actionView as SearchView).apply {
            setSearchableInfo(searchManager.getSearchableInfo(componentName))
        }

        return true
    }



    fun share(item: MenuItem) {
        val shareIntent = Intent(Intent.ACTION_SEND)
        shareIntent.type = "text/plain"
        shareIntent.putExtra(Intent.EXTRA_TEXT, "")
        val app_url =
            "Télécharger le recueil de cantiques en langue mafa en cliquant sur le lien : https://play.google.com/store/apps/details?id=com.sintel.cantiquesenmafa"
        shareIntent.putExtra(Intent.EXTRA_TEXT, app_url)
        startActivity(Intent.createChooser(shareIntent, "Partager via"))
    }

    fun noter(item: MenuItem?) {
        val intent = Intent(Intent.ACTION_VIEW)
        intent.data =
            Uri.parse("https://play.google.com/store/apps/details?id=com.sintel.cantiquesenmafa")
        startActivity(intent)
    }

    fun quit(item: MenuItem?) {
        val alertDialogBuilder =
            AlertDialog.Builder(this@MainActivity)
        alertDialogBuilder.setTitle("Confirmer la fermeture !")
        alertDialogBuilder.setIcon(R.drawable.ic_baseline_warning_24)
        alertDialogBuilder.setMessage("Voulez-vous vraiment quitter?")
        alertDialogBuilder.setCancelable(false)
        alertDialogBuilder.setPositiveButton(
            "Oui"
        ) { dialog, which -> finish() }
        alertDialogBuilder.setNeutralButton(
            "Non"
        ) { dialog, which -> }
        val alertDialog = alertDialogBuilder.create()
        alertDialog.show()
    }


}