package de.larsensmods.stl_backport.worldgen;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.util.valueproviders.IntProvider;
import net.minecraft.world.level.levelgen.feature.configurations.FeatureConfiguration;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;
import net.minecraft.world.level.levelgen.feature.treedecorators.TreeDecorator;

import java.util.List;

public class FallenTreeFeatureConfig implements FeatureConfiguration {

    public static final Codec<FallenTreeFeatureConfig> CODEC = RecordCodecBuilder.create(instance -> instance.group(
            BlockStateProvider.CODEC.fieldOf("trunk_provider").forGetter(config -> config.trunkProvider),
            IntProvider.codec(0, 16).fieldOf("log_length").forGetter(config -> config.logLength),
            TreeDecorator.CODEC.listOf().fieldOf("stump_decorators").forGetter(config -> config.stumpDecorators),
            TreeDecorator.CODEC.listOf().fieldOf("log_decorators").forGetter(config -> config.logDecorators)
    ).apply(instance, FallenTreeFeatureConfig::new));

    public final BlockStateProvider trunkProvider;
    public final IntProvider logLength;
    public final List<TreeDecorator> stumpDecorators;
    public final List<TreeDecorator> logDecorators;

    protected FallenTreeFeatureConfig(BlockStateProvider trunkProvider, IntProvider lengthProvider, List<TreeDecorator> stumpDecorators, List<TreeDecorator> logDecorators) {
        this.trunkProvider = trunkProvider;
        this.logLength = lengthProvider;
        this.stumpDecorators = stumpDecorators;
        this.logDecorators = logDecorators;
    }

}
