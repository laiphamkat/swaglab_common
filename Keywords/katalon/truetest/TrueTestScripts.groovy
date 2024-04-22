package katalon.truetest

import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import internal.GlobalVariable
import com.kms.katalon.core.model.FailureHandling
import com.kms.katalon.core.util.KeywordUtil
import com.kms.katalon.core.webui.exception.WebElementNotFoundException

public class TrueTestScripts {
    public static void login() {
        try {
            // Trigger a Login test case
            WebUI.callTestCase(findTestCase('Update/Login'), [:], FailureHandling.CONTINUE_ON_FAILURE)
            // Trigger a custom Login method
            // import your.package
            // import authentication.Login
            // call your custom login method
            // Login.login()
            // Trigger a custom Login keyword
            // CustomKeywords.login()
        } catch(Exception e) {
            if (e.getCause() instanceof WebElementNotFoundException) {
                KeywordUtil.logInfo(e.getMessage())
                KeywordUtil.markWarning(e.getMessage())
            } else {
                throw e
            }
        }
    }
    
    public static void navigate(String path) {
        String applicationDomain = GlobalVariable.application_domain;
        String queryParameters = GlobalVariable.query_params;
        if (path == null) {
            path = "";
        }
        if (!path.startsWith("/")) {
            path = "/$path";
        }
        String url = "$applicationDomain$path";
        if (queryParameters.length() > 0) {
            url = "$url?$queryParameters";
        }
        WebUI.navigateToUrl(url);
    }
}

