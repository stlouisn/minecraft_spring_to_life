package de.larsensmods.stl_backport.entity.client;

import de.larsensmods.stl_backport.entity.ColdCow;
import net.minecraft.client.model.CowModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;

public class ColdCowModel extends CowModel<ColdCow> {

    public ColdCowModel(ModelPart root) {
        super(root);
    }

    public static LayerDefinition createBodyLayer() {
        MeshDefinition meshDefinition = new MeshDefinition();
        PartDefinition partDefinition = meshDefinition.getRoot();

        PartDefinition headPart = partDefinition
                .addOrReplaceChild(
                        "head",
                        CubeListBuilder.create()
                                .texOffs(0, 0)
                                .addBox(-4.0F, -4.0F, -6.0F, 8.0F, 8.0F, 6.0F)
                                .texOffs(9, 33)
                                .addBox(-3.0F, 1.0F, -7.0F, 6.0F, 3.0F, 1.0F),
                        PartPose.offset(0.0F, 4.0F, -8.0F)
                );
        headPart.addOrReplaceChild(
                "right_horn",
                CubeListBuilder.create().texOffs(0, 40).addBox(-1.5F, -4.5F, -0.5F, 2.0F, 6.0F, 2.0F),
                PartPose.offsetAndRotation(-4.5F, -2.5F, -3.5F, 1.5708F, 0.0F, 0.0F)
        );
        headPart.addOrReplaceChild(
                "left_horn",
                CubeListBuilder.create().texOffs(0, 32).addBox(-1.5F, -3.0F, -0.5F, 2.0F, 6.0F, 2.0F),
                PartPose.offsetAndRotation(5.5F, -2.5F, -5.0F, 1.5708F, 0.0F, 0.0F)
        );

        partDefinition.addOrReplaceChild(
                "body",
                CubeListBuilder.create()
                        .texOffs(20, 32)
                        .addBox(-6.0F, -10.0F, -7.0F, 12.0F, 18.0F, 10.0F, new CubeDeformation(0.5F))
                        .texOffs(18, 4)
                        .addBox(-6.0F, -10.0F, -7.0F, 12.0F, 18.0F, 10.0F)
                        .texOffs(52, 0)
                        .addBox(-2.0F, 2.0F, -8.0F, 4.0F, 6.0F, 1.0F),
                PartPose.offsetAndRotation(0.0F, 5.0F, 2.0F, (float) (Math.PI / 2), 0.0F, 0.0F)
        );

        CubeListBuilder var2 = CubeListBuilder.create().mirror().texOffs(0, 16).addBox(-2.0F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F);
        CubeListBuilder var3 = CubeListBuilder.create().texOffs(0, 16).addBox(-2.0F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F);
        partDefinition.addOrReplaceChild("right_hind_leg", var3, PartPose.offset(-4.0F, 12.0F, 7.0F));
        partDefinition.addOrReplaceChild("left_hind_leg", var2, PartPose.offset(4.0F, 12.0F, 7.0F));
        partDefinition.addOrReplaceChild("right_front_leg", var3, PartPose.offset(-4.0F, 12.0F, -5.0F));
        partDefinition.addOrReplaceChild("left_front_leg", var2, PartPose.offset(4.0F, 12.0F, -5.0F));

        return LayerDefinition.create(meshDefinition, 64, 64);
    }
}
