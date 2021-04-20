package com.yusril.kpperamalan.activity.ses

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebChromeClient
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.yusril.kpperamalan.BuildConfig
import com.yusril.kpperamalan.R
import com.yusril.kpperamalan.databinding.FragmentSesChartBinding


class SesChartFragment : Fragment() {
    companion object {
        private const val URL_CHART_SES = BuildConfig.URL_CHART_SES
    }
    private lateinit var binding: FragmentSesChartBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_ses_chart, container, false)
    }

    @SuppressLint("SetJavaScriptEnabled")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding= FragmentSesChartBinding.bind(view)
        val webView=binding.webView
        webView.settings.javaScriptEnabled = true
        webView.settings.useWideViewPort = true
        webView.webViewClient = object : WebViewClient() {
            override fun onPageFinished(view: WebView, url: String) {
                Toast.makeText(context, getString(R.string.chart_berhasil_dimuat), Toast.LENGTH_LONG).show()
            }
        }
        webView.webChromeClient = object : WebChromeClient() {
            override fun onJsAlert(
                view: WebView,
                url: String,
                message: String,
                result: android.webkit.JsResult
            ): Boolean {
                Toast.makeText(context, message, Toast.LENGTH_LONG).show()
                result.confirm()
                return true
            }
        }
        webView.loadUrl(URL_CHART_SES)
    }

}