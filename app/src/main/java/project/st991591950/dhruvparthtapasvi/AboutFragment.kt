package project.st991591950.dhruvparthtapasvi

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.StringRes
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import project.st991591950.dhruvparthtapasvi.databinding.FragmentAboutBinding

class AboutFragment : Fragment() {

    private var _binding: FragmentAboutBinding? = null
    private val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        //return inflater.inflate(R.layout.fragment_about, container, false)

        _binding = FragmentAboutBinding.inflate(inflater, container, false)
        setActivityTitle(R.string.about_fragment_label)

        return binding.root
    }

    private fun Fragment.setActivityTitle(@StringRes id: Int) {
        (activity as? AppCompatActivity?)?.supportActionBar?.title = getString(id)
    }

}


