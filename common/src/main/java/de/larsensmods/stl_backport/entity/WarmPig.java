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

public class WarmPig extends Pig {

    private static final Set<ResourceKey<Biome>> WARM_PIG_BIOMES = new HashSet<>();

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

    public static boolean isValidBiome(Holder<Biome> biome) {
        for (ResourceKey<Biome> key : WARM_PIG_BIOMES) {
            if (biome.is(key)) {
                return true;
            }
        }
        return false;
    }

    static {
        //NATURAL
        WARM_PIG_BIOMES.add(Biomes.SAVANNA);
        WARM_PIG_BIOMES.add(Biomes.SAVANNA_PLATEAU);
        WARM_PIG_BIOMES.add(Biomes.WINDSWEPT_SAVANNA);
        WARM_PIG_BIOMES.add(Biomes.JUNGLE);
        WARM_PIG_BIOMES.add(Biomes.BAMBOO_JUNGLE);
        WARM_PIG_BIOMES.add(Biomes.SPARSE_JUNGLE);
        WARM_PIG_BIOMES.add(Biomes.BADLANDS);
        WARM_PIG_BIOMES.add(Biomes.ERODED_BADLANDS);
        WARM_PIG_BIOMES.add(Biomes.WOODED_BADLANDS);

        //JOCKEY SPAWNS ONLY
        WARM_PIG_BIOMES.add(Biomes.WARM_OCEAN);
        WARM_PIG_BIOMES.add(Biomes.LUKEWARM_OCEAN);
        WARM_PIG_BIOMES.add(Biomes.DEEP_LUKEWARM_OCEAN);
        WARM_PIG_BIOMES.add(Biomes.MANGROVE_SWAMP);
        WARM_PIG_BIOMES.add(Biomes.DESERT);

        //THE NETHER
        WARM_PIG_BIOMES.add(Biomes.NETHER_WASTES);
        WARM_PIG_BIOMES.add(Biomes.CRIMSON_FOREST);
        WARM_PIG_BIOMES.add(Biomes.WARPED_FOREST);
        WARM_PIG_BIOMES.add(Biomes.SOUL_SAND_VALLEY);
        WARM_PIG_BIOMES.add(Biomes.BASALT_DELTAS);
    }
}
