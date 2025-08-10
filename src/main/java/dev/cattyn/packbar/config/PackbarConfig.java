package dev.cattyn.packbar.config;

import eu.midnightdust.lib.config.MidnightConfig;

public final class PackbarConfig extends MidnightConfig {
    @Entry(name = "Packbar Enabled")
    public static boolean enabled = true;

    @Entry(name = "Search Hot Key")
    public static boolean searchHotKey = true;
}
