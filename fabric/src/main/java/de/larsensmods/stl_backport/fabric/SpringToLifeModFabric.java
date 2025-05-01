package de.larsensmods.stl_backport.fabric;

import de.larsensmods.stl_backport.block.STLBlocks;
import de.larsensmods.stl_backport.entity.ColdChicken;
import de.larsensmods.stl_backport.entity.STLEntityTypes;
import de.larsensmods.stl_backport.entity.WarmChicken;
import de.larsensmods.stl_backport.entity.WarmPig;
import de.larsensmods.stl_backport.fabric.register.FabricRegistrationProvider;
import de.larsensmods.stl_backport.item.STLItems;
import net.fabricmc.api.ModInitializer;

import de.larsensmods.stl_backport.SpringToLifeMod;
import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.fabricmc.fabric.api.biome.v1.ModificationPhase;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry;
import net.fabricmc.fabric.api.registry.CompostingChanceRegistry;
import net.fabricmc.fabric.api.registry.FlammableBlockRegistry;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.level.biome.Biomes;
import net.minecraft.world.level.biome.MobSpawnSettings;
import net.minecraft.world.level.levelgen.GenerationStep;

public final class SpringToLifeModFabric implements ModInitializer {
    @Override
    public void onInitialize() {
        FabricRegistrationProvider registrationProvider = new FabricRegistrationProvider();

        // Run our common setup.
        SpringToLifeMod.init(registrationProvider);

        FabricDefaultAttributeRegistry.register(STLEntityTypes.WARM_CHICKEN.get(), WarmChicken.createAttributes());
        FabricDefaultAttributeRegistry.register(STLEntityTypes.COLD_CHICKEN.get(), ColdChicken.createAttributes());
        FabricDefaultAttributeRegistry.register(STLEntityTypes.WARM_PIG.get(), WarmPig.createAttributes());
        FabricDefaultAttributeRegistry.register(STLEntityTypes.COLD_PIG.get(), WarmPig.createAttributes());
        FabricDefaultAttributeRegistry.register(STLEntityTypes.WARM_COW.get(), WarmPig.createAttributes());
        FabricDefaultAttributeRegistry.register(STLEntityTypes.COLD_COW.get(), WarmPig.createAttributes());

        FlammableBlockRegistry.getDefaultInstance().add(STLBlocks.BUSH.get(), 60, 100);
        CompostingChanceRegistry.INSTANCE.add(STLItems.BUSH.get(), 0.3f);

        this.applyBiomeModifications();
    }

    private void applyBiomeModifications() {
        BiomeModifications.create(ResourceLocation.fromNamespaceAndPath(SpringToLifeMod.MOD_ID, "add_bushes"))
                .add(ModificationPhase.ADDITIONS, BiomeSelectors.tag(TagKey.create(Registries.BIOME, ResourceLocation.fromNamespaceAndPath(SpringToLifeMod.MOD_ID, "bush_biomes"))),
                        context -> context.getGenerationSettings()
                                .addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, ResourceKey.create(Registries.PLACED_FEATURE, ResourceLocation.fromNamespaceAndPath(SpringToLifeMod.MOD_ID, "patch_bush"))));

        BiomeModifications.create(ResourceLocation.fromNamespaceAndPath(SpringToLifeMod.MOD_ID, "replace_warm_animals"))
                .add(ModificationPhase.ADDITIONS, BiomeSelectors.tag(TagKey.create(Registries.BIOME, ResourceLocation.fromNamespaceAndPath(SpringToLifeMod.MOD_ID, "warm_animal_biomes"))),
                        context -> {
                    context.getSpawnSettings().removeSpawnsOfEntityType(EntityType.COW);
                    context.getSpawnSettings().removeSpawnsOfEntityType(EntityType.PIG);
                    context.getSpawnSettings().removeSpawnsOfEntityType(EntityType.CHICKEN);
                    context.getSpawnSettings().addSpawn(MobCategory.CREATURE, new MobSpawnSettings.SpawnerData(STLEntityTypes.WARM_PIG.get(), 10, 4, 4));
                    context.getSpawnSettings().addSpawn(MobCategory.CREATURE, new MobSpawnSettings.SpawnerData(STLEntityTypes.WARM_CHICKEN.get(), 10, 4, 4));
                    context.getSpawnSettings().addSpawn(MobCategory.CREATURE, new MobSpawnSettings.SpawnerData(STLEntityTypes.WARM_COW.get(), 8, 4, 4));
                })
                .add(ModificationPhase.ADDITIONS, BiomeSelectors.includeByKey(Biomes.JUNGLE, Biomes.BAMBOO_JUNGLE, Biomes.SPARSE_JUNGLE),
                        context -> context.getSpawnSettings().addSpawn(MobCategory.CREATURE, new MobSpawnSettings.SpawnerData(STLEntityTypes.WARM_CHICKEN.get(), 10, 4, 4)));

        BiomeModifications.create(ResourceLocation.fromNamespaceAndPath(SpringToLifeMod.MOD_ID, "replace_cold_animals"))
                .add(ModificationPhase.ADDITIONS, BiomeSelectors.tag(TagKey.create(Registries.BIOME, ResourceLocation.fromNamespaceAndPath(SpringToLifeMod.MOD_ID, "cold_animal_biomes"))),
                        context -> {
                    context.getSpawnSettings().removeSpawnsOfEntityType(EntityType.COW);
                    context.getSpawnSettings().removeSpawnsOfEntityType(EntityType.PIG);
                    context.getSpawnSettings().removeSpawnsOfEntityType(EntityType.CHICKEN);
                    context.getSpawnSettings().addSpawn(MobCategory.CREATURE, new MobSpawnSettings.SpawnerData(STLEntityTypes.COLD_PIG.get(), 10, 4, 4));
                    context.getSpawnSettings().addSpawn(MobCategory.CREATURE, new MobSpawnSettings.SpawnerData(STLEntityTypes.COLD_CHICKEN.get(), 10, 4, 4));
                    context.getSpawnSettings().addSpawn(MobCategory.CREATURE, new MobSpawnSettings.SpawnerData(STLEntityTypes.COLD_COW.get(), 8, 4, 4));
                });
    }
}
