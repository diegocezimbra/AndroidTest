package br.com.androidtest.repositories.mydata

import br.com.androidtest.repositories.mydata.local.MyDataLocalDataSource
import br.com.androidtest.repositories.mydata.model.MyDataNewDomainModel
import br.com.androidtest.repositories.mydata.model.MyDataOldDomainModel
import br.com.androidtest.services.mydata.MyDataService
import br.com.androidtest.services.mydata.model.map

class DefaultMyDataRepository(
    private val service: MyDataService,
    private val localDataSource: MyDataLocalDataSource
) : MyDataRepository {

    override suspend fun getNewData(forceRefresh: Boolean): MyDataNewDomainModel {
        if (!forceRefresh) {
            localDataSource.getNewData()?.let { return it }
        }

        val data = service.fetchNew().map()
        localDataSource.saveNewData(data)
        return data
    }

    override suspend fun getOldData(forceRefresh: Boolean): MyDataOldDomainModel {
        if (!forceRefresh) {
            localDataSource.getOldData()?.let { return it }
        }

        val data = service.fetchOld().map()
        localDataSource.saveOldData(data)
        return data
    }
}
