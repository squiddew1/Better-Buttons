package me.squiddew.betterbuttons.client.gui;

import com.mojang.blaze3d.Blaze3D;
import me.squiddew.betterbuttons.client.BetterButtonsOptions;
import me.squiddew.betterbuttons.client.gui.widget.FlatWidget;
import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.client.gui.components.Tooltip;
import net.minecraft.client.gui.screens.ConfirmLinkScreen;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import org.jspecify.annotations.NonNull;

import java.net.URI;

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
        this.addRenderableWidget(new FlatWidget(
                15,
                50 + 20,
                115,
                20,
                Component.literal("Links"),
                Tooltip.create(Component.literal("Links")),
                () -> {
                    page = 2;
                    this.rebuildWidgets();
                }
        ));

        switch (page){
            case 1 -> drawButtonsPage();
            case 2 -> drawLinksPage();
        }
    }

    private void drawButtonsPage(){
        this.addRenderableWidget(new FlatWidget(
                (this.width / 2) - (150 / 2),
                30,
                150,
                20,
                Component.literal("Font: " + (BetterButtonsOptions.font ? "Consolas" : "Default")),
                Tooltip.create(Component.literal("Choose the font of your buttons, you can choose Default or Consolas")),
                () -> {
                    this.minecraft.reloadResourcePacks();
                    BetterButtonsOptions.font = !BetterButtonsOptions.font;
                    BetterButtonsOptions.save();
                    this.rebuildWidgets();
                }
        ));
        this.addRenderableWidget(new FlatWidget(
                (this.width / 2) - (150 / 2),
                30 + 20,
                150,
                20,
                Component.literal("Show Title Screen Button: " + (BetterButtonsOptions.showTitleScreenButton ? "ON" : "OFF")),
                Tooltip.create(Component.literal("Show the Button on the Title Screen to open Better Buttons configuration. In alternative you can acces the Better Buttons configuration with ModMenu")),
                () -> {
                    BetterButtonsOptions.showTitleScreenButton = !BetterButtonsOptions.showTitleScreenButton;
                    BetterButtonsOptions.save();
                    this.rebuildWidgets();
                }
        ));
    }
    private void drawLinksPage(){
        this.addRenderableWidget(new FlatWidget(
                (this.width / 2) - (150 / 2),
                15,
                150,
                20,
                Component.literal("GitHub"),
                Tooltip.create(Component.literal("GitHub sources")),
                () -> this.minecraft.gui.setScreen(new ConfirmLinkScreen(
                        (github) -> {
                            if (github){
                                Blaze3D.openUri(URI.create("https://github.com/squiddew1/Better-Buttons"));
                            }
                            this.minecraft.gui.setScreen(this);
                        },
                        URI.create("https://github.com/squiddew1/Better-Buttons"),
                        true
                ))
        ));
        this.addRenderableWidget(new FlatWidget(
                (this.width / 2) - (150 / 2),
                15 + 20,
                150,
                20,
                Component.literal("Issues"),
                Tooltip.create(Component.literal("Issues tracker")),
                () -> this.minecraft.gui.setScreen(new ConfirmLinkScreen(
                        (issues) -> {
                            if (issues){
                                Blaze3D.openUri(URI.create("https://github.com/squiddew1/Better-Buttons/issues"));
                            }
                            this.minecraft.gui.setScreen(this);
                        },
                        URI.create("https://github.com/squiddew1/Better-Buttons/issues"),
                        true
                ))
        ));
        this.addRenderableWidget(new FlatWidget(
                (this.width / 2) - (150 / 2),
                15 + 20 + 20,
                150,
                20,
                Component.literal("Homepage"),
                Tooltip.create(Component.literal("Homepage")),
                () -> this.minecraft.gui.setScreen(new ConfirmLinkScreen(
                        (homepage) -> {
                            if (homepage){
                                Blaze3D.openUri(URI.create("https://modrinth.com/project/betterbuttons"));
                            }
                            this.minecraft.gui.setScreen(this);
                        },
                        URI.create("https://modrinth.com/project/betterbuttons"),
                        true
                ))
        ));
    }

    @Override
    public void extractRenderState(@NonNull GuiGraphicsExtractor graphics, int mouseX, int mouseY, float a) {
        GuiUtils.extractBackground(graphics);
        super.extractRenderState(graphics, mouseX, mouseY, a);
        GuiUtils.extractPageBar(graphics);
    }
}
