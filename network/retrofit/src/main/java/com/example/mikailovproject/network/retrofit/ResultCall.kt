package com.example.mikailovproject.network.retrofit

import android.content.Context
import okhttp3.Request
import okio.Timeout
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.IOException
import java.net.HttpURLConnection
import java.util.concurrent.TimeoutException

class ResultCall<T>(private val delegate: Call<T>, private val context: Context) : Call<Result<T>> {

    override fun enqueue(callback: Callback<Result<T>>) {
        delegate.enqueue(object : Callback<T> {
            override fun onResponse(call: Call<T>, response: Response<T>) {
                if (response.isSuccessful) {
                    callback.onResponse(
                        this@ResultCall, Response.success(
                            response.code(), Result.success(response.body()!!)
                        )
                    )
                } else {
                    val domainException = when (response.code()) {
                        HttpURLConnection.HTTP_BAD_REQUEST -> DomainException.BadRequestException(
                            context.getString(R.string.bad_request)
                        )
                        HttpURLConnection.HTTP_UNAUTHORIZED -> DomainException.UnauthorizedException(
                            context.getString(R.string.unauthorized)
                        )
                        HttpURLConnection.HTTP_FORBIDDEN -> DomainException.ForbiddenException(
                            context.getString(
                                R.string.forbidden
                            )
                        )
                        HttpURLConnection.HTTP_NOT_FOUND -> DomainException.NotFoundException(
                            context.getString(
                                R.string.not_found
                            )
                        )
                        HttpURLConnection.HTTP_SERVER_ERROR -> DomainException.ServerErrorException(
                            context.getString(R.string.server_error)
                        )
                        else -> DomainException.UnknownException(context.getString(R.string.unknown))
                    }

                    callback.onResponse(
                        this@ResultCall, Response.success(
                            Result.failure(domainException)
                        )
                    )
                }
            }

            override fun onFailure(call: Call<T>, t: Throwable) {
                val error = when (t) {
                    is IOException -> NetworkException.IOException(context.getString(R.string.no_connectivity))
                    is TimeoutException -> NetworkException.TimeoutException(context.getString(R.string.time_out))
                    else -> t
                }
                callback.onResponse(
                    this@ResultCall, Response.success(Result.failure(error))
                )
            }
        })
    }

    override fun isExecuted(): Boolean {
        return delegate.isExecuted
    }

    override fun execute(): Response<Result<T>> {
        return Response.success(Result.success(delegate.execute().body()!!))
    }

    override fun cancel() {
        delegate.cancel()
    }

    override fun isCanceled(): Boolean {
        return delegate.isCanceled
    }

    override fun clone(): Call<Result<T>> {
        return ResultCall(delegate.clone(), context)
    }

    override fun request(): Request {
        return delegate.request()
    }

    override fun timeout(): Timeout {
        return delegate.timeout()
    }
}