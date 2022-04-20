package com.jamal2367.tgmonet.ui

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.core.content.ContextCompat
import androidx.core.content.FileProvider
import androidx.fragment.app.Fragment
import com.google.android.material.transition.MaterialFadeThrough
import com.jamal2367.tgmonet.BuildConfig
import com.jamal2367.tgmonet.R
import java.io.*

class MiXGeneratorFragment: Fragment(R.layout.fragment_mix) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        enterTransition = MaterialFadeThrough()
        returnTransition = MaterialFadeThrough()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        view.findViewById<View>(R.id.buttonMixLight).setOnClickListener {
            val lightMonetFile = "light_mix_monet.xml"
            val outputFile = "properties.xml"
            val theme : String = resources.getString(R.string.light_theme)
            shareTheme(lightMonetFile, outputFile, theme)
        }

        view.findViewById<View>(R.id.buttonMixDark).setOnClickListener {
            val darkMonetFile = "dark_mix_monet.xml"
            val outputFile = "properties.xml"
            val theme : String = resources.getString(R.string.dark_theme)
            shareTheme(darkMonetFile, outputFile, theme)
        }
    }

    @Suppress("LocalVariableName", "SameParameterValue")
    private fun shareTheme(MonetFile: String, outputFile : String, theme : String) {
        val a1_100 = "#${Integer.toHexString(ContextCompat.getColor(requireActivity(), R.color.system_accent1_100))}"
        val a1_300 = "#${Integer.toHexString(ContextCompat.getColor(requireActivity(), R.color.system_accent1_300))}"
        val a1_700 = "#${Integer.toHexString(ContextCompat.getColor(requireActivity(), R.color.system_accent1_700))}"
        val n1_10 = "#${Integer.toHexString(ContextCompat.getColor(requireActivity(), R.color.system_neutral1_10))}"
        val n1_50 = "#${Integer.toHexString(ContextCompat.getColor(requireActivity(), R.color.system_neutral1_50))}"
        val n1_800 = "#${Integer.toHexString(ContextCompat.getColor(requireActivity(), R.color.system_neutral1_800))}"
        val n1_900 = "#${Integer.toHexString(ContextCompat.getColor(requireActivity(), R.color.system_neutral1_900))}"

        val darkThemeImport = requireActivity().assets.open(MonetFile).bufferedReader().readLines().joinToString()
            .replace(", ", "\n")
            .replace("Dark_Background", n1_900)
            .replace("Dark_Highlight", n1_800)
            .replace("Dark_Accent", a1_100)
            .replace("Dark_DAccent", a1_300)
            .replace("Dark_Text", n1_50)
            .replace("Light_Background", n1_10)
            .replace("Light_Highlight", n1_50)
            .replace("Light_Accent", a1_700)
            .replace("Light_DAccent", a1_300)
            .replace("Light_Text", n1_900)

        File(requireActivity().cacheDir, outputFile).writeText(text = darkThemeImport)

        val file = File(requireActivity().cacheDir, outputFile)
        val uri = FileProvider.getUriForFile(requireActivity(), "${BuildConfig.APPLICATION_ID}.fileprovider", file)
        val intent = Intent(Intent.ACTION_SEND)
        intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)
        intent.type = "document/*"
        intent.putExtra(Intent.EXTRA_STREAM, uri)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
        startActivity(Intent.createChooser(intent, theme))
    }

}
