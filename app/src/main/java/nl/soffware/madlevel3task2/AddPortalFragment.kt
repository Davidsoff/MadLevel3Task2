package nl.soffware.madlevel3task2

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.URLUtil
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import kotlinx.android.synthetic.main.fragment_portal_add.*

const val ARG_PORTAL_TITLE = "arg_portal_title"
const val ARG_PORTAL_URL = "arg_portal_url"


/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class AddPortalFragment : Fragment() {

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_portal_add, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        btn_add_portal.setOnClickListener { onAddPortal() }
    }

    private fun onAddPortal() {
        val portalTitle = tfName.text.toString()
        val portalUrl   = tfUrl.text.toString()

        when {
            portalTitle.isNotBlank() && URLUtil.isValidUrl(portalUrl) -> handleValidEntry(portalTitle, portalUrl)
            portalTitle.isBlank() -> handleInvalidEntry(R.string.title_hint)
            !URLUtil.isValidUrl(portalUrl) -> handleInvalidEntry(R.string.url_hint)
        }
    }

    private fun handleValidEntry(portalTitle: String, portalUrl: String) {
        val args = Bundle()
        args.putString(ARG_PORTAL_TITLE, portalTitle)
        args.putString(ARG_PORTAL_URL, portalUrl)
        findNavController().navigate(R.id.action_AddPortal_to_PortaList, args)
    }

    private fun handleInvalidEntry(text: Int) {
        Toast.makeText(
            activity,
            "Invalid $text",
            Toast.LENGTH_SHORT
        ).show()
    }
}