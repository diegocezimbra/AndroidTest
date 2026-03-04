package br.com.androidtest.common

import android.content.Intent
import android.net.Uri
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner

@RunWith(RobolectricTestRunner::class)
class ShareAssetPdfTest {

    private val fakeUri: Uri = Uri.parse("content://br.com.androidtest.fileprovider/shared/terms.pdf")

    @Test
    fun `share intent must have FLAG_GRANT_READ_URI_PERMISSION`() {
        val intent = createSharePdfIntent(fakeUri)

        assertTrue(
            "Share intent must grant read URI permission to avoid SecurityException",
            intent.flags and Intent.FLAG_GRANT_READ_URI_PERMISSION != 0
        )
    }

    @Test
    fun `share intent must use ACTION_SEND with pdf type`() {
        val intent = createSharePdfIntent(fakeUri)

        assertEquals(Intent.ACTION_SEND, intent.action)
        assertEquals("application/pdf", intent.type)
    }

    @Test
    fun `share intent must include the uri as EXTRA_STREAM`() {
        val intent = createSharePdfIntent(fakeUri)

        val stream = intent.getParcelableExtra<Uri>(Intent.EXTRA_STREAM)
        assertEquals(fakeUri, stream)
    }

    @Test
    fun `chooser must also have FLAG_GRANT_READ_URI_PERMISSION`() {
        val shareIntent = createSharePdfIntent(fakeUri)
        val chooser = createShareChooser(shareIntent, "Compartilhar")

        assertTrue(
            "Chooser must also grant read URI permission or the receiving app gets SecurityException",
            chooser.flags and Intent.FLAG_GRANT_READ_URI_PERMISSION != 0
        )
    }
}
