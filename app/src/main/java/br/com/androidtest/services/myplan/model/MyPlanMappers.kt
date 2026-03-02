package br.com.androidtest.services.myplan.model

import br.com.androidtest.repositories.myplan.model.MyPlanNewDomainModel
import br.com.androidtest.repositories.myplan.model.MyPlanOldDomainModel

fun MyPlanNewServiceModel.map(): MyPlanNewDomainModel {
    val status = screen.header.getOrNull(0).orEmpty().findByTitlePrefix("status")
    val phone = screen.header.getOrNull(0).orEmpty().findByTitlePrefix("meu número")
    val plan = screen.header.getOrNull(1).orEmpty().findByTitlePrefix("plano")
    val bonus = screen.header.getOrNull(1).orEmpty().findByTitlePrefix("bônus")

    return MyPlanNewDomainModel(
        status = status,
        phoneNumber = phone,
        planValue = screen.planValue,
        planName = screen.name,
        offerDisplay = screen.offerDisplay,
        plan = plan,
        bonus = bonus,
        apps = screen.extraPlay.options.map { it.url }
    )
}

fun MyPlanOldServiceModel.map(): MyPlanOldDomainModel = MyPlanOldDomainModel(
    status = content.status,
    phoneNumber = content.phoneNumber,
    planValue = content.planValue,
    plan = content.plan,
    bonus = content.bonus,
    extraPlayBaseUrl = content.extraPlayBaseUrl,
    extraPlay = content.extraPlay.split(';').map { it.trim() }.filter { it.isNotBlank() }
)

private fun List<HeaderEntryServiceModel>.findByTitlePrefix(titlePrefix: String): String =
    firstOrNull { it.title.lowercase().startsWith(titlePrefix.lowercase()) }?.value?.text.orEmpty()
