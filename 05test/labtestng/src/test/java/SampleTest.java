import org.testng.annotations.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class SampleTest {

    @Test
    public void test01() {
        assertThat(1).isEqualTo(1);
    }
}
