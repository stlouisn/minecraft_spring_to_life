package de.larsensmods.stl_backport.audio;

import net.minecraft.world.level.block.SoundType;

public class STLSoundTypes {

    public static final SoundType LEAF_LITTER = new SoundType(
            1.0F,
            1.0F,
            STLSoundEvents.LEAF_LITTER_BREAK.get(),
            STLSoundEvents.LEAF_LITTER_STEP.get(),
            STLSoundEvents.LEAF_LITTER_PLACE.get(),
            STLSoundEvents.LEAF_LITTER_HIT.get(),
            STLSoundEvents.LEAF_LITTER_FALL.get()
    );

}
