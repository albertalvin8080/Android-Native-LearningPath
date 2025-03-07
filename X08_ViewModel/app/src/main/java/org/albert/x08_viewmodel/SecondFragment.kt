package org.albert.x08_viewmodel

import android.os.Bundle
import android.view.Gravity
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.LinearLayout
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import org.albert.x08_viewmodel.databinding.FragmentSecondBinding
import org.albert.x08_viewmodel.service.DataManager
import org.albert.x08_viewmodel.viewmodel.SecondFragmentViewModel

/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class SecondFragment : Fragment() {

    private var _binding: FragmentSecondBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!
    private lateinit var checkboxContainer: LinearLayout

    private val viewModel by lazy {
        ViewModelProvider(this)[SecondFragmentViewModel::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSecondBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        checkboxContainer = binding.checkboxContainer

        if (savedInstanceState != null)
            viewModel.restoreState(savedInstanceState)
        viewModel.newlyCreated = false

        populateCheckboxes()
    }

    private fun onCheck(view: View) {
        val box = view as CheckBox
        val msg = box.text.toString()

        if (box.isChecked)
            viewModel.checkedMessages.add(msg)
        else
            viewModel.checkedMessages.remove(msg)
    }

    private fun populateCheckboxes() {
        for (msg in DataManager.messages) {
            val checkBox = CheckBox(requireContext())
            checkBox.text = msg
            checkBox.id = View.generateViewId() // generate a unique ID for each checkbox
            checkBox.setOnClickListener(this::onCheck)

            if (viewModel.checkedMessages.indexOf(msg) != -1)
                checkBox.isChecked = true

            checkboxContainer.addView(checkBox)
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)

        viewModel.saveState(outState)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}