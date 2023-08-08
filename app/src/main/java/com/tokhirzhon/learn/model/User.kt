package com.tokhirzhon.learn.model

class User {
    private lateinit var email: String
    private lateinit var passsword: String
    private lateinit var cityName: String
    private lateinit var schoolName: String
    private lateinit var schoolNumber: String
    private lateinit var classNum: String

    public fun User() {

    }

    public fun User(
        email: String,
        passsword: String,
        cityName: String,
        schoolName: String,
        schoolNumber: String,
        classNum: String
    ) {
        this.email = email
        this.passsword = passsword
        this.cityName = cityName
        this.schoolName = schoolName
        this.schoolNumber = schoolNumber
        this.classNum = classNum
    }

    fun setEmail(email: String) {
        this.email = email
    }

    fun getEmail():String {
        return email
    }

    fun setPassword(passsword: String) {
        this.passsword = passsword
    }

    fun getPassword() : String{
        return passsword
    }

    fun setCityName(cityName: String) {
        this.cityName = cityName
    }

    fun getCityName():String {
        return cityName
    }

    fun setSchoolName(schoolName: String) {
        this.schoolName = schoolName
    }
    fun getSchoolName(): String {
        return  schoolName
    }
}
