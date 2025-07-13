package de.larsensmods.stl_backport.neoforge.register;

import com.mojang.serialization.MapCodec;
import de.larsensmods.regutil.IRegistrationProvider;
import de.larsensmods.stl_backport.SpringToLifeMod;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleType;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.core.registries.BuiltInRegistries;
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
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;
import java.util.function.Supplier;

public class NeoForgeRegistrationProvider implements IRegistrationProvider {

    private final Map<String, Object> overrideKeys = new HashMap<>();

    private static final DeferredRegister<CreativeModeTab> TAB_REGISTER = DeferredRegister.create(BuiltInRegistries.CREATIVE_MODE_TAB, SpringToLifeMod.MOD_ID);
    private static final DeferredRegister<EntityType<?>> ENTITY_TYPE_REGISTER = DeferredRegister.create(BuiltInRegistries.ENTITY_TYPE, SpringToLifeMod.MOD_ID);
    private static final DeferredRegister<Item> ITEM_REGISTER = DeferredRegister.create(BuiltInRegistries.ITEM, SpringToLifeMod.MOD_ID);
    private static final DeferredRegister<Block> BLOCK_REGISTER = DeferredRegister.create(BuiltInRegistries.BLOCK, SpringToLifeMod.MOD_ID);
    private static final DeferredRegister<SoundEvent> SOUND_REGISTER = DeferredRegister.create(BuiltInRegistries.SOUND_EVENT, SpringToLifeMod.MOD_ID);
    private static final DeferredRegister<Feature<?>> FEATURE_REGISTER = DeferredRegister.create(BuiltInRegistries.FEATURE, SpringToLifeMod.MOD_ID);
    private static final DeferredRegister<TreeDecoratorType<?>> TREE_DECORATOR_REGISTER = DeferredRegister.create(BuiltInRegistries.TREE_DECORATOR_TYPE, SpringToLifeMod.MOD_ID);
    private static final DeferredRegister<ParticleType<?>> PARTICLE_TYPE_REGISTER = DeferredRegister.create(BuiltInRegistries.PARTICLE_TYPE, SpringToLifeMod.MOD_ID);

    public void finishRegistration(IEventBus bus) {
        TAB_REGISTER.register(bus);
        ENTITY_TYPE_REGISTER.register(bus);
        ITEM_REGISTER.register(bus);
        BLOCK_REGISTER.register(bus);
        SOUND_REGISTER.register(bus);
        FEATURE_REGISTER.register(bus);
        TREE_DECORATOR_REGISTER.register(bus);
        PARTICLE_TYPE_REGISTER.register(bus);
    }

    public void addOverrideKey(String key, Object value) {
        overrideKeys.put(key, value);
    }

    @SuppressWarnings("unchecked")
    @Override
    public Supplier<Block> registerBlock(String key, Function<BlockBehaviour.Properties, Block> constructor, BlockBehaviour.Properties properties) {
        if (overrideKeys.containsKey("block:" + key) && overrideKeys.get("block:" + key) instanceof Function<?, ?> function) {
            SpringToLifeMod.LOGGER.info("Overriding Block {}", key);
            return BLOCK_REGISTER.register(key, () -> ((Function<BlockBehaviour.Properties, Block>) function).apply(properties));
        }
        return BLOCK_REGISTER.register(key, () -> constructor.apply(properties));
    }

    @Override
    public Supplier<CreativeModeTab> registerCreativeTab(String key, Supplier<CreativeModeTab.Builder> tab) {
        return TAB_REGISTER.register(key, tab.get()::build);
    }

    public <T extends Entity> Supplier<EntityType<T>> registerEntityType(String key, Supplier<EntityType.Builder<T>> entityTypeBuilder) {
        return ENTITY_TYPE_REGISTER.register(key, () -> entityTypeBuilder.get().build(key));
    }

    @Override
    public <T extends FeatureConfiguration> Supplier<Feature<T>> registerFeature(String key, Supplier<Feature<T>> feature) {
        return FEATURE_REGISTER.register(key, feature);
    }

    public Supplier<Item> registerItem(String key, Supplier<Item> item) {
        return ITEM_REGISTER.register(key, item);
    }

    @Override
    public <T extends ParticleOptions> Supplier<ParticleType<T>> registerParticleType(String key, Supplier<ParticleType<T>> particleType) {
        return PARTICLE_TYPE_REGISTER.register(key, particleType);
    }

    @Override
    public Supplier<SimpleParticleType> registerParticleTypeSimple(String key) {
        return PARTICLE_TYPE_REGISTER.register(key, () -> new SimpleParticleType(false));
    }

    @Override
    public Supplier<SoundEvent> registerSoundEvent(String key, Supplier<SoundEvent> soundEvent) {
        return SOUND_REGISTER.register(key, soundEvent);
    }

    @Override
    public <T extends TreeDecorator> Supplier<TreeDecoratorType<T>> registerTreeDecoratorType(String key, MapCodec<T> treeDecoratorTypeCodec) {
        return TREE_DECORATOR_REGISTER.register(key, () -> new TreeDecoratorType<>(treeDecoratorTypeCodec));
    }

}
