package dev.cattyn.packbar.mixin;

import dev.cattyn.packbar.handlers.HandlerFactory;
import dev.cattyn.packbar.handlers.PackHandler;
import dev.cattyn.packbar.widgets.SearchWidget;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.screen.pack.PackListWidget;
import net.minecraft.client.gui.screen.pack.PackScreen;
import net.minecraft.client.gui.screen.pack.ResourcePackOrganizer;
import net.minecraft.text.Text;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(PackScreen.class)
public class MixinPackScreen extends Screen {
    @Shadow private PackListWidget availablePackList;

    @Unique private PackHandler packbar$handler;

    protected MixinPackScreen(Text title) {
        super(title);
    }

    @Inject(method = "init", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/screen/pack/PackScreen;refresh()V"))
    private void initHook(CallbackInfo ci) {
        SearchWidget search = new SearchWidget(client.textRenderer, 200, 16);
        packbar$handler = HandlerFactory.create(this, search, availablePackList);
        if (packbar$handler.isEnabled()) addDrawableChild(search);
    }

    @Inject(method = "refreshWidgetPositions", at = @At("TAIL"))
    private void refreshWidgetPositionsHook(CallbackInfo ci) {
        packbar$handler.updatePositions();
        packbar$handler.updateSuggestions();
    }

    @Inject(method = "method_29672", at = @At("HEAD"), cancellable = true)
    private void updatePackListLambdaHook(PackListWidget packListWidget, String string, ResourcePackOrganizer.Pack pack, CallbackInfo ci) {
        if (!packbar$handler.testPack(packListWidget, pack)) ci.cancel();
    }

    @Override
    public boolean keyPressed(int keyCode, int scanCode, int modifiers) {
        packbar$handler.updateKeys(keyCode);
        return super.keyPressed(keyCode, scanCode, modifiers);
    }
}
