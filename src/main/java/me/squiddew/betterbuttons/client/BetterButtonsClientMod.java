package me.squiddew.betterbuttons.client;

import net.fabricmc.api.ClientModInitializer;

public class BetterButtonsClientMod implements ClientModInitializer {

	public static final String MOD_ID = "better-buttons";

	@Override
	public void onInitializeClient() {
		BetterButtonsOptions.load();
	}
}
