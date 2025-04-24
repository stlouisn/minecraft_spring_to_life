package de.larsensmods.stl_backport.fabric.register;

import de.larsensmods.regutil.IRegistrationProvider;
import de.larsensmods.stl_backport.SpringToLifeMod;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;

import java.util.function.Supplier;

public class FabricRegistrationProvider implements IRegistrationProvider {

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

}
