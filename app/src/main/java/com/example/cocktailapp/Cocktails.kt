package com.example.cocktailapp

class Cocktails() {
    var des: String? = null
    var id: String? = null
    var img: String? = null

    constructor(des: String, id: String, img: String): this(){

        this.des = des
        this.id = id
        this.img = img

    }
}