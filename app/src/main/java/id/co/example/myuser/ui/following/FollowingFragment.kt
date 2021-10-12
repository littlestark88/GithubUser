package id.co.example.myuser.ui.following

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import id.co.example.myuser.databinding.FragmentFollowingBinding
import id.co.example.myuser.helper.Constant.USERNAME
import id.co.example.myuser.ui.adapter.UserFollowingAdapter
import id.co.example.myuser.ui.viewmodel.UserViewModel

class FollowingFragment : Fragment() {

    private var _binding: FragmentFollowingBinding? = null
    private val binding get() = _binding
    private var userFollowingAdapter = UserFollowingAdapter()
    private val userViewModel: UserViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentFollowingBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val username = arguments?.getString(USERNAME)
        userViewModel.getFollowing(username.toString())

        userViewModel.listFollowing.observe(requireActivity(), {
            userFollowingAdapter.setFollowing(it)
        })

        userViewModel.isLoading.observe(requireActivity(), {
            showLoading(it)
        })
        recyclerFollowingUser()
    }

    private fun showLoading(isLoading: Boolean) {
        binding?.progressBar?.visibility = if (isLoading) View.VISIBLE else View.GONE
    }

    private fun recyclerFollowingUser() {
        with(binding?.rvFollowing) {
            this?.adapter?.notifyDataSetChanged()
            this?.layoutManager = LinearLayoutManager(context)
            this?.setHasFixedSize(true)
            this?.adapter = userFollowingAdapter
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}