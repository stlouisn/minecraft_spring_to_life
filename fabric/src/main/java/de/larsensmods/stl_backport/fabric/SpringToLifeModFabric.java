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
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry;
import net.fabricmc.fabric.api.registry.CompostingChanceRegistry;
import net.fabricmc.fabric.api.registry.FlammableBlockRegistry;

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
    }
}
