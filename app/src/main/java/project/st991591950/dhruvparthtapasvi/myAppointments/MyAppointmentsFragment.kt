package project.st991591950.dhruvparthtapasvi.myAppointments

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.StringRes
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.firestore.*
import kotlinx.android.synthetic.main.fragment_my_appointments.*
import kotlinx.android.synthetic.main.myappointments_item.*
import project.st991591950.dhruvparthtapasvi.R
import project.st991591950.dhruvparthtapasvi.databinding.FragmentMyAppointmentsBinding

class MyAppointmentsFragment: Fragment() {

    private lateinit var myAdapter : MyAppointmentsRecycleView
    private lateinit var recycleView: RecyclerView
    private  lateinit var list: ArrayList<MyAppointmentList>
    private var  fireStoreDatabase = FirebaseFirestore.getInstance()

    private var _binding: FragmentMyAppointmentsBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentMyAppointmentsBinding.inflate(inflater, container, false)
        val root: View = binding.root

        recycleView = binding.recycleView
        recycleView.layoutManager = LinearLayoutManager(MyAppointmentsFragment().context)
        recycleView.setHasFixedSize(true)

        list = arrayListOf()

        myAdapter = MyAppointmentsRecycleView(list)
        recycleView.adapter = myAdapter

        EventChangeListner()

        setActivityTitle(R.string.myappointments_fragment_label)

        return root
    }
    @SuppressLint("ResourceType")
    override fun onViewCreated(itemView: View, savedInstanceState: Bundle?) {
        super.onViewCreated(itemView, savedInstanceState)
        recycleView.apply {
            // set a LinearLayoutManager to handle Android
            // RecyclerView behavior
            layoutManager = LinearLayoutManager(activity)
            // set the custom adapter to the RecyclerView
            //adapter = MyAppointmentsRecycleView(generateAppointmentList(6))
        }
    }
    private  fun EventChangeListner( )
    {

        fireStoreDatabase.collection("MyAppointments").addSnapshotListener(object:
            EventListener<QuerySnapshot> {
            override  fun onEvent(
                value: QuerySnapshot?,
                error: FirebaseFirestoreException?
            ){
                if(error != null){
                    Log.e("Firestore error", error.message.toString())
                    return
                }
                for (dc: DocumentChange in value?.documentChanges!!){
                    if (dc.type == DocumentChange.Type.ADDED){
                        list.add(dc.document.toObject(MyAppointmentList::class.java))

                    }
                }
                myAdapter.notifyDataSetChanged()
            }
        })
    }

    private fun Fragment.setActivityTitle(@StringRes id: Int) {
        (activity as? AppCompatActivity?)?.supportActionBar?.title = getString(id)
    }


    //    private fun generateAppointmentList(size: Int): List<MyAppointmentList> {
//        val list = ArrayList<MyAppointmentList>()
//        for (i in 0 until size) {
//
//            val patientname = arrayOf("Tapasvi", "Dhruv", "Shukla", "patel", "Contractor", "Parth")
//
//            val item = MyAppointmentList(patientname[i])
//            list += item
//        }
//        return list
//    }

}