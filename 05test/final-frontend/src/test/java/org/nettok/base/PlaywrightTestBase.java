package org.nettok.base;

import com.microsoft.playwright.*;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;

public abstract class PlaywrightTestBase {

    private Playwright playwright;
    private Browser browser;

    private BrowserContext browserContext;
    protected Page page;

    @BeforeClass
    public void setUpClass() {
        playwright = Playwright.create();

        browser = playwright.chromium().launch(
                new BrowserType.LaunchOptions()
                        .setHeadless(true)
        );
    }

    @AfterClass
    public void tearDownClass() {
        browser.close();
        playwright.close();
    }

    @BeforeMethod
    public void setUpMethod() {
        browserContext = browser.newContext();
        page = browserContext.newPage();
    }

    @AfterMethod
    public void tearDownMethod() {
        page.close();
        browserContext.close();
    }

}
