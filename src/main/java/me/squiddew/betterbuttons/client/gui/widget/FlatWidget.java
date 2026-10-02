package me.squiddew.betterbuttons.client.gui.widget;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.client.gui.components.AbstractWidget;
import net.minecraft.client.gui.components.Tooltip;
import net.minecraft.client.gui.narration.NarrationElementOutput;
import net.minecraft.client.input.MouseButtonEvent;
import net.minecraft.network.chat.Component;
import org.jspecify.annotations.NonNull;

public class FlatWidget extends AbstractWidget {

    private final Runnable onPress;

    public FlatWidget(int x, int y, int width, int height, Component message, Tooltip tooltip, Runnable onPress) {
        super(x, y, width, height, message);

        if (tooltip != null){
            this.setTooltip(tooltip);
        }
        this.onPress = onPress;
    }

    @Override
    protected void extractWidgetRenderState(@NonNull GuiGraphicsExtractor graphics, int mouseX, int mouseY, float a) {
        int color = this.isHovered ? 0xFF000000 : 0x70000000;

        graphics.fill(this.getX(), this.getY(), this.getX() + this.width, this.getY() + this.height, color);
        graphics.text(Minecraft.getInstance().font, this.getMessage(), this.getX() + (this.width - Minecraft.getInstance().font.width(this.getMessage())) / 2, this.getY() + (this.height - 9) / 2 + 1, 0xFFFFFFFF, true);
    }

    @Override
    public void onClick(@NonNull MouseButtonEvent event, boolean doubleClick) {
        if (this.onPress != null) {
            this.onPress.run();
        }
    }

    @Override
    protected void updateWidgetNarration(@NonNull NarrationElementOutput output) {

    }
}
