package org.albert.x09_globochat

import android.os.Bundle
import android.view.*
import androidx.core.view.MenuProvider
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.NavHostFragment
import org.albert.x09_globochat.databinding.FragmentChatListBinding

class ChatListFragment : Fragment() {
    private var _binding: FragmentChatListBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentChatListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        // The 'viewLifecycleOwner' is used to make the menu appear only for the current fragment.
        requireActivity().addMenuProvider(menuProvider, viewLifecycleOwner)
    }

    private val menuProvider = object : MenuProvider {
        override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
            menuInflater.inflate(R.menu.chat_list_menu, menu)
        }

        override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
            return when (menuItem.itemId) {
                R.id.action_settings -> {
                    val navHostFragment =
                        requireActivity().supportFragmentManager.findFragmentById(R.id.nav_host_frag) as NavHostFragment
                    val navController = navHostFragment.navController
                    val action = ChatListFragmentDirections.actionChatListToSettings()
                    navController.navigate(action)
                    true
                }
                else -> false
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
