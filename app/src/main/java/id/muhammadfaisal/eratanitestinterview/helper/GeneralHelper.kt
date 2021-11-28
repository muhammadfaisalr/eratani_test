package id.muhammadfaisal.eratanitestinterview.helper

import android.content.Context
import java.io.IOException
import java.nio.charset.Charset

class GeneralHelper {
    companion object {
        fun loadJSONFromAsset(context: Context): String? {
            return try {
                val inputStream = context.assets.open("mocks/mock_data.json")
                val size = inputStream.available()
                val buffer = ByteArray(size)
                inputStream.read(buffer)
                inputStream.close()
                String(buffer, Charset.forName("UTF-8"))
            } catch (ex: IOException) {
                ex.printStackTrace()
                return null
            }
        }
    }
}