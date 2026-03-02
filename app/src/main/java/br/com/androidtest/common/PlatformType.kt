package br.com.androidtest.common

enum class PlatformType(
    val routeValue: String,
    val myDataQualifier: String,
    val myPlanQualifier: String
) {
    NP(routeValue = "np", myDataQualifier = "my_data_np", myPlanQualifier = "my_plan_np"),
    RW(routeValue = "rw", myDataQualifier = "my_data_rw", myPlanQualifier = "my_plan_rw");

    companion object {
        fun fromRoute(value: String?): PlatformType =
            entries.firstOrNull { it.routeValue == value } ?: NP
    }
}
