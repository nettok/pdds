package org.nettok.tests;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.options.AriaRole;
import org.nettok.Urls;
import org.nettok.base.PlaywrightTestBase;
import org.testng.annotations.Test;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

public class AdminTest extends PlaywrightTestBase {

    @Test
    public void adminPageRedirectsToUserManagementTest() {
        login();
        page.navigate(Urls.getAdminUrl());

        // By default, the admin module should redirect to the "user management" page
        final Locator userManagementHeading = page.getByRole(AriaRole.HEADING)
                .and(page.getByText("User Management"));

        assertThat(userManagementHeading).hasCount(1);
        assertThat(page).hasURL(Urls.ADMIN_USER_MANAGEMENT_PATTERN);
    }

}
