package project.st991591950.dhruvparthtapasvi.bookappointment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import project.st991591950.dhruvparthtapasvi.R
import project.st991591950.dhruvparthtapasvi.databinding.FragmentBookAppointmentBinding
import project.st991591950.dhruvparthtapasvi.databinding.FragmentSpecialistBinding


class BookAppointmentFragment : Fragment() {

    private var _binding: FragmentBookAppointmentBinding? = null
    private val binding get() = _binding!!


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentBookAppointmentBinding.inflate(inflater, container, false)
        val root: View = binding.root

        return root
    }

    override fun onViewCreated(itemView: View, savedInstanceState: Bundle?) {

        var mBundle: Bundle? = arguments
        var doctorName = mBundle!!.getString("doctorName")
        var clinicName = mBundle!!.getString("clinicName")
        var speciality = mBundle!!.getString("speciality")

        binding.SpecialistName.text = doctorName.toString()
        binding.textViewSpeciality.text = speciality.toString()
        binding.textViewClinicName.text = clinicName.toString()
        //Toast.makeText( BookAppointmentFragment().context, "abc.toString()",Toast.LENGTH_SHORT).show();


    }


}