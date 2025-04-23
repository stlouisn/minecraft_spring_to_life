package de.larsensmods.stl_backport.fabric.client;

import de.larsensmods.stl_backport.entity.STLEntityTypes;
import de.larsensmods.stl_backport.entity.client.*;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.rendering.v1.EntityModelLayerRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;

public final class SpringToLifeModFabricClient implements ClientModInitializer {

    @Override
    public void onInitializeClient() {
        // This entrypoint is suitable for setting up client-specific logic, such as rendering.
        EntityRendererRegistry.register(STLEntityTypes.WARM_CHICKEN.get(), WarmChickenRenderer::new);
        EntityRendererRegistry.register(STLEntityTypes.COLD_CHICKEN.get(), ColdChickenRenderer::new);

        EntityModelLayerRegistry.registerModelLayer(STLModelLayers.WARM_CHICKEN, WarmChickenModel::createBodyLayer);
        EntityModelLayerRegistry.registerModelLayer(STLModelLayers.COLD_CHICKEN, ColdChickenModel::createBodyLayer);
    }
}
