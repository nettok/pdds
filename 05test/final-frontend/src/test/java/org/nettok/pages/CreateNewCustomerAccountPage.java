package org.nettok.pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

public class CreateNewCustomerAccountPage {

    public static final String URL = "https://magento.softwaretestingboard.com/customer/account/create/";

    private final Page page;

    public CreateNewCustomerAccountPage(final Page page) {
        this.page = page;

        assertThat(page).hasURL(URL);

        final Locator firstNameInput = getFirstNameInput();
        assertThat(firstNameInput).hasAttribute("id", "firstname");

        final Locator lastNameInput = getLastNameInput();
        assertThat(lastNameInput).hasAttribute("id", "lastname");

        final Locator emailInput = getEmailInput();
        assertThat(emailInput).hasAttribute("id", "email_address");

        final Locator passwordInput = getPasswordInput();
        assertThat(passwordInput).hasAttribute("id", "password");

        final Locator confirmPasswordInput = getConfirmPasswordInput();
        assertThat(confirmPasswordInput).hasAttribute("id", "password-confirmation");

        final Locator createButton = getCreateButton();
        assertThat(createButton).hasAttribute("title", "Create an Account");
    }

    public Locator getFirstNameInput() {
        return page.getByRole(AriaRole.TEXTBOX)
                .and(page.getByLabel("First Name"));
    }

    public Locator getLastNameInput() {
        return page.getByRole(AriaRole.TEXTBOX)
                .and(page.getByLabel("Last Name"));
    }

    public Locator getEmailInput() {
        return page.getByRole(AriaRole.TEXTBOX)
                .and(page.getByLabel("Email"));
    }

    public Locator getPasswordInput() {
        return page.getByRole(AriaRole.TEXTBOX)
                .and(page.getByLabel("Password", new Page.GetByLabelOptions().setExact(true)));
    }

    public Locator getConfirmPasswordInput() {
        return page.getByRole(AriaRole.TEXTBOX)
                .and(page.getByLabel("Confirm Password", new Page.GetByLabelOptions().setExact(true)));
    }

    public Locator getCreateButton() {
        return page.getByRole(AriaRole.BUTTON).nth(1);
    }
}
