package de.larsensmods.stl_backport.neoforge;

import de.larsensmods.stl_backport.neoforge.register.EntityRegistry;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.common.Mod;

import de.larsensmods.stl_backport.SpringToLifeMod;

@Mod(SpringToLifeMod.MOD_ID)
public final class SpringToLifeModNeoForge {
    public SpringToLifeModNeoForge(IEventBus bus) {
        // Run our common setup.
        SpringToLifeMod.init();

        EntityRegistry.initEntityTypes(bus);
    }
}
