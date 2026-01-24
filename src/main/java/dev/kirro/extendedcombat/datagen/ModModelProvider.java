package dev.kirro.extendedcombat.datagen;

import dev.kirro.extendedcombat.ExtendedCombat;
import dev.kirro.extendedcombat.item.ModItems;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.data.PackOutput;
import net.minecraft.data.models.model.ModelTemplate;
import net.minecraft.data.models.model.TextureSlot;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.PackType;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.item.armortrim.TrimMaterial;
import net.minecraft.world.item.armortrim.TrimMaterials;
import net.neoforged.neoforge.client.model.generators.*;
import net.neoforged.neoforge.client.model.generators.loaders.SeparateTransformsModelBuilder;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.neoforged.neoforge.registries.DeferredItem;
import org.jetbrains.annotations.Nullable;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Optional;

public class ModModelProvider extends ItemModelProvider {
    public static final ResourceLocation GREATSWORD_TEMPLATE = ExtendedCombat.id("item/greatsword_template_handheld");
    private static LinkedHashMap<ResourceKey<TrimMaterial>, Float> trimMaterials = new LinkedHashMap<>();
    static {
        trimMaterials.put(TrimMaterials.QUARTZ, 0.1F);
        trimMaterials.put(TrimMaterials.IRON, 0.2F);
        trimMaterials.put(TrimMaterials.NETHERITE, 0.3F);
        trimMaterials.put(TrimMaterials.REDSTONE, 0.4F);
        trimMaterials.put(TrimMaterials.COPPER, 0.5F);
        trimMaterials.put(TrimMaterials.GOLD, 0.6F);
        trimMaterials.put(TrimMaterials.EMERALD, 0.7F);
        trimMaterials.put(TrimMaterials.DIAMOND, 0.8F);
        trimMaterials.put(TrimMaterials.LAPIS, 0.9F);
        trimMaterials.put(TrimMaterials.AMETHYST, 1.0F);
    }

    public ModModelProvider(PackOutput output, ExistingFileHelper existingFileHelper) {
        super(output, ExtendedCombat.MOD_ID, existingFileHelper);
    }

    @Override
    protected void registerModels() {
        basicItem(ModItems.HANDLE.get());
        basicItem(ModItems.NETHER_STEEL_INGOT.get());
        basicItem(ModItems.ECHO_STEEL_INGOT.get());
        basicItem(ModItems.NETHER_STEEL_UPGRADE.get());
        basicItem(ModItems.ECHO_STEEL_UPGRADE.get());
        handheldItem(ModItems.NETHER_STEEL_PICKAXE.get());
        handheldItem(ModItems.WOODEN_HAMMER.get());
        handheldItem(ModItems.STONE_HAMMER.get());
        handheldItem(ModItems.IRON_HAMMER.get());
        handheldItem(ModItems.GOLDEN_HAMMER.get());
        handheldItem(ModItems.DIAMOND_HAMMER.get());
        handheldItem(ModItems.NETHERITE_HAMMER.get());
        handheldItem(ModItems.NETHER_STEEL_HAMMER.get());
        handheldItem(ModItems.ECHO_STEEL_HAMMER.get());
        basicItem(ModItems.BLACK_APPLE_SEED.get());
        basicItem(ModItems.BLACK_APPLE.get());
        basicItem(ModItems.GOLDEN_STEAK.get());
        basicItem(ModItems.HONEY_BREAD.get());
        basicItem(ModItems.MILK_BOTTLE.get());
        basicItem(ModItems.SWEET_BERRY_MILK_BOTTLE.get());
        basicItem(ModItems.CHOCOLATE_MILK_BOTTLE.get());

        createVariants(GREATSWORD_TEMPLATE, ModItems.WOODEN_GREATSWORD.get());
        createVariants(GREATSWORD_TEMPLATE, ModItems.STONE_GREATSWORD.get());
        createVariants(GREATSWORD_TEMPLATE, ModItems.IRON_GREATSWORD.get());
        createVariants(GREATSWORD_TEMPLATE, ModItems.GOLDEN_GREATSWORD.get());
        createVariants(GREATSWORD_TEMPLATE, ModItems.DIAMOND_GREATSWORD.get());
        createVariants(GREATSWORD_TEMPLATE, ModItems.NETHERITE_GREATSWORD.get());
        createVariants(GREATSWORD_TEMPLATE, ModItems.NETHER_STEEL_GREATSWORD.get());
        createVariants(GREATSWORD_TEMPLATE, ModItems.ECHO_STEEL_GREATSWORD.get());

        trimmedArmorItem(ModItems.NETHER_STEEL_BOOTS);
        trimmedArmorItem(ModItems.NETHER_STEEL_LEGGINGS);
        trimmedArmorItem(ModItems.NETHER_STEEL_CHESTPLATE);
        trimmedArmorItem(ModItems.NETHER_STEEL_HELMET);

        trimmedArmorItem(ModItems.ECHO_STEEL_BOOTS);
        trimmedArmorItem(ModItems.ECHO_STEEL_LEGGINGS);
        trimmedArmorItem(ModItems.ECHO_STEEL_CHESTPLATE);
        trimmedArmorItem(ModItems.ECHO_STEEL_HELMET);

        trimmedArmorItem(ModItems.HUNTER_MASK);
        trimmedArmorItem(ModItems.HUNTER_CLOAK);
        trimmedArmorItem(ModItems.HUNTER_LEGGINGS);
        trimmedArmorItem(ModItems.HUNTER_BOOTS);
        trimmedArmorItem(ModItems.NETHER_STEEL_MASK);
        trimmedArmorItem(ModItems.NETHER_STEEL_CLOAK);
        trimmedArmorItem(ModItems.NETHER_STEEL_HUNTER_LEGGINGS);
        trimmedArmorItem(ModItems.NETHER_STEEL_HUNTER_BOOTS);
        trimmedArmorItem(ModItems.ECHO_STEEL_MASK);
        trimmedArmorItem(ModItems.ECHO_STEEL_CLOAK);
        trimmedArmorItem(ModItems.ECHO_STEEL_HUNTER_LEGGINGS);
        trimmedArmorItem(ModItems.ECHO_STEEL_HUNTER_BOOTS);
    }

