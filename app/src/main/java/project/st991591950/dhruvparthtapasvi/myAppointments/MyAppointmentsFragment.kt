package project.st991591950.dhruvparthtapasvi.myAppointments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.fragment_my_appointments.*
import project.st991591950.dhruvparthtapasvi.R
import project.st991591950.dhruvparthtapasvi.specialist.MyRecycleView


class MyAppointmentsFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_my_appointments, container, false)
    }

    override fun onViewCreated(itemView: View, savedInstanceState: Bundle?) {
        super.onViewCreated(itemView, savedInstanceState)
        recycleViewMyAppointments.apply {
            // set a LinearLayoutManager to handle Android
            // RecyclerView behavior
            layoutManager = LinearLayoutManager(activity)
            // set the custom adapter to the RecyclerView
            adapter = MyAppointmentsRecycleView(generateAppointmentList(6))
        }
    }

    private fun generateAppointmentList(size: Int): List<MyAppointmentList> {
        val list = ArrayList<MyAppointmentList>()
        for (i in 0 until size) {

            val patientname = arrayOf("Tapasvi", "Dhruv", "Shukla", "patel", "Contractor", "Parth")

            val item = MyAppointmentList(patientname[i])
            list += item
        }
        return list
    }
}