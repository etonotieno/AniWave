package sample.aniwave.data.source.network

import com.google.gson.GsonBuilder
import com.google.gson.JsonParseException
import retrofit2.HttpException
import java.io.Reader

inline fun <reified T> Reader.readerToObject(): T {
    val gson = GsonBuilder()
        .create()
    return gson.fromJson(this, T::class.java)
}

inline fun <reified T> convertErrorBody(throwable: HttpException): T? {
    return try {
        throwable.response()?.errorBody()?.charStream()?.use { it.readerToObject() }
    } catch (e: JsonParseException) {
        null
    }
}
