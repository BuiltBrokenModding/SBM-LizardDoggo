package com.builtbroken.lizarddoggo.client;

import com.builtbroken.lizarddoggo.LizardDoggo;

import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

/**
 * Created by Dark(DarkGuardsman, Robert) on 1/12/2019.
 */
@OnlyIn(Dist.CLIENT)
@Mod.EventBusSubscriber(modid = LizardDoggo.DOMAIN, bus = Bus.MOD, value = Dist.CLIENT)
public class ClientReg
{
    @SubscribeEvent
    public static void onClientSetup(FMLClientSetupEvent event)
    {
        //Entity
        RenderingRegistry.registerEntityRenderingHandler(LizardDoggo.LIZARD_ENTITY_TYPE, RenderLizard::new);
    }
}
