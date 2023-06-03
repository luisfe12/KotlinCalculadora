package com.example.myapplicationpudemy

import android.graphics.Color
import android.graphics.Typeface
import android.os.Bundle
import android.widget.*
import android.widget.MultiAutoCompleteTextView.CommaTokenizer
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.addTextChangedListener

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var tvEjemplo:TextView = findViewById(R.id.tvEjemplo);
        tvEjemplo.text = "holas  grupo";
        tvEjemplo.setTextColor(Color.GREEN);
        tvEjemplo.setTypeface(Typeface.MONOSPACE, Typeface.BOLD);


        tvEjemplo.setOnClickListener {
            Toast.makeText(this, "text view clicker", Toast.LENGTH_LONG).show();
            tvEjemplo.setTextColor(Color.RED);
        }

        //cresmos editext vie para ejecutar accioens
        var etEjemplo: EditText = findViewById(R.id.etEjemplo);
        etEjemplo.addTextChangedListener { //usado apra detectar cambisos en el contenido
            if (etEjemplo.text.length == 0){
                etEjemplo.setError("campo vacio") //solo si cambiamos  a vacio el texto

            }
        }

        //obligatorio que haya text sino manda error
        etEjemplo.setSelection(1);//seleccionamos los 3 primeros caracteres
        var  first: Int = etEjemplo.selectionStart;
        var last:Int = etEjemplo.selectionEnd;
//
        //selecionamos todos los caracteres
        etEjemplo.selectAll();

        //creamos el objeto de autocomplete
        var atctvEjemplo: AutoCompleteTextView = findViewById(R.id.actvEjemplo);

        //arreglo de los apisees del sctring, los obtenemos desde resources
        var arreglopaises: Array<String> = resources.getStringArray(R.array.country_array);

        //creamos un adaptaer para unicr el arreglo de string con el autocomplete
        var adapter: ArrayAdapter<String> = ArrayAdapter<String>(this,
            android.R.layout.simple_dropdown_item_1line, arreglopaises);//mandamos contexto, tipo de layout y el arreglo

        atctvEjemplo.setAdapter(adapter);


        //multiautocompletetextview para que complete varias opciones
        var mactvEjemplo = findViewById<MultiAutoCompleteTextView>(R.id.matvEjemplo);
        mactvEjemplo.setAdapter(adapter);

        //creamos un tokenizer para hacer las delimitaciones de vario campos de autocompleteado
        mactvEjemplo.setTokenizer(CommaTokenizer());




    }
}