package org.nettok.pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;
import org.nettok.Urls;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

public class LoginPage {

    private final Page page;

    public LoginPage(final Page page) {
        this.page = page;

        assertThat(page).hasURL(Urls.LOGIN_PATTERN);
        assertThat(page).hasTitle("OrangeHRM");

        final Locator usernameInput = getUsernameInput();
        assertThat(usernameInput).hasAttribute("name", "username");

        final Locator passwordInput = getPasswordInput();
        assertThat(passwordInput).hasAttribute("name", "password");

        final Locator loginButton = getLoginButton();
        assertThat(loginButton).containsText("Login");
        assertThat(loginButton).hasRole(AriaRole.BUTTON);
    }

    public Locator getUsernameInput() {
        return page.getByRole(AriaRole.TEXTBOX)
            .and(page.getByPlaceholder("Username"));
    }

    public Locator getPasswordInput() {
        return page.getByRole(AriaRole.TEXTBOX)
                .and(page.getByPlaceholder("Password"));
    }

    public Locator getLoginButton() {
        return page.getByRole(AriaRole.BUTTON)
                .and(page.getByText("Login"));
    }

}
