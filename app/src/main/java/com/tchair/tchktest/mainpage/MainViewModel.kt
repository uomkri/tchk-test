package com.tchair.tchktest.mainpage

import android.util.Log
import android.view.GestureDetector
import android.view.MotionEvent
import android.view.View
import android.widget.AdapterView
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import androidx.recyclerview.widget.RecyclerView
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

open class RecyclerItemClickListener(recyclerView: RecyclerView, private val mListener: OnItemClickListener?) : RecyclerView.OnItemTouchListener {
    private var mGestureDetector: GestureDetector = GestureDetector(recyclerView.context, object : GestureDetector.SimpleOnGestureListener() {
        override fun onSingleTapUp(e: MotionEvent): Boolean {
            return true
        }

        override fun onLongPress(e: MotionEvent) {}
    })

    interface OnItemClickListener {
        fun onItemClick(view: View, position: Int)
    }

    override fun onInterceptTouchEvent(view: RecyclerView, e: MotionEvent): Boolean {
        val childView = view.findChildViewUnder(e.x, e.y)
        if (childView != null && mListener != null && mGestureDetector.onTouchEvent(e)) {
            mListener.onItemClick(childView, view.getChildAdapterPosition(childView))
            return true
        }
        return false
    }

    override fun onTouchEvent(view: RecyclerView, motionEvent: MotionEvent) {}
    override fun onRequestDisallowInterceptTouchEvent(disallowIntercept: Boolean) {}
}