    private ItemModelBuilder createVariants(ResourceLocation template, Item item) {
        ResourceLocation id = BuiltInRegistries.ITEM.getKey(item);
        ItemModelBuilder handheldModel = getBuilder(id + "_base")
                .parent(new ModelFile.UncheckedModelFile(template))
                .texture("layer0", id.getNamespace() + ":item/" + id.getPath() + "_handheld");
        ItemModelBuilder inventoryModel = getBuilder(id + "_gui")
                .parent(new ModelFile.UncheckedModelFile(mcLoc("item/generated")))
                .texture("layer0", id.getNamespace() + ":item/" + id.getPath());

        return withExistingParent(id.toString(), template)
                .customLoader(SeparateTransformsModelBuilder::begin)
                .perspective(ItemDisplayContext.GUI, inventoryModel)
                .base(handheldModel)
                .end();
    }

    private void trimmedArmorItem(DeferredItem<Item> itemDeferredItem) {
        final String MOD_ID = ExtendedCombat.MOD_ID;

        if(itemDeferredItem.get() instanceof ArmorItem armorItem) {
            trimMaterials.forEach((trimMaterial, value) -> {
                float trimValue = value;

                String armorType = switch (armorItem.getEquipmentSlot()) {
                    case HEAD -> "helmet";
                    case CHEST -> "chestplate";
                    case LEGS -> "leggings";
                    case FEET -> "boots";
                    default -> "";
                };

                String armorItemPath = armorItem.toString();
                String trimPath = "trims/items/" + armorType + "_trim_" + trimMaterial.location().getPath();
                String currentTrimName = armorItemPath + "_" + trimMaterial.location().getPath() + "_trim";
                ResourceLocation armorItemResLoc = ResourceLocation.parse(armorItemPath);
                ResourceLocation trimResLoc = ResourceLocation.parse(trimPath); // minecraft namespace
                ResourceLocation trimNameResLoc = ResourceLocation.parse(currentTrimName);

                // This is used for making the ExistingFileHelper acknowledge that this texture exist, so this will
                // avoid an IllegalArgumentException
                existingFileHelper.trackGenerated(trimResLoc, PackType.CLIENT_RESOURCES, ".png", "textures");

                // Trimmed armorItem files
                getBuilder(currentTrimName)
                        .parent(new ModelFile.UncheckedModelFile("item/generated"))
                        .texture("layer0", armorItemResLoc.getNamespace() + ":item/" + armorItemResLoc.getPath())
                        .texture("layer1", trimResLoc);

                // Non-trimmed armorItem file (normal variant)
                this.withExistingParent(itemDeferredItem.getId().getPath(),
                                mcLoc("item/generated"))
                        .override()
                        .model(new ModelFile.UncheckedModelFile(trimNameResLoc.getNamespace()  + ":item/" + trimNameResLoc.getPath()))
                        .predicate(mcLoc("trim_type"), trimValue).end()
                        .texture("layer0",
                                ResourceLocation.fromNamespaceAndPath(MOD_ID,
                                        "item/" + itemDeferredItem.getId().getPath()));
            });
        }
    }
}
