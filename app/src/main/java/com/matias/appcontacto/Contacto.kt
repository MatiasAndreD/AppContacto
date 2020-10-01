package com.matias.appcontacto

class Contacto(nombre:String, apellido:String,trabajo:String,direccion:String ,telefono:String,email:String,foto:Int ) {
    var nombre:String =""
    var apellido:String = ""
    var trabajo:String = ""
    var direccion:String = ""
    var telefono:String = ""
    var email:String = ""
    var foto:Int = 0

    init {
        this.nombre = nombre
        this.apellido = apellido
        this.trabajo = trabajo
        this.direccion = direccion
        this.telefono = telefono
        this.email = email
        this.foto = foto
    }

}