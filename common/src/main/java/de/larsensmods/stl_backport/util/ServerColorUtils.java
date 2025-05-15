package de.larsensmods.stl_backport.util;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;

public class ServerColorUtils implements ColorUtils {

    @Override
    public int getBlockColor(BlockState state, Level level, BlockPos pos, int tintIndex) {
        return 0;
    }

}
