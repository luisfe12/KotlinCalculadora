package com.example.myapplicationpudemy

import android.content.Context
import android.net.Uri
import android.os.Bundle
import android.webkit.WebSettings
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.example.myapplicationpudemy.databinding.ActivityWidgetsBinding
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.lang.Thread.sleep
import java.util.*

class widgets : AppCompatActivity() {

    private lateinit var activityContext: Context;

    //para el binding
    private lateinit var binding: ActivityWidgetsBinding;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //CONECTAMSO CON BIDING
        binding = ActivityWidgetsBinding.inflate(layoutInflater);
        setContentView(binding.root);

//        setContentView(R.layout.activity_widgets);

        //iniciamos el context conthis
        activityContext = this;

        var webView: WebView = findViewById(R.id.webView);

        //debemos permitir funcionalidad javascript
        var webSettings: WebSettings =  webView.getSettings();
        //damso permisos javascript
        webSettings.javaScriptEnabled = true;
        //lo volvemos modo cliente
        webView.setWebViewClient(WebViewClient())
        webView.loadUrl("https://www.google.com.pe")

        //****hacemos el video en forma local
        var vvLocal: VideoView = findViewById(R.id.vvLocal);
        var mcLocal = MediaController(this@widgets);
        mcLocal.setAnchorView(vvLocal);
        //path del un video en el alamcenamiento del celular
        var path = "android.resource://" + packageName + "/" + R.raw.nuevo_video;
        //mandamos la ubicacion
        vvLocal.setVideoURI(Uri.parse(path));
        vvLocal.setMediaController(mcLocal);

        //***hacemos el video en la web
        var vvWeb: VideoView = findViewById(R.id.vvWeb);
        //creamos controladores
        var mcWeb = MediaController(this@widgets);
        //mandamos la vista al contolador
        mcWeb.setAnchorView(vvWeb);
        //link del video
        vvWeb.setVideoPath("https://jotajotavm.com/img/video.mp4");
        //pasamos el controlador al videoview
        vvWeb.setMediaController(mcWeb);


        //verificamos fecha del calendar view y su text view
        var cvEjemplo: CalendarView = findViewById(R.id.cvEjemplo);
        var tvFecha: TextView = findViewById(R.id.tvFecha);

        //detectar si la fecha cambio del calendarview
        cvEjemplo.setOnDateChangeListener { cv, year, month, day ->
            var date: String = "$day/$month/$year";
            tvFecha.text = "fecha seleccioanda $date";

        }

        //seleccionamos una fecha como almanaque en el calendario
        var calendar: Calendar = Calendar.getInstance();
        calendar.set(2026, 5-5, 16);
        cvEjemplo.date = calendar.timeInMillis; //debemos paasalro en milisegundos

        //poner lunes como el primer dia
        //primer dia de la semana
        var d = cvEjemplo.firstDayOfWeek;
        cvEjemplo.firstDayOfWeek = (d+1)%7;

        //hacemos el codigo para los progressbar del xml
        var pbDeterminate: ProgressBar = findViewById(R.id.pbDeterminate);
        var pbSecondary: ProgressBar = findViewById(R.id.pbSecondary);

        pbDeterminate.max =300;
        pbDeterminate.progress = 0;

        pbSecondary.progress = 0;
        pbSecondary.secondaryProgress = 0;

        /*******CODIGO RATING VAR***************/



        var rbEjemplo: RatingBar = findViewById(R.id.rbEjemplo);
        var tvRating: TextView = findViewById(R.id.tvRating);
        rbEjemplo.rating = 2.5f;

        //cambiamos el rating
        rbEjemplo.setOnRatingBarChangeListener { ratingBar, rating, _ ->
            tvRating.text = "$rating / ${ratingBar.numStars}";
        }


        /***SALIDA DEL CODIGO RATING BAR***/

        /************CODIGO DEL SEARCHVIEW *************/

        val users = arrayOf("Alberto", "Alvaro", "Ana", "Amparo", "Bartolo", "Carla", "Carlos", "Carolina");
        var adapterUser: ArrayAdapter<String> = ArrayAdapter(this, android.R.layout.simple_list_item_1, users);

        var svUsers: SearchView = findViewById(R.id.svUsers);
        var lvUsera: ListView = findViewById(R.id.lvUsers);

        //unimos el list con el adapter
        lvUsera.adapter = adapterUser;

        //haceos que encuentre el texto
        svUsers.setOnQueryTextListener(object: SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean {//escribimos el texto que queremos
                svUsers.clearFocus(); //limpiamos el serachview
                if (users.contains(query)) adapterUser.filter.filter(query);
                return false;
            }

            override fun onQueryTextChange(query: String?): Boolean {
                //buscamso la palabra mientras excribe el texto
                adapterUser.filter.filter(query);
                return false;
            }
        });
        /************SALIDA DEL SERACHVIEW*****************/

        /*********NUMBER PICKER************************/
        var npEjemplo: NumberPicker = findViewById(R.id.npEjemplo);
//        var tvNumberPicker: TextView = findViewById(R.id.tvNumberPicker);
        npEjemplo.minValue = 0;
        npEjemplo.maxValue = 60;
        npEjemplo.value = 5;
        //modo rueda
        npEjemplo.wrapSelectorWheel = true;
        //fomato de 2 digitos tipo 00-01 a 60
        npEjemplo.setFormatter { i->String.format("%02d", i);  }

        //detectar el cambio del numberpicker de posicion antigua a actual
        npEjemplo.setOnValueChangedListener { numberPicker, oldVal, newVal ->
            binding.tvNumberPicker.text = "NUmberPicker antes $oldVal, NumberPicler ahora $newVal "
        }
        /*************NUMBER PICKER FINAL**********/
        //seekbar
        var sbNormal: SeekBar = findViewById(R.id.sbNormal);

        //detectar si el seekbarfue ha cambiado
        //gesioanr el cambio de la seekbar
        sbNormal.setOnSeekBarChangeListener(object: SeekBar.OnSeekBarChangeListener{
            override fun onProgressChanged(seekbar: SeekBar?, progress: Int, fromuser: Boolean) {
                if (fromuser){
                    Toast.makeText(activityContext, "Usted lo cambio", Toast.LENGTH_SHORT).show();
                }
            }

            override fun onStartTrackingTouch(seekbar: SeekBar?) {

            }

            override fun onStopTrackingTouch(seekbar: SeekBar?) {

            }


        });
        //lanzamos avance de barradeterminada para que se llene
        GlobalScope.launch {
            progressManger(pbDeterminate);
            progressManger(pbSecondary);
            seekbarManager(sbNormal);
        }

    }

    private fun seekbarManager(sb: SeekBar){
        while (true){
            sleep(100L)
            sb.incrementProgressBy(5)
        }
    }

    private fun progressManger(pb: ProgressBar){

        while (pb.progress < pb.max){//vericar si la barra se lleno
            //haceos un sleep para que se vea el avance
            sleep(100L);
            //aumentamos de 5 en 5 la barra
            pb.incrementProgressBy(5);

            //incrementamos la barra secundaria de 10 en 10
            if (pb.id == R.id.pbSecondary) pb.incrementSecondaryProgressBy(10);
        }

    }


}