package me.syberiak.darknessinthebottle;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.registry.FabricBrewingRecipeRegistryBuilder;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.alchemy.Potion;
import net.minecraft.world.item.alchemy.Potions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DarknessInTheBottle implements ModInitializer {
	public static final String MOD_ID = "darkness-in-the-bottle";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	public static Potion DARKNESS;
    public static Potion LONG_DARKNESS;

	public static void registerPotions() {
		DARKNESS = Registry.register(
				BuiltInRegistries.POTION,
				ResourceLocation.fromNamespaceAndPath(MOD_ID, "darkness"),
				new Potion("darkness", new MobEffectInstance(MobEffects.DARKNESS, 900))
		);

		LONG_DARKNESS = Registry.register(
				BuiltInRegistries.POTION,
				ResourceLocation.fromNamespaceAndPath(MOD_ID, "darkness_long"),
				new Potion("darkness_long", new MobEffectInstance(MobEffects.DARKNESS, 1800))
		);
	}

	public static void registerPotionsRecipes() {
		FabricBrewingRecipeRegistryBuilder.BUILD.register(builder -> {
			builder.addMix(
					Potions.NIGHT_VISION,
					Items.ECHO_SHARD,
					BuiltInRegistries.POTION.wrapAsHolder(DARKNESS)
			);

			builder.addMix(
					Potions.LONG_NIGHT_VISION,
					Items.ECHO_SHARD,
					BuiltInRegistries.POTION.wrapAsHolder(LONG_DARKNESS)
			);

			builder.addMix(
					BuiltInRegistries.POTION.wrapAsHolder(DARKNESS),
					Items.REDSTONE,
					BuiltInRegistries.POTION.wrapAsHolder(LONG_DARKNESS)
			);
		});
	}

	@Override
	public void onInitialize() {
		registerPotions();
		registerPotionsRecipes();

		LOGGER.info("Initialized successfully.");
	}
}
