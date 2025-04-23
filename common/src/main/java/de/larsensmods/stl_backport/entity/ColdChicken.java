package de.larsensmods.stl_backport.entity;

import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.animal.Chicken;
import net.minecraft.world.level.Level;

public class ColdChicken extends Chicken {
    public ColdChicken(EntityType<? extends Chicken> entityType, Level level) {
        super(entityType, level);
    }
}
