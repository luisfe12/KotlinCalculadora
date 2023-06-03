package com.example.myapplicationpudemy

import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.SearchView
import androidx.appcompat.app.AppCompatActivity

class searchViewActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search_view)

        val users = arrayOf("Alberto", "Alvaro", "Ana", "Amparo", "Bartolo", "Carla", "Carlos", "Carolina");
        var adapterUser: ArrayAdapter<String> = ArrayAdapter(this, android.R.layout.simple_list_item_1, users);

        var svUsers:SearchView = findViewById(R.id.svUsers);
        var lvUser: ListView = findViewById(R.id.lvUsers);

        //unimos el list con el adapter
        lvUser.adapter = adapterUser;

        //hacemos que encuentre el texto
        svUsers.setOnQueryTextListener(object: SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean {//escribimos el texto que queremos
                svUsers.clearFocus();//limpiamos el serachview
                if (users.contains(query)) adapterUser.filter.filter(query);//el adaparador debe fl=iltrar
                return false;
            }

            override fun onQueryTextChange(query: String?): Boolean {
                //buscamso la palabra mientras excribe el texto
                adapterUser.filter.filter(query);
                return false;
            }
        })
    }
}