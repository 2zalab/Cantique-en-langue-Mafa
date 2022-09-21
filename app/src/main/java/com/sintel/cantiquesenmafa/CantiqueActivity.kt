package com.sintel.cantiquesenmafa

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_cantique.*
import kotlinx.android.synthetic.main.activity_cantique.titre_cantique
import kotlinx.android.synthetic.main.cantique_adapter.*
import java.util.*

class CantiqueActivity : AppCompatActivity() {
    var size_titre = 22
    var size_parole = 17
    var nbre_zoom_in = 1
    var nbre_zoom_out = 1
   // var Textes_cantiqus= emptyArray<String>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cantique)

        setSupportActionBar(toolbar_cantique)
        supportActionBar!!.displayOptions = ActionBar.DISPLAY_SHOW_CUSTOM
        supportActionBar!!.setCustomView(R.layout.action_bar_layout_cantique)

        var sharedPreferences: SharedPreferences? = null
        var liste_favoris: Set<String> = HashSet()

        var numero:Int = intent.getIntExtra("position", 1)
        val Titre:Array<String> = resources.getStringArray(R.array.Titre_cantiques)
        val Textes_cantiques: Array<String> = resources.getStringArray(R.array.textes_cantiques)

        titre_cantique.setText(Titre[numero])
        parole_cantique.setText(Textes_cantiques[numero])


        sharedPreferences = getSharedPreferences("com.sintel.cantiquesenmafa", Context.MODE_PRIVATE)
        liste_favoris = sharedPreferences.getStringSet("liste_favoris", HashSet())!!


        if (liste_favoris.contains("" + numero)) {
            favorite.setImageDrawable(resources.getDrawable(R.drawable.ic_baseline_favorite_24))
        }

        favorite.setOnClickListener {
            if (liste_favoris.contains("" + numero)) {
                liste_favoris.remove("" + numero)
                favorite.setImageDrawable(resources.getDrawable(R.drawable.ic_baseline_favorite_border_24))
            } else {
                liste_favoris.add("" + numero)
                favorite.setImageDrawable(resources.getDrawable(R.drawable.ic_baseline_favorite_24))
            }
            val editor = sharedPreferences.edit()
            editor.putStringSet("liste_favoris", liste_favoris)
            editor.apply()
        }

        previuos.setOnClickListener(View.OnClickListener {
            if (numero > 0) {
                titre_cantique.setText(Titre[numero - 1])
                parole_cantique.setText(Textes_cantiques[numero-1])
                numero--
            } else{
                Toast.makeText(this, "Vous avez atteint le numero minimal", Toast.LENGTH_SHORT).show();
            }
            if (liste_favoris.contains("" + numero)) {
                favorite.setImageDrawable(resources.getDrawable(R.drawable.ic_baseline_favorite_24))
            } else {
                favorite.setImageDrawable(resources.getDrawable(R.drawable.ic_baseline_favorite_border_24))
            }
        })

        next.setOnClickListener {
            if (numero==151){
                Toast.makeText(this, "Vous avez atteint le numero maximal", Toast.LENGTH_SHORT).show();
            }
            else{
                titre_cantique.setText(Titre[numero + 1])
                parole_cantique.setText(Textes_cantiques[numero+1])
                numero++
            }


            if (liste_favoris.contains("" + numero)) {
                favorite.setImageDrawable(resources.getDrawable(R.drawable.ic_baseline_favorite_24))
            } else {
                favorite.setImageDrawable(resources.getDrawable(R.drawable.ic_baseline_favorite_border_24))
            }
        }


        id_ok.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View) {
                verifier_numero()
                if (liste_favoris.contains("" + numero)) {
                    favorite.setImageDrawable(resources.getDrawable(R.drawable.ic_baseline_favorite_24))
                } else {
                    favorite.setImageDrawable(resources.getDrawable(R.drawable.ic_baseline_favorite_border_24))
                }
            }

            private fun verifier_numero() {
                var num = 1
                num = try {
                    search.getText().toString().toInt()
                } catch (e: Exception) {
                    AlertDialog.Builder(this@CantiqueActivity)
                        .setTitle("Numero incorrect")
                        .setMessage("Entrer un numero compris entre 1 et 152")
                        .setCancelable(false)
                        .setPositiveButton(
                            "OK"
                        ) { dialog, which -> }.show()
                    return
                }
                if (0 < num && num <= 152 ) {
                    //Affichage de titre
                    titre_cantique.setText(Titre[num - 1])
                    parole_cantique.setText(Textes_cantiques[num-1])
                    search.setText("")
                    numero = num - 1
                }
                else {
                    AlertDialog.Builder(this@CantiqueActivity)
                        .setTitle("Numero incorrect")
                        .setMessage("Entrer un numero compris entre 1 et 152")
                        .setCancelable(false)
                        .setPositiveButton(
                            "OK"
                        ) { dialog, which -> }.show()
                }
            }
        })

        zoom_plus.setOnClickListener(View.OnClickListener {
            if (nbre_zoom_in <= 20) {
                titre_cantique.setTextSize(size_titre + 1.toFloat())
                parole_cantique.setTextSize(size_parole +1.toFloat())
                size_titre++
                size_parole++
            }
            nbre_zoom_in++
        })

        zoom_moins.setOnClickListener(View.OnClickListener {
            if (nbre_zoom_out <= nbre_zoom_in) {
                titre_cantique.setTextSize(size_titre - 1.toFloat())
                parole_cantique.setTextSize(size_parole - 1.toFloat())
                size_titre--
                size_parole--
            }
            nbre_zoom_in--
        })

    }
}