package com.example.tutorial04.model

class FormDat ( private var studentId:String, private var year:String,
                private var semester:String,
                private var agreement:Boolean,){

        fun validateStudentId():ValidationResult{

                return if(studentId.isEmpty()){ValidationResult.Empty("Student ID is Empty !!") }
                else if(!studentId.startsWith("IT")){ValidationResult.Invalid("Should be Starting with IT")}
                else if(studentId.length<10){ValidationResult.Invalid("Student ID should have 10 Characters")}
                else if(studentId.length>10){ValidationResult.Invalid("Student ID should have 10 characters")}
                else{ValidationResult.Valid}
        }

        fun validateYear():ValidationResult{

                return if(year.isEmpty()){ValidationResult.Empty("Year is empty")}
                else{ValidationResult.Valid }

        }

        fun validateSemester():ValidationResult{

                return if(semester.isEmpty()){ValidationResult.Empty("Semester is empty")}
                else{ValidationResult.Valid }


        }

        fun validateAgreement():ValidationResult{

                return if(!agreement){ValidationResult.Invalid("You must agree for the terms and conditions")}
                else{ValidationResult.Valid}


        }



}


