package com.sintel.cantiquesenmafa

import android.app.Activity
import android.app.SearchManager
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.media.session.MediaButtonReceiver.handleIntent

class SearchableActivity : Activity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        handleIntent(intent)
        //setContentView(R.layout.activity_searchable)
    }

    override fun onNewIntent(intent: Intent?) {
        super.onNewIntent(intent)
        intent?.let { handleIntent(it) }
    }

    private fun handleIntent(intent: Intent){
        if (Intent.ACTION_SEARCH==intent.action){
            val  query=intent.getStringArrayExtra(SearchManager.QUERY)
            //suite de la requete ici
        }
    }
}