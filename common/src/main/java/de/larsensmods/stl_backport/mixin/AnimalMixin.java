package de.larsensmods.stl_backport.mixin;

import net.minecraft.world.entity.animal.Animal;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Animal.class)
public abstract class AnimalMixin extends MobMixin {

    @Inject(method = "canMate", at = @At("HEAD"), cancellable = true)
    public void canMate(Animal otherAnimal, CallbackInfoReturnable<Boolean> cir) {
        // NO MODIFICATIONS BY DEFAULT
    }

}
