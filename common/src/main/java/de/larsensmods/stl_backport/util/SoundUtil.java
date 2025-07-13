package de.larsensmods.stl_backport.util;

import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.sounds.EntityBoundSoundInstance;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundSource;

public class SoundUtil {

    public static void playPlayerSoundEffect(SoundEvent soundEvent, SoundSource source, float volume, float pitch, long seed) {
        if (Minecraft.getInstance().player != null) {
            Minecraft.getInstance().getSoundManager().play(new EntityBoundSoundInstance(soundEvent, source, volume, pitch, Minecraft.getInstance().player, seed));
        }
    }

}
