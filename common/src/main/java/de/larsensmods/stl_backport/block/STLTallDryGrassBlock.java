package de.larsensmods.stl_backport.block;

import com.mojang.serialization.MapCodec;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.BonemealableBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.Optional;

public class STLTallDryGrassBlock extends STLDryVegetationBlock implements BonemealableBlock {

    public static final MapCodec<STLTallDryGrassBlock> CODEC = simpleCodec(STLTallDryGrassBlock::new);
    private static final VoxelShape SHAPE = Block.box(1.0, 0.0, 1.0, 15.0, 16.0, 15.0);

    protected STLTallDryGrassBlock(BlockBehaviour.Properties properties) {
        super(properties);
    }

    @Override
    public boolean isValidBonemealTarget(LevelReader level, BlockPos pos, BlockState state) {
        return getValidSpreadPos(Direction.Plane.HORIZONTAL.stream().toList(), level, pos, STLBlocks.SHORT_DRY_GRASS.get().defaultBlockState()).isPresent();
    }

    @Override
    public boolean isBonemealSuccess(Level level, RandomSource randomSource, BlockPos pos, BlockState state) {
        return true;
    }

    @Override
    public void performBonemeal(ServerLevel level, RandomSource random, BlockPos pos, BlockState state) {
        getValidSpreadPos(Direction.Plane.HORIZONTAL.shuffledCopy(level.random), level, pos, STLBlocks.SHORT_DRY_GRASS.get().defaultBlockState())
                .ifPresent(param1x -> level.setBlockAndUpdate(param1x, STLBlocks.SHORT_DRY_GRASS.get().defaultBlockState()));
    }

    @Override
    protected @NotNull VoxelShape getShape(BlockState state, BlockGetter blockGetter, BlockPos pos, CollisionContext collisionContext) {
        return SHAPE;
    }

    @Override
    public @NotNull MapCodec<? extends STLDryVegetationBlock> codec() {
        return CODEC;
    }

    private Optional<BlockPos> getValidSpreadPos(List<Direction> directions, LevelReader levelReader, BlockPos blockPos, BlockState state) {
        for(Direction dir : directions) {
            BlockPos pos = blockPos.relative(dir);
            if (levelReader.isEmptyBlock(pos) && state.canSurvive(levelReader, pos)) {
                return Optional.of(pos);
            }
        }

        return Optional.empty();
    }
}
