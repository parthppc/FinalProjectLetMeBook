package project.st991591950.dhruvparthtapasvi.admin

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.StringRes
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.DocumentChange
import com.google.firebase.firestore.EventListener
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.FirebaseFirestoreException
import com.google.firebase.firestore.QuerySnapshot
import com.google.firebase.firestore.ktx.toObject
import project.st991591950.dhruvparthtapasvi.R
import project.st991591950.dhruvparthtapasvi.databinding.FragmentAdminBinding


class AdminFragment : Fragment() {

    private lateinit var myAdapter: AdminRecycleView
    private lateinit var recycleView: RecyclerView
    private lateinit var list : ArrayList<AdminList>
    private var fireStoreDatabase = FirebaseFirestore.getInstance()


    private var _binding: FragmentAdminBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentAdminBinding.inflate(inflater, container, false)
        val root: View = binding.root

        recycleView = binding.recycleView
        recycleView.layoutManager = LinearLayoutManager(AdminFragment().context)
        recycleView.setHasFixedSize(true)

        list = arrayListOf()

        myAdapter = AdminRecycleView(list)
        recycleView.adapter = myAdapter

        EventChangeListner()

        setActivityTitle(R.string.admin_fragment_label)

        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recycleView.apply {
            layoutManager = LinearLayoutManager(activity)
        }

        binding.btnLogout.setOnClickListener{
            FirebaseAuth.getInstance().signOut();
            findNavController().navigate(R.id.loginFragment)
        }

    }


    private fun EventChangeListner ()
    {
        fireStoreDatabase.collection("MyAppointments").addSnapshotListener(object:
            EventListener<QuerySnapshot> {
            override fun onEvent(
                value: QuerySnapshot?,
                error: FirebaseFirestoreException?
            ){
                if(error != null) {
                    Log.e("Firestore error!", error.message.toString())
                    return
                }
                for (dc: DocumentChange in value?.documentChanges!!){
                    if (dc.type == DocumentChange.Type.ADDED){
                        list.add(dc.document.toObject(AdminList::class.java))
                    }
                }
                myAdapter.notifyDataSetChanged()
            }
        })
    }

    private fun Fragment.setActivityTitle(@StringRes id: Int) {
        (activity as? AppCompatActivity?)?.supportActionBar?.title = getString(id)
    }

}