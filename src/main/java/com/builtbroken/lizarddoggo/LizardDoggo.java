package com.builtbroken.lizarddoggo;

import java.awt.Color;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

import com.builtbroken.lizarddoggo.entity.EntityLizard;

import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.EntitySpawnPlacementRegistry;
import net.minecraft.entity.EntityType;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.SpawnEggItem;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.Heightmap;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;
import net.minecraftforge.fml.config.ModConfig;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Created by Dark(DarkGuardsman, Robert) on 1/12/2019.
 */
@Mod(LizardDoggo.DOMAIN)
@Mod.EventBusSubscriber(bus=Bus.MOD)
public class LizardDoggo
{
    public static final String MAJOR_VERSION = "@MAJOR@";
    public static final String MINOR_VERSION = "@MINOR@";
    public static final String REVISION_VERSION = "@REVIS@";
    public static final String BUILD_VERSION = "@BUILD@";
    public static final String MC_VERSION = "@MC@";
    public static final String VERSION = MC_VERSION + "-" + MAJOR_VERSION + "." + MINOR_VERSION + "." + REVISION_VERSION + "." + BUILD_VERSION;

    public static final Logger LOGGER = LogManager.getLogger();

    public static final String DOMAIN = "sbmlizarddoggo";
    public static final EntityType<EntityLizard> LIZARD_ENTITY_TYPE = EntityType.Builder.<EntityLizard>create(EntityLizard::new, EntityClassification.CREATURE)
            .setTrackingRange(256)
            .setUpdateInterval(1)
            .setShouldReceiveVelocityUpdates(true)
            .size(0.5F, 0.4F).build(DOMAIN + ":lizard");
    public static Item lizardSpawnEgg;

    public LizardDoggo()
    {
        ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, ConfigMain.CONFIG_SPEC);
    }

    private static Map<String, BiomeDictionary.Type> biomeMap = new HashMap<>();

    private static BiomeDictionary.Type getType (String type) {
        BiomeDictionary.Type result = biomeMap.get(type);
        if (result == null) {
            result = BiomeDictionary.Type.getType(type);
            biomeMap.put(type, result);
        }
        return result;
    }

    @SubscribeEvent
    public static void registerEntityTypes(RegistryEvent.Register<EntityType<?>> event)
    {
        event.getRegistry().register(LIZARD_ENTITY_TYPE.setRegistryName(new ResourceLocation(DOMAIN, "lizard")));

        List<List<? extends String>> biomeTypes = ConfigMain.CONFIG.biomeTypes.get();
        Set<BiomeDictionary.Type> exclusions = ConfigMain.CONFIG.biomeExclusions.get().stream().map(LizardDoggo::getType).collect(Collectors.toSet());
        Set<Biome> biomes = new HashSet<>();
        for (List<? extends String> typeList : biomeTypes) {
            if (typeList.isEmpty()) {
                continue;
            }
            List<BiomeDictionary.Type> types = typeList.stream().map(LizardDoggo::getType).collect(Collectors.toList());
            Set<Biome> allBiomes = new HashSet<>();
            types.stream().map(BiomeDictionary::getBiomes).forEach(allBiomes::addAll);
            Set<Biome> acceptedBiomes = new HashSet<>();

            outer:
            for (Biome biome : allBiomes) {
                for (BiomeDictionary.Type type : types) {
                    if (!BiomeDictionary.hasType(biome, type)) {
                        continue outer;
                    }
                }
                for (BiomeDictionary.Type type : exclusions) {
                    if (BiomeDictionary.hasType(biome, type)) {
                        continue outer;
                    }
                }
                acceptedBiomes.add(biome);
            }

            biomes.addAll(acceptedBiomes);
        }

        for (Biome biome : biomes) {
            LOGGER.debug("Accepted biome: " + biome.getRegistryName().toString());
            biome.getSpawns(EntityClassification.CREATURE).add(new Biome.SpawnListEntry(LIZARD_ENTITY_TYPE, ConfigMain.CONFIG.spawnWeight.get(), ConfigMain.CONFIG.spawnMin.get(), ConfigMain.CONFIG.spawnMax.get()));
        }

        EntitySpawnPlacementRegistry.register(LIZARD_ENTITY_TYPE, EntitySpawnPlacementRegistry.PlacementType.ON_GROUND, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, EntityLizard::placement);
    }

    @SubscribeEvent
    public static void registerItems(RegistryEvent.Register<Item> event)
    {
        event.getRegistry().register(lizardSpawnEgg = new SpawnEggItem(LIZARD_ENTITY_TYPE, new Color(17, 100, 9).getRGB(), new Color(100, 79, 16).getRGB(), new Item.Properties()
                .group(ItemGroup.MISC)).setRegistryName(DOMAIN + ":lizard_doggo_spawn_egg"));
    }
}
