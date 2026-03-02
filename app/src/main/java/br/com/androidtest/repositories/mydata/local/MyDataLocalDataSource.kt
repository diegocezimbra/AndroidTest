package br.com.androidtest.repositories.mydata.local

import br.com.androidtest.repositories.mydata.model.MyDataNewDomainModel
import br.com.androidtest.repositories.mydata.model.MyDataOldDomainModel

class MyDataLocalDataSource {
    private var newDataCache: MyDataNewDomainModel? = null
    private var oldDataCache: MyDataOldDomainModel? = null

    fun getNewData(): MyDataNewDomainModel? = newDataCache

    fun saveNewData(data: MyDataNewDomainModel) {
        newDataCache = data
    }

    fun getOldData(): MyDataOldDomainModel? = oldDataCache

    fun saveOldData(data: MyDataOldDomainModel) {
        oldDataCache = data
    }
}
