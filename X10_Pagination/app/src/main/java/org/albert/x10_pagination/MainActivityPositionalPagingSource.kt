package org.albert.x10_pagination

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import org.albert.x10_pagination.adapters.CountryAdapter
import org.albert.x10_pagination.databinding.ActivityMainBinding
import org.albert.x10_pagination.utils.CountriesDb
import org.albert.x10_pagination.viewmodels.MainActivityViewModel

class MainActivityPositionalPagingSource : AppCompatActivity() {
    private val TAG = this::class.simpleName
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        CountriesDb.initialize(this)

        val adapter = CountryAdapter()
        val rv = binding.countryRv
        rv.adapter = adapter
        rv.layoutManager = LinearLayoutManager(this)

//        val viewModel = MainActivityViewModel()
        val viewModel = ViewModelProvider(this).get(MainActivityViewModel::class.java)

        // lifecycleScope is lifecycle aware, different from Handler(Looper.getMainLooper()).
        lifecycleScope.launch {
            // repeatOnLifecycle(...) is used to make the coroutine run only when the
            // chosen lifecycle is reached for this activity/fragment.
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                // Only triggered when the PagingData object changes.
                // The PagingDataAdapter handles the loading of new pages internally.
                viewModel.countries.collectLatest { pagingData ->
                    Log.v(TAG, "Observed new PagingData from viewModel...")
                    // who emits this new pagingData is the Pager(...) created inside the viewModel.
                    adapter.submitData(pagingData)
                }
            }
        }

        binding.delTopBtn.setOnClickListener {
            viewModel.deleteById(++topId)
        }
    }

    private var topId = 0;
}