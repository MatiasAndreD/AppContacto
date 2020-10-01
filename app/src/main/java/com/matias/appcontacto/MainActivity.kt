package com.matias.appcontacto

import android.app.SearchManager
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.ListView
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import androidx.appcompat.widget.Toolbar

class MainActivity : AppCompatActivity() {

    var lista:ListView? = null
    var adaptador:Adaptador? =null

    companion object{
        var contactos:ArrayList<Contacto>? = null

        fun AgregarContacto(contacto: Contacto){
            contactos?.add(contacto)
    }
        fun obtenerContacto(index:Int):Contacto{
            return contactos?.get(index)!!
        }

        fun EliminarContacto(index:Int){
            contactos?.removeAt(index)
        }
        fun ActualizarContacto(index: Int, nuevoContacto:Contacto){

            contactos?.set(index,nuevoContacto)

        }

    }


    var toolbar: Toolbar? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //---------- V A R I A B L E S   P A R A   T O O L B A R --------------------
        toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)
        //---------------------------------------------------------------------------
        contactos = ArrayList()
        contactos?.add(Contacto("Matias","Diaz Arriaza","Google","Av independencia 4599","957137788","matiasdiazarriaza@gmail.com",R.drawable.foto_01))
        contactos?.add(Contacto("Beatriz","Arriaza Luna","Colegio San Lorenzo","Av independencia 4599","957137788","matiasdiazarriaza@gmail.com",R.drawable.foto_05))


        lista = findViewById<ListView>(R.id.lista)

        adaptador = Adaptador(this,contactos!!)
        lista?.adapter = adaptador
        
        
        lista?.setOnItemClickListener { parent, view, position, id ->
            val intent = Intent(this, Detalle::class.java)
            intent.putExtra("ID", position.toString())
            startActivity(intent)
        }
    }
    //-------------------- F U N C I O N   P A R A   C R E A R    B O T O N E S   E N   L A  T O O L B A R ---------------------------------
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)

        // C O N F I G U R A R    S E R V I C I O   D E   B U S Q U E D A   P A R A    E L   L I S T V I E W
        val searchManager = getSystemService(Context.SEARCH_SERVICE) as SearchManager
        val itemBusqueda = menu?.findItem(R.id.buscar)
        val searchView = itemBusqueda?.actionView as SearchView

        searchView.setSearchableInfo(searchManager.getSearchableInfo(componentName))
        searchView.queryHint = "Buscar usuario"

        searchView.setOnQueryTextFocusChangeListener { v, hasFocus ->
            //P R E P A R A R   L O S   D A T O S
        }

        searchView.setOnQueryTextListener(object: SearchView.OnQueryTextListener{
            override fun onQueryTextChange(newText: String?): Boolean {
                //F I L T R A R  L O S  D A T O S
                adaptador?.filtrar(newText!!)

                    return true
            }

            override fun onQueryTextSubmit(query: String?): Boolean {
               // F I L T R A R
                return true
            }
        })
        //__________________________________________________________________________________________________



        searchView.setSearchableInfo(searchManager.getSearchableInfo(componentName))
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item?.itemId){
            R.id.create ->{
                val intent = Intent(this, CrearUsuario::class.java)
                startActivity(intent)
                return true
            }
            else ->{
                return super.onOptionsItemSelected(item)
            }
        }

    }

    override fun onResume() {

        adaptador?.notifyDataSetChanged()
        super.onResume()
    }
}