package net.bitburst.pollpa

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView

class ItemAdapter(
    private val value: List<GItem>,
    private val onClick: (GItem) -> Unit
) :
    RecyclerView.Adapter<ItemAdapter.ItemViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val itemView =
            LayoutInflater.from(parent?.context).inflate(R.layout.item_g, parent, false)
        return ItemViewHolder(itemView, onClick)
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val positionInList: Int = position % value.size
        holder.bind(value[positionInList])
    }

    override fun getItemCount(): Int {
        return Integer.MAX_VALUE
    }

    class ItemViewHolder(item: View, onClick: (GItem) -> Unit) : RecyclerView.ViewHolder(item) {

        val img = item.findViewById<ImageView>(R.id.imView)
        private lateinit var gItem: GItem

        init {
            itemView.setOnClickListener {
                onClick.invoke(gItem)
            }
        }

        fun bind(item: GItem) {
            img.setImageResource(item.resId)
            gItem = item
        }
    }
}