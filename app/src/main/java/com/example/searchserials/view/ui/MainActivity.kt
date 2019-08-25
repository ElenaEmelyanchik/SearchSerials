package com.example.searchserials.view.ui

import android.app.SearchManager
import android.content.Context
import android.os.Bundle
import android.view.Menu
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.searchserials.App
import com.example.searchserials.R
import com.example.searchserials.service.model.Serial
import com.example.searchserials.utils.launchActivity
import com.example.searchserials.view.adapter.MyItemRecyclerViewAdapter
import com.example.searchserials.viewmodel.SearchViewModel
import com.example.searchserials.viewmodel.SearchViewModelFactory
import javax.inject.Inject
import kotlinx.android.synthetic.main.activity_main.my_recycler_view as recyclerView

class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var viewModelFactory: SearchViewModelFactory

    val viewModel: SearchViewModel by lazy {
        ViewModelProvider(this, viewModelFactory).get(SearchViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        (application as App).component().inject(this)

        val viewManager = LinearLayoutManager(this)
        val viewAdapter = MyItemRecyclerViewAdapter(object :
            ItemFragment.OnListFragmentInteractionListener {
            override fun onListFragmentInteraction(item: Serial?) {
                launchActivity<VideoActivity>()
            }
        })

        (recyclerView as RecyclerView).apply {
            setHasFixedSize(true)
            layoutManager = viewManager
            adapter = viewAdapter

        }

        viewModel.serials().observe(this, Observer {
            viewAdapter.updateValue(it)
        })
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.search_menu, menu)
        val searchManager = getSystemService(Context.SEARCH_SERVICE) as SearchManager
        (menu.findItem(R.id.search).actionView as SearchView).apply {
            setSearchableInfo(searchManager.getSearchableInfo(componentName))
            setIconifiedByDefault(false)
            setSubmitButtonEnabled(true)
            setQueryRefinementEnabled(true)
            setOnQueryTextListener(object:SearchView.OnQueryTextListener{
                override fun onQueryTextChange(newText: String?): Boolean {
                    newText?.apply {
                        if(length > 2){
                            viewModel.search(this)
                        }
                    }
                    return true
                }

                override fun onQueryTextSubmit(query: String?): Boolean {
                    query?.apply {
                        if (length > 2) {
                            viewModel.search(this)
                        }
                    }
                    return true
                }
            })
        }

        return true
    }
}
