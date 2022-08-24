package hu.wolfman.fishscreensaver.frame;

import com.sun.jna.platform.win32.Advapi32Util;
import com.sun.jna.platform.win32.Win32Exception;
import com.sun.jna.platform.win32.WinReg;

public class Settings {

    public static final int MIN_FISH = 5;
    public static final int MAX_FISH = 20;

    private static final WinReg.HKEY HKCU = WinReg.HKEY_CURRENT_USER;
    private static final String PARENT_PATH = "SOFTWARE";
    private static final String KEY_NAME = "FishScreensaver";
    private static final String KEY_PATH = PARENT_PATH + "\\" + KEY_NAME;
    private static final String VALUE_NAME = "NumberOfFish";

    public static int readNumber() throws Win32Exception {
        int num;
        try {
            num = Advapi32Util.registryGetIntValue(HKCU, KEY_PATH, VALUE_NAME);
        } catch (Win32Exception e) {
            createKey();
            num = MIN_FISH;
        }
        return num;
    }

    private static void createKey() throws Win32Exception {
        Advapi32Util.registryCreateKey(HKCU, PARENT_PATH, KEY_NAME);
        saveNumber(MIN_FISH);
    }

    public static void saveNumber(int num) throws Win32Exception {
        Advapi32Util.registrySetIntValue(HKCU, KEY_PATH, VALUE_NAME, num);
    }

}
