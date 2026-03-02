package br.com.androidtest.services.mydata

import br.com.androidtest.services.mydata.model.MyDataNewServiceModel
import br.com.androidtest.services.mydata.model.MyDataOldServiceModel

interface MyDataService {
    suspend fun fetchNew(): MyDataNewServiceModel
    suspend fun fetchOld(): MyDataOldServiceModel
}
