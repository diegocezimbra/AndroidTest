package br.com.androidtest.repositories.mydata

import br.com.androidtest.repositories.mydata.model.MyDataNewDomainModel
import br.com.androidtest.repositories.mydata.model.MyDataOldDomainModel

interface MyDataRepository {
    suspend fun getNewData(forceRefresh: Boolean = false): MyDataNewDomainModel
    suspend fun getOldData(forceRefresh: Boolean = false): MyDataOldDomainModel
}
