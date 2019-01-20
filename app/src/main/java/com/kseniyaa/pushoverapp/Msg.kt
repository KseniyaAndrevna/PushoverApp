package com.kseniyaa.pushoverapp

data class Msg(var token: String?, var user: String?, var message: String?) {

    var date: Long? = null

    constructor(token: String?, user: String?, message: String?, date: Long ) : this(token,user,message) {
        this.date = date
    }
}