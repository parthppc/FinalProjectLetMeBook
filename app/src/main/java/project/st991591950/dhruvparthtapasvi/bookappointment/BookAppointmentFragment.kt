package project.st991591950.dhruvparthtapasvi.bookappointment

import android.app.DatePickerDialog
import android.icu.util.Calendar
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebViewClient
import android.widget.*
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import project.st991591950.dhruvparthtapasvi.R
import project.st991591950.dhruvparthtapasvi.databinding.FragmentBookAppointmentBinding
import java.util.*

const val TAG = "FIRESTORE"

private const val KEY_RESULT = "result"
private const val datePickerDialog = "result"

class BookAppointmentFragment : Fragment() {

    private var _binding: FragmentBookAppointmentBinding? = null
    private val binding get() = _binding!!
    private val fireStoreDatabase = FirebaseFirestore.getInstance()

    // var editText: EditText? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentBookAppointmentBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(itemView: View, savedInstanceState: Bundle?) {

        val mBundle: Bundle? = arguments
        val doctorName = mBundle!!.getString("doctorName")
        val clinicName = mBundle!!.getString("clinicName")
        val speciality = mBundle!!.getString("speciality")
        val photoUrl = mBundle!!.getString("photoUrl")

        //var selectedDate:String = binding!!.editTextDate.text.toString()

        val specialistName: String = doctorName.toString()
        val doctorSpeciality: String = speciality.toString()

        val user = FirebaseAuth.getInstance()?.currentUser
        var patientName =  " "
        var appointmentTime: String
        if (user != null) {
            patientName = user.displayName.toString()

        }

        val bookedAppointment: MutableMap<String, Any> = HashMap()


        bookedAppointment["sName"] = specialistName
        bookedAppointment["patientName"] = patientName
        bookedAppointment["doctorSpeciality"]=doctorSpeciality

        binding.imageView.webViewClient = WebViewClient()
        binding.imageView.settings.loadWithOverviewMode = true
        binding.imageView.settings.useWideViewPort = true
        binding.imageView.loadUrl(photoUrl.toString())


        binding.SpecialistName.text = doctorName.toString()
        binding.textViewSpeciality.text = speciality.toString()
        binding.textViewClinicName.text = clinicName.toString()
        //Toast.makeText( BookAppointmentFragment().context, "abc.toString()",Toast.LENGTH_SHORT).show();


        //dateEdt =binding.editTextDate
        binding.editTextDate.setOnClickListener {

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
                this.requireContext(), { view, year, monthOfYear, dayOfMonth ->
                    // on below line we are setting
                    // date to our edit text.
                    val dat = (dayOfMonth.toString() + "-" + (monthOfYear + 1) + "-" + year)
                    binding.editTextDate.setText(dat)
                    val selectedDate:String = dat
                    bookedAppointment["appointmentDate"] = selectedDate
                },
                // on below line we are passing year, month
                // and day for the selected date in our date picker.
                year,
                month,
                day
            )
            // at last we are calling show
            // to display our date picker dialog.
            datePickerDialog.datePicker.minDate = c.timeInMillis
            datePickerDialog.show()

        }

        val timeslots = resources.getStringArray(R.array.TimeSlots)

        // access the spinner
        val spinner = binding.spinner2
        if (spinner != null) {
            val adapter = ArrayAdapter(requireContext(),android.R.layout.simple_spinner_item,timeslots)
            spinner.adapter = adapter

            spinner.onItemSelectedListener = object :
                AdapterView.OnItemSelectedListener {
                override fun onItemSelected(parent: AdapterView<*>,
                                            view: View, position: Int, id: Long) {
                    Toast.makeText(context,
                        getString(R.string.selected_item) + " " +
                                "" + timeslots[position], Toast.LENGTH_SHORT).show()
                    appointmentTime = timeslots[position].toString()
                    bookedAppointment["appointmentTime"] = appointmentTime
                }

                override fun onNothingSelected(parent: AdapterView<*>) {
                    // write code to perform some action
                }
            }
        }

        //Book appointment button
        binding.buttonBookAppointment.setOnClickListener(View.OnClickListener {view->

            val appointmentReason: String = binding.editTextTextMultiLine.text.toString()
            bookedAppointment["reason"] = appointmentReason

            fireStoreDatabase.collection("MyAppointments")
                .add(bookedAppointment)
                .addOnSuccessListener {
                    Log.d(TAG, "Added document with ${it.id}")
                }
                .addOnFailureListener {
                    Log.d(TAG, "error document with ${it}")
                }

            Toast.makeText(view.context,
                "Appointment Booked. \nRefresh Page and Go to My Appointments", Toast.LENGTH_LONG).show()

            //findNavController().navigate(R.id.action_bookAppointmentFragment_to_myAppointmentsFragment)
        })
    }

    override fun onSaveInstanceState(savedInstanceState: Bundle) {
        super.onSaveInstanceState(savedInstanceState)
        savedInstanceState.putString(KEY_RESULT, datePickerDialog)
    }

}