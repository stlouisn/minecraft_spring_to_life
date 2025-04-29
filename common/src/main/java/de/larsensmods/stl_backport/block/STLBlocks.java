package de.larsensmods.stl_backport.block;

import de.larsensmods.regutil.IRegistrationProvider;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.level.material.PushReaction;

import java.util.function.Supplier;

public class STLBlocks {

    public static Supplier<Block> BUSH;

    public static void initBlocks(IRegistrationProvider provider) {
        BUSH = provider.registerBlock("bush", STLBushBlock::new,Block.Properties.of()
                .mapColor(MapColor.PLANT)
                .replaceable()
                .noCollission()
                .instabreak()
                .sound(SoundType.GRASS)
                .ignitedByLava()
                .pushReaction(PushReaction.DESTROY));
    }

}
