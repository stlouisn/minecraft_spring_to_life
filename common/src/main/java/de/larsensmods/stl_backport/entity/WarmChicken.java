package de.larsensmods.stl_backport.entity;

import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.animal.Chicken;
import net.minecraft.world.level.Level;

public class WarmChicken extends Chicken {
    public WarmChicken(EntityType<? extends Chicken> entityType, Level level) {
        super(entityType, level);
    }
}
