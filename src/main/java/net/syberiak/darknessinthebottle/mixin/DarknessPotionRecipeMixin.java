package net.syberiak.darknessinthebottle.mixin;

import net.syberiak.darknessinthebottle.DarknessInTheBottle;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.potion.Potion;
import net.minecraft.potion.Potions;
import net.minecraft.recipe.BrewingRecipeRegistry;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(BrewingRecipeRegistry.class)
public class DarknessPotionRecipeMixin {
    
	@Shadow
    private static void registerPotionRecipe(Potion input, Item item, Potion output) {
        throw new RuntimeException();
    }

	@Inject(at = @At("TAIL"), method = "registerDefaults()V")
    private static void RegisterDefaults(CallbackInfo ci) {
        registerPotionRecipe(Potions.NIGHT_VISION, Items.ECHO_SHARD, DarknessInTheBottle.DARKNESS_POTION);
        registerPotionRecipe(Potions.LONG_NIGHT_VISION, Items.ECHO_SHARD, DarknessInTheBottle.LONG_DARKNESS_POTION);
        registerPotionRecipe(DarknessInTheBottle.DARKNESS_POTION, Items.REDSTONE, DarknessInTheBottle.LONG_DARKNESS_POTION);
    }
}
