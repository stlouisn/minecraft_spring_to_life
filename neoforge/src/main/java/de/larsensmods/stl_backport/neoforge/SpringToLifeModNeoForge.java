package de.larsensmods.stl_backport.neoforge;

import de.larsensmods.stl_backport.neoforge.block.*;
import de.larsensmods.stl_backport.neoforge.register.NeoForgeRegistrationProvider;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.common.Mod;

import de.larsensmods.stl_backport.SpringToLifeMod;
import net.neoforged.fml.loading.FMLEnvironment;

import java.util.function.Function;

@Mod(SpringToLifeMod.MOD_ID)
public final class SpringToLifeModNeoForge {
    public SpringToLifeModNeoForge(IEventBus bus) {
        NeoForgeRegistrationProvider registrationProvider = new NeoForgeRegistrationProvider();

        //Register override keys
        registrationProvider.addOverrideKey("block:leaf_litter", (Function<BlockBehaviour.Properties, Block>) STLLeafLitterBlockNeoForge::new);
        registrationProvider.addOverrideKey("block:bush", (Function<BlockBehaviour.Properties, Block>) STLBushBlockNeoForge::new);
        registrationProvider.addOverrideKey("block:firefly_bush", (Function<BlockBehaviour.Properties, Block>) STLFireflyBushBlockNeoForge::new);
        registrationProvider.addOverrideKey("block:short_dry_grass", (Function<BlockBehaviour.Properties, Block>) STLShortDryGrassBlockNeoForge::new);
        registrationProvider.addOverrideKey("block:tall_dry_grass", (Function<BlockBehaviour.Properties, Block>) STLTallDryGrassBlockNeoForge::new);
        registrationProvider.addOverrideKey("block:cactus_flower", (Function<BlockBehaviour.Properties, Block>) STLCactusFlowerBlockNeoForge::new);

        // Run our common setup.
        SpringToLifeMod.init(registrationProvider, FMLEnvironment.dist.isClient());

        registrationProvider.finishRegistration(bus);
    }
}
