package com.example.tutorial04

import android.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.Spinner
import android.widget.TextView
import com.example.tutorial04.model.FormDat
import com.example.tutorial04.model.FormData
import com.example.tutorial04.model.ValidationResult

class MainActivity : AppCompatActivity() {

    lateinit var edtStudentId:EditText
    lateinit var spnYear:Spinner
    lateinit var spnSemester:Spinner
    lateinit var cbAgree:CheckBox
    lateinit var btnSubmit:Button
    private var count = 0;

    //fun displayAlert
    fun displayAlert(title:String, message:String){
        val builder = AlertDialog.Builder(this)
        builder.setTitle(title)
        builder.setMessage(message)
        builder.setPositiveButton("OK") { dialog, which ->
            // Do something when the "OK" button is clicked
        }
        val dialog = builder.create()
        dialog.show()
    }



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        edtStudentId = findViewById(R.id.edtStudentId)
        spnYear = findViewById(R.id.spnYear)
        spnSemester = findViewById(R.id.spnSemester)
        cbAgree = findViewById(R.id.cbAgree)
        btnSubmit = findViewById(R.id.btnSubmit)
    }

    override fun onResume() {
        super.onResume()

        btnSubmit.setOnClickListener(View.OnClickListener {

            val myForm = FormDat(edtStudentId.text.toString(),
                                 spnYear.selectedItem.toString(),
                                 spnSemester.selectedItem.toString(),
                                 cbAgree.isChecked)

            val studentIDValidation = myForm.validateStudentId()
            val yearValidation = myForm.validateYear()
            val semesterValidation = myForm.validateSemester()
            val agreementValidation = myForm.validateAgreement()

            when(studentIDValidation){
                is ValidationResult.Valid->{
                    count++

                }
                is ValidationResult.Invalid->{
                    edtStudentId.error=studentIDValidation.errorMessage

                }
                is ValidationResult.Empty->{
                    edtStudentId.error=studentIDValidation.errorMessage

                }
            }

            when(yearValidation){
                is ValidationResult.Valid->{
                    count++

                }
                is ValidationResult.Invalid->{

                    val tv:TextView = spnYear.selectedView as TextView
                    tv.error=""
                    tv.text=yearValidation.errorMessage

                }
                is ValidationResult.Empty->{

                    val tv:TextView = spnYear.selectedView as TextView
                    tv.error=""
                    tv.text=yearValidation.errorMessage

                }

            }

            when(semesterValidation){
                is ValidationResult.Valid->{
                    count++

                }
                is ValidationResult.Invalid->{

                    val tv:TextView = spnSemester.selectedView as TextView
                    tv.error=""
                    tv.text=semesterValidation.errorMessage

                }
                is ValidationResult.Empty->{

                    val tv:TextView = spnSemester.selectedView as TextView
                    tv.error=""
                    tv.text=semesterValidation.errorMessage

                }

            }

            when(agreementValidation) {
                is ValidationResult.Valid -> {
                    count++

                }
                is ValidationResult.Invalid -> {

                    displayAlert("error",agreementValidation.errorMessage)

                }
                is ValidationResult.Empty -> {


                }
            }

            if(count==4) {
                displayAlert("Success","You have successfully Registered !!")

                FormData(edtStudentId.text.toString(),
                         Integer.parseInt(spnYear.selectedItem.toString()),
                         spnSemester.selectedItem.toString(),
                         cbAgree.isChecked)
            }
        })
    }
}