package br.com.androidtest.common

import android.content.Context
import android.content.Intent
import androidx.core.content.FileProvider
import java.io.File

fun shareAssetPdf(context: Context, assetName: String) {
    val cacheFile = File(context.cacheDir, assetName)
    if (!cacheFile.exists()) {
        context.assets.open(assetName).use { input ->
            cacheFile.outputStream().use { output ->
                input.copyTo(output)
            }
        }
    }

    val uri = FileProvider.getUriForFile(
        context,
        "${context.packageName}.fileprovider",
        cacheFile
    )

    val shareIntent = Intent(Intent.ACTION_SEND).apply {
        type = "application/pdf"
        putExtra(Intent.EXTRA_STREAM, uri)
        addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)
    }

    context.startActivity(Intent.createChooser(shareIntent, "Compartilhar contrato"))
}
