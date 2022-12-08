package project.st991591950.dhruvparthtapasvi.admin

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.admin_item.view.*
import project.st991591950.dhruvparthtapasvi.R

class AdminRecycleView (private val adminList: List<AdminList>) : RecyclerView.Adapter<AdminRecycleView.AdminViewHolder>() {

    class AdminViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val patientNameView: TextView = itemView.textView_patientName
        val specialistNameView: TextView = itemView.textView_SpecialistName
        val appointmentDateView: TextView = itemView.textView_AppointmentDate
        val appointmentTimeView: TextView = itemView.textView_time
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AdminViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.admin_item, parent, false)
        return AdminViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: AdminViewHolder, position: Int) {
        val currentPatient = adminList[position]

        holder.patientNameView.text = currentPatient.patientName
        holder.specialistNameView.text = currentPatient.sName
        holder.appointmentDateView.text = currentPatient.appointmentDate
        holder.appointmentTimeView.text = currentPatient.appointmentTime
    }

    override fun getItemCount() = adminList.size
}