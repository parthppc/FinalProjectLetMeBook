package project.st991591950.dhruvparthtapasvi.bookappointment

import project.st991591950.dhruvparthtapasvi.R
import android.app.DatePickerDialog
import android.icu.text.SimpleDateFormat
import android.icu.util.Calendar
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebViewClient
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.*
import android.widget.EditText
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import project.st991591950.dhruvparthtapasvi.databinding.FragmentBookAppointmentBinding
import java.time.LocalTime
import java.time.format.DateTimeFormatter.ISO_DATE
import java.time.format.DateTimeFormatter.ofLocalizedTime
import java.util.*
const val TAG = "FIRESTORE"

class BookAppointmentFragment : Fragment() {

    private var _binding: FragmentBookAppointmentBinding? = null
    private val binding get() = _binding!!
   // lateinit var dateEdt: EditText
    val fireStoreDatabase = FirebaseFirestore.getInstance()

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

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onViewCreated(itemView: View, savedInstanceState: Bundle?) {

        var mBundle: Bundle? = arguments
        var doctorName = mBundle!!.getString("doctorName")
        var clinicName = mBundle!!.getString("clinicName")
        var speciality = mBundle!!.getString("speciality")
        var photoUrl = mBundle!!.getString("photoUrl")

        //var selectedDate:String = binding!!.editTextDate.text.toString()

        var specialistName: String = doctorName.toString()
        var doctorSpeciality:String = speciality.toString()

        val user = FirebaseAuth.getInstance().currentUser
        var patientName: String =""
        var appointmentTime: String =""
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
                this.requireContext(),
                { view, year, monthOfYear, dayOfMonth ->
                    // on below line we are setting
                    // date to our edit text.
                    val dat = (dayOfMonth.toString() + "-" + (monthOfYear + 1) + "-" + year)
                    binding.editTextDate.setText(dat)
                    var selectedDate:String = dat.toString()
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
            datePickerDialog.getDatePicker().setMinDate(c.getTimeInMillis())
            datePickerDialog.show()

        }

        val timeslots = resources.getStringArray(R.array.TimeSlots)
        //var timeslots = arrayOf("Java", "PHP", "Kotlin", "Javascript", "Python", "Swift")

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

        binding.buttonBookAppointment.setOnClickListener{

            var appointmentReason: String = binding!!.editTextTextMultiLine.text.toString()
            bookedAppointment["reason"] = appointmentReason

            fireStoreDatabase.collection("MyAppointments")
                .add(bookedAppointment)
                .addOnSuccessListener {
                    Log.d(TAG, "Added document with ${it.id}")
                }
                .addOnFailureListener {
                    Log.d(TAG, "error document with ${it}")
                }
        }
    }

//    @RequiresApi(Build.VERSION_CODES.O)
//    fun TimeSlot.divide(lengthHours: Long): List<TimeSlot> {
//        require(lengthHours > 0) { "lengthHours was $lengthHours. Must specify positive amount of hours."}
//        val timeSlots = mutableListOf<TimeSlot>()
//        var nextStartTime = startTime
//        while (true) {
//            val nextEndTime = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
//                nextStartTime.plusHours(lengthHours)
//            } else {
//                TODO("VERSION.SDK_INT < O")
//            }
//            if (nextEndTime > endTime) {
//                break
//            }
//            timeSlots.add(TimeSlot(nextStartTime, nextEndTime))
//            nextStartTime = nextEndTime
//        }
//        return timeSlots
//    }

    private fun updateLabel() {
        val myFormat = "MM/dd/yy"
        val dateFormat = SimpleDateFormat(myFormat, Locale.US)
        //binding.editTextDate.setText(dateFormat.format(myCalendar.time))
    }




}