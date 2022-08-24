package hu.wolfman.fishscreensaver;

import com.sun.jna.Native;
import com.sun.jna.Pointer;
import com.sun.jna.platform.win32.User32;
import com.sun.jna.platform.win32.WinDef.HWND;
import com.sun.jna.platform.win32.WinDef.RECT;
import com.sun.jna.platform.win32.WinUser;
import hu.wolfman.fishscreensaver.frame.*;
import hu.wolfman.fishscreensaver.util.Messages;
import hu.wolfman.fishscreensaver.util.StringUtil;

import javax.swing.*;
import java.awt.*;

public final class Main {

    public static void main(final String[] args) {
        if (args.length == 0) {
            new SettingsForm().setVisible(true);
        } else {
            String firstArgument = args[0].toLowerCase().trim();
            String secondArgument = "";

            if (firstArgument.length() > 2) {
                secondArgument = firstArgument.substring(3).trim();
                firstArgument = firstArgument.substring(0, 2);
            } else if (args.length > 1) {
                secondArgument = args[1];
            }

            switch (firstArgument) {
                case "/c":
                    new SettingsForm().setVisible(true);
                    break;
                case "/p":
                    if (StringUtil.isEmpty(secondArgument)) {
                        Messages.errorMessage("A szülő ablak nem található.", "Hiba");
                    } else if (secondArgument.equals("0")) {
                        testPreview();
                    } else {
                        HWND parentWindow = new HWND(new Pointer(Long.parseLong(secondArgument)));
                        initPreview(parentWindow);
                    }
                    break;
                case "/s":
                    initFrame();
                    break;
                default:
                    Messages.errorMessage("Hibás argumentum került megadásra." + args[0], "Hiba");
                    break;
            }
        }
    }

    private static void initFrame() throws HeadlessException {
        JFrame screenSaverFrame = new JFrame();
        screenSaverFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        screenSaverFrame.setUndecorated(true);
        screenSaverFrame.setResizable(false);

        RajzPanel panel = new RajzPanel();
        panel.addMouseMotionListener(new InputHandler());
        screenSaverFrame.setContentPane(panel);
        screenSaverFrame.validate();

        Vezerlo vezerlo = new Vezerlo();
        panel.setVezerlo(vezerlo);
        vezerlo.setRajzPanel(panel);

        int numberOfFish = Settings.readNumber();

        GraphicsEnvironment.getLocalGraphicsEnvironment()
                .getDefaultScreenDevice()
                .setFullScreenWindow(screenSaverFrame);

        vezerlo.vizbeRak(numberOfFish);
    }

    private static void testPreview() {
        JFrame frame = new JFrame();
        frame.setUndecorated(true);
        frame.setResizable(false);
        frame.setBounds(100, 100, 100, 100);
        frame.setVisible(true);
        initPreview(new HWND(Native.getComponentPointer(frame)));
    }

    private static void initPreview(HWND parentWindow) {
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setUndecorated(true);
        frame.setResizable(false);

        RajzPanel panel = new RajzPanel();
        frame.setContentPane(panel);
        frame.validate();

        Vezerlo vezerlo = new Vezerlo();
        panel.setVezerlo(vezerlo);
        vezerlo.setRajzPanel(panel);

        try {
            int numberOfFish = Settings.readNumber();
            frame.setVisible(true);

            // Source: https://github.com/haschdl/processingscreensaver_windows
            HWND hwndChild = User32.INSTANCE.FindWindow("SunAwtFrame", null);
            RECT previewWindowRect = new RECT();
            User32.INSTANCE.GetWindowRect(parentWindow, previewWindowRect);

            //https://msdn.microsoft.com/en-us/library/windows/desktop/ms633541(v=vs.85).aspx
            User32.INSTANCE.SetWindowLong(hwndChild, WinUser.GWL_STYLE, 0); //keeps all existing styles, minus WS_CAPTION
            User32.INSTANCE.SetWindowLong(hwndChild, WinUser.GWL_STYLE, WinUser.WS_CHILDWINDOW | WinUser.WS_VISIBLE); //keeps all existing styles, minus WS_CAPTION

            //SetWindowsPos: https://msdn.microsoft.com/en-us/library/windows/desktop/ms633545(v=vs.85).aspx
            HWND zero = new HWND((new User32.INT_PTR(Integer.parseInt("0", 16))).toPointer()); //replaces constant HWND_TOP (0): Places the window at the top of the Z order.
            User32.INSTANCE.SetParent(hwndChild, parentWindow);
            User32.INSTANCE.SetWindowPos(hwndChild, zero, 0, 0,
                    previewWindowRect.right - previewWindowRect.left,
                    previewWindowRect.bottom - previewWindowRect.top,
                    0x0040);

            vezerlo.vizbeRak(numberOfFish);
        } catch (Exception e) {
        }
    }
}
