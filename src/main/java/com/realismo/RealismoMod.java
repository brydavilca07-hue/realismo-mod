package com.realismo;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.world.biome.Biome;

public class RealismoMod implements ModInitializer {
    public static final String MOD_ID = "realismo";
    private int tickCounter = 0;

    @Override
    public void onInitialize() {
        ServerTickEvents.END_SERVER_TICK.register(server -> {
            // Se ejecuta una vez por segundo (20 ticks)
            if (++tickCounter % 20 != 0) return;

            for (ServerPlayerEntity player : server.getPlayerManager().getPlayerList()) {
                if (player.isCreative() || player.isSpectator()) continue;
                applyInjuries(player);
                applyWeight(player);
                applyClimate(player);
                applyEffort(player);
            }
        });
    }

    /** Heridas: con poca vida cojeas y te cuesta minar. */
    private void applyInjuries(ServerPlayerEntity p) {
        float health = p.getHealth();
        if (health <= 6.0f) {
            int amp = health <= 3.0f ? 1 : 0;
            p.addStatusEffect(new StatusEffectInstance(StatusEffects.SLOWNESS, 60, amp, false, false, true));
        }
        if (health <= 3.0f) {
            p.addStatusEffect(new StatusEffectInstance(StatusEffects.MINING_FATIGUE, 60, 0, false, false, true));
        }
    }

    /** Peso: llevar el inventario muy lleno te hace más lento. */
    private void applyWeight(ServerPlayerEntity p) {
        PlayerInventory inv = p.getInventory();
        double weight = 0;
        for (ItemStack stack : inv.main) {
            if (!stack.isEmpty()) {
                weight += stack.getCount() / (double) stack.getMaxCount();
            }
        }
        for (ItemStack stack : inv.armor) {
            if (!stack.isEmpty()) weight += 1.0;
        }
        if (weight > 32) {
            p.addStatusEffect(new StatusEffectInstance(StatusEffects.SLOWNESS, 60, 1, false, false, true));
        } else if (weight > 24) {
            p.addStatusEffect(new StatusEffectInstance(StatusEffects.SLOWNESS, 60, 0, false, false, true));
        }
    }

    /** Clima: el frío y el calor extremos te desgastan más rápido. */
    private void applyClimate(ServerPlayerEntity p) {
        Biome biome = p.getWorld().getBiome(p.getBlockPos()).value();
        float temp = biome.getTemperature();

        boolean warmClothes = p.getEquippedStack(EquipmentSlot.CHEST).isOf(Items.LEATHER_CHESTPLATE);

        if (temp < 0.15f && !warmClothes) {
            p.addExhaustion(0.4f); // frío sin abrigo
        } else if (temp >= 1.5f) {
            p.addExhaustion(0.4f); // calor del desierto
        }

        // Estar bajo la lluvia sin techo enfría un poco
        if (p.getWorld().isRaining() && p.getWorld().isSkyVisible(p.getBlockPos()) && !warmClothes) {
            p.addExhaustion(0.1f);
        }
    }

    /** Esfuerzo: correr cansa de verdad. */
    private void applyEffort(ServerPlayerEntity p) {
        if (p.isSprinting()) {
            p.addExhaustion(0.3f);
        }
    }
}
