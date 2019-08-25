package com.example.searchserials.service

import com.example.searchserials.service.model.Serial
import com.example.searchserials.utils.getAsJsonObjectOrNull
import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import java.lang.reflect.Type


class SerialDeserialization : JsonDeserializer<Serial> {
    override fun deserialize(json: JsonElement?, typeOfT: Type?, context: JsonDeserializationContext?): Serial? {
        val jsonObject = json?.getAsJsonObjectOrNull()
        val showJsonObject = jsonObject?.get("show")?.getAsJsonObjectOrNull()
        val title = showJsonObject?.get("name")?.asString
        val genres = showJsonObject?.get("genres")?.asJsonArray?.map { jsonElement -> jsonElement.asString  }
        val imagesJsonObject = showJsonObject?.get("image")?.getAsJsonObjectOrNull()
        val image = imagesJsonObject?.get("medium")?.asString
        return Serial(title, genres, image)
    }
}