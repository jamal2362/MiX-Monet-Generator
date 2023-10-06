package com.jamal2367.mixmonet.ui

import android.content.Intent
import android.os.Bundle
import android.os.Environment
import android.view.View
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.core.content.FileProvider
import androidx.fragment.app.Fragment
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.android.material.transition.MaterialFadeThrough
import com.jamal2367.mixmonet.R
import java.io.File
import java.io.FileOutputStream
import java.util.zip.ZipEntry
import java.util.zip.ZipOutputStream

class MiXGeneratorFragment : Fragment(R.layout.fragment_mix) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        enterTransition = MaterialFadeThrough()
        exitTransition = MaterialFadeThrough()
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val blackMixFile = "black_mix_monet.xml"
        val darkMixFile = "dark_mix_monet.xml"
        val lightMixFile = "light_mix_monet.xml"
        val outputFile = "properties.xml"

        view.findViewById<View>(R.id.buttonMixLight).setOnClickListener {
            createTheme(lightMixFile, outputFile)
        }

        view.findViewById<View>(R.id.buttonMixDark).setOnClickListener {
            createTheme(darkMixFile, outputFile)
        }

        view.findViewById<View>(R.id.buttonMixBlack).setOnClickListener {
            createTheme(blackMixFile, outputFile)
        }
    }


    private fun createTheme(monetFile: String, outputFile: String) {
        val a1100 = "#${Integer.toHexString(ContextCompat.getColor(requireActivity(), R.color.system_accent1_100))}"
        val a1300 = "#${Integer.toHexString(ContextCompat.getColor(requireActivity(), R.color.system_accent1_300))}"
        val a1700 = "#${Integer.toHexString(ContextCompat.getColor(requireActivity(), R.color.system_accent1_700))}"
        val n110 = "#${Integer.toHexString(ContextCompat.getColor(requireActivity(), R.color.system_neutral1_10))}"
        val n150 = "#${Integer.toHexString(ContextCompat.getColor(requireActivity(), R.color.system_neutral1_50))}"
        val n1800 = "#${Integer.toHexString(ContextCompat.getColor(requireActivity(), R.color.system_neutral1_800))}"
        val n1900 = "#${Integer.toHexString(ContextCompat.getColor(requireActivity(), R.color.system_neutral1_900))}"
        val n11000 = "#${Integer.toHexString(ContextCompat.getColor(requireActivity(), R.color.system_neutral1_1000))}"

        // generate the theme
        val themeImport = requireActivity().assets.open(monetFile).bufferedReader().readLines().joinToString()
            .replace(", ", "\n")
            .replace("Black_Background", n11000)
            .replace("Black_Highlight", n1900)
            .replace("Dark_Background", n1900)
            .replace("Dark_Highlight", n1800)
            .replace("Dark_Accent", a1100)
            .replace("Dark_DAccent", a1300)
            .replace("Dark_Text", n150)
            .replace("Light_Background", n110)
            .replace("Light_Highlight", n150)
            .replace("Light_Accent", a1700)
            .replace("Light_DAccent", a1300)
            .replace("Light_Text", n1900)

        // directory for the download folder
        val downloadDir = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS)
        downloadDir.mkdirs()

        // create a MIT file and add the XML file
        val zipFile = File(downloadDir, "properties.mit")
        val zipOutputStream = ZipOutputStream(FileOutputStream(zipFile))
        val entry = ZipEntry(outputFile)
        zipOutputStream.putNextEntry(entry)
        val xmlBytes = themeImport.toByteArray()
        zipOutputStream.write(xmlBytes)
        zipOutputStream.closeEntry()
        zipOutputStream.close()

        // open instruction dialog
        dialogMIX(zipFile)
    }


    private fun dialogMIX(zipFile: File) {
        MaterialAlertDialogBuilder(requireActivity())
            .setTitle(R.string.assistance)
            .setIcon(R.drawable.ic_twotone_info_24)
            .setMessage(R.string.mix_generator_info)
            .setPositiveButton(R.string.open_mixplorer) { dialog, _ ->
                openOtherApp(zipFile)
                dialog.dismiss()
            }
            .setNeutralButton(R.string.close) { dialog, _ ->
                dialog.dismiss()
            }
            .show()
    }


    private fun openOtherApp(zipFile: File) {
        val intent = Intent(Intent.ACTION_VIEW)
        val uri = FileProvider.getUriForFile(requireContext(), "com.jamal2367.mixmonet.fileprovider", zipFile)

        intent.setDataAndType(uri, "application/zip")
        intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)

        try {
            startActivity(intent)
            Toast.makeText(context, R.string.open_download_folder, Toast.LENGTH_LONG).show()
        } catch (e: Exception) {
            e.printStackTrace()
            Toast.makeText(context, R.string.open_failed, Toast.LENGTH_LONG).show()
        }
    }

}
