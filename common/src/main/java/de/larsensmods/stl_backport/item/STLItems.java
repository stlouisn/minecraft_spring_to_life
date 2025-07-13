package de.larsensmods.stl_backport.item;

import de.larsensmods.regutil.IRegistrationProvider;
import de.larsensmods.stl_backport.SpringToLifeMod;
import de.larsensmods.stl_backport.block.STLBlocks;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;

import java.util.function.Supplier;

public class STLItems {

    //BLOCK ITEMS
    public static Supplier<Item> BUSH;
    public static Supplier<Item> FIREFLY_BUSH;

    public static void initItems(IRegistrationProvider registrationProvider) {
        SpringToLifeMod.LOGGER.info("Initializing items");

        BUSH = registerBlockItem(
                registrationProvider,
                "bush",
                STLBlocks.BUSH,
                new Item.Properties()
        );
        FIREFLY_BUSH = registerBlockItem(
                registrationProvider,
                "firefly_bush",
                STLBlocks.FIREFLY_BUSH,
                new Item.Properties()
        );
    }

    private static Supplier<Item> registerBlockItem(IRegistrationProvider provider, String key, Supplier<Block> block, Item.Properties properties) {
        return provider.registerItem(key, () -> new BlockItem(block.get(), properties));
    }

}
