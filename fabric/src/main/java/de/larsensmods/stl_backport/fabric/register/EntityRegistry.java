package de.larsensmods.stl_backport.fabric.register;

import de.larsensmods.stl_backport.SpringToLifeMod;
import de.larsensmods.stl_backport.entity.ColdChicken;
import de.larsensmods.stl_backport.entity.STLEntityTypes;
import de.larsensmods.stl_backport.entity.WarmChicken;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.phys.Vec3;

import java.util.function.Supplier;

public class EntityRegistry {

    public static void initEntityTypes() {
        STLEntityTypes.WARM_CHICKEN = register(
                "warm_chicken",
                EntityType.Builder.of(WarmChicken::new, MobCategory.CREATURE)
                        .sized(0.4F, 0.7F)
                        .eyeHeight(0.644F)
                        .passengerAttachments(new Vec3(0.0, 0.7, -0.1))
                        .clientTrackingRange(10)
        );
        STLEntityTypes.COLD_CHICKEN = register(
                "cold_chicken",
                EntityType.Builder.of(ColdChicken::new, MobCategory.CREATURE)
                        .sized(0.4F, 0.7F)
                        .eyeHeight(0.644F)
                        .passengerAttachments(new Vec3(0.0, 0.7, -0.1))
                        .clientTrackingRange(10)
        );
    }

    private static <T extends Entity > Supplier<EntityType<T>> register(String key, EntityType.Builder<T> builder) {
        EntityType<T> entityType = Registry.register(BuiltInRegistries.ENTITY_TYPE, ResourceLocation.fromNamespaceAndPath(SpringToLifeMod.MOD_ID, key), builder.build(key));
        return new Supplier<>() {
            /**
             * Gets a result.
             *
             * @return a result
             */
            @Override
            public EntityType<T> get() {
                return entityType;
            }
        };
    }

}
