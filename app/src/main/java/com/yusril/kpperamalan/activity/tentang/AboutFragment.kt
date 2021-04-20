package com.yusril.kpperamalan.activity.tentang

import android.os.Bundle
import android.text.Html
import android.text.method.LinkMovementMethod
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.yusril.kpperamalan.R
import com.yusril.kpperamalan.databinding.FragmentAboutBinding


class AboutFragment : Fragment() {

    private lateinit var binding: FragmentAboutBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_about, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding= FragmentAboutBinding.bind(view)
        val btn=getString(R.string.lihat_data)
        val text =
            "<a href='http://hub.satudata.bappenas.go.id/nl/dataset/econ_board_ed-inf-001'>$btn</a>"
        binding.btnLihatData.setText(Html.fromHtml(text))
        binding.btnLihatData.movementMethod = LinkMovementMethod.getInstance()
        binding.btnLihatData.setOnClickListener {

        }
    }
}