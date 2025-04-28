package de.larsensmods.stl_backport.entity;

import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.AgeableMob;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.ai.goal.BreedGoal;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.animal.Pig;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;

public class WarmPig extends Pig {

    public WarmPig(EntityType<? extends Pig> entityType, Level level) {
        super(entityType, level);
    }

    @Override
    protected void registerGoals() {
        super.registerGoals();
        this.goalSelector.addGoal(2, new BreedGoal(this, 1.0, ColdPig.class));
        this.goalSelector.addGoal(2, new BreedGoal(this, 1.0, Pig.class));
    }

    @Override
    public boolean canMate(Animal otherAnimal) {
        if(otherAnimal == this){
            return false;
        }
        return otherAnimal instanceof Pig && this.isInLove() && otherAnimal.isInLove();
    }

    @Override
    public @Nullable Pig getBreedOffspring(ServerLevel level, AgeableMob otherParent) {
        if(!(otherParent instanceof Pig otherPig)) {
            return null;
        }
        if(otherPig instanceof WarmPig) {
            return STLEntityTypes.WARM_PIG.get().create(level);
        }else if(otherPig instanceof ColdPig) {
            return Math.random() < 0.5 ? STLEntityTypes.WARM_PIG.get().create(level) : STLEntityTypes.COLD_PIG.get().create(level);
        }else{
            return Math.random() < 0.5 ? STLEntityTypes.WARM_PIG.get().create(level) : EntityType.PIG.create(level);
        }
    }
}
