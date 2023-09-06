/*
 * Copyright 2023 Eton Otieno
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
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
