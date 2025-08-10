package dev.cattyn.packbar;

import dev.cattyn.packbar.config.PackbarConfig;
import eu.midnightdust.lib.config.MidnightConfig;
import net.fabricmc.api.ModInitializer;

public final class Packbar implements ModInitializer {
    public static final String MOD_ID = "packbar";

    @Override
    public void onInitialize() {
        MidnightConfig.init(MOD_ID, PackbarConfig.class);
    }
}
