package de.larsensmods.stl_backport.mixin;

import de.larsensmods.stl_backport.entity.ColdChicken;
import de.larsensmods.stl_backport.entity.STLEntityTypes;
import de.larsensmods.stl_backport.entity.WarmChicken;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.AgeableMob;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.ai.goal.BreedGoal;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.animal.Chicken;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Chicken.class)
public abstract class ChickenMixin extends AnimalMixin {

    @Inject(method = "registerGoals", at = @At("TAIL"))
    protected void registerGoals(CallbackInfo ci) {
        Chicken c = (Chicken) (Object) this;

        this.goalSelector.addGoal(2, new BreedGoal(c, 1.0, ColdChicken.class));
        this.goalSelector.addGoal(2, new BreedGoal(c, 1.0, WarmChicken.class));
    }

    @Override
    public void canMate(Animal otherAnimal, CallbackInfoReturnable<Boolean> cir) {
        Chicken c = (Chicken) (Object) this;

        if(otherAnimal == c){
            cir.setReturnValue(false);
        }
        cir.setReturnValue(otherAnimal instanceof Chicken && c.isInLove() && otherAnimal.isInLove());
    }

    @Inject(method = "getBreedOffspring(Lnet/minecraft/server/level/ServerLevel;Lnet/minecraft/world/entity/AgeableMob;)Lnet/minecraft/world/entity/animal/Chicken;", at = @At("HEAD"), cancellable = true)
    public void getBreedOffspring(ServerLevel level, AgeableMob otherParent, CallbackInfoReturnable<Chicken> cir) {
        if(!(otherParent instanceof Chicken)) {
            cir.setReturnValue(null);
        }
        Chicken otherChicken = (Chicken) otherParent;
        if(otherChicken instanceof WarmChicken) {
            cir.setReturnValue(Math.random() < 0.5 ? STLEntityTypes.WARM_CHICKEN.get().create(level) : EntityType.CHICKEN.create(level));
        }else if(otherChicken instanceof ColdChicken) {
            cir.setReturnValue(Math.random() < 0.5 ? STLEntityTypes.COLD_CHICKEN.get().create(level) : EntityType.CHICKEN.create(level));
        }else{
            cir.setReturnValue(EntityType.CHICKEN.create(level));
        }
    }

}
