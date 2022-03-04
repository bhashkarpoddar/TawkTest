package com.example.tawktest.utils.ext

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.*
import com.example.tawktest.data.dataSource.remote.Resource
import com.example.tawktest.data.dataSource.remote.Status
import com.example.tawktest.utils.BaseViewModelFactory
import kotlinx.coroutines.Dispatchers

/**Create Factory Class at runtime*/
inline fun <reified T : ViewModel> Fragment.getViewModel(noinline creator: (() -> T)? = null): T {
    return if (creator == null)
        ViewModelProvider(this).get(T::class.java)
    else
        ViewModelProvider(this, BaseViewModelFactory(creator)).get(T::class.java)
}

inline fun <reified T : ViewModel> FragmentActivity.getViewModel(noinline creator: (() -> T)? = null): T {
    return if (creator == null)
        ViewModelProvider(this).get(T::class.java)
    else
        ViewModelProvider(this, BaseViewModelFactory(creator)).get(T::class.java)
}

/*fun <T, A> resultLiveData(databaseQuery: () -> LiveData<T>,
                          networkCall: suspend () -> Resource<A>,
                          saveCallResult: suspend (A?) -> Unit) = liveData(Dispatchers.IO) {
        emit(Resource.loading(data = null))
        val source = databaseQuery.invoke().map { Resource.success(data = it) }
        emitSource(source)

        val responseStatus = networkCall.invoke()
        if (responseStatus.status == Status.SUCCESS) {
            saveCallResult(responseStatus.data)
        } else if (responseStatus.status == Status.ERROR) {
            emit(Resource.error(data = null, message = responseStatus.message ?: "Error Occurred!"))
            emitSource(source)
        }
    }

fun <T, A> mResultLiveData(databaseQuery: () -> LiveData<T>,
                          networkCall: suspend () -> Result<A>,
                          saveCallResult: suspend (A?) -> Unit): LiveData<Result<T>> =
    liveData(Dispatchers.IO) {
        emit(Result.loading())
        val source = databaseQuery.invoke().map { Result.success(it) }
        emitSource(source)

        val responseStatus = networkCall.invoke()
        if (responseStatus.status == Result.Status.SUCCESS) {
            saveCallResult(responseStatus.data)
        } else if (responseStatus.status == Result.Status.ERROR) {
            emit(Result.error<T>(responseStatus.message ?: "Unknown error"))
            emitSource(source)
        }
    }*/
