package org.nettok;

import org.nettok.base.PlaywrightTestBase;
import org.testng.annotations.Test;

public class LoginTest extends PlaywrightTestBase {

    @Test
    public void loginTest() {
        login();
    }

}
