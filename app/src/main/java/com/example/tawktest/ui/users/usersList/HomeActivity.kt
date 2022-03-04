package com.example.tawktest.ui.users.usersList

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.example.tawktest.R
import com.example.tawktest.data.dataSource.local.DatabaseBuilder
import com.example.tawktest.data.dataSource.remote.RetrofitBuilder
import com.example.tawktest.data.dataSource.remote.Status.SUCCESS
import com.example.tawktest.data.dataSource.remote.Status.ERROR
import com.example.tawktest.data.dataSource.remote.Status.LOADING
import com.example.tawktest.data.dataSource.repository.UsersRepository
import com.example.tawktest.data.entity.UsersEntity
import com.example.tawktest.data.model.User
import com.example.tawktest.databinding.ActivityHomeBinding
import com.example.tawktest.ui.users.UsersViewModel
import com.example.tawktest.utils.ext.getViewModel
import com.example.tawktest.utils.ext.gone
import com.example.tawktest.utils.ext.toast
import com.example.tawktest.utils.ext.visible

class HomeActivity : AppCompatActivity() {
    private lateinit var binding: ActivityHomeBinding
    private lateinit var viewModel: UsersViewModel
    private lateinit var adapter: UsersAdapter
    private var usersList: MutableList<UsersEntity>? = null
    private val appDataBaseInstance by lazy {
        DatabaseBuilder.getInstance(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_home)
        binding.lifecycleOwner = this
        viewModel = getViewModel { UsersViewModel(UsersRepository(RetrofitBuilder.apiService, appDataBaseInstance.getUsersDao())) }
        binding.viewModel = viewModel

        initAdapter()
        initObserver()
    }

    private fun initObserver() {
        viewModel.getUsers().observe(this) {
            Log.d("TAG", "observe: $it")
            it?.let { resource ->
                Log.d("TAG", "Resource: $resource")
                when (resource.status) {
                    SUCCESS -> {
                        binding.rvUsers.visible()
                        // Stopping Shimmer Effect's animation after data is loaded to RVView
                        binding.shimmerFrameLayout.stopShimmerAnimation()
                        binding.shimmerFrameLayout.gone()
                        resource.data?.let { users ->
                            /*retrieveList(users)*/
                            usersList?.addAll(users)
                            adapter.notifyDataSetChanged()
                            Log.d("TAG", "initObserver: $users")
                        }
                    }
                    ERROR -> {
                        binding.rvUsers.visible()
                        // Stopping Shimmer Effect's animation after data is loaded to RVView
                        binding.shimmerFrameLayout.stopShimmerAnimation()
                        binding.shimmerFrameLayout.gone()
                        toast(it.message!!)
                    }
                    LOADING -> {
                        binding.rvUsers.gone()
                    }
                }
            }
        }

        viewModel.usersList.observe(this){
            Log.d("TAG", "initObserver with Internet: ${it.data}")
        }
    }

    private fun initAdapter() {
        usersList = mutableListOf()
        adapter = UsersAdapter(usersList!!, object : UsersAdapter.Interaction{
            override fun onItemClick(position: Int, item: UsersEntity) {

            }

            override fun onEdit(position: Int, item: UsersEntity) {

            }
        })
        binding.rvUsers.adapter = adapter
    }

    override fun onResume() {
        super.onResume()
        binding.shimmerFrameLayout.startShimmerAnimation()
    }

    override fun onPause() {
        binding.shimmerFrameLayout.stopShimmerAnimation()
        super.onPause()
    }

}