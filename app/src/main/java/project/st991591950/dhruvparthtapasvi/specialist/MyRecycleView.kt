package project.st991591950.dhruvparthtapasvi.specialist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RelativeLayout
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentTransaction
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.specialist_item.view.*
import project.st991591950.dhruvparthtapasvi.R
import project.st991591950.dhruvparthtapasvi.bookappointment.BookAppointmentFragment


class MyRecycleView(private val sampleList: List<SpecialistList>) : RecyclerView.Adapter<MyRecycleView.MyViewHolder>() {



    class MyViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val imageView: ImageView = itemView.imageView
        val specialistNameView: TextView = itemView.textView_specialistName
        val specialityView: TextView = itemView.textView_speciality
        val clinicNameView: TextView = itemView.textView_clinicName
        val specialistcardView:RelativeLayout = itemView.specialistCard

       // val viewSpecialist: Button = itemView.button_ViewSpecialist
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.specialist_item, parent, false)

      //  _binding = FragmentSpecialistBinding.inflate(inflater, container, false)
       // val root: View = binding.root

        return MyViewHolder(itemView)
    }



    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {

        val currentItem = sampleList[position]

       // holder.imageView.setImageResource(currentItem.imageResource)
        holder.specialistNameView.text = currentItem.dName
        holder.specialityView.text = currentItem.dSpeciality
        holder.clinicNameView.text = currentItem.clinicName

        holder.specialistcardView.setOnClickListener{
            Toast.makeText(holder.specialistcardView.context,
                "You selected " +currentItem.dName+ ".", Toast.LENGTH_SHORT).show()

            //Navigation.findNavController().navigate(R.id.action_specialistFragment_to_bookAppointmentFragment)

        }

        holder.specialistcardView.setOnClickListener(View.OnClickListener { view -> //Here goes your desired onClick behaviour. Like:
            Toast.makeText(view.context, "You have clicked " + currentItem.dName, Toast.LENGTH_SHORT)
                .show() //you can add data to the tag of your cardview in onBind... and retrieve it here with with.getTag().toString()..
            //You can change the fragment, something like this, not tested, please correct for your desired output:
            val activity = view.context as AppCompatActivity
            val myFragment = BookAppointmentFragment()


            //Create a bundle to pass data, add data, set the bundle to your fragment and:
            val bundle = Bundle()
            val fragmentTransaction: FragmentTransaction = activity.supportFragmentManager.beginTransaction()

            bundle.putString("doctorName",currentItem.dName)
            bundle.putString("speciality",currentItem.dSpeciality)
            bundle.putString("clinicName",currentItem.clinicName)

            myFragment.arguments = bundle;

            fragmentTransaction.replace(R.id.cardOpenGallery, myFragment).addToBackStack(null).commit()

//            activity.supportFragmentManager.beginTransaction()
//                .replace(R.id.cardOpenGallery, myFragment).addToBackStack(null).commit()

        })
    }


    override fun getItemCount() = sampleList.size

}


