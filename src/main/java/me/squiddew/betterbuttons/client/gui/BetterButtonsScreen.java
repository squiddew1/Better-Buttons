package me.squiddew.betterbuttons.client.gui;

import me.squiddew.betterbuttons.client.gui.widget.FlatWidget;
import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.client.gui.components.Tooltip;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import org.jspecify.annotations.NonNull;

public class BetterButtonsScreen extends Screen {

    private final Screen parent;
    private int page = 1;

    public BetterButtonsScreen(Component title, Screen parent) {
        super(title);
        this.parent = parent;
    }

    @Override
    public void onClose() {
        if (this.parent != null){
            this.minecraft.gui.setScreen(this.parent);
        } else {
            super.onClose();
        }
    }

    @Override
    protected void init() {
        super.init();

        this.addRenderableWidget(new FlatWidget(
                this.width - 60 - 15,
                this.height - 20 - 15,
                60,
                20,
                Component.translatable("gui.done"),
                Tooltip.create(Component.translatable("gui.done")),
                this::onClose
        ));

        this.addRenderableWidget(new FlatWidget(
                15,
                50,
                115,
                20,
                Component.literal("Buttons"),
                Tooltip.create(Component.literal("Buttons")),
                () -> {
                    page = 1;
                    this.rebuildWidgets();
                }
        ));



        switch (page){
            case 1 -> drawButtonsPage();
        }
    }

    private void drawButtonsPage(){

    }

    @Override
    public void extractRenderState(@NonNull GuiGraphicsExtractor graphics, int mouseX, int mouseY, float a) {
        GuiUtils.extractBackground(graphics);
        super.extractRenderState(graphics, mouseX, mouseY, a);
        GuiUtils.extractPageBar(graphics);
    }
}
