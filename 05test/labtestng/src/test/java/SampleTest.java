import com.microsoft.playwright.*;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.nio.file.Paths;

import static org.assertj.core.api.Assertions.assertThat;

public class SampleTest {

    private Playwright playwright;
    private Page page;

    @BeforeMethod
    public void setUp() {
        playwright = Playwright.create();

        var browser = playwright.chromium().launch(
                new BrowserType.LaunchOptions()
                        .setHeadless(true));

        page = browser.newPage();
    }

    @AfterMethod
    public void tearDown() {
        playwright.close();
    }

    @Test
    public void test01() {
        assertThat(1).isEqualTo(1);
    }

    @Test
    public void testPW() {
        page.navigate("https://playwright.dev/");
        page.screenshot(new Page.ScreenshotOptions().setPath(Paths.get("example.png")));
    }
}
