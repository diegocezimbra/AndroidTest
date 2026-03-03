package br.com.androidtest.common

enum class PlatformType(
    val routeValue: String,
    val myDataQualifier: String,
    val myPlanQualifier: String
) {
    NP(
        routeValue = "np",
        myDataQualifier = ViewModelQualifierKeys.MY_DATA_NP,
        myPlanQualifier = ViewModelQualifierKeys.MY_PLAN_NP
    ),
    RW(
        routeValue = "rw",
        myDataQualifier = ViewModelQualifierKeys.MY_DATA_RW,
        myPlanQualifier = ViewModelQualifierKeys.MY_PLAN_RW
    );

    companion object {
        fun fromRoute(value: String?): PlatformType =
            entries.firstOrNull { it.routeValue == value } ?: NP
    }
}
