package de.johnpork;

import net.fabricmc.api.ModInitializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Initialer Einstiegspunkt der JohnPork-Mod.
 * Die Begleiter-Entitaet, KI, Skin und Animationen werden in weiteren
 * Entwicklungsschritten hinzugefuegt.
 */
public final class JohnPorkMod implements ModInitializer {
    public static final String MOD_ID = "johnpork";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

    @Override
    public void onInitialize() {
        LOGGER.info("JohnPork Companion wurde geladen. Das Grundgeruest ist aktiv.");
    }
}
