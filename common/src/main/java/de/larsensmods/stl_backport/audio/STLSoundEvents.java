package de.larsensmods.stl_backport.audio;

import de.larsensmods.regutil.IRegistrationProvider;
import de.larsensmods.stl_backport.SpringToLifeMod;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;

import java.util.function.Supplier;

public class STLSoundEvents {

    public static Supplier<SoundEvent> LEAF_LITTER_BREAK;
    public static Supplier<SoundEvent> LEAF_LITTER_STEP;
    public static Supplier<SoundEvent> LEAF_LITTER_PLACE;
    public static Supplier<SoundEvent> LEAF_LITTER_HIT;
    public static Supplier<SoundEvent> LEAF_LITTER_FALL;

    public static Supplier<SoundEvent> DRY_GRASS;

    public static void initSounds(IRegistrationProvider provider) {
        SpringToLifeMod.LOGGER.info("Initializing sound events");

        LEAF_LITTER_BREAK = provider.registerSoundEvent("block.leaf_litter.break", () -> SoundEvent.createVariableRangeEvent(ResourceLocation.fromNamespaceAndPath(SpringToLifeMod.MOD_ID, "block.leaf_litter.break")));
        LEAF_LITTER_STEP = provider.registerSoundEvent("block.leaf_litter.step", () -> SoundEvent.createVariableRangeEvent(ResourceLocation.fromNamespaceAndPath(SpringToLifeMod.MOD_ID, "block.leaf_litter.step")));
        LEAF_LITTER_PLACE = provider.registerSoundEvent("block.leaf_litter.place", () -> SoundEvent.createVariableRangeEvent(ResourceLocation.fromNamespaceAndPath(SpringToLifeMod.MOD_ID, "block.leaf_litter.place")));
        LEAF_LITTER_HIT = provider.registerSoundEvent("block.leaf_litter.hit", () -> SoundEvent.createVariableRangeEvent(ResourceLocation.fromNamespaceAndPath(SpringToLifeMod.MOD_ID, "block.leaf_litter.hit")));
        LEAF_LITTER_FALL = provider.registerSoundEvent("block.leaf_litter.fall", () -> SoundEvent.createVariableRangeEvent(ResourceLocation.fromNamespaceAndPath(SpringToLifeMod.MOD_ID, "block.leaf_litter.fall")));

        DRY_GRASS = provider.registerSoundEvent("block.dry_grass.ambient", () -> SoundEvent.createVariableRangeEvent(ResourceLocation.fromNamespaceAndPath(SpringToLifeMod.MOD_ID, "block.dry_grass.ambient")));
    }

}
