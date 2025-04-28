package de.larsensmods.stl_backport.mixin;

import de.larsensmods.stl_backport.entity.STLEntityTypes;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.level.levelgen.Heightmap;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(SpawnPlacements.class)
public class SpawnPlacementsMixin {

    @Shadow
    private static <T extends Mob> void register(EntityType<T> param0, SpawnPlacementType param1, Heightmap.Types param2, SpawnPlacements.SpawnPredicate<T> param3){}

    @Inject(method = "<clinit>", at = @At("TAIL"))
    private static void onClinit(CallbackInfo ci) {
        register(STLEntityTypes.COLD_CHICKEN.get(), SpawnPlacementTypes.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, Animal::checkAnimalSpawnRules);
        register(STLEntityTypes.WARM_CHICKEN.get(), SpawnPlacementTypes.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, Animal::checkAnimalSpawnRules);
        register(STLEntityTypes.COLD_PIG.get(), SpawnPlacementTypes.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, Animal::checkAnimalSpawnRules);
        register(STLEntityTypes.WARM_PIG.get(), SpawnPlacementTypes.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, Animal::checkAnimalSpawnRules);
        register(STLEntityTypes.COLD_COW.get(), SpawnPlacementTypes.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, Animal::checkAnimalSpawnRules);
        register(STLEntityTypes.WARM_COW.get(), SpawnPlacementTypes.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, Animal::checkAnimalSpawnRules);
    }

}
