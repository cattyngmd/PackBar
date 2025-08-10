package dev.cattyn.packbar.mixin;

import net.minecraft.client.gui.screen.pack.PackScreen;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Invoker;

@Mixin(PackScreen.class)
public interface AccessorPackScreen {
    @Invoker("refresh") void packbar$refresh();
}
