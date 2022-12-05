package project.st991591950.dhruvparthtapasvi.myAppointments

import android.icu.text.SimpleDateFormat
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.myappointments_item.view.*
import project.st991591950.dhruvparthtapasvi.R
import java.util.*

class MyAppointmentsRecycleView (private val appointmentList: List<MyAppointmentList>): RecyclerView.Adapter<MyAppointmentsRecycleView.MyAppointmentsViewHolder>() {

    class MyAppointmentsViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {

        val doctorNameView: TextView = itemView.dName
        val patientReasonView: TextView = itemView.reason
        val appointmentTitleView: TextView = itemView.title
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
        holder.appointmentTitleView.text = currentAppointment.title
        holder.datetime.text = currentAppointment.dateTime?.toDate().toString()

        val currentDate: String = SimpleDateFormat("dd-MM-yyyy", Locale.getDefault()).format(Date())
        val firebaseDate: String = SimpleDateFormat("dd-MM-yyyy", Locale.getDefault()).format(currentAppointment.dateTime?.toDate())

        //holder.datetime.text = currentDate

        if (firebaseDate.compareTo(currentDate) > 0) {
            //Log.i("app", "Date1 is after Date2");
        } else if (firebaseDate.compareTo(currentDate) < 0) {
           // Log.i("app", "Date1 is before Date2");
            holder.cancelbtn.isEnabled = false
            holder.reschedulebtn.isEnabled = false
        } else if (firebaseDate.compareTo(currentDate) == 0) {
          //  Log.i("app", "Date1 is equal to Date2");
        }



    }

    override fun getItemCount() = appointmentList.size
}