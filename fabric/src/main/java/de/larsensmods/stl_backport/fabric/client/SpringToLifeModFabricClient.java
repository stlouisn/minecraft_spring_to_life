package de.larsensmods.stl_backport.fabric.client;

import de.larsensmods.stl_backport.entity.STLEntityTypes;
import de.larsensmods.stl_backport.entity.client.WarmChickenRenderer;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;

public final class SpringToLifeModFabricClient implements ClientModInitializer {

    @Override
    public void onInitializeClient() {
        // This entrypoint is suitable for setting up client-specific logic, such as rendering.
        EntityRendererRegistry.register(STLEntityTypes.WARM_CHICKEN.get(), WarmChickenRenderer::new);
    }
}
