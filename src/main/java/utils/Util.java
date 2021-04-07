package utils;

public class Util {
    public static int boolToInt(boolean bool) {
        if (bool) {
            return 1;
        } else {
            return 0;
        }
    }

    public static boolean intToBool(int i) {
        if (i > 0) {
            return true;
        } else {
            return false;
        }
    }
}
