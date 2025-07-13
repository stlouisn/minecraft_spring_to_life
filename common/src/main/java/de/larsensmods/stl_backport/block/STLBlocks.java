package de.larsensmods.stl_backport.block;

import de.larsensmods.regutil.IRegistrationProvider;
import de.larsensmods.stl_backport.SpringToLifeMod;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.level.material.PushReaction;

import java.util.function.Supplier;

public class STLBlocks {

    public static Supplier<Block> BUSH;
    public static Supplier<Block> FIREFLY_BUSH;

    public static void initBlocks(IRegistrationProvider provider) {
        SpringToLifeMod.LOGGER.info("Initializing blocks");

        BUSH = provider.registerBlock(
                "bush",
                STLBushBlock::new,
                Block.Properties.of()
                        .mapColor(MapColor.PLANT)
                        .replaceable()
                        .noCollission()
                        .instabreak()
                        .sound(SoundType.GRASS)
                        .ignitedByLava()
                        .pushReaction(PushReaction.DESTROY)
        );

        FIREFLY_BUSH = provider.registerBlock(
                "firefly_bush",
                STLFireflyBushBlock::new,
                BlockBehaviour.Properties.of()
                        .mapColor(MapColor.PLANT)
                        .ignitedByLava()
                        .lightLevel(state -> 2)
                        .noCollission()
                        .instabreak()
                        .sound(SoundType.SWEET_BERRY_BUSH)
                        .pushReaction(PushReaction.DESTROY)
        );

    }
}
