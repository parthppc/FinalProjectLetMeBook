package project.st991591950.dhruvparthtapasvi.myAppointments

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.myappointments_item.view.*
import project.st991591950.dhruvparthtapasvi.R

class MyAppointmentsRecycleView (private val appointmentList: List<MyAppointmentList>): RecyclerView.Adapter<MyAppointmentsRecycleView.MyAppointmentsViewHolder>() {

    class MyAppointmentsViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {

        val doctorNameView: TextView = itemView.dName
        val patientReasonView: TextView = itemView.reason
        val appointmentTitleView: TextView = itemView.title


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


    }

    override fun getItemCount() = appointmentList.size
}