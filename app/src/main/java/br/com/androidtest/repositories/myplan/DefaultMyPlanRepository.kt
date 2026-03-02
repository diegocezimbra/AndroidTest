package br.com.androidtest.repositories.myplan

import br.com.androidtest.repositories.myplan.local.MyPlanLocalDataSource
import br.com.androidtest.repositories.myplan.model.MyPlanNewDomainModel
import br.com.androidtest.repositories.myplan.model.MyPlanOldDomainModel
import br.com.androidtest.services.myplan.MyPlanService
import br.com.androidtest.services.myplan.model.map

class DefaultMyPlanRepository(
    private val service: MyPlanService,
    private val localDataSource: MyPlanLocalDataSource
) : MyPlanRepository {

    override suspend fun getNewData(forceRefresh: Boolean): MyPlanNewDomainModel {
        if (!forceRefresh) {
            localDataSource.getNewData()?.let { return it }
        }

        val data = service.fetchNew().map()
        localDataSource.saveNewData(data)
        return data
    }

    override suspend fun getOldData(forceRefresh: Boolean): MyPlanOldDomainModel {
        if (!forceRefresh) {
            localDataSource.getOldData()?.let { return it }
        }

        val data = service.fetchOld().map()
        localDataSource.saveOldData(data)
        return data
    }
}
