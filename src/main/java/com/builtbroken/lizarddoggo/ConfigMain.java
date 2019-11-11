package com.builtbroken.lizarddoggo;

import net.minecraftforge.common.config.Config;
import net.minecraftforge.common.config.Config.Comment;
import net.minecraftforge.common.config.Config.Name;

@Config(modid=LizardDoggo.DOMAIN)
public class ConfigMain
{
    @Name("breeding_items")
    @Comment({"Items that the lizard doggo can be bred with.", "Examples: minecraft:glowstone_dust, minecraft:stone, ..."})
    public static String[] breedingItems = {"minecraft:glowstone_dust"};

    @Name("taming_items")
    @Comment({"Items that the lizard doggo can be tamed with.", "Examples: minecraft:glowstone_dust, minecraft:stone, ..."})
    public static String[] tamingItems = {"minecraft:fish"};
}
