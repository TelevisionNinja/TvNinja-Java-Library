package televisionninja.lib.unittest;

public class UnitTest {
	public static void UNIT_TEST_EQ(boolean actual, boolean expected) {
        if (actual != expected) {
            throw new AssertionError("Failed UNIT_TEST_EQ\nValue:\n" + actual + "\n\nExpected:\n" + expected + "\n\n");
        }
    }

    public static void UNIT_TEST_NEQ(boolean actual, boolean notExpected) {
        if (actual == notExpected) {
            throw new AssertionError("Failed UNIT_TEST_NEQ\nValue:\n" + actual + "\n\nNot Expected:\n" + notExpected + "\n\n");
        }
    }
}
