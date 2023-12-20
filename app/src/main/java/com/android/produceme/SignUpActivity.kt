package com.android.produceme

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class SignUpActivity : AppCompatActivity(){
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signup)

        val btn_join2 = findViewById<Button>(R.id.btn_join2)

        btn_join2.setOnClickListener {
            val id = findViewById<EditText>(R.id.et_id2)
            val pw = findViewById<EditText>(R.id.editTextTextPassword2)
            val name = findViewById<EditText>(R.id.et_name)

            //Log.d("check","${id.editableText.toString()}")
            if(id.text.isEmpty()){
                Toast.makeText(this,"아이디를 입력해주세요", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            } else if(pw.text.isEmpty()){
                Toast.makeText(this, "비밀번호를 입력해주세요", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            } else if(name.text.isEmpty()){
                Toast.makeText(this, "이름을 입력해주세요", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            if(pw.text.isNotEmpty() && name.text.isNotEmpty() && id.text.isNotEmpty()){
                intent.putExtra("id",id.text.toString())
                intent.putExtra("pw",pw.text.toString())
                setResult(RESULT_OK,intent)
                finish()
            }

        }
    }
}