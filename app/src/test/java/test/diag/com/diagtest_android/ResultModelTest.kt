package test.diag.com.diagtest_android

import junit.framework.TestCase
import org.junit.Test
import org.junit.runner.RunWith
import test.diag.com.diagtest_android.modules.retrofit.ResultModel
import java.net.UnknownHostException

/**
 * Created By Ben on 10/18/20
 */
class ResultModelTest {

    @Test
    fun `result success test`() {
        ResultModel<String>(data = "test").onSuccess {
            TestCase.assertTrue(it.isNotEmpty())
            print(it)
        }
    }

    @Test
    fun `result success fail`() {
        ResultModel<String>(errorException = UnknownHostException()).onError {
            TestCase.assertTrue(it is UnknownHostException)
        }
    }

}