package de.larsensmods.stl_backport.particles;

import de.larsensmods.regutil.IRegistrationProvider;
import de.larsensmods.stl_backport.SpringToLifeMod;
import net.minecraft.core.particles.SimpleParticleType;

import java.util.function.Supplier;

public class STLParticleTypes {

    public static Supplier<SimpleParticleType> FIREFLY;

    public static void initParticleTypes(IRegistrationProvider provider) {
        SpringToLifeMod.LOGGER.info("Initializing particle types");

        FIREFLY = provider.registerParticleTypeSimple("firefly");
    }

}
