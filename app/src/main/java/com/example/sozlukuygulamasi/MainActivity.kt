package com.example.sozlukuygulamasi

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import androidx.appcompat.widget.SearchView
import androidx.recyclerview.widget.LinearLayoutManager
import com.info.sqlitekullanimihazirveritabani.DatabaseCopyHelper
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), SearchView.OnQueryTextListener {

    private lateinit var kelimelerListe:ArrayList<Kelimeler>
    private lateinit var adapter:KelimelerAdapter
    private lateinit var vt:VeritabaniYardimcisi

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        veritabaniKopyala()

        toolbar.title = "English / Turkish 40 words"
        setSupportActionBar(toolbar)

        rv.setHasFixedSize(true)
        rv.layoutManager = LinearLayoutManager(this)

        vt = VeritabaniYardimcisi(this) //veritabanı context istiyor.

        kelimelerListe = Kelimelerdao().tumKelimeler(vt) //veritabanında bulunan tum kelimeleri uygulamaya aktardık

        adapter = KelimelerAdapter(this,kelimelerListe)

        rv.adapter = adapter

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.toolbar_menu,menu)

        val item = menu?.findItem(R.id.action_ara)
        val searchView = item?.actionView as SearchView
        searchView.setOnQueryTextListener(this)

        return super.onCreateOptionsMenu(menu)
    }

    override fun onQueryTextSubmit(query: String): Boolean {
        arama(query)
        Log.e("Arama yapıldığında",query)
        return true
    }

    override fun onQueryTextChange(newText: String): Boolean {
        arama(newText) //Girilen harfler arama fonksiyonuna aktarılır.
        Log.e("Harf Girildiğinde", newText)
        return true
    }

    fun veritabaniKopyala(){
        val copyHelper = DatabaseCopyHelper(this)

        try {
            copyHelper.createDataBase()
            copyHelper.openDataBase()
        }catch (e:Exception){
            e.printStackTrace()
        }
    }

    fun arama(aramaKelime:String){

        kelimelerListe = Kelimelerdao().aramaYap(vt,aramaKelime) //Arama yapılacak nesneleri kelimelerListe'nin içerisine aktardık.

        adapter = KelimelerAdapter(this,kelimelerListe)

        rv.adapter = adapter

    }
}