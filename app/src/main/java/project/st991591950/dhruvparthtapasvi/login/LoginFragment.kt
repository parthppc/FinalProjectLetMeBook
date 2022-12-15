package project.st991591950.dhruvparthtapasvi.login

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.annotation.StringRes
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.firebase.ui.auth.AuthUI
import com.firebase.ui.auth.IdpResponse
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import project.st991591950.dhruvparthtapasvi.R
import project.st991591950.dhruvparthtapasvi.databinding.FragmentLoginBinding

@Suppress("DEPRECATION")
class LoginFragment : Fragment() {
    private var auth: FirebaseAuth? = null

    private val viewModel by viewModels<LoginViewModel>()
    private var fragment = 0
    private var _binding: FragmentLoginBinding? = null


    companion object {
        const val TAG = "MainFragment"
        const val SIGN_IN_RESULT_CODE = 1001
    }

    private val binding get() = _binding!!

    @SuppressLint("SetTextI18n")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        auth = Firebase.auth

        _binding = FragmentLoginBinding.inflate(inflater, container, false)

        val textView: TextView = binding.textWelcome

        textView.text = "Welcome to Dental Appointment Booking System"

        setActivityTitle(R.string.login_fragment_label)

        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        NavigateAuthenticationStateFragment()


    }

    @Deprecated("Deprecated in Java")
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == SIGN_IN_RESULT_CODE) {
            val response = IdpResponse.fromResultIntent(data)
            if (resultCode == Activity.RESULT_OK) {
                Log.i(
                    TAG,
                    "Successfully signed in user ${FirebaseAuth.getInstance().currentUser?.displayName}!"
                )
            } else {
              Log.i(TAG, "Sign in unsuccessful ${response?.error?.errorCode}")
            }
        }
    }

    private fun NavigateAuthenticationStateFragment() {

        viewModel.authenticationState.observe(viewLifecycleOwner, Observer { authenticationState ->
            when (authenticationState) {
                LoginViewModel.AuthenticationState.AUTHENTICATED -> {
                    if (fragment == 1 && FirebaseAuth.getInstance().currentUser?.email == "letmebookap@gmail.com"){
                        findNavController().navigate(R.id.action_loginFragment_to_adminFragment)
                    }
                    else{
                        findNavController().navigate(R.id.action_loginFragment_to_homeFragment)
                    }
                }
                else -> {

                    binding.authButton.setOnClickListener {

                        fragment = 0
                        launchSignInFlow()
                    }
                    binding.textViewAdmin.setOnClickListener {

                        fragment = 1

                        launchSignInFlow()
                    }
                }
            }
        })
    }

    private fun launchSignInFlow() {
        val providers = arrayListOf(
            AuthUI.IdpConfig.EmailBuilder().build()

        )
        startActivityForResult(
            AuthUI.getInstance().createSignInIntentBuilder().setAvailableProviders(
                providers
            ).build(), SIGN_IN_RESULT_CODE
        )


    }

    private fun Fragment.setActivityTitle(@StringRes id: Int) {
        (activity as? AppCompatActivity?)?.supportActionBar?.title = getString(id)
    }

}