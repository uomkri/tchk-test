package com.tchair.tchktest.mainpage

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.tchair.tchktest.datasource.DataSourceFactory
import com.tchair.tchktest.net.UserData
//import com.tchair.tchktest.datasource.UsersDataSource

class MainViewModel() : ViewModel() {



    private val dataSource = DataSourceFactory()

        val config = PagedList.Config.Builder()
            .setPageSize(30)
            .setPrefetchDistance(5)
            .setEnablePlaceholders(false)
            .build()
        val usersLiveData = LivePagedListBuilder(dataSource, config).build()


    fun getUsers() : LiveData<PagedList<UserData>> = usersLiveData



      fun fetchUsers(query: String){
          val search = query.trim()
            return dataSource.updateQuery(search)

      }

  }



