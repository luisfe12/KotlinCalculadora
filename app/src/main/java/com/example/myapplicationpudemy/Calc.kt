package com.example.myapplicationpudemy

import android.os.Bundle
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class Calc : AppCompatActivity() {

//    var nu1:TextView? = null;
//    var nu2:TextView? = null;
//    var nu3:TextView? = null;
//    var nu4:TextView? = null;
//    var nu5:TextView? = null;
//    var nu6:TextView? = null;
//    lateinit var nu7:TextView;
//    lateinit var nu8:TextView;
//    var nu9:TextView? = null;
//    var nu0:TextView? = null;
//
//    var delete:TextView? = null;
//    var sumar:TextView? = null;
//    var restar:TextView? = null;
//    var multiplicar:TextView? = null;
//    var dividir:TextView? = null;
//    var igual:TextView? = null;
//    var punto:TextView? = null;
    lateinit  var total:TextView;
    var nume_1:String = "";
    var nume_2:String = "";
    var signo:String = "";
    var arreglo_Opera:Array<String>? = null;
    var operacion:Boolean = false;
    var final_igual:Boolean = false; //para evitar que despues del igual se ingresen numeros o puntos
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.calc);

//        nu1 = findViewById(R.id.numero1);
//        nu2 = findViewById(R.id.numero2);
//        nu3 = findViewById(R.id.numero3);
//        nu4 = findViewById(R.id.numero4);
//        nu5 = findViewById(R.id.numero5);
//        nu6 = findViewById(R.id.numero6);
//        nu7 = findViewById(R.id.numero7);
//        nu8 = findViewById(R.id.numero8);
//        nu9 = findViewById(R.id.numero9);

