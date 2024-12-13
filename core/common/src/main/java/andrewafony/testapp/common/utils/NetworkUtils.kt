package andrewafony.testapp.common.utils

import retrofit2.Response

inline fun <T : Response<S>, S> T.onSuccess(block: (S) -> Unit): T {
    this.body()?.let(block)
    return this
}