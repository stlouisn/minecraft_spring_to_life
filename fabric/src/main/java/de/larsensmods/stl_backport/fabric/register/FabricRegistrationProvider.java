package de.larsensmods.stl_backport.fabric.register;

import de.larsensmods.regutil.IRegistrationProvider;
import de.larsensmods.stl_backport.SpringToLifeMod;
import net.minecraft.core.Registry;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleType;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;

import java.util.function.Function;
import java.util.function.Supplier;

public class FabricRegistrationProvider implements IRegistrationProvider {

    @Override
    public Supplier<Block> registerBlock(String key, Function<BlockBehaviour.Properties, Block> constructor, BlockBehaviour.Properties properties) {
        Block regBlock = Registry.register(BuiltInRegistries.BLOCK, ResourceLocation.fromNamespaceAndPath(SpringToLifeMod.MOD_ID, key), constructor.apply(properties));
        return () -> regBlock;
    }

    @Override
    public Supplier<CreativeModeTab> registerCreativeTab(String key, Supplier<CreativeModeTab.Builder> tab) {
        CreativeModeTab creativeTab = Registry.register(BuiltInRegistries.CREATIVE_MODE_TAB, ResourceLocation.fromNamespaceAndPath(SpringToLifeMod.MOD_ID, key), tab.get().build());
        return () -> creativeTab;
    }

    public <T extends Entity> Supplier<EntityType<T>> registerEntityType(String key, Supplier<EntityType.Builder<T>> entityTypeBuilder) {
        EntityType<T> entityType = Registry.register(BuiltInRegistries.ENTITY_TYPE, ResourceLocation.fromNamespaceAndPath(SpringToLifeMod.MOD_ID, key), entityTypeBuilder.get().build(key));
        return () -> entityType;
    }

    public Supplier<Item> registerItem(String key, Supplier<Item> item) {
        Item regItem = Registry.register(BuiltInRegistries.ITEM, ResourceLocation.fromNamespaceAndPath(SpringToLifeMod.MOD_ID, key), item.get());
        return () -> regItem;
    }

    @Override
    public <T extends ParticleOptions> Supplier<ParticleType<T>> registerParticleType(String key, Supplier<ParticleType<T>> particleType) {
        ParticleType<T> regParticleType = Registry.register(BuiltInRegistries.PARTICLE_TYPE, ResourceLocation.fromNamespaceAndPath(SpringToLifeMod.MOD_ID, key), particleType.get());
        return () -> regParticleType;
    }

    @Override
    public Supplier<SoundEvent> registerSoundEvent(String key, Supplier<SoundEvent> soundEvent) {
        SoundEvent regSoundEvent = Registry.register(BuiltInRegistries.SOUND_EVENT, ResourceLocation.fromNamespaceAndPath(SpringToLifeMod.MOD_ID, key), soundEvent.get());
        return () -> regSoundEvent;
    }

}
