package br.com.androidtest.services.myplan

import br.com.androidtest.services.common.AssetReader
import br.com.androidtest.services.myplan.model.MyPlanNewServiceModel
import br.com.androidtest.services.myplan.model.MyPlanOldServiceModel
import kotlinx.serialization.json.Json

class AssetMyPlanService(
    private val assetReader: AssetReader,
    private val json: Json
) : MyPlanService {

    override suspend fun fetchNew(): MyPlanNewServiceModel {
        val content = assetReader.readText("my_plan/my_plan_new.json")
        return json.decodeFromString(content)
    }

    override suspend fun fetchOld(): MyPlanOldServiceModel {
        val content = assetReader.readText("my_plan/my_plan_old.json")
        return json.decodeFromString(content)
    }
}
