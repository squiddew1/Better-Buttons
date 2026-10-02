package me.squiddew.betterbuttons.mixin;

import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.client.gui.components.SpriteIconButton;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(SpriteIconButton.CenteredIcon.class)
public class SpriteIconButtonMixin {

    @Redirect(
            method = "extractContents",
            at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/components/SpriteIconButton$CenteredIcon;extractDefaultSprite(Lnet/minecraft/client/gui/GuiGraphicsExtractor;)V")
    )
    private void redirectDefaultSprite(SpriteIconButton.CenteredIcon button, GuiGraphicsExtractor graphics) {
        boolean hovered = button.isHoveredOrFocused();
        int color = hovered ? 0xFF000000 : 0x70000000;

        graphics.fill(button.getX(), button.getY(), button.getX() + button.getWidth(), button.getY() + button.getHeight(), color);

    }
}
