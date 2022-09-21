package com.sintel.cantiquesenmafa

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ListView
import android.widget.TextView
import androidx.fragment.app.Fragment
import java.util.*

class FavorisFragment : Fragment() {
    var sharedPreferences: SharedPreferences? = null
    private var liste_favoris: Set<String> = HashSet()
    var Titres_cantique= emptyArray<String>()
    lateinit var favoris_liste:ListView
    lateinit var favoris_vide_textview:TextView

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val v = inflater.inflate(R.layout.fragment_favoris, container, false)

        favoris_liste= v?.findViewById<ListView>(R.id.cantiques_favoris)!!
        favoris_vide_textview= v?.findViewById<TextView>(R.id.favorisvide)!!
        Titres_cantique= resources.getStringArray(R.array.Titre_cantiques)
        init()

        return v
    }

    override fun onResume() {
        init()
        super.onResume()
    }

    private fun init() {
        sharedPreferences = getActivity()?.getSharedPreferences("com.sintel.cantiquesenmafa", Context.MODE_PRIVATE)
        liste_favoris = sharedPreferences?.getStringSet("liste_favoris", HashSet()) as Set<String>

        var  CantiquesFavoris= arrayOfNulls<String>(liste_favoris.size)
        val positions = ArrayList<Int>()
        var ligne = 0
        for (i in 0..Titres_cantique.size) {
            if (liste_favoris.contains("" + i)) {
                CantiquesFavoris[ligne] = Titres_cantique[i]
                positions.add(i)
                ligne++
            }
        }

        if (!(liste_favoris.isEmpty())) {
            val favorisAdapter = context?.let { FavorisAdapter(it, CantiquesFavoris) }
            favoris_liste?.adapter=favorisAdapter
        } else {
            favoris_vide_textview.setText(resources.getString(R.string.favoris_vide));
        }

        favoris_liste?.setOnItemClickListener { parent, view, position, id ->
            val intent = Intent(getActivity(), CantiqueActivity::class.java)
            intent.putExtra("position", positions[position])
            startActivity(intent)
        }
    }
}

