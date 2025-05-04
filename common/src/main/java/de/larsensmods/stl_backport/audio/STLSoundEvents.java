package de.larsensmods.stl_backport.audio;

import de.larsensmods.regutil.IRegistrationProvider;
import de.larsensmods.stl_backport.SpringToLifeMod;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;

import java.util.function.Supplier;

public class STLSoundEvents {

    public static Supplier<SoundEvent> DRY_GRASS;

    public static void initSounds(IRegistrationProvider provider) {
        DRY_GRASS = provider.registerSoundEvent("block.dry_grass.ambient", () -> SoundEvent.createVariableRangeEvent(ResourceLocation.fromNamespaceAndPath(SpringToLifeMod.MOD_ID, "block.dry_grass.ambient")));
    }

}
