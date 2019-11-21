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
    public final ForgeConfigSpec.ConfigValue<List<List<? extends String>>> biomeTypes;
    public final ForgeConfigSpec.ConfigValue<List<? extends String>> biomeExclusions;
    public final ForgeConfigSpec.ConfigValue<Integer> spawnWeight;
    public final ForgeConfigSpec.ConfigValue<Integer> spawnMin;
    public final ForgeConfigSpec.ConfigValue<Integer> spawnMax;

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
        biomeTypes = builder
                .comment("Biome types that the lizard doggo can be spawned in.\n" +
                         "Examples: [[\"hot\", \"dry\"], [\"jungle\"], [\"beach\"], [\"savanna\"], ...")
                .define("biome_types", Arrays.asList(Arrays.asList("HOT", "DRY"), Arrays.asList("JUNGLE"), Arrays.asList("BEACH"), Arrays.asList("SAVANNA")));
        biomeExclusions = builder
                .comment("Biome types that the lizard doggo CAN'T be spawned in.\n" +
                         "Examples: [\"nether\", \"end\", \"cold\"] ...")
                .define("biome_exclusions", Arrays.asList("NETHER", "END", "COLD"));
        spawnWeight = builder
                .comment("The weight that lizard doggo spawns at.\n" +
                         "Example: default 2")
                .define("spawn_weight", 2);
        spawnMin = builder
                .comment("The minimum number of lizard doggos that can spawn.\n" +
                         "Example: default 1")
                .define("spawn_min", 1);
        spawnMax = builder
                .comment("The maximum number of lizard doggos that can spawn (i.e., a pack)\n" +
                         "Example: default 1")
                .define("spawn_max", 1);
    }
}
