package org.nettok.base;

import com.microsoft.playwright.*;
import org.nettok.Urls;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.nettok.pages.LoginPage;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

public abstract class PlaywrightTestBase {

    private static final String USERNAME = "Admin";
    private static final String PASSWORD = "admin123";

    // Class lifetime
    private Playwright playwright;
    private Browser browser;

    // Method lifetime
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

    protected void login() {
        page.navigate(Urls.getLoginUrl());

        final var loginPage = new LoginPage(page);
        loginPage.getUsernameInput().fill(USERNAME);
        loginPage.getPasswordInput().fill(PASSWORD);
        loginPage.getLoginButton().click();

        // After login, we should see the "dashboard index" page.
        assertThat(page).hasURL(Urls.INDEX_PATTERN);
    }

}
