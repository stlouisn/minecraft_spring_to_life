package de.larsensmods.stl_backport.entity;

import de.larsensmods.stl_backport.item.STLItems;
import net.minecraft.core.Holder;
import net.minecraft.resources.ResourceKey;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.AgeableMob;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.ai.goal.BreedGoal;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.animal.Chicken;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.Biomes;
import net.minecraft.world.level.gameevent.GameEvent;
import org.jetbrains.annotations.Nullable;

import java.util.HashSet;
import java.util.Set;

public class WarmChicken extends Chicken {

    private static final Set<ResourceKey<Biome>> WARM_CHICKEN_BIOMES = new HashSet<>();

    public WarmChicken(EntityType<? extends Chicken> entityType, Level level) {
        super(entityType, level);
    }

    @Override
    protected void registerGoals() {
        super.registerGoals();
        this.goalSelector.addGoal(2, new BreedGoal(this, 1.0, ColdChicken.class));
        this.goalSelector.addGoal(2, new BreedGoal(this, 1.0, Chicken.class));
    }

    @Override
    public void aiStep() {
        boolean layEgg = false;
        if (--this.eggTime <= 0 && !this.level().isClientSide && this.isAlive() && !this.isBaby() && !this.isChickenJockey()) {
            layEgg = true;
            this.eggTime = this.random.nextInt(6000) + 6001;
        }else{
            this.eggTime++;
        }
        super.aiStep();
        if(layEgg){
            this.playSound(SoundEvents.CHICKEN_EGG, 1.0F, (this.random.nextFloat() - this.random.nextFloat()) * 0.2F + 1.0F);
            this.spawnAtLocation(STLItems.BROWN_EGG.get());
            this.gameEvent(GameEvent.ENTITY_PLACE);
        }
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
            return STLEntityTypes.WARM_CHICKEN.get().create(level);
        }else if(otherChicken instanceof ColdChicken) {
            return Math.random() < 0.5 ? STLEntityTypes.WARM_CHICKEN.get().create(level) : STLEntityTypes.COLD_CHICKEN.get().create(level);
        }else{
            return Math.random() < 0.5 ? STLEntityTypes.WARM_CHICKEN.get().create(level) : EntityType.CHICKEN.create(level);
        }
    }

    public static boolean isValidBiome(Holder<Biome> biome) {
        for (ResourceKey<Biome> key : WARM_CHICKEN_BIOMES) {
            if (biome.is(key)) {
                return true;
            }
        }
        return false;
    }

    static {
        //NATURAL
        WARM_CHICKEN_BIOMES.add(Biomes.SAVANNA);
        WARM_CHICKEN_BIOMES.add(Biomes.SAVANNA_PLATEAU);
        WARM_CHICKEN_BIOMES.add(Biomes.WINDSWEPT_SAVANNA);
        WARM_CHICKEN_BIOMES.add(Biomes.JUNGLE);
        WARM_CHICKEN_BIOMES.add(Biomes.BAMBOO_JUNGLE);
        WARM_CHICKEN_BIOMES.add(Biomes.SPARSE_JUNGLE);
        WARM_CHICKEN_BIOMES.add(Biomes.BADLANDS);
        WARM_CHICKEN_BIOMES.add(Biomes.ERODED_BADLANDS);
        WARM_CHICKEN_BIOMES.add(Biomes.WOODED_BADLANDS);

        //JOCKEY SPAWNS ONLY
        WARM_CHICKEN_BIOMES.add(Biomes.WARM_OCEAN);
        WARM_CHICKEN_BIOMES.add(Biomes.LUKEWARM_OCEAN);
        WARM_CHICKEN_BIOMES.add(Biomes.DEEP_LUKEWARM_OCEAN);
        WARM_CHICKEN_BIOMES.add(Biomes.MANGROVE_SWAMP);
        WARM_CHICKEN_BIOMES.add(Biomes.DESERT);

        //THE NETHER
        WARM_CHICKEN_BIOMES.add(Biomes.NETHER_WASTES);
        WARM_CHICKEN_BIOMES.add(Biomes.CRIMSON_FOREST);
        WARM_CHICKEN_BIOMES.add(Biomes.WARPED_FOREST);
        WARM_CHICKEN_BIOMES.add(Biomes.SOUL_SAND_VALLEY);
        WARM_CHICKEN_BIOMES.add(Biomes.BASALT_DELTAS);
    }
}
