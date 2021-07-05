package com.james54.moviedatabase.util

sealed class Resource<T>(data:T?,message:String?){

    class Success<T>(data: T?):Resource<T>(data,null)
    class Error<T>(message: String?):Resource<T>(null,message)

}
