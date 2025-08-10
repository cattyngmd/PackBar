package dev.cattyn.packbar;

import dev.cattyn.packbar.mixin.AccessorPackScreen;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.screen.Screen;
import org.lwjgl.glfw.GLFW;

import java.util.Locale;

public final class Utils {
    public static final String PACK_PREFIX = "file/";
    private static final MinecraftClient mc = MinecraftClient.getInstance();

    private Utils() {
        throw new AssertionError();
    }

    public static void updatePacks(Screen screen) {
        if (screen instanceof AccessorPackScreen pack) pack.packbar$refresh();
    }

    public static boolean isCtrl() {
        return isKeyDown(GLFW.GLFW_KEY_LEFT_CONTROL);
    }

    public static boolean isKeyDown(int key) {
        return GLFW.glfwGetKey(mc.getWindow().getHandle(), key) == 1;
    }

    public static String normalize(String input) {
        return input.toLowerCase(Locale.ROOT).trim();
    }

    public static String removePackPrefix(String input) {
        if (input.startsWith(PACK_PREFIX)) return input.substring(PACK_PREFIX.length());
        return input;
    }

    public static String getRemaining(String input, String entry) {
        if (!entry.startsWith(input)) return null;
        return entry.substring(input.length());
    }
}
