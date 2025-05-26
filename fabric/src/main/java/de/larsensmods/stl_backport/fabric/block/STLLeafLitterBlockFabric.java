package de.larsensmods.stl_backport.fabric.block;

import de.larsensmods.stl_backport.audio.STLSoundTypes;
import de.larsensmods.stl_backport.block.STLLeafLitterBlock;

public class STLLeafLitterBlockFabric extends STLLeafLitterBlock {

    public STLLeafLitterBlockFabric(Properties properties) {
        super(properties.sound(STLSoundTypes.LEAF_LITTER));
    }
}
