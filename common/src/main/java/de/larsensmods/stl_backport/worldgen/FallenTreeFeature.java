package de.larsensmods.stl_backport.worldgen;

import com.mojang.serialization.Codec;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.RotatedPillarBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;
import net.minecraft.world.level.levelgen.feature.TreeFeature;
import net.minecraft.world.level.levelgen.feature.treedecorators.TreeDecorator;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.function.BiConsumer;
import java.util.function.Function;

public class FallenTreeFeature extends Feature<FallenTreeFeatureConfig> {

    public FallenTreeFeature(Codec<FallenTreeFeatureConfig> configCodec) {
        super(configCodec);
    }

    @Override
    public boolean place(FeaturePlaceContext<FallenTreeFeatureConfig> placeContext) {
        this.placeFallenTree(placeContext.config(), placeContext.origin(), placeContext.level(), placeContext.random());
        return true;
    }

    private void placeFallenTree(FallenTreeFeatureConfig config, BlockPos pos, WorldGenLevel level, RandomSource randomSource) {
        this.placeStump(config, level, randomSource, pos.mutable());
        Direction direction = Direction.Plane.HORIZONTAL.getRandomDirection(randomSource);
        int length = config.logLength.sample(randomSource) - 2;
        BlockPos.MutableBlockPos mutableBlockPos = pos.relative(direction, 2 + randomSource.nextInt(2)).mutable();
        this.setGroundHeightForFallenLogStartPos(level, mutableBlockPos);
        if (this.canPlaceEntireFallenLog(level, length, mutableBlockPos, direction)) {
            this.placeFallenLog(config, level, randomSource, length, mutableBlockPos, direction);
        }
    }

    private void setGroundHeightForFallenLogStartPos(WorldGenLevel level, BlockPos.MutableBlockPos mutableBlockPos) {
        mutableBlockPos.move(Direction.UP, 1);

        for(int i = 0; i < 6; i++) {
            if (this.mayPlaceOn(level, mutableBlockPos)) {
                return;
            }

            mutableBlockPos.move(Direction.DOWN);
        }
    }

    private void placeStump(FallenTreeFeatureConfig config, WorldGenLevel level, RandomSource randomSource, BlockPos.MutableBlockPos mutableBlockPos) {
        BlockPos blockPos = this.placeLogBlock(config, level, randomSource, mutableBlockPos, Function.identity());
        this.decorateLogs(level, randomSource, Set.of(blockPos), config.stumpDecorators);
    }

    private boolean canPlaceEntireFallenLog(WorldGenLevel level, int length, BlockPos.MutableBlockPos mutableBlockPos, Direction direction) {
        int suspendedLogs = 0;

        for(int i = 0; i < length; i++) {
            if (!TreeFeature.validTreePos(level, mutableBlockPos)) {
                return false;
            }

            if (!this.isOverSolidGround(level, mutableBlockPos)) {
                if (++suspendedLogs > 2) {
                    return false;
                }
            } else {
                suspendedLogs = 0;
            }

            mutableBlockPos.move(direction);
        }

        mutableBlockPos.move(direction.getOpposite(), length);
        return true;
    }

    private void placeFallenLog(FallenTreeFeatureConfig config, WorldGenLevel level, RandomSource randomSource, int length, BlockPos.MutableBlockPos mutableBlockPos, Direction direction) {
        Set<BlockPos> fallenLogPositions = new HashSet<>();

        for(int i = 0; i < length; i++) {
            fallenLogPositions.add(this.placeLogBlock(config, level, randomSource, mutableBlockPos, getSidewaysStateModifier(direction)));
            mutableBlockPos.move(direction);
        }

        this.decorateLogs(level, randomSource, fallenLogPositions, config.logDecorators);
    }

    private boolean mayPlaceOn(LevelAccessor level, BlockPos pos) {
        return TreeFeature.validTreePos(level, pos) && this.isOverSolidGround(level, pos);
    }

    private boolean isOverSolidGround(LevelAccessor level, BlockPos pos) {
        return level.getBlockState(pos.below()).isFaceSturdy(level, pos, Direction.UP);
    }

    private BlockPos placeLogBlock(FallenTreeFeatureConfig config, WorldGenLevel level, RandomSource randomSource, BlockPos.MutableBlockPos mutableBlockPos, Function<BlockState, BlockState> blockStateModifier) {
        level.setBlock(mutableBlockPos, blockStateModifier.apply(config.trunkProvider.getState(randomSource, mutableBlockPos)), 3);
        BlockPos below = mutableBlockPos.below();
        if(level.getBlockState(below).is(Blocks.GRASS_BLOCK)) {
            level.setBlock(below, Blocks.DIRT.defaultBlockState(), 3);
        }
        this.markAboveForPostProcessing(level, mutableBlockPos);
        return mutableBlockPos.immutable();
    }

    private void decorateLogs(WorldGenLevel level, RandomSource randomSource, Set<BlockPos> logPositions, List<TreeDecorator> treeDecorators) {
        if (!treeDecorators.isEmpty()) {
            TreeDecorator.Context decoratorContext = new TreeDecorator.Context(level, this.getDecorationSetter(level), randomSource, logPositions, Set.of(), Set.of());
            treeDecorators.forEach(decorator -> decorator.place(decoratorContext));
        }
    }

    private BiConsumer<BlockPos, BlockState> getDecorationSetter(WorldGenLevel level) {
        return (pos, state) -> level.setBlock(pos, state, 19);
    }

    private static Function<BlockState, BlockState> getSidewaysStateModifier(Direction direction) {
        return original -> original.trySetValue(RotatedPillarBlock.AXIS, direction.getAxis());
    }
}
