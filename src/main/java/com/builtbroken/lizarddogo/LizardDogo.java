package com.builtbroken.lizarddogo;

import java.awt.Color;

import com.builtbroken.lizarddogo.entity.EntityLizard;

import net.minecraft.entity.EntityType;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemSpawnEgg;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;

/**
 * Created by Dark(DarkGuardsman, Robert) on 1/12/2019.
 */
@Mod(LizardDogo.DOMAIN)
@Mod.EventBusSubscriber(bus=Bus.MOD)
public class LizardDogo
{
    public static final String MAJOR_VERSION = "@MAJOR@";
    public static final String MINOR_VERSION = "@MINOR@";
    public static final String REVISION_VERSION = "@REVIS@";
    public static final String BUILD_VERSION = "@BUILD@";
    public static final String MC_VERSION = "@MC@";
    public static final String VERSION = MC_VERSION + "-" + MAJOR_VERSION + "." + MINOR_VERSION + "." + REVISION_VERSION + "." + BUILD_VERSION;

    public static final String DOMAIN = "sbmlizarddogo";
    public static final EntityType<EntityLizard> LIZARD_ENTITY_TYPE = EntityType.register(DOMAIN + ":lizard", EntityType.Builder.create(EntityLizard.class, EntityLizard::new).tracker(256, 1, true));
    public static Item lizardSpawnEgg;

    @SubscribeEvent
    public static void registerItems(RegistryEvent.Register<Item> event)
    {
        event.getRegistry().register(lizardSpawnEgg = new ItemSpawnEgg(LIZARD_ENTITY_TYPE, new Color(17, 100, 9).getRGB(), new Color(100, 79, 16).getRGB(), new Item.Properties()
                .group(ItemGroup.MISC)).setRegistryName(DOMAIN + ":lizard_dogo_spawn_egg"));
    }
}
