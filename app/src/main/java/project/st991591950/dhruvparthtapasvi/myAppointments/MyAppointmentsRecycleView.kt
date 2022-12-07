package project.st991591950.dhruvparthtapasvi.myAppointments

import android.icu.text.SimpleDateFormat
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.myappointments_item.view.*
import project.st991591950.dhruvparthtapasvi.R
import java.util.*


const val TAG ="FIRESTORE"

class MyAppointmentsRecycleView (private val appointmentList: List<MyAppointmentList>): RecyclerView.Adapter<MyAppointmentsRecycleView.MyAppointmentsViewHolder>() {

    val fireStoreDatabase = FirebaseFirestore.getInstance()


    class MyAppointmentsViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {

        val doctorNameView: TextView = itemView.dName
        val patientReasonView: TextView = itemView.reason
        val appointmentTimeView: TextView = itemView.time
        val datetime: TextView = itemView.editTextDate

        val cancelbtn: Button = itemView.cancelbtn
        val reschedulebtn: Button = itemView.reschedulebtn


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyAppointmentsViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.myappointments_item, parent, false)
        return MyAppointmentsViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MyAppointmentsViewHolder, position: Int) {
        val currentAppointment = appointmentList[position]

        holder.doctorNameView.text = currentAppointment.sName
        holder.patientReasonView.text = currentAppointment.reason
        holder.appointmentTimeView.text = currentAppointment.appointmentTime
        holder.datetime.text = currentAppointment.appointmentDate

//        val currentDate: String = SimpleDateFormat("dd-MM-yyyy", Locale.getDefault()).format(Date())
//        val firebaseDate: String = SimpleDateFormat("dd-MM-yyyy", Locale.getDefault()).format(currentAppointment.appointmentDate.toString())
//
//        //holder.datetime.text = currentDate
//
//        if (firebaseDate.compareTo(currentDate) > 0) {
//            //Log.i("app", "Date1 is after Date2");
//        } else if (firebaseDate.compareTo(currentDate) < 0) {
//           // Log.i("app", "Date1 is before Date2");
//            holder.cancelbtn.isEnabled = false
//            holder.reschedulebtn.isEnabled = false
//        } else if (firebaseDate.compareTo(currentDate) == 0) {
//          //  Log.i("app", "Date1 is equal to Date2");
//        }
//
//        holder!!.cancelbtn.setOnClickListener(){
//            var title: String = currentAppointment.appointmentTime.toString()
//
//
//            val query = fireStoreDatabase.collection("MyAppointments")
//                .whereEqualTo("title", title)
//                .get()
//
//            query.addOnSuccessListener {
//                for(document in it){
//                    fireStoreDatabase.collection("MyAppointments").document(document.id).delete()
//                        .addOnSuccessListener {
//                            Log.d(TAG," document deleted with")
//                        }
//
//
//                }
//            }
//
//            query.addOnFailureListener{
//             //   Toast.makeText(view.context,"Appoinment Not found", Toast.LENGTH_SHORT).show()
//            }
//        }


    }

    override fun getItemCount() = appointmentList.size
}