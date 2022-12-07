package project.st991591950.dhruvparthtapasvi

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.StringRes
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.findNavController
import com.google.firebase.auth.FirebaseAuth
import project.st991591950.dhruvparthtapasvi.databinding.FragmentHomeBinding


/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null

    //private val specialistbutton: Button = button_Specialists
    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
//        val homeViewModel =
//            ViewModelProvider(this).get(HomeViewModel::class.java)
//
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
         val root: View = binding.root

        setActivityTitle(R.string.home_fragment_label)
        //setActivityTitle("Home")
        return root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setActivityTitle(R.string.home_fragment_label)

        binding.btnLogout.setOnClickListener{
            FirebaseAuth.getInstance().signOut();
            findNavController().navigate(R.id.loginFragment)
        }

        binding.buttonSpecialists.setOnClickListener{

            findNavController().navigate(R.id.action_homeFragment_to_specialistFragment)
        }


        binding.buttonMyAppointments.setOnClickListener{
            findNavController().navigate(R.id.action_homeFragment_to_myAppointmentsFragment)
}
        binding.profilebtn.setOnClickListener{

            findNavController().navigate(R.id.action_homeFragment_to_profileFragment)

        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    //code to apply title name from the string
    private fun Fragment.setActivityTitle(@StringRes id: Int) {
        (activity as? AppCompatActivity?)?.supportActionBar?.title = getString(id)
    }

    //code to directly apply the title name in the fragment itself.
    fun Fragment.setActivityTitle(title: String) {
        (activity as? AppCompatActivity?)?.supportActionBar?.title = title
    }

}