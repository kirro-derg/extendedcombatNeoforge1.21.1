package dev.kirro.extendedcombat;

import com.mojang.logging.LogUtils;
import dev.kirro.extendedcombat.block.ModBlocks;
import dev.kirro.extendedcombat.data.ModDataAttachments;
import dev.kirro.extendedcombat.enchantment.ModEnchantmentEffects;
import dev.kirro.extendedcombat.data.ModDataComponents;
import dev.kirro.extendedcombat.item.ModItems;
import dev.kirro.extendedcombat.loot.ModLootModifiers;
import dev.kirro.extendedcombat.villager.ModPoi;
import net.minecraft.resources.ResourceLocation;

import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.config.ModConfig;
import net.neoforged.fml.ModContainer;
import org.slf4j.Logger;

// The value here should match an entry in the META-INF/neoforge.mods.toml file
@Mod(ExtendedCombat.MOD_ID)
public class ExtendedCombat {

    public static final String MOD_ID = "extendedcombat";
    public static final Logger LOGGER = LogUtils.getLogger();

    public static ResourceLocation id(String id) {
        return ResourceLocation.fromNamespaceAndPath(MOD_ID, id);
    }

    public ExtendedCombat(IEventBus eventBus, ModContainer modContainer) throws InstantiationException, IllegalAccessException {
        ModItems.register(eventBus);
        ModBlocks.register(eventBus);

        ModEnchantmentEffects.register(eventBus);
        ModDataComponents.register(eventBus);
        ModDataAttachments.register(eventBus);
        ModPoi.register(eventBus);
        ModLootModifiers.register(eventBus);

        modContainer.registerConfig(ModConfig.Type.COMMON, Config.SPEC);
    }
}