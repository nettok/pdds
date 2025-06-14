package org.nettok.tests;

import org.nettok.base.PlaywrightTestBase;
import org.testng.annotations.Test;

public class LoginTest extends PlaywrightTestBase {

    @Test
    public void loginTest() {
        login();
    }

}
