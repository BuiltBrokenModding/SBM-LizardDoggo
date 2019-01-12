package com.builtbroken.lizarddogo.client;

import com.builtbroken.lizarddogo.LizardDogo;
import com.builtbroken.lizarddogo.entity.EntityLizard;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;

import javax.annotation.Nullable;

/**
 * Created by Dark(DarkGuardsman, Robert) on 1/12/2019.
 */
public class RenderLizard extends RenderLiving<EntityLizard>
{
    public static final ResourceLocation TEXTURE = new ResourceLocation(LizardDogo.DOMAIN, "textures/entity/lizard.png");

    public RenderLizard(RenderManager rendermanagerIn)
    {
        super(rendermanagerIn, new ModelLizard(), 0);
    }

    @Nullable
    @Override
    protected ResourceLocation getEntityTexture(EntityLizard entity)
    {
        return TEXTURE;
    }
}
