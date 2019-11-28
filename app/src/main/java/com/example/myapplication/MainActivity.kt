package com.example.myapplication

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.example.myapplication.databinding.ActivityMainBinding
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    private lateinit var binding : ActivityMainBinding
    private val myContact = Contact( "Crypto",
            "012345678")


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this,R.layout.activity_main)

        binding.contact = myContact
        binding.buttonSave.setOnClickListener{
            with(binding) {

                contact?.name = editTextName.text.toString()
                contact?.phone = editTextPhone.text.toString()
                invalidateAll() // refresh UI
            }

        }

        binding.buttonSend.setOnClickListener{
            //Create an Explicit Intent = provide the name of the component
            // to pass control from 1 activity  2another
            val intent = Intent(this, Main2Activity::class.java)

            //Prepare xtra data 4 the intent

            intent.putExtra(
                EXTRA_NAME,binding.contact?.name)
            intent.putExtra(
                EXTRA_PHONE,binding.contact?.phone)

            //startActivity(intent) // Your parent expect no return value
            startActivityForResult(intent, REQUEST_REPLY) // PTPTN expects result
        }

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == REQUEST_REPLY) {
            if(resultCode == Activity.RESULT_OK){
                val reply = data?.getStringExtra(EXTRA_REPLY)
                textViewReply.text = String.format("Reply : %s" , reply)
            }
        }

        super.onActivityResult(requestCode, resultCode, data)
    }

    companion object{
        const val EXTRA_NAME = "com.example.myapplication.NAME"
        const val EXTRA_PHONE = "com.example.myapplication.PHONE"
        const val EXTRA_REPLY = "com.example.myapplication.REPLY"
        const val REQUEST_REPLY = 1
    }

}//End of class
