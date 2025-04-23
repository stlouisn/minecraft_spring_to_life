package de.larsensmods.stl_backport.entity;

import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.AgeableMob;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.ai.goal.BreedGoal;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.animal.Chicken;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;

public class ColdChicken extends Chicken {
    public ColdChicken(EntityType<? extends Chicken> entityType, Level level) {
        super(entityType, level);
    }

    @Override
    protected void registerGoals() {
        super.registerGoals();
        this.goalSelector.addGoal(2, new BreedGoal(this, 1.0, WarmChicken.class));
        this.goalSelector.addGoal(2, new BreedGoal(this, 1.0, Chicken.class));
    }

    @Override
    public boolean canMate(Animal otherAnimal) {
        if(otherAnimal == this){
            return false;
        }
        return otherAnimal instanceof Chicken && this.isInLove() && otherAnimal.isInLove();
    }

    @Override
    public @Nullable Chicken getBreedOffspring(ServerLevel level, AgeableMob otherParent) {
        if(!(otherParent instanceof Chicken otherChicken)) {
            return null;
        }
        if(otherChicken instanceof WarmChicken) {
            return Math.random() < 0.5 ? STLEntityTypes.COLD_CHICKEN.get().create(level) : STLEntityTypes.WARM_CHICKEN.get().create(level);
        }else if(otherChicken instanceof ColdChicken) {
            return STLEntityTypes.COLD_CHICKEN.get().create(level);
        }else{
            return Math.random() < 0.5 ? STLEntityTypes.COLD_CHICKEN.get().create(level) : EntityType.CHICKEN.create(level);
        }
    }
}
