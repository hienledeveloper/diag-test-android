package test.diag.com.diagtest_android

import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import dagger.hilt.android.testing.HiltTestApplication
import junit.framework.TestCase
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config
import test.diag.com.diagtest_android.modules.summary.SummaryRepository
import javax.inject.Inject

/**
 * Created By Ben on 10/18/20
 */
@HiltAndroidTest
@Config(application = HiltTestApplication::class)
@RunWith(RobolectricTestRunner::class)
class SummaryViewModelTest {

    @get:Rule
    var hiltRule = HiltAndroidRule(this)


    @Inject
    lateinit var summaryRepository: SummaryRepository

    @Before
    fun before() {
        hiltRule.inject()
    }

    @Test
    fun `test injection & request api to get summary`() {
        runBlocking {
            summaryRepository.getSummary().onSuccess {
                TestCase.assertTrue(it.countries.isNotEmpty())
                print(it)
            }
        }
    }

    @Test
    fun `test injection & request api to get info filter`() {
        runBlocking {
            summaryRepository.getInfoByCountry("afghanistan").onSuccess {
                it.first().let {  area ->
                    TestCase.assertTrue(area.country == "Afghanistan")
                    print(area)
                }

            }
        }
    }

}