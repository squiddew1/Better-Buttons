package me.squiddew.betterbuttons.client.gui;

import me.squiddew.betterbuttons.client.BetterButtonsClientMod;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.renderer.RenderPipelines;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;

public class GuiUtils {

    public static void extractBackground(GuiGraphicsExtractor graphics){
        Screen screen = Minecraft.getInstance().gui.screen();

        Identifier configWheel = Identifier.fromNamespaceAndPath(BetterButtonsClientMod.MOD_ID, "/textures/gui/config-wheel.png");

        if (screen != null){
            graphics.fill(screen.width, screen.height, 0, 0, 0x50000000);
            graphics.blit(RenderPipelines.GUI_TEXTURED, configWheel, 15, screen.height - 60 - 15, 0f, 0f, 60, 60, 60, 60, 60, 60);
            graphics.blit(RenderPipelines.GUI_TEXTURED, configWheel, screen.width - 60 - 15, 15, 0f, 0f, 60, 60, 60, 60, 60, 60);
        }
    }

    public static void extractPageBar(GuiGraphicsExtractor graphics){
        graphics.fill(15, 15, 130, 50, 0x90000000);
    }
}
