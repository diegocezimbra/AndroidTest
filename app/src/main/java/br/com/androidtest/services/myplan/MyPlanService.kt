package br.com.androidtest.services.myplan

import br.com.androidtest.services.myplan.model.MyPlanNewServiceModel
import br.com.androidtest.services.myplan.model.MyPlanOldServiceModel

interface MyPlanService {
    suspend fun fetchNew(): MyPlanNewServiceModel
    suspend fun fetchOld(): MyPlanOldServiceModel
}
