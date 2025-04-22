package de.larsensmods.stl_backport.neoforge;

import net.neoforged.fml.common.Mod;

import de.larsensmods.stl_backport.SpringToLifeMod;

@Mod(SpringToLifeMod.MOD_ID)
public final class SpringToLifeModNeoForge {
    public SpringToLifeModNeoForge() {
        // Run our common setup.
        SpringToLifeMod.init();
    }
}
