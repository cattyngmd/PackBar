package dev.cattyn.packbar.handlers;

import dev.cattyn.packbar.Utils;
import dev.cattyn.packbar.config.PackbarConfig;
import dev.cattyn.packbar.widgets.SearchWidget;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.screen.pack.PackListWidget;
import net.minecraft.client.gui.screen.pack.ResourcePackOrganizer;
import net.minecraft.client.gui.widget.Widget;
import org.lwjgl.glfw.GLFW;

import static dev.cattyn.packbar.Utils.normalize;
import static dev.cattyn.packbar.Utils.removePackPrefix;

public class PackbarHandlerImpl implements PackHandler {
    private static final int SEARCH_BAR_HEIGHT = 16;
    private static final int SEARCH_BAR_OFFSET = 4;

    private final Screen screen;
    private final SearchWidget search;
    private final PackListWidget packList;

    public PackbarHandlerImpl(Screen screen, SearchWidget search, PackListWidget packList) {
        this.screen = screen;
        this.search = search;
        this.packList = packList;
        this.search.setChangedListener(v -> updateSuggestions());
    }

    @Override
    public void updatePositions() {
        int offset = SEARCH_BAR_HEIGHT + SEARCH_BAR_OFFSET;
        packList.setHeight(packList.getHeight() - offset);
        int x = packList.getX();
        int y = packList.getY() + packList.getHeight() + SEARCH_BAR_OFFSET;
        search.setPosition(x, y);
    }

    @Override
    public void updateSuggestions() {
        String input = normalize(search.getText());
        String suggestion = getSuggestion(input);
        search.setSuggestion(suggestion);
        if (search.isFocused()) Utils.updatePacks(screen);
    }

    @Override
    public void updateKeys(int key) {
        activateSearch(key);
        clearSearch(key);
    }

    @Override
    public boolean testPack(Widget widget, ResourcePackOrganizer.Pack pack) {
        if (widget != packList) return true;
        String input = normalize(search.getText());
        return input.isEmpty() || normalize(pack.getName()).contains(input);
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    private void activateSearch(int key) {
        if (PackbarConfig.searchHotKey && Utils.isCtrl() && key == GLFW.GLFW_KEY_F) {
            screen.setFocused(search);
        }
    }

    private void clearSearch(int key) {
        if (key == GLFW.GLFW_KEY_ENTER) {
            search.setText("");

            search.setFocused(false);
            screen.setFocused(null);
        }
    }

    private String getSuggestion(String input) {
        if (input.isEmpty()) return "Search";
        for (PackListWidget.ResourcePackEntry entry : packList.children()) {
            String packName = normalize(entry.getName());
            packName = removePackPrefix(packName);
            String remaining = Utils.getRemaining(input, packName);
            if (remaining != null)
                return remaining;
        }
        return null;
    }
}
