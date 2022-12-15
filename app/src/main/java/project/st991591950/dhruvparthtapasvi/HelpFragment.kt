package project.st991591950.dhruvparthtapasvi

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.StringRes
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import project.st991591950.dhruvparthtapasvi.databinding.FragmentHelpBinding

class HelpFragment : Fragment() {

    private var _binding: FragmentHelpBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        //return inflater.inflate(R.layout.fragment_help, container, false)

        _binding = FragmentHelpBinding.inflate(inflater, container, false)
        setActivityTitle(R.string.help_fragment_label)

        return binding.root
    }

    private fun Fragment.setActivityTitle(@StringRes id: Int) {
        (activity as? AppCompatActivity?)?.supportActionBar?.title = getString(id)
    }

}