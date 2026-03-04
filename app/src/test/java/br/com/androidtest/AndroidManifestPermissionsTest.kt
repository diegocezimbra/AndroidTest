package br.com.androidtest

import org.junit.Assert.assertTrue
import org.junit.Assert.fail
import org.junit.Test
import javax.xml.parsers.DocumentBuilderFactory

class AndroidManifestPermissionsTest {

    private val manifestPath = "src/main/AndroidManifest.xml"

    private fun getManifestPermissions(): Set<String> {
        val file = java.io.File(manifestPath)
        if (!file.exists()) fail("AndroidManifest.xml not found at $manifestPath")

        val doc = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(file)
        val nodes = doc.getElementsByTagName("uses-permission")
        return (0 until nodes.length).map { i ->
            nodes.item(i).attributes.getNamedItem("android:name").nodeValue
        }.toSet()
    }

    @Test
    fun `manifest must declare INTERNET permission`() {
        val permissions = getManifestPermissions()
        assertTrue(
            "INTERNET permission is required for network calls (Coil, OkHttp) but is missing from AndroidManifest.xml",
            permissions.contains("android.permission.INTERNET")
        )
    }
}
