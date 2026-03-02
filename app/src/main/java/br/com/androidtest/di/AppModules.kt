package br.com.androidtest.di

import br.com.androidtest.common.DefaultDispatcherProvider
import br.com.androidtest.common.DispatcherProvider
import br.com.androidtest.features.mydata.viewmodel.MyDataViewModel
import br.com.androidtest.features.mydata.viewmodel.NPMyDataViewModel
import br.com.androidtest.features.mydata.viewmodel.RWMyDataViewModel
import br.com.androidtest.features.myplan.viewmodel.MyPlanViewModel
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
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.qualifier.named
import org.koin.dsl.module

private val coreModule = module {
    single<DispatcherProvider> { DefaultDispatcherProvider() }
    single { Json { ignoreUnknownKeys = true } }
    single { AssetReader(androidContext()) }
    single(named("privacy_url")) { getKoin().getProperty("privacy_url", "https://www.lipsum.com/") }
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
    viewModel<MyDataViewModel>(qualifier = named("my_data_np")) {
        NPMyDataViewModel(
            repository = get(),
            dispatcherProvider = get()
        )
    }

    viewModel<MyDataViewModel>(qualifier = named("my_data_rw")) {
        RWMyDataViewModel(
            repository = get(),
            privacyUrl = get(named("privacy_url")),
            dispatcherProvider = get()
        )
    }

    viewModel<MyPlanViewModel>(qualifier = named("my_plan_np")) {
        NPMyPlanViewModel(
            repository = get(),
            dispatcherProvider = get()
        )
    }

    viewModel<MyPlanViewModel>(qualifier = named("my_plan_rw")) {
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
