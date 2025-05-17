package de.larsensmods.stl_backport.neoforge.event;

import de.larsensmods.stl_backport.SpringToLifeMod;
import de.larsensmods.stl_backport.block.STLBlocks;
import net.minecraft.world.entity.npc.VillagerTrades;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.trading.ItemCost;
import net.minecraft.world.item.trading.MerchantOffer;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.village.WandererTradesEvent;

@EventBusSubscriber(modid = SpringToLifeMod.MOD_ID, bus = EventBusSubscriber.Bus.GAME)
public class GameEvents {

    public static final VillagerTrades.ItemListing DRY_GRASS_TRADE = (trader, random) -> new MerchantOffer(new ItemCost(Items.EMERALD, 1), new ItemStack(STLBlocks.TALL_DRY_GRASS.get()), 12, 0, 0);

    @SubscribeEvent
    public static void addWandererTrades(WandererTradesEvent event) {
        event.getGenericTrades().add(DRY_GRASS_TRADE);
    }

}
