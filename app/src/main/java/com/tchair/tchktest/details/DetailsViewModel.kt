package com.tchair.tchktest.details

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProviders
import com.tchair.tchktest.net.GithubApi
import com.tchair.tchktest.net.User
import kotlinx.coroutines.*
import java.lang.Exception

class DetailsViewModel : ViewModel() {

    private val supervisorJob = SupervisorJob()
    private val scope = CoroutineScope(supervisorJob + getJobErrorHandler())

    private var _response = MutableLiveData<User>()
    val response : LiveData<User>
        get() = _response

    fun getUser(login: String?) {
        scope.launch {
            var getUserDeferred = GithubApi.retrofitService.getUserData(login)
            try {
                var user = getUserDeferred.await()
                _response.postValue(user)
                Log.i("USER", user.toString())
            } catch(e: Exception) {
                Log.e("ERROR", e.message)
            }
        }
    }

    private fun getJobErrorHandler() = CoroutineExceptionHandler { _, e ->
        Log.e("DSERROR", "An error happened: $e")
    }

    /*override fun onCleared() {
        super.onCleared()
        scope.cancel()
    }*/


}