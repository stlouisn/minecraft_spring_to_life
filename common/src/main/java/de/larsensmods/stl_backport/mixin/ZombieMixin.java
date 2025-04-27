package de.larsensmods.stl_backport.mixin;

import de.larsensmods.stl_backport.entity.ColdChicken;
import de.larsensmods.stl_backport.entity.STLEntityTypes;
import de.larsensmods.stl_backport.entity.WarmChicken;
import net.minecraft.core.Holder;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntitySelector;
import net.minecraft.world.entity.animal.Chicken;
import net.minecraft.world.entity.monster.Zombie;
import net.minecraft.world.level.biome.Biome;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;

import java.util.List;

@Mixin(Zombie.class)
public class ZombieMixin {

    @ModifyVariable(method = "finalizeSpawn", at =@At("STORE"), ordinal = 0)
    private Chicken injected(Chicken original) {
        Zombie zombie = (Zombie) (Object) this;
        if(original == null) {
            return null;
        }
        List<Chicken> validChickens = zombie.level().getEntitiesOfClass(Chicken.class, zombie.getBoundingBox().inflate(5.0, 3.0, 5.0), EntitySelector.ENTITY_STILL_ALIVE);
        if(validChickens.contains(original)) {
            return original;
        }
        Holder<Biome> biomeHolder = zombie.level().getBiome(zombie.blockPosition());
        if(ColdChicken.isValidBiome(biomeHolder)) {
            Chicken coldChicken = STLEntityTypes.COLD_CHICKEN.get().create(zombie.level());
            original.remove(Entity.RemovalReason.DISCARDED);
            return coldChicken;
        }else if(WarmChicken.isValidBiome(biomeHolder)) {
            Chicken warmChicken = STLEntityTypes.WARM_CHICKEN.get().create(zombie.level());
            original.remove(Entity.RemovalReason.DISCARDED);
            return warmChicken;
        }else{
            return original;
        }
    }

}
