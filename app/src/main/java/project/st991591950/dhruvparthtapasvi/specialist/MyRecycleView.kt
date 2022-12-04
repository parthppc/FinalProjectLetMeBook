package project.st991591950.dhruvparthtapasvi.specialist

import android.app.Activity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import kotlinx.android.synthetic.main.specialist_item.view.*
import project.st991591950.dhruvparthtapasvi.R

class MyRecycleView(private val sampleList: List<SpecialistList>) : RecyclerView.Adapter <MyRecycleView.MyViewHolder>() {



    class MyViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val imageView: ImageView = itemView.imageView
        val specialistNameView: TextView = itemView.textView_specialistName
        val specialityView: TextView = itemView.textView_speciality
        val clinicNameView: TextView = itemView.textView_clinicName
        val specialistcardView:RelativeLayout = itemView.specialistCard

        val viewSpecialist: Button = itemView.button_ViewSpecialist
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.specialist_item, parent, false)
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

        holder.viewSpecialist.setOnClickListener{

        }
    }


    override fun getItemCount() = sampleList.size

}


