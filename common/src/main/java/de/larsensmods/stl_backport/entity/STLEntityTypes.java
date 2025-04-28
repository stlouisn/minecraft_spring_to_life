package de.larsensmods.stl_backport.entity;

import de.larsensmods.regutil.IRegistrationProvider;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.phys.Vec3;

import java.util.function.Supplier;

public abstract class STLEntityTypes {

    public static Supplier<EntityType<WarmChicken>> WARM_CHICKEN;
    public static Supplier<EntityType<ColdChicken>> COLD_CHICKEN;

    public static Supplier<EntityType<WarmPig>> WARM_PIG;
    public static Supplier<EntityType<ColdPig>> COLD_PIG;

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

        WARM_PIG = registrationProvider.registerEntityType(
                "warm_pig",
                () -> EntityType.Builder.of(WarmPig::new, MobCategory.CREATURE).sized(0.9F, 0.9F).passengerAttachments(0.86875F).clientTrackingRange(10)
        );
        COLD_PIG = registrationProvider.registerEntityType(
                "cold_pig",
                () -> EntityType.Builder.of(ColdPig::new, MobCategory.CREATURE).sized(0.9F, 0.9F).passengerAttachments(0.86875F).clientTrackingRange(10)
        );
    }

}
