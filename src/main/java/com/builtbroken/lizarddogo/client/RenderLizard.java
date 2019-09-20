package com.builtbroken.lizarddogo.client;

import javax.annotation.Nullable;

import com.builtbroken.lizarddogo.LizardDogo;
import com.builtbroken.lizarddogo.entity.EntityLizard;

import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.util.ResourceLocation;

/**
 * Created by Dark(DarkGuardsman, Robert) on 1/12/2019.
 */
public class RenderLizard extends MobRenderer<EntityLizard,ModelLizard>
{
    public static final ResourceLocation TEXTURE = new ResourceLocation(LizardDogo.DOMAIN, "textures/entity/lizard.png");

    public RenderLizard(EntityRendererManager rendermanagerIn)
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
