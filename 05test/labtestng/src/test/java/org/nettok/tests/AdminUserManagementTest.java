package org.nettok.tests;

import com.microsoft.playwright.Locator;
import org.assertj.core.api.Assertions;
import org.nettok.Urls;
import org.nettok.base.PlaywrightTestBase;
import org.nettok.pages.AdminUserManagementPage;
import org.testng.annotations.Test;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

public class AdminUserManagementTest extends PlaywrightTestBase {

    @Test
    public void searchUserTest() {
        login();
        page.navigate(Urls.getAdminUserManagementUrl());

        // When searching the "Admin" user, we should find 1 record
        final var adminUserManagementPage = new AdminUserManagementPage(page);
        adminUserManagementPage.getUsernameInput().fill("Admin");
        adminUserManagementPage.getSearchButton().click();

        final Locator recordsFoundLocator = page.getByText("(1) Record Found");

        assertThat(recordsFoundLocator).hasCount(1);
    }

    @Test
    public void resetSearchFormTest() {
        searchUserTest();

        // After searching for a user, if we click the "Reset" button, the form input resets.
        final var adminUserManagementPage = new AdminUserManagementPage(page);
        adminUserManagementPage.getResetButton().click();

        final String usernameInputValue = adminUserManagementPage.getUsernameInput().inputValue();

        Assertions.assertThat(usernameInputValue).isEmpty();

    }

    @Test
    public void clickAddUserTest() {
        login();
        page.navigate(Urls.getAdminUserManagementUrl());

        // If we click the "Add" button, we are taken to the "saveSystemUser" page.
        final var adminUserManagementPage = new AdminUserManagementPage(page);
        adminUserManagementPage.getAddButton().click();

        assertThat(page).hasURL(Urls.getAdminSaveUserUrl());
    }

}
