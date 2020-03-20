package com.tchair.tchktest.datasource

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import com.tchair.tchktest.net.UserData
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay


class DataSourceFactory(private var query: String = ""): DataSource.Factory<Int, UserData>() {

    val dataSource = MutableLiveData<UsersSearchDataSource>()

    private val scope = CoroutineScope(Dispatchers.Default)

    override fun create(): DataSource<Int, UserData> {
        val dataSource = UsersSearchDataSource(scope, query)
        this.dataSource.postValue(dataSource)
        return dataSource
    }

    fun getQuery() = query

    fun getSource() = dataSource.value



    fun updateQuery(query: String){
        this.query = query
        Log.i("query", this.query)
        getSource()?.refresh()
    }

}