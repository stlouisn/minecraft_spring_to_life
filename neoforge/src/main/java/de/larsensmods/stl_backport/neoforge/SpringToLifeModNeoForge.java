package de.larsensmods.stl_backport.neoforge;

import de.larsensmods.stl_backport.neoforge.register.NeoForgeRegistrationProvider;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.common.Mod;

import de.larsensmods.stl_backport.SpringToLifeMod;

@Mod(SpringToLifeMod.MOD_ID)
public final class SpringToLifeModNeoForge {
    public SpringToLifeModNeoForge(IEventBus bus) {
        NeoForgeRegistrationProvider registrationProvider = new NeoForgeRegistrationProvider();

        // Run our common setup.
        SpringToLifeMod.init(registrationProvider);

        registrationProvider.finishRegistration(bus);
    }
}
