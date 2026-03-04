package br.com.androidtest.common

import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.core.content.FileProvider
import java.io.File

internal fun createSharePdfIntent(uri: Uri): Intent {
    return Intent(Intent.ACTION_SEND).apply {
        type = "application/pdf"
        putExtra(Intent.EXTRA_STREAM, uri)
        addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)
    }
}

internal fun createShareChooser(shareIntent: Intent, title: String): Intent {
    return Intent.createChooser(shareIntent, title).apply {
        addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)
    }
}

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

    val shareIntent = createSharePdfIntent(uri)
    val chooser = createShareChooser(shareIntent, "Compartilhar contrato")
    context.startActivity(chooser)
}
