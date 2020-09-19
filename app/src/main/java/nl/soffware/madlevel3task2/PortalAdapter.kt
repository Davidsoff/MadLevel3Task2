package nl.soffware.madlevel3task2

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main._portal.view.*


class PortalAdapter(
    private val portals: List<Portal>,
    private val listener: (Portal) -> Unit
) : RecyclerView.Adapter<PortalAdapter.ViewHolder>(){
    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        fun databind(portal: Portal){
            itemView.tvTitle.text = portal.title
            itemView.tvUrl.text = portal.url
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout._portal, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val portal = portals[position]
        holder.databind(portal)
        holder.itemView.setOnClickListener { listener(portal) }
    }

    override fun getItemCount(): Int {
        return portals.size
    }
}