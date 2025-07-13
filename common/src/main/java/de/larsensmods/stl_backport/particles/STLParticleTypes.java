package de.larsensmods.stl_backport.particles;

import de.larsensmods.regutil.IRegistrationProvider;
import net.minecraft.core.particles.SimpleParticleType;

import java.util.function.Supplier;

public class STLParticleTypes {

    public static Supplier<SimpleParticleType> FIREFLY;

    public static void initParticleTypes(IRegistrationProvider provider) {
        FIREFLY = provider.registerParticleTypeSimple("firefly");
    }

}
