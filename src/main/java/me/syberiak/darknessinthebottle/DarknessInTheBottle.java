package me.syberiak.darknessinthebottle;

import me.syberiak.darknessinthebottle.mixin.BrewingRecipeRegistryMixin;
import net.fabricmc.api.ModInitializer;
import net.minecraft.item.Items;
import net.minecraft.potion.Potions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.potion.Potion;
import net.minecraft.util.Identifier;
import net.minecraft.registry.Registry;
import net.minecraft.registry.Registries;

public class DarknessInTheBottle implements ModInitializer {
	public static final String MOD_ID = "darkness-in-the-bottle";

	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	public static Potion DARKNESS;
    public static Potion LONG_DARKNESS;

	public static void registerPotions() {
		DARKNESS = Registry.register(
				Registries.POTION,
				new Identifier(MOD_ID, "darkness"),
				new Potion(new StatusEffectInstance(StatusEffects.DARKNESS, 900))
		);
		LONG_DARKNESS = Registry.register(
				Registries.POTION,
				new Identifier(MOD_ID, "darkness_long"),
				new Potion(new StatusEffectInstance(StatusEffects.DARKNESS, 1800))
		);
	}

	public static void registerPotionsRecipes() {
		BrewingRecipeRegistryMixin.invokeRegisterPotionRecipe(
				Potions.NIGHT_VISION,
				Items.ECHO_SHARD,
				DarknessInTheBottle.DARKNESS
		);
		BrewingRecipeRegistryMixin.invokeRegisterPotionRecipe(
				Potions.LONG_NIGHT_VISION,
				Items.ECHO_SHARD,
				DarknessInTheBottle.LONG_DARKNESS
		);
		BrewingRecipeRegistryMixin.invokeRegisterPotionRecipe(
				DarknessInTheBottle.DARKNESS,
				Items.REDSTONE,
				DarknessInTheBottle.LONG_DARKNESS
		);
	}

	@Override
	public void onInitialize() {
		registerPotions();
		registerPotionsRecipes();

		LOGGER.info("Initialized successfully.");
	}
}
