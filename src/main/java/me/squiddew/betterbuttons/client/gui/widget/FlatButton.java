package me.squiddew.betterbuttons.client.gui.widget;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.components.Tooltip;
import net.minecraft.network.chat.Component;
import org.jspecify.annotations.NonNull;
import org.jspecify.annotations.Nullable;

public class FlatButton extends Button {

    public FlatButton(int x, int y, int width, int height, Component message, @Nullable Tooltip tooltip, OnPress onPress, CreateNarration createNarration) {
        super(x, y, width, height, message, onPress, createNarration);

        if (tooltip != null) {
            this.setTooltip(tooltip);
        }
    }

    @Override
    protected void extractContents(@NonNull GuiGraphicsExtractor graphics, int mouseX, int mouseY, float a) {
        int color = this.isHovered ? 0xFF000000 : 0x70000000;

        graphics.fill(this.getX(), this.getY(), this.getX() + this.width, this.getY() + this.height, color);
        graphics.text(Minecraft.getInstance().font, this.getMessage(), this.getX() + (this.width - Minecraft.getInstance().font.width(this.getMessage())) / 2, this.getY() + (this.height - 9) / 2 + 1, 0xFFFFFFFF, true);
    }
}
