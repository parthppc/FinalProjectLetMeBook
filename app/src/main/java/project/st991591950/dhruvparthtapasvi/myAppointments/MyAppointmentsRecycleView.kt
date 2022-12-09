package project.st991591950.dhruvparthtapasvi.myAppointments

import android.icu.text.SimpleDateFormat
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.RelativeLayout
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentTransaction
import androidx.lifecycle.viewmodel.viewModelFactory
import androidx.navigation.Navigation
import androidx.navigation.Navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.fragment_my_appointments.*
import kotlinx.android.synthetic.main.myappointments_item.view.*
import kotlinx.android.synthetic.main.specialist_item.view.*
import project.st991591950.dhruvparthtapasvi.HomeFragment
import project.st991591950.dhruvparthtapasvi.R
import project.st991591950.dhruvparthtapasvi.bookappointment.BookAppointmentFragment
import project.st991591950.dhruvparthtapasvi.specialist.MyRecycleView
import java.util.*


const val TAG ="FIRESTORE"

class MyAppointmentsRecycleView (private val appointmentList: List<MyAppointmentList>): RecyclerView.Adapter<MyAppointmentsRecycleView.MyAppointmentsViewHolder>() {

    val fireStoreDatabase = FirebaseFirestore.getInstance()

    val calendar = Calendar.getInstance()

    val thisYear = calendar[Calendar.YEAR]
    var thisMonth = calendar[Calendar.MONTH]
    val thisDay = calendar[Calendar.DAY_OF_MONTH]

    class MyAppointmentsViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {

        val doctorNameView: TextView = itemView.dName
        val patientReasonView: TextView = itemView.reason
        val appointmentTimeView: TextView = itemView.editTextTime
        val date: TextView = itemView.editTextDate
        val speciality: TextView = itemView.Speciality
        val myAppointmentCardView: RelativeLayout = itemView.myAppointmentsCard


        val cancelbtn: Button = itemView.cancelbtn
        val reschedulebtn: Button = itemView.reschedulebtn


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyAppointmentsViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.myappointments_item, parent, false)
        return MyAppointmentsViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MyAppointmentsViewHolder, position: Int) {
        val currentAppointment = appointmentList[position]

        val user = FirebaseAuth.getInstance().currentUser
        var userName = ""
        if (user != null) {
            userName = user.displayName.toString()
        }

        if(userName == currentAppointment.patientName) {

        holder.doctorNameView.text = currentAppointment.sName
        holder.patientReasonView.text = currentAppointment.reason
        holder.appointmentTimeView.text = currentAppointment.appointmentTime
        holder.date.text = currentAppointment.appointmentDate
        holder.speciality.text = currentAppointment.doctorSpeciality

        val currentDate: String = SimpleDateFormat("dd-MM-yyyy", Locale.getDefault()).format(Date())
        //val firebaseDate: String = SimpleDateFormat("dd-MM-yyyy", Locale.getDefault()).format(currentAppointment.appointmentDate)

       // holder.date.text = currentDate
        if (thisMonth == 12) {
            thisMonth = 1
        }
        else {
            thisMonth = thisMonth + 1
        }

        val substringDay = currentAppointment.appointmentDate?.subSequence(0, 1)
        val substringCurrentMonth = currentDate.subSequence(3,5)


        if(substringDay == "0"){
            val substringfirebaseDate = currentAppointment.appointmentDate?.subSequence(3, 5)

            val firebaseDate = substringfirebaseDate.toString().toIntOrNull()
           // val todayMonth = substringCurrentMonth.toString().toIntOrNull()

            if (firebaseDate != null) {
                if(firebaseDate < thisMonth){

                    holder.cancelbtn.isEnabled = false
                    holder.reschedulebtn.isEnabled = false
                }
            }
        }

        else{
            val substringfirebaseDate = currentAppointment.appointmentDate?.subSequence(2, 4)

            val firebaseDate = substringfirebaseDate.toString().toIntOrNull()
           // val todayMonth = substringCurrentMonth.toString().toIntOrNull()

            if (firebaseDate != null) {
                if(firebaseDate < thisMonth){
                    holder.cancelbtn.isEnabled = false
                    holder.reschedulebtn.isEnabled = false
                }


            }
        }

//        if (currentAppointment.doctorSpeciality!! > currentDate) {
//            //Log.i("app", "Date1 is after Date2");
//            holder.cancelbtn.isEnabled = false
//            holder.reschedulebtn.isEnabled = false
//        } else if (currentAppointment.doctorSpeciality < currentDate) {
//           // Log.i("app", "Date1 is before Date2");
//            holder.cancelbtn.isEnabled = false
//            holder.reschedulebtn.isEnabled = false
//        } else if (currentAppointment.doctorSpeciality.compareTo(currentDate) == 0) {
//          //  Log.i("app", "Date1 is equal to Date2");
//        }

        //this is the Cancel Button
        holder!!.cancelbtn.setOnClickListener(View.OnClickListener { view->

            var title: String = currentAppointment.reason.toString()

            val query = fireStoreDatabase.collection("MyAppointments")
                .whereEqualTo("reason", title)
                .get()

            query.addOnSuccessListener {
                for(document in it){
                    fireStoreDatabase.collection("MyAppointments").document(document.id).delete()
                        .addOnSuccessListener {
                            Log.d(TAG," document deleted with")
                            Toast.makeText( view.context, "Appointment Canceled", Toast.LENGTH_SHORT).show()

                        }
                }
            }
            //notifyItemRemoved(fireStoreDatabase)
            query.addOnFailureListener{
             //   Toast.makeText(view.context,"Appoinment Not found", Toast.LENGTH_SHORT).show()
            }

//              val activity = view.context as AppCompatActivity
//              val myFragment = MyAppointmentsFragment()
//
//            val fragmentTransaction: FragmentTransaction = activity.supportFragmentManager.beginTransaction()
//            fragmentTransaction.replace(R.id.myAppointmentsCardGallery, myFragment).addToBackStack(null).commit()
//            val ft = activity.supportFragmentManager.beginTransaction()
//            ft.detach(this).attach(this).commit()

//            val navController = Navigation.findNavController()
//            navController.run {
//                popBackStack()
//                navigate(R.id.adminFragment)
//            }

        })

        } else {
            holder.myAppointmentCardView.visibility = View.GONE
        }
    }

    override fun getItemCount() = appointmentList.size

}