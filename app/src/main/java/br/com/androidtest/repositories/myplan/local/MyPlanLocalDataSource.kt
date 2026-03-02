package br.com.androidtest.repositories.myplan.local

import br.com.androidtest.repositories.myplan.model.MyPlanNewDomainModel
import br.com.androidtest.repositories.myplan.model.MyPlanOldDomainModel

class MyPlanLocalDataSource {
    private var newDataCache: MyPlanNewDomainModel? = null
    private var oldDataCache: MyPlanOldDomainModel? = null

    fun getNewData(): MyPlanNewDomainModel? = newDataCache

    fun saveNewData(data: MyPlanNewDomainModel) {
        newDataCache = data
    }

    fun getOldData(): MyPlanOldDomainModel? = oldDataCache

    fun saveOldData(data: MyPlanOldDomainModel) {
        oldDataCache = data
    }
}
