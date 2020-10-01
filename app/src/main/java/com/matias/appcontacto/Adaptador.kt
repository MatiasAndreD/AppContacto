package com.matias.appcontacto

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.widget.SearchView

class Adaptador(var context: Context, items:ArrayList<Contacto>): BaseAdapter()  {

    var items:ArrayList<Contacto>? = null
    var copiaitems:ArrayList<Contacto>? = null

    init{
        this.items = ArrayList(items)
        this.copiaitems = items
    }

    override fun getCount(): Int {
        return items?.count()!!
    }

    override fun getItem(position: Int): Any {
        return items?.get(position)!!
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }
    //N O T I F Y   D A T A   S E T   C H A N G E D: Notifica a los observadores adjuntos que los datos subyacentes
    // se han modificado y que cualquier Vista que refleje el conjunto de datos debe actualizarse.
    fun filtrar(str:String){
        items?.clear()
        if(str.isEmpty()){
            items = ArrayList(copiaitems)
            notifyDataSetChanged()
            return
        }
        var busqueda = str
        busqueda = busqueda.toLowerCase()

        for (item in copiaitems!!){
            val nombre = item.nombre.toLowerCase()

            if (nombre.contains(busqueda)){
                items?.add(item)
            }
        }
        notifyDataSetChanged()

    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        var viewHolder:ViewHolder? = null
        var vista:View? = convertView

        if (vista== null){
            vista = LayoutInflater.from(context).inflate(R.layout.template_contacto, null)
            viewHolder = ViewHolder(vista)
            vista.tag = viewHolder
        }else{
            viewHolder = vista.tag as? ViewHolder
        }

        val item = getItem(position) as Contacto

        // -------------------- A S I G N A C I O N  D E  V A L O R E S  A   E L E M E N T O S   G R Á F I C O S

        // NOMBRE ES LA DE LA INTERFAZ GRAFICA Y ITEM.NOMBRE ES LA DE LA CLASE.
        viewHolder?.nombre?.text = item.nombre + " " + item.apellido
        viewHolder?.trabajo?.text = item.trabajo
        viewHolder?.numero?.text = item.telefono
        viewHolder?.imagen?.setImageResource(item.foto)


        return vista!!
    }

    private class ViewHolder(vista: View){
        var nombre: TextView? = null
        var trabajo: TextView? = null
        var numero: TextView? = null
        var imagen: ImageView? = null

        init {
            nombre = vista.findViewById(R.id.nombre)
            trabajo = vista.findViewById(R.id.txtTrabajo)
            numero = vista.findViewById(R.id.txtNumero)
            imagen = vista.findViewById(R.id.imgContacto)
        }
    }
}