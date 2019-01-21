package com.kseniyaa.pushoverapp

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.widget.Toast
import com.kseniyaa.pushoverapp.utils.getFromSP
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class MainActivity : AppCompatActivity() {

    private var api: Api? = null
    private var msg: Msg? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        api = (application as App).api

        btn_send.setOnClickListener {
            if (!et_msg.text?.isEmpty()!!) {
                msg = Msg(APP_KEY, USER_KEY, et_msg.text.toString())
                send()
            } else {
                toast("Сообщение не написано")
            }
        }

        btn_qr_read.setOnClickListener {
            val intent = Intent(this@MainActivity, QRActivity::class.java)
            startActivityForResult(intent, 1)
        }

        btn_history.setOnClickListener {
            loadMsg()
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (data == null) {
            return
        }

        val value = data.getStringExtra("value")
        et_msg.setText(value)
    }

    private fun send() {

        val call = api?.pushMsg(msg)

        call?.enqueue(object : Callback<Msg> {

            override fun onResponse(call: Call<Msg>, response: Response<Msg>) {
                toast("Сообщение отправлено")
                et_msg.setText("")
            }

            override fun onFailure(call: Call<Msg>, t: Throwable) {
                Log.i("MainActivity", t.toString())
            }
        })
    }

    private fun loadMsg() {
        val call = api?.getMsg(getFromSP("secret", baseContext), getFromSP("device_id", baseContext))
        call?.enqueue(object : Callback<Resp> {
            override fun onResponse(call: Call<Resp>, response: Response<Resp>) {
                val resp = response.body()
                if (resp != null) {
                    val msgs = resp.messages

                    val adapter = ItemsAdapter()
                    adapter.setItems(msgs)
                    recycler.adapter = adapter
                    recycler.layoutManager = LinearLayoutManager(this@MainActivity)
                }
            }

            override fun onFailure(call: Call<Resp>, t: Throwable) {
                println(t)
            }
        })

    }

    private fun toast(text: String) {
        Toast.makeText(this, text, Toast.LENGTH_SHORT).show()
    }

    companion object {
        const val APP_KEY = "a323fnvjeivc4z7dj2k7hu74rkg34m"
      //  const val USER_KEY = "uz6rybzvbtgbbk1mdgrakugnpn1gcj"
        const val USER_KEY = "u3ba37zs37ftt72cab43qwp2xofidn"

    }
}
