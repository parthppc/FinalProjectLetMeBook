package project.st991591950.dhruvparthtapasvi

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.google.firebase.auth.FirebaseAuth
import project.st991591950.dhruvparthtapasvi.databinding.FragmentAdminBinding
import project.st991591950.dhruvparthtapasvi.databinding.FragmentHomeBinding


class AdminFragment : Fragment() {

    private var _binding: FragmentAdminBinding? = null

    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentAdminBinding.inflate(inflater, container, false)
        val root: View = binding.root

        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnLogout.setOnClickListener{
            FirebaseAuth.getInstance().signOut();
            findNavController().navigate(R.id.loginFragment)
        }

    }


}