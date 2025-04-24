package de.larsensmods.regutil;

import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;

import java.util.function.Supplier;

public interface IRegistrationProvider {

    <T extends Entity> Supplier<EntityType<T>> registerEntityType(String key, EntityType.Builder<T> builder);

}