//        nu1 = findViewById(R.id.numero1);
//        nu1 = findViewById(R.id.numero1);
//        nu1 = findViewById(R.id.numero1);
        total = findViewById(R.id.tvTotal);
        total.text = "0";


    }

    fun pulsar(view: View){


        when(view.tag){
            "0", "1", "2", "3", "4",
                "5", "6", "7", "8", "9", "." ->{
                if(!final_igual) {
                    var tempoString: String = view.tag.toString()
                    if (operacion) {
                        //var tempoString: String = view.tag.toString()
                        nume_2 = nume_2 + tempoString;
                        total.text =
                            total.text.toString() + tempoString;//sumamos los valores en la pantalla
                    } else {
                        nume_1 = nume_1 + tempoString;
                        total.text = "$nume_1";
                    }
                }else{
                    Toast.makeText(this@Calc, "Ingrese un signo", Toast.LENGTH_SHORT).show()
                }
            }

//            "1" ->{
//                if (operacion){
//                    nume_2 = nume_2+1;
//                    total.text = total.text.toString()+1;
//                }else{
//                    nume_1 = nume_1+1;
//                    total.text = "$nume_1";
//                }
//            }
//            "2" ->{
//                if (operacion){
//                    nume_2 = nume_2+2;
//                    total.text = total.text.toString()+2;
//                }else{
//                    nume_1 = nume_1 + 2;
//                    total.text = "$nume_1";
//                }
//            }
//            "3" ->{
//                if (operacion){
//                    nume_2 = nume_2+3;
//                    total.text = total.text.toString()+2;
//                }else{
//                    nume_1 = nume_1 + 3;
//                    total.text = "$nume_1";
//                }
//            }
//            "4" ->{
//                if (operacion){
//                    nume_2 = nume_2+4;
//                    total.text = total.text.toString()+4;
//                }else{
//                    nume_1 = nume_1 + 4;
//                    total.text = "$nume_1";
//                }
//            }
//            "5" ->{
//                if (operacion){
//                    nume_2 = nume_2+5;
//                    total.text = total.text.toString()+5;
//                }else{
//                    nume_1 = nume_1 + 5;
//                    total.text = "$nume_1";
//                }
//            }
//            "6" ->{
//                if (operacion){
//                    nume_2 = nume_2+6;
//                    total.text = total.text.toString()+6;
//                }else{
//                    nume_1 = nume_1 + 6;
//                    total.text = "$nume_1";
//                }
//            }
//            "7" ->{
//                if (operacion){
//                    nume_2 = nume_2+7;
//                    total.text = total.text.toString()+7;
//                }else{
//                    nume_1 = nume_1 + 7;
//                    total.text = "$nume_1";
//                }
//            }
//            "8" ->{
//                if (operacion){
//                    println("entro")
//                    nume_2 = nume_2+"8"
//                    total.text = total.text.toString()+"8";
////                    total.setText("mmm ");
//                   // Toast.makeText(this@Calc, "${total.text}", Toast.LENGTH_SHORT).show();
//                }else{
//                   // Toast.makeText(this, "entro .88 $operacion", Toast.LENGTH_SHORT).show()
//                    nume_1 = nume_1 + 8;
//                    total.text = "$nume_1";
//                }
//                println("salio")
//            }
//            "9" ->{
//                if (operacion){
//                    nume_2 = nume_2+9;
//                    total.text = "${total.text }"+"9";
//                   // Toast.makeText(this@Calc, "${total.text.toString()}", Toast.LENGTH_SHORT).show();
//                }else{
//                    nume_1 = nume_1 + 9;
//                    total.text = "$nume_1";
//                }
//            }

//            "." ->{
//                if (operacion){
//                    nume_2 = nume_2 + ".";
//                    total.text = total.text.toString() + ".";
//                }else{
//                    nume_1 = nume_1 + ".";
//                    total.text = "$nume_1"
//                }
//            }

            "*", "/", "-", "+" ->{

                if (final_igual) final_igual = false;

                var signoString: String = view.tag.toString();
                operacion = true;
                signo = signoString;
                total.text = total.text.toString() + signoString;
                Toast.makeText(this@Calc, "${total.text}", Toast.LENGTH_SHORT).show();
            }

//            "/" -> {
//                signo = "/";
//                total.text = "$nume_1/";
//                operacion = true;
//            }
//
//            "-" -> {
//                operacion = true;
//                total.text = "$nume_1-";
//                signo = "-";
//            }
//
//            "+" -> {
//                signo = "+";
//                total.text = "$nume_1+";
//                operacion = true;
//            }

            "D"-> {
                var lastPosition: Int = total.text.lastIndex;


                if (final_igual) {
                    Toast.makeText(this@Calc, "entro D", Toast.LENGTH_SHORT).show()
                    final_igual = false
                    Toast.makeText(this@Calc, "primer $nume_1", Toast.LENGTH_SHORT).show()

                }
                Toast.makeText(this@Calc, "$lastPosition", Toast.LENGTH_SHORT).show()
                total.text = "${total.text.toString().dropLast(1)}";
                nume_1 = total.text.toString();//necesario para poder usar las modificaciones despues del '='

            }

            "C" -> {
                if (final_igual) final_igual = false;
                nume_1 = "";
                nume_2 = "";
                operacion = false;
                total.text = "0"
            }

            "=" ->{
                Toast.makeText(this@Calc, "$signo", Toast.LENGTH_SHORT).show();
                Toast.makeText(this@Calc, "${total.text}", Toast.LENGTH_SHORT).show();
                var resultado: String = operar(nume_1, nume_2, signo );
                total.text = resultado;
                operacion = false;
                nume_1 = resultado;
                nume_2 = "";

                final_igual = true
            }




        }
    }

    fun operar(val1: String, val2: String, signo:String): String{
        var resultado:String = "";
        when (signo){
            "+" ->{
                resultado ="${(val1.toFloat()) + val2.toFloat()}";
            }

            "-" -> {
                resultado = "${(val1.toFloat()) - val2.toFloat()}";
            }

            "/" -> {
                resultado = "${(val1.toFloat()) / (val2.toFloat())}";
            }

            "*" -> {
                resultado = "${(val1.toFloat()) * (val2.toFloat())}";
            }
        }

        return resultado;

    }
}