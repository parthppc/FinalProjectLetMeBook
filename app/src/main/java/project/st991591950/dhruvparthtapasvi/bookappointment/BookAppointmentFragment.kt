package project.st991591950.dhruvparthtapasvi.bookappointment

import android.app.DatePickerDialog
import android.app.DatePickerDialog.OnDateSetListener
import android.icu.text.SimpleDateFormat
import android.icu.util.Calendar
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import project.st991591950.dhruvparthtapasvi.databinding.FragmentBookAppointmentBinding
import java.util.*


class BookAppointmentFragment : Fragment() {

    private var _binding: FragmentBookAppointmentBinding? = null
    private val binding get() = _binding!!

    val myCalendar: Calendar = Calendar.getInstance()
   // var editText: EditText? = null

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

        val date =
            OnDateSetListener { view, year, month, day ->
                myCalendar[Calendar.YEAR] = year
                myCalendar[Calendar.MONTH] = month
                myCalendar[Calendar.DAY_OF_MONTH] = day
                updateLabel()
            }

        binding.editTextDate.setOnClickListener(View.OnClickListener {
            BookAppointmentFragment().context?.let { it1 ->
                DatePickerDialog(
                    it1, date,
                    myCalendar[Calendar.YEAR],
                    myCalendar[Calendar.MONTH], myCalendar[Calendar.DAY_OF_MONTH]
                ).show()
            }
        })
    }

    private fun updateLabel() {
        val myFormat = "MM/dd/yy"
        val dateFormat = SimpleDateFormat(myFormat, Locale.US)
        binding.editTextDate.setText(dateFormat.format(myCalendar.time))
    }


}