package br.com.androidtest.repositories.myplan.model

data class MyPlanNewDomainModel(
    val status: String,
    val phoneNumber: String,
    val planValue: String,
    val planName: String,
    val offerDisplay: String,
    val plan: String,
    val bonus: String,
    val apps: List<String>
)

data class MyPlanOldDomainModel(
    val status: String,
    val phoneNumber: String,
    val planValue: String,
    val plan: Int,
    val bonus: Int,
    val extraPlayBaseUrl: String,
    val extraPlay: List<String>
)
