package de.larsensmods.stl_backport.block;

import de.larsensmods.regutil.IRegistrationProvider;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.level.material.PushReaction;

import java.util.function.Supplier;

public class STLBlocks {

    public static Supplier<Block> BUSH;

    public static Supplier<Block> SHORT_DRY_GRASS;
    public static Supplier<Block> TALL_DRY_GRASS;

    public static Supplier<Block> CACTUS_FLOWER;

    public static void initBlocks(IRegistrationProvider provider) {
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

        SHORT_DRY_GRASS = provider.registerBlock(
                "short_dry_grass",
                STLShortDryGrassBlock::new,
                Block.Properties.of()
                        .mapColor(MapColor.COLOR_YELLOW)
                        .replaceable()
                        .noCollission()
                        .instabreak()
                        .sound(SoundType.GRASS)
                        .ignitedByLava()
                        .offsetType(BlockBehaviour.OffsetType.XYZ)
                        .pushReaction(PushReaction.DESTROY)
        );

        TALL_DRY_GRASS = provider.registerBlock(
                "tall_dry_grass",
                STLTallDryGrassBlock::new,
                BlockBehaviour.Properties.of()
                        .mapColor(MapColor.COLOR_YELLOW)
                        .replaceable()
                        .noCollission()
                        .instabreak()
                        .sound(SoundType.GRASS)
                        .ignitedByLava()
                        .offsetType(BlockBehaviour.OffsetType.XYZ)
                        .pushReaction(PushReaction.DESTROY)
        );

        CACTUS_FLOWER = provider.registerBlock(
                "cactus_flower",
                STLCactusFlowerBlock::new,
                BlockBehaviour.Properties.of()
                        .mapColor(MapColor.COLOR_PINK)
                        .noCollission()
                        .instabreak()
                        .ignitedByLava()
                        //TODO: .sound(SoundType.CACTUS_FLOWER)
                        .pushReaction(PushReaction.DESTROY)
        );
    }

}
