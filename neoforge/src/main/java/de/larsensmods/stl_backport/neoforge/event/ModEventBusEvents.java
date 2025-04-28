package de.larsensmods.stl_backport.neoforge.event;

import de.larsensmods.stl_backport.SpringToLifeMod;
import de.larsensmods.stl_backport.entity.*;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.EntityAttributeCreationEvent;

@EventBusSubscriber(modid = SpringToLifeMod.MOD_ID, bus = EventBusSubscriber.Bus.MOD)
public class ModEventBusEvents {

    @SubscribeEvent
    public static void registerAttributes(EntityAttributeCreationEvent event) {
        event.put(STLEntityTypes.WARM_CHICKEN.get(), WarmChicken.createAttributes().build());
        event.put(STLEntityTypes.COLD_CHICKEN.get(), ColdChicken.createAttributes().build());
        event.put(STLEntityTypes.WARM_PIG.get(), WarmPig.createAttributes().build());
        event.put(STLEntityTypes.COLD_PIG.get(), ColdPig.createAttributes().build());
    }

}
