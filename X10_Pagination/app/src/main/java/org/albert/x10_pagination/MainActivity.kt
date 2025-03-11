package org.albert.x10_pagination

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.room.Room
import androidx.room.RoomDatabase
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import org.albert.x10_pagination.adapters.ContactAdapter
import org.albert.x10_pagination.adapters.CountryAdapter
import org.albert.x10_pagination.data.ContactDatabase
import org.albert.x10_pagination.databinding.ActivityMainBinding
import org.albert.x10_pagination.models.Contact
import org.albert.x10_pagination.utils.CountriesDb
import org.albert.x10_pagination.viewmodels.ContactViewModel
import org.albert.x10_pagination.viewmodels.MainActivityViewModel

class MainActivity : AppCompatActivity() {
    private val TAG = this::class.simpleName
    private lateinit var binding: ActivityMainBinding

    private lateinit var viewModel: ContactViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val adapter = ContactAdapter()
        val rv = binding.countryRv // recyclerView
        rv.adapter = adapter
        rv.layoutManager = LinearLayoutManager(this)

        val db = Room.databaseBuilder(
            applicationContext,
            ContactDatabase::class.java,
            "contacts.sqlite"
        ).allowMainThreadQueries().build()

        viewModel = ViewModelProvider(
            this,
            ContactViewModelFactory(db)
        )[ContactViewModel::class.java]

        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.contacts.collectLatest { pagingData ->
                    Log.d(TAG, "New paging data observed...")
                    adapter.submitData(pagingData)
                }
            }
        }

        binding.delTopBtn.setOnClickListener {
            deleteTop(it)
        }

        binding.addBtn.setOnClickListener {
            addContact(it)
        }
    }

    class ContactViewModelFactory(private val db: ContactDatabase) : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(ContactViewModel::class.java)) {
                @Suppress("UNCHECKED_CAST")
                return ContactViewModel(db) as T
            }
            throw IllegalArgumentException("Unknown ViewModel class")
        }
    }

    fun deleteTop(view: View) {
        val topContact = viewModel.contactDao.getTopContact()
        viewModel.contactDao.delete(topContact)
    }

    val firstNames = arrayOf("Steve", "Bill", "Mike", "George", "John", "Chris")
    val lastNames = arrayOf("Mathers", "Hanlon", "Taylor", "Smith", "Trevolta")

    fun addContact(view: View?) {
        val name = "${firstNames.random()} ${lastNames.random()}";
        val lastId = viewModel.contactDao.getLastId()
        Log.i(TAG, "lastId is ${lastId}")
        val contact = Contact(lastId + 1, name)

        Log.i(TAG, "inserting contact to the database ${contact}")
        viewModel.contactDao.insertContact(contact)
    }
}