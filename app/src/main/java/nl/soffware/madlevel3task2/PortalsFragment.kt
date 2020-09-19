package nl.soffware.madlevel3task2

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.setFragmentResultListener
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.GridLayoutManager
import kotlinx.android.synthetic.main.fragment_portals.*


/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class PortalsFragment : Fragment() {
    private val portals = arrayListOf<Portal>()
    private val portalAdapter = PortalAdapter(portals, this::clickListener)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_portals, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
        observeAddPortalResult()
    }

    private fun initViews() {
        rvPortals.layoutManager = GridLayoutManager(context, 2, GridLayoutManager.VERTICAL, false)
        rvPortals.adapter = portalAdapter
    }

    private fun clickListener(portal: Portal) {
        val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse(portal.url))
        ContextCompat.startActivity(requireContext(), browserIntent, null)
    }

    private fun observeAddPortalResult() {
        setFragmentResultListener(REQ_PORTAL_ADD) { _, bundle ->
            val title = bundle.getString(ARG_PORTAL_TITLE)
            val url = bundle.getString(ARG_PORTAL_URL)
            portals.add(Portal(title!!, url!!))
            portalAdapter.notifyDataSetChanged()
        }

    }

}