package de.larsensmods.stl_backport.fabric;

import de.larsensmods.stl_backport.entity.ColdChicken;
import de.larsensmods.stl_backport.entity.STLEntityTypes;
import de.larsensmods.stl_backport.entity.WarmChicken;
import de.larsensmods.stl_backport.entity.WarmPig;
import de.larsensmods.stl_backport.fabric.register.FabricRegistrationProvider;
import net.fabricmc.api.ModInitializer;

import de.larsensmods.stl_backport.SpringToLifeMod;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry;

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
    }
}
