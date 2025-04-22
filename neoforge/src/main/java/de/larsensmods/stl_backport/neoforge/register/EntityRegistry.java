package de.larsensmods.stl_backport.neoforge.register;

import de.larsensmods.stl_backport.SpringToLifeMod;
import de.larsensmods.stl_backport.entity.STLEntityTypes;
import de.larsensmods.stl_backport.entity.WarmChicken;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.phys.Vec3;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class EntityRegistry {

    public static final DeferredRegister<EntityType<?>> ENTITY_TYPE_REGISTER = DeferredRegister.create(BuiltInRegistries.ENTITY_TYPE, SpringToLifeMod.MOD_ID);

    public static void initEntityTypes(IEventBus bus) {
        STLEntityTypes.WARM_CHICKEN = register(
                "warm_chicken",
                EntityType.Builder.of(WarmChicken::new, MobCategory.CREATURE)
                        .sized(0.4F, 0.7F)
                        .eyeHeight(0.644F)
                        .passengerAttachments(new Vec3(0.0, 0.7, -0.1))
                        .clientTrackingRange(10)
        );

        ENTITY_TYPE_REGISTER.register(bus);
    }

    private static <T extends Entity> Supplier<EntityType<T>> register(String key, EntityType.Builder<T> builder) {
        return ENTITY_TYPE_REGISTER.register(key, () -> builder.build(key));
    }

}
