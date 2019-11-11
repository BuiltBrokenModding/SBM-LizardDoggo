package com.builtbroken.lizarddoggo;

import java.awt.Color;

import com.builtbroken.lizarddoggo.entity.EntityLizard;

import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.EntityEntry;
import net.minecraftforge.fml.common.registry.EntityEntryBuilder;

/**
 * Created by Dark(DarkGuardsman, Robert) on 1/12/2019.
 */
@Mod(modid = LizardDoggo.DOMAIN, name = "SBM-Lizard Doggo", version = LizardDoggo.VERSION)
@Mod.EventBusSubscriber
public class LizardDoggo
{
    public static final String MAJOR_VERSION = "@MAJOR@";
    public static final String MINOR_VERSION = "@MINOR@";
    public static final String REVISION_VERSION = "@REVIS@";
    public static final String BUILD_VERSION = "@BUILD@";
    public static final String MC_VERSION = "@MC@";
    public static final String VERSION = MC_VERSION + "-" + MAJOR_VERSION + "." + MINOR_VERSION + "." + REVISION_VERSION + "." + BUILD_VERSION;

    public static final String DOMAIN = "sbmlizarddogo";

    @SubscribeEvent
    public static void registerEntity(RegistryEvent.Register<EntityEntry> event)
    {
        EntityEntryBuilder<EntityLizard> builder = EntityEntryBuilder.create();
        builder.name(DOMAIN + ":lizard");
        builder.id(new ResourceLocation(DOMAIN, "lizard"), 0);
        builder.tracker(128, 1, true);
        builder.entity(EntityLizard.class);
        builder.egg(new Color(17, 100, 9).getRGB(), new Color(100, 79, 16).getRGB());
        //builder.spawn() TODO
        event.getRegistry().register(builder.build());
    }
}
