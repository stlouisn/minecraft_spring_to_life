package de.larsensmods.stl_backport.worldgen.decorators;

import de.larsensmods.regutil.IRegistrationProvider;
import net.minecraft.world.level.levelgen.feature.treedecorators.TreeDecoratorType;

import java.util.function.Supplier;

public class STLDecoratorTypes {

    public static Supplier<TreeDecoratorType<AttachedToLogDecorator>> ATTACHED_TO_LOG;

    public static void initDecoratorTypes(IRegistrationProvider provider) {
        ATTACHED_TO_LOG = provider.registerTreeDecoratorType("attached_to_log", AttachedToLogDecorator.CODEC);
    }

}
