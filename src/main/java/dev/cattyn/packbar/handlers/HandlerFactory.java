package dev.cattyn.packbar.handlers;

import dev.cattyn.packbar.config.PackbarConfig;
import dev.cattyn.packbar.widgets.SearchWidget;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.screen.pack.PackListWidget;

public final class HandlerFactory {
    private static final PackHandler EMPTY = new EmptyHandler();

    private HandlerFactory() { }

    public static PackHandler create(Screen screen, SearchWidget search, PackListWidget packs) {
        return PackbarConfig.enabled ? new PackbarHandlerImpl(screen, search, packs) : EMPTY;
    }
}
