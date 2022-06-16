package net.syberiak.darknessinthebottle;

import net.fabricmc.api.ModInitializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.potion.Potion;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class DarknessInTheBottle implements ModInitializer {
	public static final String MOD_ID = "darkness-in-the-bottle";

	public static Potion DARKNESS_POTION = new Potion(new StatusEffectInstance(StatusEffects.DARKNESS, 900));
    public static Potion LONG_DARKNESS_POTION = new Potion(new StatusEffectInstance(StatusEffects.DARKNESS, 1800));


	// This logger is used to write text to the console and the log file.
	// It is considered best practice to use your mod id as the logger's name.
	// That way, it's clear which mod wrote info, warnings, and errors.
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
		// This code runs as soon as Minecraft is in a mod-load-ready state.
		// However, some things (like resources) may still be uninitialized.
		// Proceed with mild caution.
		DARKNESS_POTION = Registry.register(Registry.POTION, new Identifier(MOD_ID, "darkness"), DARKNESS_POTION);
        LONG_DARKNESS_POTION = Registry.register(Registry.POTION, new Identifier(MOD_ID, "darkness_long"), LONG_DARKNESS_POTION);

		LOGGER.info("Initialized successfully.");
	}
}
