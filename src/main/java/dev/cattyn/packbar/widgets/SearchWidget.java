package dev.cattyn.packbar.widgets;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.font.TextRenderer;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.widget.TextFieldWidget;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

public class SearchWidget extends TextFieldWidget {
    private static final Identifier MENU_LIST_BACKGROUND_TEXTURE = Identifier.ofVanilla("textures/gui/menu_list_background.png");
    private static final Identifier INWORLD_MENU_LIST_BACKGROUND_TEXTURE = Identifier.ofVanilla("textures/gui/inworld_menu_list_background.png");

    private boolean hookBackground;

    public SearchWidget(TextRenderer textRenderer, int width, int height) {
        super(textRenderer, width, height, Text.empty());
    }

    @Override
    public void renderWidget(DrawContext context, int mouseX, int mouseY, float deltaTicks) {
        if (drawsBackground()) {
            drawBackground(context);
        }

        hookBackground = true;
        super.renderWidget(context, mouseX, mouseY, deltaTicks);
        hookBackground = false;
    }

    @Override
    public boolean drawsBackground() {
        if (hookBackground) return false;
        return super.drawsBackground();
    }

    // EntryListWidget.drawMenuListBackground()
    private void drawBackground(DrawContext context) {
        Identifier identifier = MinecraftClient.getInstance().world == null ? MENU_LIST_BACKGROUND_TEXTURE : INWORLD_MENU_LIST_BACKGROUND_TEXTURE;
        context.drawTexture(
                RenderLayer::getGuiTextured,
                identifier,
                this.getX(),
                this.getY(),
                this.getRight(),
                this.getBottom(),
                this.getWidth(),
                this.getHeight(),
                32,
                32
        );
    }
}
