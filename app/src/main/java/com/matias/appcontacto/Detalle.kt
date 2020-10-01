package com.matias.appcontacto

import android.content.Intent
import android.media.Image
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.widget.Toolbar

class Detalle : AppCompatActivity() {

    var index:Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detalle)

        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        toolbar?.setTitle(R.string.app_name)
        setSupportActionBar(toolbar)

        // --------------------------------- C A R G A R   B O T O N   P A R A    R E G R E S A R     A T R A S
        var actionBar = supportActionBar
        actionBar?.setDisplayHomeAsUpEnabled(true)

        index = intent.getStringExtra("ID")!!.toInt()

        mapearDatos()


    }

    fun mapearDatos(){
        val contacto = MainActivity.obtenerContacto(index!!)

        val tvNombre = findViewById<TextView>(R.id.txtNombre)
        val tvApellido = findViewById<TextView>(R.id.txtApellidos)
        val tvTrabajo = findViewById<TextView>(R.id.txtTrabajo)
        val tvDireccion = findViewById<TextView>(R.id.txtDireccion)
        val tvTelefono = findViewById<TextView>(R.id.txtTelefono)
        val tvEmail = findViewById<TextView>(R.id.txtEmail)
        val tvImagen = findViewById<ImageView>(R.id.imagen)



        tvNombre.setText(contacto.nombre)
        tvApellido.setText(contacto.apellido)
        tvTrabajo.setText(contacto.trabajo)
        tvDireccion.setText(contacto.direccion)
        tvTelefono.setText(contacto.telefono)
        tvEmail.setText(contacto.email)
        tvImagen.setImageResource(contacto.foto)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_detalle, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item?.itemId){

            android.R.id.home ->{
                finish()
                return true
            }

            R.id.btnModificar ->{
                val intent = Intent(this, CrearUsuario::class.java)
                intent.putExtra("ID", index.toString())
                startActivity(intent)
                return true
            }

            R.id.btnEliminar ->{
                MainActivity.EliminarContacto(index)
                finish()
                return true
            }
            else ->{
                return super.onOptionsItemSelected(item)
            }
        }

    }

    override fun onResume(){
        super.onResume()
        mapearDatos()
    }

}