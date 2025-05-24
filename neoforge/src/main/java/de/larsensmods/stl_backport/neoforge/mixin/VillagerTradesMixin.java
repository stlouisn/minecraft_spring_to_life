package de.larsensmods.stl_backport.neoforge.mixin;

import de.larsensmods.stl_backport.neoforge.event.GameEvents;
import net.minecraft.world.entity.npc.VillagerTrades;
import org.apache.commons.lang3.tuple.Pair;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(VillagerTrades.class)
public class VillagerTradesMixin {

    @Redirect(method = "<clinit>", at = @At(value = "INVOKE", target = "Lorg/apache/commons/lang3/tuple/Pair;of(Ljava/lang/Object;Ljava/lang/Object;)Lorg/apache/commons/lang3/tuple/Pair;", ordinal = 2))
    private static Pair<VillagerTrades.ItemListing[], Integer> experimentalTradeAdders(Object left, Object right){
        VillagerTrades.ItemListing[] castLeft = (VillagerTrades.ItemListing[]) left;
        Integer castRight = (Integer) right;

        VillagerTrades.ItemListing[] newLeft = new VillagerTrades.ItemListing[castLeft.length + 2];
        System.arraycopy(castLeft, 0, newLeft, 0, castLeft.length);
        newLeft[castLeft.length] = GameEvents.DRY_GRASS_TRADE;
        newLeft[castLeft.length + 1] = GameEvents.FIREFLY_BUSH_TRADE;

        return Pair.of(newLeft, castRight);
    }

}
