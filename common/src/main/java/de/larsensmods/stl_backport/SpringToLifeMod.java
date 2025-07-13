package de.larsensmods.stl_backport;

import de.larsensmods.regutil.IRegistrationProvider;
import de.larsensmods.stl_backport.audio.STLSoundEvents;
import de.larsensmods.stl_backport.block.STLBlocks;
import de.larsensmods.stl_backport.item.STLCreativeTabs;
import de.larsensmods.stl_backport.item.STLItems;
import de.larsensmods.stl_backport.particles.STLParticleTypes;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public final class SpringToLifeMod {

    public static final String MOD_ID = "spring_to_life";

    public static final Logger LOGGER = LoggerFactory.getLogger(SpringToLifeMod.class);

    public static void init(IRegistrationProvider regProvider) {

        STLSoundEvents.initSounds(regProvider);

        STLBlocks.initBlocks(regProvider);
        STLItems.initItems(regProvider);
        STLCreativeTabs.initCreativeTabs(regProvider);

        STLParticleTypes.initParticleTypes(regProvider);

    }
}
