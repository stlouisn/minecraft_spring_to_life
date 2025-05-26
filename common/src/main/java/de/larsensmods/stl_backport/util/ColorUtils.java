package de.larsensmods.stl_backport.util;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.BlockAndTintGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;

public interface ColorUtils {

    int getAverageDryFoliageColor(BlockAndTintGetter blockAndTintGetter, BlockPos pos);
    int getBlockColor(BlockState state, Level level, BlockPos pos, int tintIndex);

}
