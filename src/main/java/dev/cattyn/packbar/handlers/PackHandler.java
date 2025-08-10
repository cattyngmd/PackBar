package dev.cattyn.packbar.handlers;

import net.minecraft.client.gui.screen.pack.ResourcePackOrganizer;
import net.minecraft.client.gui.widget.Widget;

public interface PackHandler {
    void updatePositions();

    void updateSuggestions();

    void updateKeys(int key);

    boolean testPack(Widget widget, ResourcePackOrganizer.Pack pack);

    boolean isEnabled();
}
