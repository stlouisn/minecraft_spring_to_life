package de.larsensmods.stl_backport.neoforge.register;

import de.larsensmods.regutil.IRegistrationProvider;
import de.larsensmods.stl_backport.SpringToLifeMod;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class NeoForgeRegistrationProvider implements IRegistrationProvider {

    private static final DeferredRegister<CreativeModeTab> TAB_REGISTER = DeferredRegister.create(BuiltInRegistries.CREATIVE_MODE_TAB, SpringToLifeMod.MOD_ID);
    private static final DeferredRegister<EntityType<?>> ENTITY_TYPE_REGISTER = DeferredRegister.create(BuiltInRegistries.ENTITY_TYPE, SpringToLifeMod.MOD_ID);
    private static final DeferredRegister<Item> ITEM_REGISTER = DeferredRegister.create(BuiltInRegistries.ITEM, SpringToLifeMod.MOD_ID);

    public void finishRegistration(IEventBus bus) {
        TAB_REGISTER.register(bus);
        ENTITY_TYPE_REGISTER.register(bus);
        ITEM_REGISTER.register(bus);
    }

    @Override
    public Supplier<CreativeModeTab> registerCreativeTab(String key, Supplier<CreativeModeTab.Builder> tab) {
        return TAB_REGISTER.register(key, tab.get()::build);
    }

    public <T extends Entity> Supplier<EntityType<T>> registerEntityType(String key, Supplier<EntityType.Builder<T>> entityTypeBuilder) {
        return ENTITY_TYPE_REGISTER.register(key, () -> entityTypeBuilder.get().build(key));
    }

    public Supplier<Item> registerItem(String key, Supplier<Item> item) {
        return ITEM_REGISTER.register(key, item);
    }

}
