package de.larsensmods.stl_backport.fabric.register;

import de.larsensmods.regutil.IRegistrationProvider;
import de.larsensmods.stl_backport.SpringToLifeMod;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;

import java.util.function.Supplier;

public class FabricRegistrationProvider implements IRegistrationProvider {

    public <T extends Entity> Supplier<EntityType<T>> registerEntityType(String key, EntityType.Builder<T> builder) {
        EntityType<T> entityType = Registry.register(BuiltInRegistries.ENTITY_TYPE, ResourceLocation.fromNamespaceAndPath(SpringToLifeMod.MOD_ID, key), builder.build(key));
        return () -> entityType;
    }

}
