package de.larsensmods.stl_backport.item;

import de.larsensmods.regutil.IRegistrationProvider;
import de.larsensmods.stl_backport.entity.STLEntityTypes;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.SpawnEggItem;

import java.util.function.Supplier;

public class STLItems {

    public static Supplier<Item> WARM_CHICKEN_SPAWN_EGG;
    public static Supplier<Item> COLD_CHICKEN_SPAWN_EGG;

    public static void initItems(IRegistrationProvider registrationProvider){
        WARM_CHICKEN_SPAWN_EGG = registrationProvider.registerItem(
                "warm_chicken_spawn_egg",
                () -> new SpawnEggItem(STLEntityTypes.WARM_CHICKEN.get(), 0xFFAA00, 0xE5B54E, new Item.Properties())
        );
        COLD_CHICKEN_SPAWN_EGG = registrationProvider.registerItem(
                "cold_chicken_spawn_egg",
                () -> new SpawnEggItem(STLEntityTypes.COLD_CHICKEN.get(), 0xADACAC, 0x696969, new Item.Properties())
        );
    }

}
