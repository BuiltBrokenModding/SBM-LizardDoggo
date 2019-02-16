package com.builtbroken.lizarddogo.client;

import com.builtbroken.lizarddogo.LizardDogo;
import com.builtbroken.lizarddogo.entity.EntityLizard;

import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.common.Mod;

/**
 * Created by Dark(DarkGuardsman, Robert) on 1/12/2019.
 */
@OnlyIn(Dist.CLIENT)
@Mod.EventBusSubscriber(modid = LizardDogo.DOMAIN, value = Dist.CLIENT)
public class ClientReg
{
    @SubscribeEvent
    public static void registerAllModels(ModelRegistryEvent event)
    {
        //Entity
        RenderingRegistry.registerEntityRenderingHandler(EntityLizard.class, manager -> new RenderLizard(manager));
    }
}
