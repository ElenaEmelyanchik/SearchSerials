package com.example.searchserials

import android.app.SearchManager
import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.Menu
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import javax.inject.Inject
import kotlinx.android.synthetic.main.activity_main.my_recycler_view as recyclerView

class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var viewModelFactory: SearchViewModelFactory

    val viewModel: SearchViewModel by lazy {
        ViewModelProviders.of(this, viewModelFactory).get(SearchViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        (application as App).component().inject(this)

        val viewManager = LinearLayoutManager(this)
        val viewAdapter = MyItemRecyclerViewAdapter(object:ItemFragment.OnListFragmentInteractionListener{
            override fun onListFragmentInteraction(item: Serial?) {
                Log.d(this.javaClass.name, item?.name)
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
                            viewModel.search(newText)
                        }
                    }
                    return true
                }

                override fun onQueryTextSubmit(query: String?): Boolean {
                    Log.d(this.javaClass.canonicalName, query)
                    return true
                }
            })
        }

        return true
    }
}
