package com.jamal2367.mixmonet.ui

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.jamal2367.mixmonet.BuildConfig.*
import com.jamal2367.mixmonet.R
import com.google.android.material.transition.MaterialFadeThrough

class AboutFragment: Fragment(R.layout.fragment_about) {
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)

    enterTransition = MaterialFadeThrough()
    returnTransition = MaterialFadeThrough()
  }

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)

    val version : String = resources.getString(R.string.version, VERSION_NAME, BUILD_TYPE)
    view.findViewById<TextView>(R.id.version).text = version
    view.findViewById<View>(R.id.feedback).openLink("https://t.me/jamal2367")
    view.findViewById<View>(R.id.source).openLink("https://github.com/jamal2362/Monet-Generator")
  }

  private fun View.openLink(url: String) {
    setOnClickListener {
      it.context.startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(url)))
    }
  }

}