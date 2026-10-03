package me.squiddew.betterbuttons.mixin;

import me.squiddew.betterbuttons.client.BetterButtonsClientMod;
import me.squiddew.betterbuttons.client.BetterButtonsOptions;
import net.minecraft.client.gui.font.FontManager;
import net.minecraft.client.gui.font.FontSet;
import net.minecraft.resources.Identifier;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.Map;

@Mixin(FontManager.class)
public class FontManagerMixin {

    @Shadow @Final private Map<Identifier, FontSet> fontSets;

    @Unique
    private static final Identifier MINECRAFT_DEFAULT = Identifier.fromNamespaceAndPath("minecraft", "default");
    @Unique
    private static final Identifier CUSTOM_FONT = Identifier.fromNamespaceAndPath(BetterButtonsClientMod.MOD_ID, "consolas");


    @Inject(method = "getFontSetRaw", at = @At("TAIL"), cancellable = true)
    private void replaceFont(Identifier id, CallbackInfoReturnable<FontSet> cir){
        if (BetterButtonsOptions.font && MINECRAFT_DEFAULT.equals(id)) {

            if (this.fontSets.containsKey(CUSTOM_FONT)) {
                cir.setReturnValue(this.fontSets.get(CUSTOM_FONT));
            }
        }
    }
}
