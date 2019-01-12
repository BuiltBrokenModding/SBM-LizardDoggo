package com.builtbroken.lizarddogo.client;

import com.builtbroken.lizarddogo.LizardDogo;
import com.builtbroken.lizarddogo.entity.EntityLizard;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

/**
 * Created by Dark(DarkGuardsman, Robert) on 1/12/2019.
 */
@SideOnly(Side.CLIENT)
@Mod.EventBusSubscriber(modid = LizardDogo.DOMAIN, value = Side.CLIENT)
public class ClientReg
{
    @SubscribeEvent
    public static void registerAllModels(ModelRegistryEvent event)
    {
        //Entity
        RenderingRegistry.registerEntityRenderingHandler(EntityLizard.class, manager -> new RenderLizard(manager));
    }
}
