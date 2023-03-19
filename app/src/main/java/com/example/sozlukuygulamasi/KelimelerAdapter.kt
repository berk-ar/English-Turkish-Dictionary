package com.example.sozlukuygulamasi

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView

class KelimelerAdapter(private val mContext:Context,private val kelimelerListe:List<Kelimeler>) : RecyclerView.Adapter<KelimelerAdapter.CardTasarimTutucu>() {

    inner class CardTasarimTutucu(tasarim:View) : RecyclerView.ViewHolder(tasarim){

        var kelime_card:CardView
        var textViewEnglish:TextView
        var textViewTurkish:TextView

        init {
            kelime_card = tasarim.findViewById(R.id.kelime_card)
            textViewEnglish = tasarim.findViewById(R.id.textViewEnglish)
            textViewTurkish = tasarim.findViewById(R.id.textViewTurkish)
        }


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CardTasarimTutucu {
        val tasarim = LayoutInflater.from(mContext).inflate(R.layout.card_tasarim,parent,false)
        return CardTasarimTutucu(tasarim)
    }

    override fun getItemCount(): Int {
        return kelimelerListe.size
    }

    override fun onBindViewHolder(holder: CardTasarimTutucu, position: Int) {
        val kelime = kelimelerListe.get(position)

        holder.textViewEnglish.text = kelime.english
        holder.textViewTurkish.text = kelime.turkish

        holder.kelime_card.setOnClickListener {

            val intent = Intent(mContext,DetayActivity::class.java)

            intent.putExtra("nesne",kelime)

            mContext.startActivity(intent)

        }
    }


}