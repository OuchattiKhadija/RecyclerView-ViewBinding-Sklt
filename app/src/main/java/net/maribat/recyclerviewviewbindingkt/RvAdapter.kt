package net.maribat.recyclerviewviewbindingkt

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import net.maribat.recyclerviewviewbindingkt.databinding.SingleItemBinding


class RvAdapter() : RecyclerView.Adapter<RvAdapter.ViewHolder>() {
    var languageList: List<Language> = ArrayList<Language>()
    private lateinit var  mListener: OnItemClickListener
    // create an inner class with name ViewHolder
    // It takes a view argument, in which pass the generated class of single_item.xml
    // ie SingleItemBinding and in the RecyclerView.ViewHolder(binding.root) pass it like this
    inner class ViewHolder(val binding: SingleItemBinding) : RecyclerView.ViewHolder(binding.root)

    // inside the onCreateViewHolder inflate the view of SingleItemBinding
    // and return new ViewHolder object containing this layout
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = SingleItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return ViewHolder(binding)
    }

    // bind the items with each item
    // of the list languageList
    // which than will be
    // shown in recycler view
    // to keep it simple we are
    // not setting any image data to view
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        with(holder) {
            with(languageList[position]) {
                binding.tvLangName.text = this.name
                binding.tvExp.text = this.exp

                itemView.setOnClickListener { //get adapter position of the cerd clicked
                    if (position != RecyclerView.NO_POSITION) {
                        mListener.onItemClick(languageList.get(position))
                    }
                }
            }
        }
    }

    // return the size of languageList
    override fun getItemCount(): Int {
        return languageList.size
    }

    fun setList(languageList: List<Language>) {
       this.languageList = languageList
    }

    interface OnItemClickListener {
        fun onItemClick(language: Language?)
    }

    fun setOnItemClickListener(listener: OnItemClickListener) {
        mListener = listener
    }
}