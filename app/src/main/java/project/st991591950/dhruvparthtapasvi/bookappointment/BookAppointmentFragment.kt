package project.st991591950.dhruvparthtapasvi.bookappointment

import android.app.DatePickerDialog
import android.app.DatePickerDialog.OnDateSetListener
import android.icu.text.SimpleDateFormat
import android.icu.util.Calendar
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.fragment.app.Fragment
import project.st991591950.dhruvparthtapasvi.databinding.FragmentBookAppointmentBinding
import java.util.*


class BookAppointmentFragment : Fragment() {

    private var _binding: FragmentBookAppointmentBinding? = null
    private val binding get() = _binding!!
    lateinit var dateEdt: EditText

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

//        val date =
//            OnDateSetListener { view, year, month, day ->
//                myCalendar[Calendar.YEAR] = year
//                myCalendar[Calendar.MONTH] = month
//                myCalendar[Calendar.DAY_OF_MONTH] = day
//                updateLabel()
//            }


//        binding.editTextDate.setOnClickListener(View.OnClickListener {
//            BookAppointmentFragment().context?.let { it1 ->
//                DatePickerDialog(
//                    it1, date,
//                    myCalendar[Calendar.YEAR],
//                    myCalendar[Calendar.MONTH], myCalendar[Calendar.DAY_OF_MONTH]
//                ).show()
//            }
//        })
        dateEdt =binding.editTextDate
        dateEdt.setOnClickListener {

            // on below line we are getting
            // the instance of our calendar.
            val c = Calendar.getInstance()

            // on below line we are getting
            // our day, month and year.
            val year = c.get(Calendar.YEAR)
            val month = c.get(Calendar.MONTH)
            val day = c.get(Calendar.DAY_OF_MONTH)

            // on below line we are creating a
            // variable for date picker dialog.
            val datePickerDialog = DatePickerDialog(
                // on below line we are passing context.
                this.requireContext(),
                { view, year, monthOfYear, dayOfMonth ->
                    // on below line we are setting
                    // date to our edit text.
                    val dat = (dayOfMonth.toString() + "-" + (monthOfYear + 1) + "-" + year)
                    dateEdt.setText(dat)
                },
                // on below line we are passing year, month
                // and day for the selected date in our date picker.
                year,
                month,
                day
            )
            // at last we are calling show
            // to display our date picker dialog.
            datePickerDialog.show()
        }
    }

    private fun updateLabel() {
        val myFormat = "MM/dd/yy"
        val dateFormat = SimpleDateFormat(myFormat, Locale.US)
        //binding.editTextDate.setText(dateFormat.format(myCalendar.time))
    }


}