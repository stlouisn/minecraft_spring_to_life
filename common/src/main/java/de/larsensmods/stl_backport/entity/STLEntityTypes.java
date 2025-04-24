package de.larsensmods.stl_backport.entity;

import de.larsensmods.regutil.IRegistrationProvider;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.phys.Vec3;

import java.util.function.Supplier;

public abstract class STLEntityTypes {

    public static Supplier<EntityType<WarmChicken>> WARM_CHICKEN;
    public static Supplier<EntityType<ColdChicken>> COLD_CHICKEN;

    public static void initEntityTypes(IRegistrationProvider registrationProvider){
        WARM_CHICKEN = registrationProvider.registerEntityType(
                "warm_chicken",
                () -> EntityType.Builder.of(WarmChicken::new, MobCategory.CREATURE)
                        .sized(0.4F, 0.7F)
                        .eyeHeight(0.644F)
                        .passengerAttachments(new Vec3(0.0, 0.7, -0.1))
                        .clientTrackingRange(10)
        );
        COLD_CHICKEN = registrationProvider.registerEntityType(
                "cold_chicken",
                () -> EntityType.Builder.of(ColdChicken::new, MobCategory.CREATURE)
                        .sized(0.4F, 0.7F)
                        .eyeHeight(0.644F)
                        .passengerAttachments(new Vec3(0.0, 0.7, -0.1))
                        .clientTrackingRange(10)
        );
    }

}
