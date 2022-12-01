package project.st991591950.dhruvparthtapasvi.login

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.fragment.app.viewModels
import project.st991591950.dhruvparthtapasvi.databinding.*
import com.firebase.ui.auth.AuthUI
import com.firebase.ui.auth.IdpResponse
import com.google.firebase.auth.FirebaseAuth
import project.st991591950.dhruvparthtapasvi.R

class LoginFragment : Fragment() {
    private val viewModel by viewModels<LoginViewModel>()
    private var fraagment = 0
    private var _binding: FragmentLoginBinding? = null
    companion object {
        const val TAG = "MainFragment"
        const val SIGN_IN_RESULT_CODE = 1001
    }

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentLoginBinding.inflate(inflater, container, false)

        val textView: TextView = binding.textWelcome

        textView.text = "Welcome to LetMeBoot"

        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

//        binding.CustLogin.setOnClickListener {
//            findNavController().navigate(R.id.action_SecondFragment_to_customerLogin)
//        }
//
//        binding.ProfLogin.setOnClickListener{
//            findNavController().navigate(R.id.action_SecondFragment_to_professionalLogin)
//        }
        observeAuthenticationState()
//        binding.CustLogin.setOnClickListener{
//            AuthState = 1
//            launchSignInFlow()
//        }
//        binding.ProfLogin.setOnClickListener{
//            AuthState = 2
//            launchSignInFlow()
//        }
//        binding.authButton.setOnClickListener{
//            launchSignInFlow()
//        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == SIGN_IN_RESULT_CODE) {
            val response = IdpResponse.fromResultIntent(data)
            if (resultCode == Activity.RESULT_OK) {
                // User successfully signed in
                Log.i(
                    TAG,
                    "Successfully signed in user ${FirebaseAuth.getInstance().currentUser?.displayName}!"
                )
            } else {
                // Sign in failed. If response is null the user canceled the
                // sign-in flow using the back button. Otherwise check
                // response.getError().getErrorCode() and handle the error.
                Log.i(TAG, "Sign in unsuccessful ${response?.error?.errorCode}")
            }
        }
    }

    private fun observeAuthenticationState() {
        val factToDisplay = viewModel.getFactToDisplay(requireContext())

        viewModel.authenticationState.observe(viewLifecycleOwner, Observer { authenticationState ->
            when (authenticationState) {
                LoginViewModel.AuthenticationState.AUTHENTICATED -> {

                    if (fraagment == 1){

                        findNavController().navigate(R.id.action_loginFragment_to_adminFragment)

                    }
                    else{
                       // findNavController().navigate(R.id.action_loginFragment_to_adminFragment)
                        findNavController().navigate(R.id.action_loginFragment_to_homeFragment)

                    }

                }
                else -> {

                    binding.authButton.text = "Login"
                    binding.authButton.setOnClickListener {
                        fraagment = 0
                        launchSignInFlow()
                    }
                    binding.textViewAdmin.setOnClickListener {
                        fraagment = 1
                        launchSignInFlow()
                    }
                }
            }
        })
    }

    private fun launchSignInFlow() {
        // Give users the option to sign in / register with their email
        // If users choose to register with their email,
        // they will need to create a password as well
        val providers = arrayListOf(
            AuthUI.IdpConfig.EmailBuilder().build()
            //
        )

        // Create and launch sign-in intent.
        // We listen to the response of this activity with the
        // SIGN_IN_RESULT_CODE code
        startActivityForResult(
            AuthUI.getInstance().createSignInIntentBuilder().setAvailableProviders(
                providers
            ).build(), SIGN_IN_RESULT_CODE
        )
    }

}