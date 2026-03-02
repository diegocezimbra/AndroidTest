package br.com.androidtest.services.mydata

import br.com.androidtest.services.common.AssetReader
import br.com.androidtest.services.mydata.model.MyDataNewServiceModel
import br.com.androidtest.services.mydata.model.MyDataOldServiceModel
import kotlinx.serialization.json.Json

class AssetMyDataService(
    private val assetReader: AssetReader,
    private val json: Json
) : MyDataService {

    override suspend fun fetchNew(): MyDataNewServiceModel {
        val content = assetReader.readText("my_data/my_data_new.json")
        return json.decodeFromString(content)
    }

    override suspend fun fetchOld(): MyDataOldServiceModel {
        val content = assetReader.readText("my_data/my_data_old.json")
        return json.decodeFromString(content)
    }
}
