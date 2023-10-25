package com.fikrisandi.framework.network

import android.util.Log
import com.fikrisandi.provider.constant.ConstantVal
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.engine.android.Android
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging
import io.ktor.client.request.request
import io.ktor.http.ContentType
import io.ktor.http.HttpMethod
import io.ktor.http.contentType
import io.ktor.serialization.gson.gson
import javax.inject.Inject


class AppHttpClient @Inject constructor() {
    operator fun invoke() = HttpClient(Android) {
        engine {
            connectTimeout = 60_000
        }

        install(Logging) {
            logger = object : Logger {
                override fun log(message: String) {
                    Log.d("KtorHttpResponse", message)
                }

            }
        }

        install(ContentNegotiation) {
            gson {
                setPrettyPrinting()
                setLenient()
                disableHtmlEscaping()
            }
        }
    }
}

@Throws(Exception::class)
suspend inline fun <reified T> AppHttpClient.call(
    route: String,
    method: HttpMethod,
    params: Map<String, String> = mapOf()
): T {
    val result = this.invoke().request("${ConstantVal.BASE_URL}${route}") {
        this.method = method

        contentType(ContentType.Application.Json)

        url {
            params.forEach { (keys, value) ->
                parameters.append(keys, value)
            }
        }
    }
    return result.body()
}