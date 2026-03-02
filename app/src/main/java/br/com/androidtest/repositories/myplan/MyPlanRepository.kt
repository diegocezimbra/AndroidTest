package br.com.androidtest.repositories.myplan

import br.com.androidtest.repositories.myplan.model.MyPlanNewDomainModel
import br.com.androidtest.repositories.myplan.model.MyPlanOldDomainModel

interface MyPlanRepository {
    suspend fun getNewData(forceRefresh: Boolean = false): MyPlanNewDomainModel
    suspend fun getOldData(forceRefresh: Boolean = false): MyPlanOldDomainModel
}
