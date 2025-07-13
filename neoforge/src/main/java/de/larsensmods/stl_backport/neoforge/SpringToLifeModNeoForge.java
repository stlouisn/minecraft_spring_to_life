package de.larsensmods.stl_backport.neoforge;

import de.larsensmods.stl_backport.SpringToLifeMod;
import de.larsensmods.stl_backport.neoforge.block.STLBushBlockNeoForge;
import de.larsensmods.stl_backport.neoforge.block.STLFireflyBushBlockNeoForge;
import de.larsensmods.stl_backport.neoforge.register.NeoForgeRegistrationProvider;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.loading.FMLEnvironment;

import java.util.function.Function;

@Mod(SpringToLifeMod.MOD_ID)
public final class SpringToLifeModNeoForge {
    public SpringToLifeModNeoForge(IEventBus bus) {
        NeoForgeRegistrationProvider registrationProvider = new NeoForgeRegistrationProvider();

        //Register override keys
        registrationProvider.addOverrideKey("block:bush", (Function<BlockBehaviour.Properties, Block>) STLBushBlockNeoForge::new);
        registrationProvider.addOverrideKey("block:firefly_bush", (Function<BlockBehaviour.Properties, Block>) STLFireflyBushBlockNeoForge::new);

        // Run our common setup.
        SpringToLifeMod.init(registrationProvider, FMLEnvironment.dist.isClient());

        registrationProvider.finishRegistration(bus);
    }
}
