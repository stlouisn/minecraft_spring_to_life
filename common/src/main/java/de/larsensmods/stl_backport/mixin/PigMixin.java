package de.larsensmods.stl_backport.mixin;

import de.larsensmods.stl_backport.entity.*;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.AgeableMob;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.ai.goal.BreedGoal;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.animal.Pig;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Pig.class)
public abstract class PigMixin extends AnimalMixin {

    @Inject(method = "registerGoals", at = @At("TAIL"))
    protected void registerGoals(CallbackInfo ci) {
        Pig p = (Pig) (Object) this;

        this.goalSelector.addGoal(2, new BreedGoal(p, 1.0, ColdPig.class));
        this.goalSelector.addGoal(2, new BreedGoal(p, 1.0, WarmPig.class));
    }

    @Override
    public void canMate(Animal otherAnimal, CallbackInfoReturnable<Boolean> cir) {
        Pig p = (Pig) (Object) this;

        if(otherAnimal == p){
            cir.setReturnValue(false);
        }
        cir.setReturnValue(otherAnimal instanceof Pig && p.isInLove() && otherAnimal.isInLove());
    }

    @Inject(method = "getBreedOffspring(Lnet/minecraft/server/level/ServerLevel;Lnet/minecraft/world/entity/AgeableMob;)Lnet/minecraft/world/entity/animal/Pig;", at = @At("HEAD"), cancellable = true)
    public void getBreedOffspring(ServerLevel level, AgeableMob otherParent, CallbackInfoReturnable<Pig> cir) {
        if(!(otherParent instanceof Pig)) {
            cir.setReturnValue(null);
        }
        Pig otherPig = (Pig) otherParent;
        if(otherPig instanceof WarmPig) {
            cir.setReturnValue(Math.random() < 0.5 ? STLEntityTypes.WARM_PIG.get().create(level) : EntityType.PIG.create(level));
        }else if(otherPig instanceof ColdPig) {
            cir.setReturnValue(Math.random() < 0.5 ? STLEntityTypes.COLD_PIG.get().create(level) : EntityType.PIG.create(level));
        }else{
            cir.setReturnValue(EntityType.PIG.create(level));
        }
    }

}
