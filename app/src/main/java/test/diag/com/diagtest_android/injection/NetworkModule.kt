package test.diag.com.diagtest_android.injection

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import test.diag.com.diagtest_android.modules.retrofit.ApiService
import test.diag.com.diagtest_android.modules.retrofit.RetrofitClient
import javax.inject.Singleton

/**
 * Created By Ben on 10/16/20
 */
@Module
@InstallIn(ApplicationComponent::class)
class NetworkModule {

    @Provides
    @Singleton
    fun provideApiService(): ApiService {
        return RetrofitClient.retrofit.create(ApiService::class.java)
    }

}