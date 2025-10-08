package televisionninja.lib.unittest;

import java.util.Objects;

public class UnitTest {
	public static <T> void UNIT_TEST_EQ(T actual, T expected) {
        if (!Objects.equals(actual, expected)) {
            throw new AssertionError("Failed UNIT_TEST_EQ\nValue:\n" + actual + "\n\nExpected:\n" + expected + "\n\n");
        }
    }

    public static <T> void UNIT_TEST_NEQ(T actual, T notExpected) {
        if (Objects.equals(actual, notExpected)) {
            throw new AssertionError("Failed UNIT_TEST_NEQ\nValue:\n" + actual + "\n\nNot Expected:\n" + notExpected + "\n\n");
        }
    }
}
