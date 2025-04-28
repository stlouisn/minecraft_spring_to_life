package de.larsensmods.stl_backport.entity;

import net.minecraft.core.Holder;
import net.minecraft.resources.ResourceKey;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.AgeableMob;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.ai.goal.BreedGoal;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.animal.Pig;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.Biomes;
import org.jetbrains.annotations.Nullable;

import java.util.HashSet;
import java.util.Set;

public class ColdPig extends Pig {

    private static final Set<ResourceKey<Biome>> COLD_PIG_BIOMES = new HashSet<>();
    
    public ColdPig(EntityType<? extends Pig> entityType, Level level) {
        super(entityType, level);
    }

    @Override
    protected void registerGoals() {
        super.registerGoals();
        this.goalSelector.addGoal(2, new BreedGoal(this, 1.0, WarmPig.class));
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
            return Math.random() < 0.5 ? STLEntityTypes.COLD_PIG.get().create(level) : STLEntityTypes.WARM_PIG.get().create(level);
        }else if(otherPig instanceof ColdPig) {
            return STLEntityTypes.COLD_PIG.get().create(level);
        }else{
            return Math.random() < 0.5 ? STLEntityTypes.COLD_PIG.get().create(level) : EntityType.PIG.create(level);
        }
    }

    public static boolean isValidBiome(Holder<Biome> biome) {
        for (ResourceKey<Biome> key : COLD_PIG_BIOMES) {
            if (biome.is(key)) {
                return true;
            }
        }
        return false;
    }

    static {
        //NATURAL
        COLD_PIG_BIOMES.add(Biomes.TAIGA);
        COLD_PIG_BIOMES.add(Biomes.SNOWY_TAIGA);
        COLD_PIG_BIOMES.add(Biomes.OLD_GROWTH_PINE_TAIGA);
        COLD_PIG_BIOMES.add(Biomes.OLD_GROWTH_SPRUCE_TAIGA);
        COLD_PIG_BIOMES.add(Biomes.WINDSWEPT_HILLS);
        COLD_PIG_BIOMES.add(Biomes.WINDSWEPT_GRAVELLY_HILLS);
        COLD_PIG_BIOMES.add(Biomes.WINDSWEPT_FOREST);

        //JOCKEY SPAWNS ONLY
        COLD_PIG_BIOMES.add(Biomes.SNOWY_PLAINS);
        COLD_PIG_BIOMES.add(Biomes.ICE_SPIKES);
        COLD_PIG_BIOMES.add(Biomes.FROZEN_PEAKS);
        COLD_PIG_BIOMES.add(Biomes.JAGGED_PEAKS);
        COLD_PIG_BIOMES.add(Biomes.STONY_PEAKS);
        COLD_PIG_BIOMES.add(Biomes.SNOWY_SLOPES);
        COLD_PIG_BIOMES.add(Biomes.FROZEN_OCEAN);
        COLD_PIG_BIOMES.add(Biomes.DEEP_FROZEN_OCEAN);
        COLD_PIG_BIOMES.add(Biomes.COLD_OCEAN);
        COLD_PIG_BIOMES.add(Biomes.DEEP_COLD_OCEAN);
        COLD_PIG_BIOMES.add(Biomes.GROVE);
        COLD_PIG_BIOMES.add(Biomes.DEEP_DARK);
        COLD_PIG_BIOMES.add(Biomes.FROZEN_RIVER);
        COLD_PIG_BIOMES.add(Biomes.SNOWY_BEACH);

        //THE END
        COLD_PIG_BIOMES.add(Biomes.THE_END);
        COLD_PIG_BIOMES.add(Biomes.END_HIGHLANDS);
        COLD_PIG_BIOMES.add(Biomes.END_MIDLANDS);
        COLD_PIG_BIOMES.add(Biomes.SMALL_END_ISLANDS);
        COLD_PIG_BIOMES.add(Biomes.END_BARRENS);
    }
}
