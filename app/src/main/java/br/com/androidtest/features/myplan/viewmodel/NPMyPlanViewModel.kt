package br.com.androidtest.features.myplan.viewmodel

import br.com.androidtest.common.DispatcherProvider
import br.com.androidtest.features.myplan.model.MyPlanUiModel
import br.com.androidtest.repositories.myplan.MyPlanRepository

class NPMyPlanViewModel(
    private val repository: MyPlanRepository,
    dispatcherProvider: DispatcherProvider,
    loadingDelayMs: Long = 2_000
) : MyPlanViewModel(
    dispatcherProvider = dispatcherProvider,
    loadingDelayMs = loadingDelayMs
) {

    override suspend fun buildUiModel(forceRefresh: Boolean): MyPlanUiModel {
        val domain = repository.getNewData(forceRefresh)
        return MyPlanUiModel(
            status = domain.status,
            phoneNumber = domain.phoneNumber,
            planValue = domain.planValue,
            planName = domain.planName,
            offerDisplay = domain.offerDisplay,
            plan = domain.plan,
            bonus = domain.bonus,
            includedApps = domain.apps
        )
    }
}
