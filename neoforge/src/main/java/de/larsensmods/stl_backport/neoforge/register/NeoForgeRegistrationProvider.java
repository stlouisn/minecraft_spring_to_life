package de.larsensmods.stl_backport.neoforge.register;

import de.larsensmods.regutil.IRegistrationProvider;
import de.larsensmods.stl_backport.SpringToLifeMod;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class NeoForgeRegistrationProvider implements IRegistrationProvider {

    private static final DeferredRegister<EntityType<?>> ENTITY_TYPE_REGISTER = DeferredRegister.create(BuiltInRegistries.ENTITY_TYPE, SpringToLifeMod.MOD_ID);

    public void finishRegistration(IEventBus bus) {
        ENTITY_TYPE_REGISTER.register(bus);
    }

    public <T extends Entity> Supplier<EntityType<T>> registerEntityType(String key, EntityType.Builder<T> builder) {
        return ENTITY_TYPE_REGISTER.register(key, () -> builder.build(key));
    }

}
