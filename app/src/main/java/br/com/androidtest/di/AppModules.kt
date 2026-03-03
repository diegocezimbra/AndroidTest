package br.com.androidtest.di

import br.com.androidtest.common.AppConfig
import br.com.androidtest.common.DefaultDispatcherProvider
import br.com.androidtest.common.DispatcherProvider
import br.com.androidtest.common.ViewModelQualifierKeys
import br.com.androidtest.features.mydata.viewmodel.NPMyDataViewModel
import br.com.androidtest.features.mydata.viewmodel.RWMyDataViewModel
import br.com.androidtest.features.myplan.viewmodel.NPMyPlanViewModel
import br.com.androidtest.features.myplan.viewmodel.RWMyPlanViewModel
import br.com.androidtest.repositories.mydata.DefaultMyDataRepository
import br.com.androidtest.repositories.mydata.MyDataRepository
import br.com.androidtest.repositories.mydata.local.MyDataLocalDataSource
import br.com.androidtest.repositories.myplan.DefaultMyPlanRepository
import br.com.androidtest.repositories.myplan.MyPlanRepository
import br.com.androidtest.repositories.myplan.local.MyPlanLocalDataSource
import br.com.androidtest.services.common.AssetReader
import br.com.androidtest.services.mydata.AssetMyDataService
import br.com.androidtest.services.mydata.MyDataService
import br.com.androidtest.services.myplan.AssetMyPlanService
import br.com.androidtest.services.myplan.MyPlanService
import kotlinx.serialization.json.Json
import org.koin.android.ext.koin.androidContext
import org.koin.core.module.dsl.viewModel
import org.koin.core.qualifier.named
import org.koin.dsl.module

private val coreModule = module {
    single<DispatcherProvider> { DefaultDispatcherProvider() }
    single { Json { ignoreUnknownKeys = true } }
    single { AssetReader(androidContext()) }
    single(named(AppConfig.PRIVACY_URL_PROPERTY_KEY)) {
        getKoin().getProperty(AppConfig.PRIVACY_URL_PROPERTY_KEY, AppConfig.DEFAULT_PRIVACY_URL)
    }
}

private val serviceModule = module {
    single<MyDataService> { AssetMyDataService(get(), get()) }
    single<MyPlanService> { AssetMyPlanService(get(), get()) }
}

private val repositoryModule = module {
    single { MyDataLocalDataSource() }
    single<MyDataRepository> { DefaultMyDataRepository(get(), get()) }

    single { MyPlanLocalDataSource() }
    single<MyPlanRepository> { DefaultMyPlanRepository(get(), get()) }
}

private val viewModelModule = module {
    viewModel<NPMyDataViewModel>(qualifier = named(ViewModelQualifierKeys.MY_DATA_NP)) {
        NPMyDataViewModel(
            repository = get(),
            dispatcherProvider = get()
        )
    }

    viewModel<RWMyDataViewModel>(qualifier = named(ViewModelQualifierKeys.MY_DATA_RW)) {
        RWMyDataViewModel(
            repository = get(),
            privacyUrl = get(named(AppConfig.PRIVACY_URL_PROPERTY_KEY)),
            dispatcherProvider = get()
        )
    }

    viewModel<NPMyPlanViewModel>(qualifier = named(ViewModelQualifierKeys.MY_PLAN_NP)) {
        NPMyPlanViewModel(
            repository = get(),
            dispatcherProvider = get()
        )
    }

    viewModel<RWMyPlanViewModel>(qualifier = named(ViewModelQualifierKeys.MY_PLAN_RW)) {
        RWMyPlanViewModel(
            repository = get(),
            dispatcherProvider = get()
        )
    }
}

val appModules = listOf(
    coreModule,
    serviceModule,
    repositoryModule,
    viewModelModule
)
