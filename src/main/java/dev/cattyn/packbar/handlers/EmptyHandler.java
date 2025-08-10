package dev.cattyn.packbar.handlers;

import net.minecraft.client.gui.screen.pack.ResourcePackOrganizer;
import net.minecraft.client.gui.widget.Widget;

public final class EmptyHandler implements PackHandler {
    @Override public void updatePositions() { }

    @Override public void updateSuggestions() { }

    @Override public void updateKeys(int key) { }

    @Override
    public boolean testPack(Widget widget, ResourcePackOrganizer.Pack pack) {
        return false;
    }

    @Override
    public boolean isEnabled() {
        return false;
    }
}
