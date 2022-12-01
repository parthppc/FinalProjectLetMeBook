package project.st991591950.dhruvparthtapasvi.specialist

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.fragment_specialist.*
import project.st991591950.dhruvparthtapasvi.R

class SpecialistFragment : Fragment() {

    private var layoutManager: RecyclerView.LayoutManager? = null
    private var adapter: RecyclerView.Adapter<MyRecycleView.MyViewHolder>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val ourList = generateDummyList(6)

        //recycleView.adapter = MyRecycleView(ourList)
        //recycleView.layoutManager = LinearLayoutManager(this)
        //recycleView.setHasFixedSize(true)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_specialist, container, false)
    }

    override fun onViewCreated(itemView: View, savedInstanceState: Bundle?) {
        super.onViewCreated(itemView, savedInstanceState)
        recycleView.apply {
            // set a LinearLayoutManager to handle Android
            // RecyclerView behavior
            layoutManager = LinearLayoutManager(activity)
            // set the custom adapter to the RecyclerView
            adapter = MyRecycleView(generateDummyList(6))
        }
    }

    private fun generateDummyList(size: Int): List<SpecialistList> {
        val list = ArrayList<SpecialistList>()
        for (i in 0 until size) {

            val specialistname = arrayOf("Mango", "Banana", "Pineapple", "Apple", "Orange", "WaterMelon")
            val speciality = arrayOf("Mango", "Banana", "Pineapple", "Apple", "Orange", "WaterMelon")
            val clinicname = arrayOf("Mango", "Banana", "Pineapple", "Apple", "Orange", "WaterMelon")

            val item = SpecialistList(specialistname[i], speciality[i], clinicname[i])
            list += item
        }
        return list
    }

}