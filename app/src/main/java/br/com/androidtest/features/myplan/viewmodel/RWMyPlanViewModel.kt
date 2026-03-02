package br.com.androidtest.features.myplan.viewmodel

import br.com.androidtest.common.DispatcherProvider
import br.com.androidtest.features.myplan.model.MyPlanUiModel
import br.com.androidtest.repositories.myplan.MyPlanRepository

class RWMyPlanViewModel(
    private val repository: MyPlanRepository,
    dispatcherProvider: DispatcherProvider,
    loadingDelayMs: Long = 2_000
) : MyPlanViewModel(
    dispatcherProvider = dispatcherProvider,
    loadingDelayMs = loadingDelayMs
) {

    override suspend fun buildUiModel(forceRefresh: Boolean): MyPlanUiModel {
        val domain = repository.getOldData(forceRefresh)
        return MyPlanUiModel(
            status = domain.status,
            phoneNumber = domain.phoneNumber,
            planValue = domain.planValue,
            planName = "Plano Claro Flex",
            offerDisplay = "${domain.plan + domain.bonus}GB",
            plan = "${domain.plan}GB",
            bonus = "${domain.bonus}GB",
            includedApps = domain.extraPlay.map { "${domain.extraPlayBaseUrl}/$it" }
        )
    }
}
