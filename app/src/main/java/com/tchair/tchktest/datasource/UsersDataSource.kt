package com.tchair.tchktest.datasource

import android.util.Log
import androidx.paging.PageKeyedDataSource
import com.tchair.tchktest.net.GithubApi
import com.tchair.tchktest.net.UserData
import kotlinx.coroutines.*



class UsersSearchDataSource(private val scope: CoroutineScope, private var query: String = "moj") : PageKeyedDataSource<Int, UserData>() {


    private var supervisorJob = SupervisorJob()

    override fun loadInitial(
        params: LoadInitialParams<Int>,
        callback: LoadInitialCallback<Int, UserData>
    ) {
        Log.i("DS", "USED SEARCH")
        Log.i("query", "Got query $query")

            //val getDataDeferred = GithubApi.retrofitService.getSearchData(query)
                runQuery(1) {
                    callback.onResult(it, null, 2)
                }

    }


    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, UserData>) {

        val page = params.key

            runQuery(page) {
                callback.onResult(it, page + 1)
            }

    }

    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Int, UserData>) {

    }



    override fun invalidate() {
        super.invalidate()
        scope.cancel()
    }

    fun refresh() {
        this.invalidate()
        Log.i("REFR", "called refresh")
    }

    fun runQuery(page: Int, callback:(List<UserData>) -> Unit){
        Log.i("query", "query ran")
        if(query.isNotEmpty()){
        scope.launch(getJobErrorHandler() + supervisorJob) {
            delay(200)
            val users = GithubApi.retrofitService.getSearchData(query, page).await()
            Log.i("users", users.items.toString())
            callback(users.items)
            }
        }

    }

    private fun getJobErrorHandler() = CoroutineExceptionHandler { _, e ->
        Log.e("DSERROR", "An error happened: $e")
    }

}



