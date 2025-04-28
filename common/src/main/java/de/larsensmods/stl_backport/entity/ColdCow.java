package de.larsensmods.stl_backport.entity;

import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.AgeableMob;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.ai.goal.BreedGoal;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.animal.Cow;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;

public class ColdCow extends Cow {

    public ColdCow(EntityType<? extends Cow> entityType, Level level) {
        super(entityType, level);
    }

    @Override
    protected void registerGoals() {
        super.registerGoals();
        this.goalSelector.addGoal(2, new BreedGoal(this, 1.0, WarmCow.class));
        this.goalSelector.addGoal(2, new BreedGoal(this, 1.0, Cow.class));
    }

    @Override
    public boolean canMate(Animal otherAnimal) {
        if(otherAnimal == this){
            return false;
        }
        return otherAnimal instanceof Cow && this.isInLove() && otherAnimal.isInLove();
    }

    @Override
    public @Nullable Cow getBreedOffspring(ServerLevel level, AgeableMob otherParent) {
        if(!(otherParent instanceof Cow otherCow)) {
            return null;
        }
        if(otherCow instanceof WarmCow) {
            return Math.random() < 0.5 ? STLEntityTypes.COLD_COW.get().create(level) : STLEntityTypes.WARM_COW.get().create(level);
        }else if(otherCow instanceof ColdCow) {
            return STLEntityTypes.COLD_COW.get().create(level);
        }else{
            return Math.random() < 0.5 ? STLEntityTypes.COLD_COW.get().create(level) : EntityType.COW.create(level);
        }
    }
}
