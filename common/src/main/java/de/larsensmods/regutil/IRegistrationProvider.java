package de.larsensmods.regutil;

import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;

import java.util.function.Supplier;

public interface IRegistrationProvider {

    Supplier<CreativeModeTab> registerCreativeTab(String key, Supplier<CreativeModeTab.Builder> tab);
    <T extends Entity> Supplier<EntityType<T>> registerEntityType(String key, Supplier<EntityType.Builder<T>> entityTypeBuilder);
    Supplier<Item> registerItem(String key, Supplier<Item> item);

}
