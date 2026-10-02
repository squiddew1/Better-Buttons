package me.squiddew.betterbuttons.client;

import net.fabricmc.api.ClientModInitializer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BetterButtonsClientMod implements ClientModInitializer {

	public static final String MOD_ID = "better-buttons";
	private static final Logger LOGGER = LoggerFactory.getLogger("Better Buttons");

	@Override
	public void onInitializeClient() {
		LOGGER.info("Better Buttons initialized");
	}
}
