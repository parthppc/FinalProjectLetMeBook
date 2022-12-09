package project.st991591950.dhruvparthtapasvi.admin

import android.annotation.SuppressLint
import android.os.Build
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import androidx.navigation.fragment.NavHostFragment.Companion.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.admin_item.*
import kotlinx.android.synthetic.main.admin_item.view.*
import kotlinx.android.synthetic.main.fragment_admin.*
import kotlinx.android.synthetic.main.myappointments_item.view.*
import project.st991591950.dhruvparthtapasvi.R
import project.st991591950.dhruvparthtapasvi.myAppointments.TAG


const val TAG ="FIRESTORE"


class AdminRecycleView (private val adminList: List<AdminList>) : RecyclerView.Adapter<AdminRecycleView.AdminViewHolder>() {

    val fireStoreDatabase = FirebaseFirestore.getInstance()

    private val adminFragment: Fragment = Fragment()

    class AdminViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val patientNameView: TextView = itemView.textView_patientName
        val specialistNameView: TextView = itemView.textView_SpecialistName
        val appointmentDateView: TextView = itemView.textView_AppointmentDate
        val appointmentTimeView: TextView = itemView.textView_time
        val reasonView: TextView = itemView.textView_reason
        val cancelbtn: Button = itemView.button_Cancel
        val reschedulebtn: Button = itemView.button_reschedulebtn

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AdminViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.admin_item, parent, false)
        return AdminViewHolder(itemView)
    }

    @SuppressLint("DetachAndAttachSameFragment")
    override fun onBindViewHolder(holder: AdminViewHolder, position: Int) {
        val currentPatient = adminList[position]

        holder.patientNameView.text = currentPatient.patientName
        holder.specialistNameView.text = currentPatient.sName
        holder.appointmentDateView.text = currentPatient.appointmentDate
        holder.appointmentTimeView.text = currentPatient.appointmentTime
        holder.reasonView.text = currentPatient.reason

        holder!!.cancelbtn.setOnClickListener(View.OnClickListener { view ->

            var title: String = currentPatient.reason.toString()

            val query = fireStoreDatabase.collection("MyAppointments")
                .whereEqualTo("reason", title)
                .get()

            query.addOnSuccessListener {
                for (document in it) {
                    fireStoreDatabase.collection("MyAppointments").document(document.id).delete()
                        .addOnSuccessListener {
                            Log.d(TAG, " document deleted with")
                            Toast.makeText(view.context, "Appointment Canceled. \nRefresh page and check again", Toast.LENGTH_SHORT)
                                .show()

                        }
                }
            }
        })

            holder!!.reschedulebtn.setOnClickListener(View.OnClickListener { view ->
                var title: String = currentPatient.reason.toString()

                val query = fireStoreDatabase.collection("MyAppointments")
                    .whereEqualTo("reason", title)
                    .get()

                query.addOnSuccessListener {
                    for (document in it) {
                        fireStoreDatabase.collection("MyAppointments").document(document.id)
                            .update("appointmentTime", holder!!.appointmentTimeView.text.toString())
                            .addOnSuccessListener {
                                Log.d(TAG, "document updated")
                                Toast.makeText(
                                    view.context,
                                    "Appointment Time Rescheduled.\n Refresh page and check again!",
                                    Toast.LENGTH_SHORT
                                ).show()

                            }
                    }
                }

                query.addOnSuccessListener {
                    for (document in it) {
                        fireStoreDatabase.collection("MyAppointments").document(document.id)
                            .update("appointmentDate", holder!!.appointmentDateView.text.toString())
                            .addOnSuccessListener {
                                Log.d(TAG, "document updated")
                                Toast.makeText(
                                    view.context,
                                    "Appointment Date Rescheduled.\n Refresh page and check again!",
                                    Toast.LENGTH_SHORT
                                ).show()

                            }
                    }
                }
            })
//            val navController = findNavController(this.adminFragment)
//            navController.run {
//                popBackStack()
//                navigate(R.id.adminFragment)
//            }





    }
    override fun getItemCount() = adminList.size
}