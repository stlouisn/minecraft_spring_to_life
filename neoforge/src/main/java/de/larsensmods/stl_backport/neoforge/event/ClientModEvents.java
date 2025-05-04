package de.larsensmods.stl_backport.neoforge.event;

import de.larsensmods.stl_backport.SpringToLifeMod;
import de.larsensmods.stl_backport.block.STLBlocks;
import de.larsensmods.stl_backport.entity.STLEntityTypes;
import de.larsensmods.stl_backport.entity.client.*;
import de.larsensmods.stl_backport.item.STLItems;
import net.minecraft.client.model.geom.builders.CubeDeformation;
import net.minecraft.client.renderer.BiomeColors;
import net.minecraft.client.renderer.entity.EntityRenderers;
import net.minecraft.world.level.GrassColor;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;
import net.neoforged.neoforge.client.event.EntityRenderersEvent;
import net.neoforged.neoforge.client.event.RegisterColorHandlersEvent;

@EventBusSubscriber(modid = SpringToLifeMod.MOD_ID, bus = EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class ClientModEvents {

    @SubscribeEvent
    public static void onClientSetup(FMLClientSetupEvent event) {
        EntityRenderers.register(STLEntityTypes.WARM_CHICKEN.get(), WarmChickenRenderer::new);
        EntityRenderers.register(STLEntityTypes.COLD_CHICKEN.get(), ColdChickenRenderer::new);
        EntityRenderers.register(STLEntityTypes.WARM_PIG.get(), WarmPigRenderer::new);
        EntityRenderers.register(STLEntityTypes.COLD_PIG.get(), ColdPigRenderer::new);
        EntityRenderers.register(STLEntityTypes.WARM_COW.get(), WarmCowRenderer::new);
        EntityRenderers.register(STLEntityTypes.COLD_COW.get(), ColdCowRenderer::new);
    }

    @SubscribeEvent
    public static void registerLayerDefinitions(EntityRenderersEvent.RegisterLayerDefinitions event) {
        event.registerLayerDefinition(STLModelLayers.WARM_CHICKEN, WarmChickenModel::createBodyLayer);
        event.registerLayerDefinition(STLModelLayers.COLD_CHICKEN, ColdChickenModel::createBodyLayer);
        event.registerLayerDefinition(STLModelLayers.WARM_PIG, () -> WarmPigModel.createBodyLayer(CubeDeformation.NONE));
        event.registerLayerDefinition(STLModelLayers.COLD_PIG, ColdPigModel::createBodyLayer);
        event.registerLayerDefinition(STLModelLayers.WARM_COW, WarmCowModel::createBodyLayer);
        event.registerLayerDefinition(STLModelLayers.COLD_COW, ColdCowModel::createBodyLayer);
    }

    @SubscribeEvent
    public static void registerBlockColorHandlers(RegisterColorHandlersEvent.Block event) {
        event.register((state, level, pos, tintIndex) -> level != null && pos != null
                ? BiomeColors.getAverageGrassColor(level, pos)
                : GrassColor.getDefaultColor(),

                STLBlocks.BUSH.get());
    }

    @SubscribeEvent
    public static void registerItemColorHandlers(RegisterColorHandlersEvent.Item event) {
        event.register((stack, tintIndex) -> GrassColor.getDefaultColor(),

                STLItems.BUSH.get());
    }

}
