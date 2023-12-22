package com.android.produceme

import android.annotation.SuppressLint
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.view.View.OnFocusChangeListener
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import java.util.regex.Pattern

class SignUpActivity : AppCompatActivity(){
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signup)

        val id = findViewById<EditText>(R.id.et_id2)
        val idBack = findViewById<EditText>(R.id.et_emailSelf)
        val pw = findViewById<EditText>(R.id.editTextTextPassword2)
        val pwCheck = findViewById<EditText>(R.id.editTextTextPassword3)
        val emailSpinner = findViewById<Spinner>(R.id.spin_email) as Spinner
        val name = findViewById<EditText>(R.id.et_name)
        val btn_join2 = findViewById<Button>(R.id.btn_join2)
        val emailBack = findViewById<EditText>(R.id.et_emailSelf)
        val emailArray = resources.getStringArray(R.array.email_array)
        val arrayAdapter = ArrayAdapter.createFromResource(this,R.array.email_array,android.R.layout.simple_spinner_item)
        val warnName = findViewById<TextView>(R.id.tv_warnName)
        val warnNameok = findViewById<TextView>(R.id.tv_warnNameok)
        val warnNamevalid = findViewById<TextView>(R.id.tv_warnNamevalid)
        val warnEmail = findViewById<TextView>(R.id.tv_warnemail)
        val warnEmailok = findViewById<TextView>(R.id.tv_warnemailok)
        val warnEmailvalid = findViewById<TextView>(R.id.tv_warnemailvalid)
        val warnPw = findViewById<TextView>(R.id.tv_warnPassword)
        val warnPwvalid = findViewById<TextView>(R.id.tv_warnPasswordValid)
        val warnPwCheck = findViewById<TextView>(R.id.tv_warnPasswordCheck)
        val warnPwCheckok = findViewById<TextView>(R.id.tv_warnPasswordCheckok)
        val warnPwok = findViewById<TextView>(R.id.tv_warnPasswordok)
        var namevalidcheck = false
        var idvalidcheck = false
        var idbackvalidcheck = false
        var pwvalidcheck = false
        var pwchkvalidcheck = false

        emailSpinner.adapter = arrayAdapter
        emailSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id2: Long) {
                if(emailArray[position]=="직접입력"){
                    idBack.setText("")
                    emailBack.isVisible=true
                }else{
                    emailBack.isVisible=false
                    idBack.setText(emailArray[position])
                }
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {

            }
        }

        btn_join2.setOnClickListener {


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
                var sumid = "${id.text}@${idBack.text}"
                intent.putExtra("id",sumid)
                intent.putExtra("pw",pw.text.toString())
                setResult(RESULT_OK,intent)
                finish()
            }
        }
        val nameRegex = """^(?=.*[A-Za-z가-힣\d])[A-Za-z가-힣\d]{1,24}$"""
        val namePattern = Pattern.compile(nameRegex) //대소문자o,숫자o,한글만,1~24자만 가능
        name.addTextChangedListener (object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                btn_join2.isEnabled = (namevalidcheck&& idvalidcheck && idbackvalidcheck && pwvalidcheck&& pwchkvalidcheck)
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                if(namePattern.matcher(name.text.toString()).matches() ){
                    warnName.isVisible = false
                    warnNamevalid.isVisible = false
                    warnNameok.isVisible = true
                    namevalidcheck = true
                } else if(name.text.isEmpty()){
                    warnName.isVisible = true
                    warnNameok.isVisible = false
                    warnNamevalid.isVisible = false
                    namevalidcheck = false
                } else{
                    warnNamevalid.isVisible = true
                    warnNameok.isVisible = false
                    warnName.isVisible = false
                    namevalidcheck = false
                }
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

        })
        val idRegex = """^(?=.*[A-Za-z\d!@#$%^&_])[A-Za-z\d!#$%^&_]{1,}$"""
        val idbackRegex = """^(?=.*[A-Za-z\d!#$%^&_])[A-Za-z\d.]{1,}$"""
        val idPattern = Pattern.compile(idRegex) //대소문자o,숫자o,공백x,특문:!#$%^&_만, 길이제한없음
        val idbackPattern = Pattern.compile(idbackRegex) //대소문자o,숫자o,공백x,특문.만, 길이제한없음
        id.addTextChangedListener (object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun afterTextChanged(s: Editable?) {
                btn_join2.isEnabled = (namevalidcheck&& idvalidcheck && idbackvalidcheck && pwvalidcheck&& pwchkvalidcheck)
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                if(idPattern.matcher(id.text.toString()).matches() && idbackPattern.matcher(idBack.text.toString()).matches()  ){
                    warnEmail.isVisible = false
                    warnEmailvalid.isVisible = false
                    warnEmailok.isVisible = true
                    idvalidcheck = true
                    idbackvalidcheck = true
                } else if(id.text.isEmpty()||idBack.text.isEmpty()){
                    warnEmail.isVisible = true
                    warnEmailok.isVisible = false
                    warnEmailvalid.isVisible = false
                    idvalidcheck = false
                    idbackvalidcheck = false
                } else{
                    warnEmailvalid.isVisible = true
                    warnEmailok.isVisible = false
                    warnEmail.isVisible = false
                    idvalidcheck = false
                    idbackvalidcheck = false
                }
            }

        })
        val pwregex = """^(?=.*\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#$%^&])[A-Za-z\d!@#$%^&]{8,24}$"""
        val pwPattern = Pattern.compile(pwregex ) //대소문자o,숫자o,공백x,특문:!@#$%^&만 가능

        pw.addTextChangedListener (object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                btn_join2.isEnabled = (namevalidcheck&& idvalidcheck && idbackvalidcheck && pwvalidcheck&& pwchkvalidcheck)
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                if(pwPattern.matcher(pw.text.toString()).matches() ){
                    warnPw.isVisible = false
                    warnPwvalid.isVisible = false
                    warnPwok.isVisible = true
                    pwvalidcheck = true
                } else if(pw.text.isEmpty()){
                    warnPw.isVisible = true
                    warnPwok.isVisible = false
                    warnPwvalid.isVisible = false
                    pwvalidcheck = false
                }else {
                    warnPwvalid.isVisible = true
                    warnPwok.isVisible = false
                    warnPw.isVisible = false
                    pwvalidcheck = false
                }
            }
        })

        pwCheck.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                btn_join2.isEnabled = (namevalidcheck&& idvalidcheck && idbackvalidcheck && pwvalidcheck&& pwchkvalidcheck)
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                if(pw.text.toString().equals(pwCheck.text.toString())){
                    warnPwCheck.isVisible = false
                    warnPwCheckok.isVisible = true
                    pwchkvalidcheck = true
                } else {
                    warnPwCheck.isVisible = true
                    warnPwCheckok.isVisible = false
                    pwchkvalidcheck = false
                }
            }
        })

    }

}
