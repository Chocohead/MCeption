package com.enderzombi102.mception.client;

import blue.endless.jankson.api.SyntaxError;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.Environment;
import net.fabricmc.loader.api.FabricLoader;

import java.io.IOException;
import java.nio.file.Path;

import static com.enderzombi102.mception.MCeption.LOGGER;

@Environment(net.fabricmc.api.EnvType.CLIENT)
public class MCeptionClient implements ClientModInitializer {

	static final Path GAME_DIR = FabricLoader.getInstance().getGameDir();
	static final Path MCEPTION_DIR = GAME_DIR.resolve("MCeption");
	public static boolean installationSucceeded = false;
	public static GuestRunner runner = new GuestRunner();

	@Override
	@SuppressWarnings("ResultOfMethodCallIgnored")
	public void onInitializeClient() {
		LOGGER.info("[MCeption] Initializing!");
		LOGGER.info("[MCeption] Checking installation...");
		MCEPTION_DIR.toFile().mkdirs();
		if (! BinInstaller.isInstalled() ) {
			LOGGER.info("[MCeption] Missing binaries detected!");
			LOGGER.info("[MCeption] Installing minecraft 1.2.5 binaries...");
			try {
				BinInstaller.doInstall();
				LOGGER.info("[MCeption] Binaries installation finished!");
			} catch (IOException e) {
				LOGGER.fatal("[MCeption] Binaries installation failed! mc 1.2.5 will not work!", e);
				return;
			}
		} else LOGGER.info("[MCeption] Binaries present!");

		if (! ResourceInstaller.isInstalled() ) {
			LOGGER.info("[MCeption] No resources detected!");
			LOGGER.info("[MCeption] Installing minecraft 1.2.5 resources...");
			try {
				ResourceInstaller.doInstall();
				LOGGER.info("[MCeption] Resources installation finished!");
			} catch (IOException | SyntaxError e) {
				LOGGER.fatal("[MCeption] Resources installation failed! mc 1.2.5 will not work correctly!", e);
				return;
			}
		} else LOGGER.info("[MCeption] Resources present!");

		LOGGER.info("[MCeption] Loading finished! Let's revive the past!");
	}
}
