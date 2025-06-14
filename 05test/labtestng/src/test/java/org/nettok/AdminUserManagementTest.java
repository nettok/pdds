package org.nettok;

import com.microsoft.playwright.Locator;
import org.assertj.core.api.Assertions;
import org.nettok.base.PlaywrightTestBase;
import org.nettok.pages.AdminUserManagementPage;
import org.testng.annotations.Test;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

public class AdminUserManagementTest extends PlaywrightTestBase {

    @Test
    public void searchUserTest() {
        login();
        page.navigate(Urls.getAdminUserManagementUrl());

        final var adminUserManagementPage = new AdminUserManagementPage(page);
        adminUserManagementPage.getUsernameInput().fill("Admin");
        adminUserManagementPage.getSearchButton().click();

        final Locator recordsFoundLocator = page.getByText("(1) Record Found");

        assertThat(recordsFoundLocator).hasCount(1);
    }

    @Test
    public void resetSearchFormTest() {
        searchUserTest();

        final var adminUserManagementPage = new AdminUserManagementPage(page);
        adminUserManagementPage.getResetButton().click();

        final String usernameInputValue = adminUserManagementPage.getUsernameInput().inputValue();

        Assertions.assertThat(usernameInputValue).isEmpty();

    }

    @Test
    public void clickAddUserTest() {
        login();
        page.navigate(Urls.getAdminUserManagementUrl());

        final var adminUserManagementPage = new AdminUserManagementPage(page);
        adminUserManagementPage.getAddButton().click();

        assertThat(page).hasURL(Urls.getAdminSaveUserUrl());
    }

}
