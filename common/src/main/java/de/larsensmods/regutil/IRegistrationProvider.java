package de.larsensmods.regutil;

import com.mojang.serialization.MapCodec;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleType;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.FeatureConfiguration;
import net.minecraft.world.level.levelgen.feature.treedecorators.TreeDecorator;
import net.minecraft.world.level.levelgen.feature.treedecorators.TreeDecoratorType;

import java.util.function.Function;
import java.util.function.Supplier;

public interface IRegistrationProvider {

    Supplier<Block> registerBlock(String key, Function<BlockBehaviour.Properties, Block> constructor, BlockBehaviour.Properties properties);
    Supplier<CreativeModeTab> registerCreativeTab(String key, Supplier<CreativeModeTab.Builder> tab);
    <T extends Entity> Supplier<EntityType<T>> registerEntityType(String key, Supplier<EntityType.Builder<T>> entityTypeBuilder);
    <T extends FeatureConfiguration> Supplier<Feature<T>> registerFeature(String key, Supplier<Feature<T>> feature);
    Supplier<Item> registerItem(String key, Supplier<Item> item);
    <T extends ParticleOptions> Supplier<ParticleType<T>> registerParticleType(String key, Supplier<ParticleType<T>> particleType);
    Supplier<SoundEvent> registerSoundEvent(String key, Supplier<SoundEvent> soundEvent);
    <T extends TreeDecorator> Supplier<TreeDecoratorType<T>> registerTreeDecoratorType(String key, MapCodec<T> treeDecoratorTypeCodec);

}
