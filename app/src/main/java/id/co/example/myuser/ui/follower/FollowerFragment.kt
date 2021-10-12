package id.co.example.myuser.ui.follower

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import id.co.example.myuser.databinding.FragmentFollowerBinding
import id.co.example.myuser.helper.Constant.USERNAME
import id.co.example.myuser.ui.adapter.UserFollowerAdapter
import id.co.example.myuser.ui.viewmodel.UserViewModel
import id.co.example.myuser.ui.viewmodel.ViewModelFactory


class FollowerFragment : Fragment() {

    private var _binding: FragmentFollowerBinding? = null
    private val binding get() = _binding
    private var userFollowerAdapter = UserFollowerAdapter()
    private lateinit var userViewModel: UserViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentFollowerBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        userViewModel = obtainViewModel(requireActivity())
        val username = arguments?.getString(USERNAME)
        userViewModel.getFollower(username.toString())

        userViewModel.listFollower.observe(requireActivity(), {
            userFollowerAdapter.setFollower(it)
        })

        userViewModel.isLoading.observe(requireActivity(), {
            showLoading(it)
        })
        recyclerFollowerUser()
    }


    private fun recyclerFollowerUser() {
        with(binding?.rvFollower) {
            this?.adapter?.notifyDataSetChanged()
            this?.layoutManager = LinearLayoutManager(context)
            this?.setHasFixedSize(true)
            this?.adapter = userFollowerAdapter
        }
    }

    private fun showLoading(isLoading: Boolean) {
        binding?.progressBar?.visibility = if (isLoading) View.VISIBLE else View.GONE
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    private fun obtainViewModel(fragment: FragmentActivity): UserViewModel {
        val factory = ViewModelFactory.getInstance(fragment.application)
        return ViewModelProvider(fragment, factory).get(UserViewModel::class.java)
    }

}