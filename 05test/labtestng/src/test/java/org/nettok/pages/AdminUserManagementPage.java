package org.nettok.pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;
import org.nettok.Urls;

import java.util.regex.Pattern;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

public class AdminUserManagementPage {

    private final Page page;

    public AdminUserManagementPage(final Page page) {
        this.page = page;

        assertThat(page).hasURL(Urls.ADMIN_USER_MANAGEMENT_PATTERN);

        final var usernameInput = getUsernameInput();
        assertThat(usernameInput).hasCount(1);
        assertThat(usernameInput).not().hasAttribute("placeholder", Pattern.compile(".+"));

        final var searchButton = getSearchButton();
        assertThat(searchButton).hasCount(1);

        final var resetButton = getResetButton();
        assertThat(resetButton).hasCount(1);

        final var addButton = getAddButton();
        assertThat(addButton).hasCount(1);
    }

    public Locator getUsernameInput() {
        return page.getByRole(AriaRole.TEXTBOX)
                .nth(1);
    }

    public Locator getSearchButton() {
        return page.getByRole(AriaRole.BUTTON)
                .and(page.getByText("Search"));
    }

    public Locator getResetButton() {
        return page.getByRole(AriaRole.BUTTON)
                .and(page.getByText("Reset"));
    }

    public Locator getAddButton() {
        return page.getByRole(AriaRole.BUTTON)
                .and(page.getByText("Add"));
    }
}
