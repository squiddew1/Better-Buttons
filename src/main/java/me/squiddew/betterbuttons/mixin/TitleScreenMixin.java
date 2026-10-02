package me.squiddew.betterbuttons.mixin;

import com.llamalad7.mixinextras.expression.Definition;
import com.llamalad7.mixinextras.expression.Expression;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import com.llamalad7.mixinextras.sugar.Local;
import com.llamalad7.mixinextras.sugar.ref.LocalIntRef;
import me.squiddew.betterbuttons.client.gui.BetterButtonsScreen;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.components.Tooltip;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.gui.screens.TitleScreen;
import net.minecraft.network.chat.Component;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(TitleScreen.class)
public abstract class TitleScreenMixin {

    @Shadow
    protected abstract int getHorizontalPosition(int currentButton, int numberOfButtons, int buttonWidth);

    @Definition(id = "numberOfButtons", local = @Local(type = int.class, name = "numberOfButtons"))
    @Expression("numberOfButtons = ?")
    @Inject(method = "init", at = @At(value = "MIXINEXTRAS:EXPRESSION", shift = At.Shift.AFTER))
    private void incrementIconButtonsCount(CallbackInfo ci, @Local(name = "numberOfButtons") LocalIntRef numberOfButtons) {
        numberOfButtons.set(numberOfButtons.get() + 1);
    }

    @WrapOperation(method = "init", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/screens/TitleScreen;getHorizontalPosition(III)I"))
    private int customHorizontalPosition(TitleScreen instance, int currentButton, int numberOfButtons, int buttonWidth, Operation<Integer> original, @Local(name = "numberOfButtons") int actualNumberOfButtons) {
        return original.call(instance, currentButton, actualNumberOfButtons, buttonWidth);
    }

    @Definition(id = "width", field = "Lnet/minecraft/client/gui/screens/TitleScreen;width:I")
    @Expression("this.width / 2 - 100")
    @Inject(method = "init", at = @At(value = "MIXINEXTRAS:EXPRESSION", ordinal = 0))
    private void injectMyCustomSmallButton(CallbackInfo ci, @Local(name = "currentButton") LocalIntRef currentButton, @Local(name = "topPos") int topPos, @Local(name = "numberOfButtons") int numberOfButtons) {

        currentButton.set(currentButton.get() + 1);
        int xPos = this.getHorizontalPosition(currentButton.get(), numberOfButtons, 20);

        Screen titleScreen = (TitleScreen) (Object) this;

        Button titleScreenWidget = Button.builder(
                Component.literal("BB"), _ -> Minecraft.getInstance().gui.setScreen(new BetterButtonsScreen(Component.literal("Better Buttons Screen"), titleScreen))
        ).bounds(xPos, topPos, 20, 20)
                .tooltip(Tooltip.create(Component.literal("Better Buttons")))
                .build();

        ((ScreenInvoker) this).invokeAddRenderableWidget(titleScreenWidget);
    }
}
