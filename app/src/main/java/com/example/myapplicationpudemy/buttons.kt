package com.example.myapplicationpudemy

import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.google.android.material.chip.Chip
import com.google.android.material.chip.ChipGroup
import com.google.android.material.floatingactionbutton.FloatingActionButton

class buttons : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_buttons)


        var buttonN : Button = findViewById(R.id.button1);
        buttonN.setOnClickListener {
            Toast.makeText(this, "presionaste botton", Toast.LENGTH_LONG).show();
        }

        var imageButton: ImageButton = findViewById(R.id.imageButton);
        imageButton.setOnClickListener {
            Toast.makeText(this, "presionast ebotn imagen", Toast.LENGTH_SHORT).show();
        }

        //POENMOS CARACTERISTICAS EN LOS CHIPS del chipgroup
        var cgCondiones: ChipGroup = findViewById(R.id.cgCondiciones);
        var chip: Chip;
        //bucle para obtener cas chip del grupo
        for (i in 0..cgCondiones.childCount-1 ){
            //casteo para ser chip y no view
            chip = cgCondiones.getChildAt(i) as Chip;//lugar del hijo para castear
            //chip.textAlignment = View.TEXT_ALIGNMENT_CENTER

            //removemos el chip si presionamos la X
            chip.setOnCloseIconClickListener {
                cgCondiones.removeView(it); //la visa en esa posicion sera removida
            }

            chip.setOnClickListener {
                var aux = it as Chip;
                Toast.makeText(this, "${aux.text} oulsado", Toast.LENGTH_LONG).show();
            }
        }

        //crear nuevo chip y adherirlo
        var newChip = Chip(this);
        newChip.text = "opcion";
        newChip.chipIcon = ContextCompat.getDrawable(this@buttons, R.drawable.ic_car);
        newChip.isChipIconVisible = true;
      //newChip.isChipIconVisible = false // para no hacer visible el icono del chip
        newChip.isCloseIconVisible = true //hacer visible icono de cerrado
        newChip.isClickable = true
      //newChip.isClickable = false; //para que no se pueda clickear

        //para pponerlo el chipgroup debemos enviarle una vista
        cgCondiones.addView(newChip as View);
        //darle opcion de remover este nuevo chip agregado
        newChip.setOnCloseIconClickListener { cgCondiones.removeView(newChip as View)}

        //codigo para los radiogroups y radiobutton
        var rgVacaciones = findViewById<View>(R.id.rgVacaciones) as RadioGroup;
        var rb: RadioButton = rgVacaciones.getChildAt(1) as RadioButton;
        //marcamos el hijo del group seleccionado
        rgVacaciones.check(rb.id);


        //presonalizacion de check box
        var cbSeguro: CheckBox = findViewById(R.id.cbSeguro);
//        cbSeguro.isChecked = true;
//        cbSeguro.isEnabled = true;
        cbSeguro.isEnabled = true;
        cbSeguro.isChecked = true;

        //personalizaion de toogle buttom
        var tbEjemplo: ToggleButton = findViewById(R.id.tbEjemplo);
        tbEjemplo.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) Toast.makeText(this@buttons, "ToggleActivado", Toast.LENGTH_SHORT).show();
            else Toast.makeText(this@buttons, "Toggel Desactivado", Toast.LENGTH_SHORT).show();
        }
        
        var swNormal: Switch = findViewById(R.id.swnNormal);
        swNormal.setOnCheckedChangeListener { _, isChecked -> 
            if (isChecked) Toast.makeText(this@buttons, "switch buttom activado", Toast.LENGTH_SHORT).show();
            else Toast.makeText(this@buttons, "switch desactivado", Toast.LENGTH_SHORT).show();
        }

        //Floatin Action Buttom codigo para desde el activity kotlin
        var faButtom: FloatingActionButton = findViewById(R.id.faButtom);
        faButtom.setOnClickListener {
            Toast.makeText(this@buttons, "faButtom pulsado", Toast.LENGTH_SHORT).show()
        }

    }

    fun onRadioButtonClicked(vista: View){
        if (vista is RadioButton){
            var checked = vista.isChecked;
            when(vista.id){
                //vemos si fue selecionada alguna de estas 2 opciones
                R.id.rbPlaya -> {
                    if (checked) Toast.makeText(this@buttons, "Vamos a la playa", Toast.LENGTH_SHORT).show();
                }
                R.id.rbMontaña -> {
                    if(checked) Toast.makeText(this@buttons, "Vamos a la montaña", Toast.LENGTH_SHORT).show();
                }
            }
        }
    }

    fun onCheckBoxClicked(vista: View){
        if (vista is CheckBox){
            var checked = vista.isChecked;
            when(vista.id){
                R.id.cbSeguro -> {
                    if (checked) Toast.makeText(this@buttons, "Seguro Contratado", Toast.LENGTH_SHORT).show();
                    else Toast.makeText(this@buttons, "Seguro Cancelado", Toast.LENGTH_SHORT).show()
                }

                R.id.cbCancelable -> {
                    if (checked) Toast.makeText(this@buttons, "Resrva Cancelada", Toast.LENGTH_SHORT).show();
                    else Toast.makeText(this@buttons, "La reserva no puede ser cnacelada", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}