package com.android.produceme

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import kotlinx.coroutines.NonCancellable

class SignInActivity : AppCompatActivity() {
    lateinit var resultLauncher:ActivityResultLauncher<Intent>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_loginin)

        val id = findViewById<EditText>(R.id.et_id)
        val pw = findViewById<EditText>(R.id.editTextTextPassword)
        val btn_login = findViewById<Button>(R.id.btn_login)
        val btn_join = findViewById<Button>(R.id.btn_join)

        resultLauncher =registerForActivityResult(ActivityResultContracts.StartActivityForResult()){
            if(it.resultCode == RESULT_OK){
                id.setText( it.data?.getStringExtra("id"))
                pw.setText( it.data?.getStringExtra("pw"))
            }
        }

        btn_login.setOnClickListener {


            if(id.text.isEmpty()){
                Toast.makeText(this,"아이디를 입력해주세요",Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            if(pw.text.isEmpty()){
                Toast.makeText(this, "비밀번호를 입력해주세요",Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            if(pw.text.isNotEmpty()&&id.text.isNotEmpty()){
                val intent = Intent(this, HomeActivity::class.java)

                Toast.makeText(this,"로그인 성공",Toast.LENGTH_SHORT).show()
                intent.putExtra("id",id.text.toString())
                //intent.putExtra("pw", pw.text.toString())
                startActivity(intent)
            }
        }

        btn_join.setOnClickListener {
            val intent = Intent(this, SignUpActivity::class.java)
            resultLauncher.launch(intent)
        }

    }

}