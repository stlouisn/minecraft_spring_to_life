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

import java.util.Set;

@EventBusSubscriber(modid = SpringToLifeMod.MOD_ID, bus = EventBusSubscriber.Bus.GAME)
public class GameEvents {

    public static final VillagerTrades.ItemListing DRY_GRASS_TRADE = (trader, random) -> new MerchantOffer(new ItemCost(Items.EMERALD, 1), new ItemStack(STLBlocks.TALL_DRY_GRASS.get()), 12, 0, 0);
    public static final VillagerTrades.ItemListing WILDFLOWERS_TRADE = (trader, random) -> new MerchantOffer(new ItemCost(Items.EMERALD, 1), new ItemStack(STLBlocks.WILDFLOWERS.get()), 12, 0, 0);
    public static final  VillagerTrades.ItemListing FIREFLY_BUSH_TRADE = (trader, random) -> new MerchantOffer(new ItemCost(Items.EMERALD, 3), new ItemStack(STLBlocks.FIREFLY_BUSH.get()), 12, 0, 0);

    @SubscribeEvent
    public static void addWandererTrades(WandererTradesEvent event) {
        event.getGenericTrades().addAll(Set.of(DRY_GRASS_TRADE, WILDFLOWERS_TRADE));
        event.getRareTrades().add(FIREFLY_BUSH_TRADE);
    }

}
