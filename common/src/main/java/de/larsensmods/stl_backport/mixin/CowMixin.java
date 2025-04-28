package de.larsensmods.stl_backport.mixin;

import de.larsensmods.stl_backport.entity.*;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.AgeableMob;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.ai.goal.BreedGoal;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.animal.Cow;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Cow.class)
public abstract class CowMixin extends AnimalMixin {

    @Inject(method = "registerGoals", at = @At("TAIL"))
    protected void registerGoals(CallbackInfo ci) {
        Cow c = (Cow) (Object) this;

        this.goalSelector.addGoal(2, new BreedGoal(c, 1.0, ColdCow.class));
        this.goalSelector.addGoal(2, new BreedGoal(c, 1.0, WarmCow.class));
    }

    @Override
    public void canMate(Animal otherAnimal, CallbackInfoReturnable<Boolean> cir) {
        Cow c = (Cow) (Object) this;

        if(otherAnimal == c){
            cir.setReturnValue(false);
        }
        cir.setReturnValue(otherAnimal instanceof Cow && c.isInLove() && otherAnimal.isInLove());
    }

    @Inject(method = "getBreedOffspring(Lnet/minecraft/server/level/ServerLevel;Lnet/minecraft/world/entity/AgeableMob;)Lnet/minecraft/world/entity/animal/Cow;", at = @At("HEAD"), cancellable = true)
    public void getBreedOffspring(ServerLevel level, AgeableMob otherParent, CallbackInfoReturnable<Cow> cir) {
        if(!(otherParent instanceof Cow)) {
            cir.setReturnValue(null);
        }
        Cow otherCow = (Cow) otherParent;
        if(otherCow instanceof WarmCow) {
            cir.setReturnValue(Math.random() < 0.5 ? STLEntityTypes.WARM_COW.get().create(level) : EntityType.COW.create(level));
        }else if(otherCow instanceof ColdCow) {
            cir.setReturnValue(Math.random() < 0.5 ? STLEntityTypes.COLD_COW.get().create(level) : EntityType.COW.create(level));
        }else{
            cir.setReturnValue(EntityType.COW.create(level));
        }
    }

}
