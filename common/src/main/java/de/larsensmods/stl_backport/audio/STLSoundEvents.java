package de.larsensmods.stl_backport.audio;

import de.larsensmods.regutil.IRegistrationProvider;
import de.larsensmods.stl_backport.SpringToLifeMod;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;

import java.util.function.Supplier;

public class STLSoundEvents {

    public static Supplier<SoundEvent> FIREFLY_BUSH_IDLE;

    public static void initSounds(IRegistrationProvider provider) {
        SpringToLifeMod.LOGGER.info("Initializing sound events");

        FIREFLY_BUSH_IDLE = provider.registerSoundEvent("block.firefly_bush.ambient", () -> SoundEvent.createVariableRangeEvent(ResourceLocation.fromNamespaceAndPath(SpringToLifeMod.MOD_ID, "block.firefly_bush.ambient")));
    }

}
