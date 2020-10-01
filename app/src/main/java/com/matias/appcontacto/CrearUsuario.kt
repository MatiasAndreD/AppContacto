package com.matias.appcontacto

import android.app.Dialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.*
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.widget.Toolbar

class CrearUsuario : AppCompatActivity() {


    var fotosIndex:Int = 0
    val fotos = arrayOf(R.drawable.foto_01,R.drawable.foto_02,R.drawable.foto_03,R.drawable.foto_04,R.drawable.foto_05,R.drawable.foto_06)

    var foto:ImageView? = null

    var index:Int = -1


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_crear_usuario)

        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)

        var actionBar = supportActionBar
        actionBar?.setDisplayHomeAsUpEnabled(true)

        foto = findViewById<ImageView>(R.id.imagen)

        foto?.setOnClickListener {
            seleccionarFoto()
        }

        //R E C O N O C E R   O P C I O N   D E    C R E A R   V / S   E D I T A R

        if (intent.hasExtra("ID")){
            index = intent.getStringExtra("ID")!!.toInt()
            rellenarDatos(index)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_nuevo, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item?.itemId){

            android.R.id.home ->{
                finish()
                return true
            }



            R.id.agregarNuevo ->{
                seleccionarFoto()
                val nombre = findViewById<EditText>(R.id.etNombre)
                val apellido = findViewById<EditText>(R.id.etApellido)
                val trabajo = findViewById<EditText>(R.id.etTrabajo)
                val telefono = findViewById<EditText>(R.id.etTelefono)
                val email = findViewById<EditText>(R.id.etEmail)
                val direccion = findViewById<EditText>(R.id.etDireccion)


                //validar campos

                var campos = ArrayList<String>()
                campos.add(nombre.text.toString())
                campos.add(apellido.text.toString())
                campos.add(trabajo.text.toString())
                campos.add(direccion.text.toString())
                campos.add(telefono.text.toString())
                campos.add(email.text.toString())

                var flag = 0
                for (campo in campos){
                    if(campo.isNullOrEmpty())
                        flag++
                    }
                if (flag > 0){
                    Toast.makeText(this, "Rellena todos los campos", Toast.LENGTH_SHORT).show()
                }else{
                    if(index > -1){
                        MainActivity.ActualizarContacto(index,Contacto(nombre.text.toString(),apellido.text.toString(),trabajo.text.toString(),direccion.text.toString(),telefono.text.toString(),email.text.toString(),obtenerImagen(fotosIndex)))
                        finish() 
                    }else{
                    MainActivity.AgregarContacto(Contacto(nombre.text.toString(),apellido.text.toString(),trabajo.text.toString(),direccion.text.toString(),telefono.text.toString(),email.text.toString(),obtenerImagen(fotosIndex)))
                    finish()
                    }
                }
                
                return true
            }
            else ->{
                return super.onOptionsItemSelected(item)
            }


        }

    }


    // FUNCION PARA QUE EL USUARIO PUEDA SELECCIONAR SU IMAGEN SIN TENER QUE SER ESTATICA
    fun seleccionarFoto(){
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Selecciona imagen de perfil")

        val adaptadorDialogo = ArrayAdapter<String>(this, android.R.layout.simple_selectable_list_item)
        //Opciones que tendria la persona para elegir las fotos
        adaptadorDialogo.add("Foto 01")
        adaptadorDialogo.add("Foto 02")
        adaptadorDialogo.add("Foto 03")
        adaptadorDialogo.add("Foto 04")
        adaptadorDialogo.add("Foto 05")
        adaptadorDialogo.add("Foto 06")

        builder.setAdapter(adaptadorDialogo){
                dialog, which ->

            fotosIndex = which
            foto?.setImageResource(obtenerImagen(fotosIndex))

        }

        builder.setNegativeButton("Cancelar") {
                dialog, which ->
            dialog.dismiss()
        }

        builder.show()

    }

    fun obtenerImagen(index: Int):Int{

        return  fotos.get(index)

    }

    fun rellenarDatos(index: Int){
        val contacto = MainActivity.obtenerContacto(index)

        val tvNombre = findViewById<EditText>(R.id.etNombre)
        val tvApellido = findViewById<EditText>(R.id.etApellido)
        val tvTrabajo = findViewById<EditText>(R.id.etTrabajo)
        val tvDireccion = findViewById<EditText>(R.id.etDireccion)
        val tvTelefono = findViewById<EditText>(R.id.etTelefono)
        val tvEmail = findViewById<EditText>(R.id.etEmail)
        val tvImagen = findViewById<ImageView>(R.id.imagen)



        tvNombre.setText(contacto.nombre)
        tvApellido.setText(contacto.apellido)
        tvTrabajo.setText(contacto.trabajo)
        tvDireccion.setText(contacto.direccion)
        tvTelefono.setText(contacto.telefono)
        tvEmail.setText(contacto.email)
        tvImagen.setImageResource(contacto.foto)
    }
}