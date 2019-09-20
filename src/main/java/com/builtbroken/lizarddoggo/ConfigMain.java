package com.builtbroken.lizarddoggo;

import java.util.Arrays;
import java.util.List;

import org.apache.commons.lang3.tuple.Pair;

import net.minecraftforge.common.ForgeConfigSpec;

public class ConfigMain
{
    public static final ForgeConfigSpec CONFIG_SPEC;
    public static final ConfigMain CONFIG;

    public final ForgeConfigSpec.ConfigValue<List<? extends String>> breedingItems;
    public final ForgeConfigSpec.ConfigValue<List<? extends String>> tamingItems;

    static {
        Pair<ConfigMain,ForgeConfigSpec> specPair = new ForgeConfigSpec.Builder().configure(ConfigMain::new);
        CONFIG_SPEC = specPair.getRight();
        CONFIG = specPair.getLeft();
    }

    ConfigMain(ForgeConfigSpec.Builder builder)
    {
        breedingItems = builder
                .comment("Items that the lizard doggo can be bred with.\n" +
                        "Examples: minecraft:glowstone_dust, minecraft:stone, ...")
                .define("breeding_items", Arrays.asList("minecraft:glowstone_dust"));
        tamingItems = builder
                .comment("Items that the lizard doggo can be tamed with.\n" +
                        "Examples: minecraft:glowstone_dust, minecraft:stone, ...")
                .define("taming_items", Arrays.asList("minecraft:cod", "minecraft:salmon", "minecraft:tropical_fish"));
    }
}
