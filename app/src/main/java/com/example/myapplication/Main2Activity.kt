package com.example.myapplication

import android.app.Activity
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main2.*

class Main2Activity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)

        val name = intent.getStringExtra(MainActivity.EXTRA_NAME)
        textViewReceiveName.text = name
        val phone = intent.getStringExtra(MainActivity.EXTRA_PHONE)
        textViewReceiveName.text = phone

        buttonDone.setOnClickListener{
            if(editTextReply.text.isEmpty()) {
                val reply = editTextReply.text.toString()
                val intent = getIntent()
                intent.putExtra(MainActivity.EXTRA_REPLY, reply)
                setResult(Activity.RESULT_OK, intent)
            }else{
                setResult(Activity.RESULT_CANCELED, intent)
            }

            finish()
        }


        buttonCall.setOnClickListener {

            //Create an implicit Intent
            val phone = Uri.parse("tel:" +
                        textViewReceivePhone.text.toString())

            val intent = Intent(Intent.ACTION_VIEW)
            intent.setData(phone)
            startActivity(intent)

            if(intent.resolveActivity(packageManager) != null) {
                startActivity(intent)
            }


        }
    }




}
