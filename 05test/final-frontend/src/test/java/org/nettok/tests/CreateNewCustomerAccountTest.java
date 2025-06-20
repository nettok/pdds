package org.nettok.tests;

import org.nettok.base.PlaywrightTestBase;
import org.nettok.pages.CreateNewCustomerAccountPage;
import org.testng.annotations.Test;

import java.util.Random;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

public class CreateNewCustomerAccountTest extends PlaywrightTestBase {

    private static final String ALPHANUMERIC_CHARS = "abcdefghijklmnopqrstuvwxyz0123456789";
    private static final Random random = new Random();

    @Test
    public void successfulAccountCreationTest() {
        page.navigate(CreateNewCustomerAccountPage.URL);

        final var pageModel = new CreateNewCustomerAccountPage(page);
        pageModel.getFirstNameInput().fill("Nombre");
        pageModel.getLastNameInput().fill("Apellido");
        pageModel.getEmailInput().fill("%s@gmail.com".formatted(randomEmailUser()));
        pageModel.getPasswordInput().fill("&nqNwz2ya3Q@F#Dbf");
        pageModel.getConfirmPasswordInput().fill("&nqNwz2ya3Q@F#Dbf");
        pageModel.getCreateButton().click();

        // Successfully redirected to the customer account data page
        assertThat(page).hasURL("https://magento.softwaretestingboard.com/customer/account/");
    }

    private static String randomEmailUser() {
        final int length = 12;
        final StringBuilder username = new StringBuilder(length);

        for (int i = 0; i < length; i++) {
            int randomIndex = random.nextInt(ALPHANUMERIC_CHARS.length());
            username.append(ALPHANUMERIC_CHARS.charAt(randomIndex));
        }

        return username.toString();
    }
}
