package com.kseniyaa.pushoverapp

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.View
import android.widget.Toast
import com.kseniyaa.pushoverapp.utils.saveToSP
import com.kseniyaa.pushoverapp.utils.getFromSP
import kotlinx.android.synthetic.main.activity_autch.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class AutchActivity : AppCompatActivity() {

    private var api: Api? = null
    private var logopass: Logopass? = null
    private var device: Device? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_autch)

        api = (application as App).api

        btn_check.setOnClickListener {
            logopass = Logopass(et_mail.text.toString(), et_pass.text.toString())
            sendLogopass()
        }

        btn_reg.setOnClickListener {
            device = Device(getFromSP("secret",this),et_div_name.text.toString(), "O")
            sendDevName()
        }
    }

    private fun sendLogopass() {
        val call = api?.getSecret(logopass)

        call?.enqueue(object : Callback<Resp> {

            override fun onResponse(call: Call<Resp>, response: Response<Resp>) {
                val resp = response.body()
                if (resp != null) {
                    val secret: String = resp.secret
                    saveToSP("secret", secret, baseContext)
                    toast("Успешно")
                    et_div_name.visibility = View.VISIBLE
                    btn_reg.visibility = View.VISIBLE
               } else {
                    toast("Ошибка проверки")
                }
            }

            override fun onFailure(call: Call<Resp>, t: Throwable) {
                Log.i("MainActivity", t.toString())
            }
        })
    }

    private fun sendDevName() {
        val call = api?.getDeviceId(device)

        call?.enqueue(object : Callback<Resp> {

            override fun onResponse(call: Call<Resp>, response: Response<Resp>) {
                val resp = response.body()
                if (resp != null) {
                    val id: String = resp.id
                    saveToSP("device_id", id, baseContext)
                    toast("Устройство зарегистрировано")
                    saveToSP("auth","true",baseContext)
                    openAct()

                } else {
                    toast("Ошибка регистрации, попробуйте другое имя")
                }
            }

            override fun onFailure(call: Call<Resp>, t: Throwable) {
                Log.i("MainActivity", t.toString())
            }
        })
    }


    private fun toast(text: String) {
        Toast.makeText(this, text, Toast.LENGTH_SHORT).show()
    }

    private fun openAct() {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)

    }
}
