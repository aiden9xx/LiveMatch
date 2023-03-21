package data.remote

sealed class Resource<T>(val data: T? = null, val code: Int? = null, val message: String? = null) {
    class Success<T>(data: T) : Resource<T>(data = data)
    class Error<T>(message: String, code: Int? = null, data: T? = null) : Resource<T>(
        data,
        code = code,
        message = message
    )

    class Loading<T>(data: T? = null) : Resource<T>(data = data)
}
