package com.example.mizu.model.storage_utils

import androidx.datastore.core.Serializer
import com.example.mizu.features.homescreen.utils.StreakClass
import com.example.mizu.features.homescreen.utils.StreakMonthClass
import com.example.mizu.features.homescreen.utils.UserSettings
import com.example.mizu.features.homescreen.utils.UserValues
import com.example.mizu.features.homescreen.utils.WaterAmount
import kotlinx.serialization.SerializationException
import kotlinx.serialization.json.Json
import java.io.InputStream
import java.io.OutputStream


object SerializerStreak:Serializer<StreakClass> {
    override val defaultValue: StreakClass
        get() = StreakClass()

    override suspend fun readFrom(input: InputStream): StreakClass {
        return try {
            Json.decodeFromString(
                deserializer = StreakClass.serializer(),
                string = input.readBytes().decodeToString()
            )
        }catch (e:SerializationException){
            e.printStackTrace()
            defaultValue
        }
    }

    override suspend fun writeTo(t: StreakClass, output: OutputStream) {
        output.write(
            Json.encodeToString(
                serializer = StreakClass.serializer(),
                value = t
            ).encodeToByteArray()
        )
    }
}

object SerializerStreakMonth:Serializer<StreakMonthClass> {
    override val defaultValue: StreakMonthClass
        get() = StreakMonthClass()

    override suspend fun readFrom(input: InputStream): StreakMonthClass {
        return try {
            Json.decodeFromString(
                deserializer = StreakMonthClass.serializer(),
                string = input.readBytes().decodeToString()
            )
        }catch (e:SerializationException){
            e.printStackTrace()
            defaultValue
        }
    }

    override suspend fun writeTo(t: StreakMonthClass, output: OutputStream) {
        output.write(
            Json.encodeToString(
                serializer = StreakMonthClass.serializer(),
                value = t
            ).encodeToByteArray()
        )
    }
}

object SerializerUserSetting:Serializer<UserSettings> {
    override val defaultValue: UserSettings
        get() = UserSettings()

    override suspend fun readFrom(input: InputStream): UserSettings {
        return try {
            Json.decodeFromString(
                deserializer = UserSettings.serializer(),
                string = input.readBytes().decodeToString()
            )
        }catch (e:SerializationException){
            e.printStackTrace()
            defaultValue
        }
    }

    override suspend fun writeTo(t: UserSettings, output: OutputStream) {
        output.write(
            Json.encodeToString(
                serializer = UserSettings.serializer(),
                value = t
            ).encodeToByteArray()
        )
    }
}


object SerializerWaterAmount:Serializer<WaterAmount> {
    override val defaultValue: WaterAmount
        get() = WaterAmount()

    override suspend fun readFrom(input: InputStream): WaterAmount {
        return try {
            Json.decodeFromString(
                deserializer = WaterAmount.serializer(),
                string = input.readBytes().decodeToString()
            )
        }catch (e:SerializationException){
            e.printStackTrace()
            defaultValue
        }
    }

    override suspend fun writeTo(t: WaterAmount, output: OutputStream) {
        output.write(
            Json.encodeToString(
                serializer = WaterAmount.serializer(),
                value = t
            ).encodeToByteArray()
        )
    }
}


object SerializerUserValues:Serializer<UserValues> {
    override val defaultValue: UserValues
        get() = UserValues()

    override suspend fun readFrom(input: InputStream): UserValues {
        return try {
            Json.decodeFromString(
                deserializer = UserValues.serializer(),
                string = input.readBytes().decodeToString()
            )
        }catch (e:SerializationException){
            e.printStackTrace()
            defaultValue
        }
    }

    override suspend fun writeTo(t: UserValues, output: OutputStream) {
        output.write(
            Json.encodeToString(
                serializer = UserValues.serializer(),
                value = t
            ).encodeToByteArray()
        )
    }
}
