package com.jamal2367.mixmonet.ui

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.core.content.ContextCompat
import androidx.core.content.FileProvider
import androidx.fragment.app.Fragment
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.android.material.transition.MaterialFadeThrough
import com.jamal2367.mixmonet.BuildConfig
import com.jamal2367.mixmonet.R
import java.io.File

class TGGeneratorFragment: Fragment(R.layout.fragment_tg) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        enterTransition = MaterialFadeThrough()
        returnTransition = MaterialFadeThrough()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        view.findViewById<View>(R.id.buttonTGLight).setOnClickListener {
            val lightMonetFile = "light_tg_monet.attheme"
            val lightThemeImport = requireActivity().assets.open(lightMonetFile).bufferedReader().readText()
            val themeString = changeText(lightThemeImport)
            val fileName = "Telegram_Monet_Light.attheme"
            File(requireActivity().cacheDir, fileName).writeText(text = themeString)
            val themeName: String = resources.getString(R.string.light_theme)
            shareTheme(themeName, fileName)
        }

        view.findViewById<View>(R.id.buttonTGDark).setOnClickListener {
            val darkMonetFile = "dark_tg_monet.attheme"
            val darkThemeImport = requireActivity().assets.open(darkMonetFile).bufferedReader().readText()
            val themeString = changeText(darkThemeImport)
            val fileName = "Telegram_Monet_Dark.attheme"
            File(requireActivity().cacheDir, fileName).writeText(text = themeString)
            val themeName: String = resources.getString(R.string.dark_theme)
            shareTheme(themeName, fileName)
        }

        view.findViewById<View>(R.id.infoTG).setOnClickListener {
            dialogTG()
        }
    }

    private fun changeText(file: String): String {
        val monetList = mapOf(
            "a1_0" to ContextCompat.getColor(requireActivity(), R.color.system_accent1_0),
            "a1_200" to ContextCompat.getColor(requireActivity(), R.color.system_accent1_200),
            "a1_300" to ContextCompat.getColor(requireActivity(), R.color.system_accent1_300),
            "a1_400" to ContextCompat.getColor(requireActivity(), R.color.system_accent1_400),
            "a1_500" to ContextCompat.getColor(requireActivity(), R.color.system_accent1_500),
            "a1_600" to ContextCompat.getColor(requireActivity(), R.color.system_accent1_600),
            "a1_700" to ContextCompat.getColor(requireActivity(), R.color.system_accent1_700),
            "a1_800" to ContextCompat.getColor(requireActivity(), R.color.system_accent1_800),
            "a1_900" to ContextCompat.getColor(requireActivity(), R.color.system_accent1_900),
            "a1_1000" to ContextCompat.getColor(requireActivity(), R.color.system_accent1_1000),
            "a1_100" to ContextCompat.getColor(requireActivity(), R.color.system_accent1_100),
            "a1_10" to ContextCompat.getColor(requireActivity(), R.color.system_accent1_10),
            "a1_50" to ContextCompat.getColor(requireActivity(), R.color.system_accent1_50),
            "a2_0" to ContextCompat.getColor(requireActivity(), R.color.system_accent2_0),
            "a2_200" to ContextCompat.getColor(requireActivity(), R.color.system_accent2_200),
            "a2_300" to ContextCompat.getColor(requireActivity(), R.color.system_accent2_300),
            "a2_400" to ContextCompat.getColor(requireActivity(), R.color.system_accent2_400),
            "a2_500" to ContextCompat.getColor(requireActivity(), R.color.system_accent2_500),
            "a2_600" to ContextCompat.getColor(requireActivity(), R.color.system_accent2_600),
            "a2_700" to ContextCompat.getColor(requireActivity(), R.color.system_accent2_700),
            "a2_800" to ContextCompat.getColor(requireActivity(), R.color.system_accent2_800),
            "a2_900" to ContextCompat.getColor(requireActivity(), R.color.system_accent2_900),
            "a2_1000" to ContextCompat.getColor(requireActivity(), R.color.system_accent2_1000),
            "a2_100" to ContextCompat.getColor(requireActivity(), R.color.system_accent2_100),
            "a2_10" to ContextCompat.getColor(requireActivity(), R.color.system_accent2_10),
            "a2_50" to ContextCompat.getColor(requireActivity(), R.color.system_accent2_50),
            "a3_0" to ContextCompat.getColor(requireActivity(), R.color.system_accent3_0),
            "a3_200" to ContextCompat.getColor(requireActivity(), R.color.system_accent3_200),
            "a3_300" to ContextCompat.getColor(requireActivity(), R.color.system_accent3_300),
            "a3_400" to ContextCompat.getColor(requireActivity(), R.color.system_accent3_400),
            "a3_500" to ContextCompat.getColor(requireActivity(), R.color.system_accent3_500),
            "a3_600" to ContextCompat.getColor(requireActivity(), R.color.system_accent3_600),
            "a3_700" to ContextCompat.getColor(requireActivity(), R.color.system_accent3_700),
            "a3_800" to ContextCompat.getColor(requireActivity(), R.color.system_accent3_800),
            "a3_900" to ContextCompat.getColor(requireActivity(), R.color.system_accent3_900),
            "a3_1000" to ContextCompat.getColor(requireActivity(), R.color.system_accent3_1000),
            "a3_100" to ContextCompat.getColor(requireActivity(), R.color.system_accent3_100),
            "a3_10" to ContextCompat.getColor(requireActivity(), R.color.system_accent3_10),
            "a3_50" to ContextCompat.getColor(requireActivity(), R.color.system_accent3_50),
            "n1_0" to ContextCompat.getColor(requireActivity(), R.color.system_neutral1_0),
            "n1_200" to ContextCompat.getColor(requireActivity(), R.color.system_neutral1_200),
            "n1_300" to ContextCompat.getColor(requireActivity(), R.color.system_neutral1_300),
            "n1_400" to ContextCompat.getColor(requireActivity(), R.color.system_neutral1_400),
            "n1_500" to ContextCompat.getColor(requireActivity(), R.color.system_neutral1_500),
            "n1_600" to ContextCompat.getColor(requireActivity(), R.color.system_neutral1_600),
            "n1_700" to ContextCompat.getColor(requireActivity(), R.color.system_neutral1_700),
            "n1_800" to ContextCompat.getColor(requireActivity(), R.color.system_neutral1_800),
            "n1_900" to ContextCompat.getColor(requireActivity(), R.color.system_neutral1_900),
            "n1_1000" to ContextCompat.getColor(requireActivity(), R.color.system_neutral1_1000),
            "n1_100" to ContextCompat.getColor(requireActivity(), R.color.system_neutral1_100),
            "n1_10" to ContextCompat.getColor(requireActivity(), R.color.system_neutral1_10),
            "n1_50" to ContextCompat.getColor(requireActivity(), R.color.system_neutral1_50),
            "n2_0" to ContextCompat.getColor(requireActivity(), R.color.system_neutral2_0),
            "n2_200" to ContextCompat.getColor(requireActivity(), R.color.system_neutral2_200),
            "n2_300" to ContextCompat.getColor(requireActivity(), R.color.system_neutral2_300),
            "n2_400" to ContextCompat.getColor(requireActivity(), R.color.system_neutral2_400),
            "n2_500" to ContextCompat.getColor(requireActivity(), R.color.system_neutral2_500),
            "n2_600" to ContextCompat.getColor(requireActivity(), R.color.system_neutral2_600),
            "n2_700" to ContextCompat.getColor(requireActivity(), R.color.system_neutral2_700),
            "n2_800" to ContextCompat.getColor(requireActivity(), R.color.system_neutral2_800),
            "n2_900" to ContextCompat.getColor(requireActivity(), R.color.system_neutral2_900),
            "n2_1000" to ContextCompat.getColor(requireActivity(), R.color.system_neutral2_1000),
            "n2_100" to ContextCompat.getColor(requireActivity(), R.color.system_neutral2_100),
            "n2_10" to ContextCompat.getColor(requireActivity(), R.color.system_neutral2_10),
            "n2_50" to ContextCompat.getColor(requireActivity(), R.color.system_neutral2_50),
        )

        var themeText = file.replace("\$", "")

        monetList.forEach {
            themeText = themeText.replace(it.key, it.value.toString())
        }

        return themeText
    }

    private fun shareTheme(theme: String, file_name: String) {
        val file = File(requireActivity().cacheDir, file_name)
        val uri = FileProvider.getUriForFile(requireActivity(), BuildConfig.APPLICATION_ID + ".provider", file)
        val intent = Intent(Intent.ACTION_SEND)
        intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)
        intent.type = "*/attheme"
        intent.putExtra(Intent.EXTRA_STREAM, uri)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
        startActivity(Intent.createChooser(intent, theme))
    }

    private fun dialogTG() {
        MaterialAlertDialogBuilder(requireActivity())
            .setTitle(R.string.assistance)
            .setIcon(R.drawable.ic_twotone_info_24)
            .setMessage(R.string.tg_generator_info)
            .setNegativeButton(android.R.string.cancel) { _, _ ->
                // Close dialog
            }
            .show()
    }
}

