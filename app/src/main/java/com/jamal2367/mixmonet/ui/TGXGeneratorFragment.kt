package com.jamal2367.mixmonet.ui

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.view.ContextThemeWrapper
import androidx.core.content.FileProvider
import androidx.fragment.app.Fragment
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.jamal2367.mixmonet.BuildConfig
import com.jamal2367.mixmonet.R
import com.jamal2367.mixmonet.data.tgx.Generator
import com.google.android.material.transition.MaterialFadeThrough
import java.io.File

class TGXGeneratorFragment: Fragment(R.layout.fragment_tgx) {
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)

    enterTransition = MaterialFadeThrough()
    returnTransition = MaterialFadeThrough()
  }

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)

    view.findViewById<View>(R.id.buttonTGXDark).setOnClickListener {
      val file = File(requireContext().cacheDir, "Telegram_X_Monet_Dark.tgx-theme")

      if (file.exists()) file.delete()
      file.createNewFile()
      file.writeText(Generator.generateFileContentDark(ContextThemeWrapper(requireContext(), android.R.style.Theme_DeviceDefault)))

      val fileUri = FileProvider.getUriForFile(requireActivity(), BuildConfig.APPLICATION_ID + ".provider", file)

      requireActivity().startActivity(Intent.createChooser(Intent().apply {
        action = Intent.ACTION_SEND
        putExtra(Intent.EXTRA_STREAM, fileUri)
        type = "application/octet-stream"
        flags = Intent.FLAG_GRANT_READ_URI_PERMISSION }, "Share a theme"))
    }

    view.findViewById<View>(R.id.buttonTGXLight).setOnClickListener {
      val file = File(requireContext().cacheDir, "Telegram_X_Monet_Light.tgx-theme")

      if (file.exists()) file.delete()
      file.createNewFile()
      file.writeText(Generator.generateFileContentLight(ContextThemeWrapper(requireContext(), android.R.style.Theme_DeviceDefault)))

      val fileUri = FileProvider.getUriForFile(requireActivity(), BuildConfig.APPLICATION_ID + ".provider", file)

      requireActivity().startActivity(Intent.createChooser(Intent().apply {
        action = Intent.ACTION_SEND
        putExtra(Intent.EXTRA_STREAM, fileUri)
        type = "application/octet-stream"
        flags = Intent.FLAG_GRANT_READ_URI_PERMISSION }, "Share a theme"))
    }

    view.findViewById<View>(R.id.infoTGX).setOnClickListener {
      dialogTGX()
    }
  }

  private fun dialogTGX() {
    MaterialAlertDialogBuilder(requireActivity())
      .setTitle(R.string.assistance)
      .setIcon(R.drawable.ic_twotone_info_24)
      .setMessage(R.string.tgx_generator_info)
      .setNegativeButton(android.R.string.cancel) { _, _ ->
        // Close dialog
      }
      .show()
  }
}