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
package sample.aniwave.data.source.network.model

import com.google.gson.annotations.SerializedName

/**
 * An error response returned by [sample.aniwave.data.source.network.AnimeApi]
 */
data class AnimeApiError(
    @SerializedName("error")
    val error: String?,
    @SerializedName("message")
    val message: String?,
    @SerializedName("report_url")
    val reportUrl: String?,
    @SerializedName("status")
    val status: Int?,
    @SerializedName("type")
    val type: String?,
)
