package de.larsensmods.stl_backport.entity.client;

import de.larsensmods.stl_backport.SpringToLifeMod;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.resources.ResourceLocation;

public class STLModelLayers {

    public static final ModelLayerLocation WARM_CHICKEN = new ModelLayerLocation(ResourceLocation.fromNamespaceAndPath(SpringToLifeMod.MOD_ID, "warm_chicken"), "main");
    public static final ModelLayerLocation COLD_CHICKEN = new ModelLayerLocation(ResourceLocation.fromNamespaceAndPath(SpringToLifeMod.MOD_ID, "cold_chicken"), "main");

}
