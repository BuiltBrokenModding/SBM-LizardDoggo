package com.builtbroken.lizarddogo.client;

import com.builtbroken.lizarddogo.entity.EntityLizard;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.math.MathHelper;

/**
 * Dimetrodon - M1W3ST
 * Created using Tabula 7.0.0
 */
public class ModelLizard extends ModelBase
{
    public ModelRenderer BipedBody;
    public ModelRenderer neck;
    public ModelRenderer Tail;
    public ModelRenderer RightFrontLeg;
    public ModelRenderer LeftFrontLeg;
    public ModelRenderer LeftbackLeg;
    public ModelRenderer RightbackLeg;
    public ModelRenderer shape36;
    public ModelRenderer head;
    public ModelRenderer shape40;
    public ModelRenderer snout;
    public ModelRenderer shape41;
    public ModelRenderer mouth;
    public ModelRenderer Teeth;
    public ModelRenderer Teeth_1;
    public ModelRenderer Tail2;
    public ModelRenderer shape37;
    public ModelRenderer Tail3;
    public ModelRenderer shape38;
    public ModelRenderer shape39;
    public ModelRenderer RightFrontRadius;
    public ModelRenderer RightFrontFoot;
    public ModelRenderer LeftFrontRadius;
    public ModelRenderer LeftFrontFoot;
    public ModelRenderer Leftbacktibia;
    public ModelRenderer Leftbackfoot;
    public ModelRenderer Rightbacktibia;
    public ModelRenderer Rightbackfoot;

    private float LeftFrontLegRotation;
    private float LeftbackLegRotation;

    private float RightFrontLegRotation;
    private float RightbackLegRotation;

    public ModelLizard()
    {
        this.textureWidth = 64;
        this.textureHeight = 32;
        this.shape41 = new ModelRenderer(this, 0, 2);
        this.shape41.setRotationPoint(0.0F, -2.0F, -2.1F);
        this.shape41.addBox(0.0F, -2.0F, 0.0F, 0, 2, 4, 0.0F);

        this.LeftbackLeg = new ModelRenderer(this, 37, 0);
        this.LeftbackLeg.setRotationPoint(2.0F, -1.0F, 4.5F);
        this.LeftbackLeg.addBox(-0.5F, 0.0F, -1.0F, 2, 4, 2, 0.0F);
        this.setRotateAngle(LeftbackLeg, -0.6981317007977318F, 0.0F, 0.0F);

        this.Rightbackfoot = new ModelRenderer(this, 19, 4);
        this.Rightbackfoot.setRotationPoint(0.0F, 3.0F, 0.0F);
        this.Rightbackfoot.addBox(-1.0F, -0.5F, -2.0F, 2, 1, 3, 0.0F);
        this.setRotateAngle(Rightbackfoot, -0.3839724354387525F, 0.0F, 0.0F);

        this.LeftFrontFoot = new ModelRenderer(this, 19, 0);
        this.LeftFrontFoot.setRotationPoint(0.5F, 3.0F, 0.0F);
        this.LeftFrontFoot.addBox(-1.5F, -0.5F, -1.5F, 2, 1, 3, 0.0F);
        this.setRotateAngle(LeftFrontFoot, 0.3839724354387525F, 0.0F, 0.0F);

        this.Tail = new ModelRenderer(this, 29, 18);
        this.Tail.setRotationPoint(0.0F, -2.0F, 5.5F);
        this.Tail.addBox(-1.5F, 0.0F, 0.0F, 3, 4, 4, 0.0F);
        this.setRotateAngle(Tail, -0.136659280431156F, 0.0F, 0.0F);

        this.Tail3 = new ModelRenderer(this, 0, 18);
        this.Tail3.setRotationPoint(0.0F, 0.7F, 4.0F);
        this.Tail3.addBox(-0.5F, 0.0F, 0.0F, 1, 2, 4, 0.0F);
        this.setRotateAngle(Tail3, -0.18203784098300857F, 0.0F, 0.0F);

        this.RightFrontLeg = new ModelRenderer(this, 37, 0);
        this.RightFrontLeg.setRotationPoint(-1.5F, -1.0F, -4.0F);
        this.RightFrontLeg.addBox(-2.0F, 0.0F, -1.0F, 2, 4, 2, 0.0F);
        this.setRotateAngle(RightFrontLeg, 0.6981317007977318F, 0.0F, 0.0F);

        this.Tail2 = new ModelRenderer(this, 41, 5);
        this.Tail2.setRotationPoint(0.0F, 0.5F, 4.0F);
        this.Tail2.addBox(-1.0F, 0.0F, 0.0F, 2, 3, 4, 0.0F);
        this.setRotateAngle(Tail2, -0.136659280431156F, 0.0F, 0.0F);

        this.shape36 = new ModelRenderer(this, 0, -11);
        this.shape36.setRotationPoint(0.0F, -2.5F, 0.0F);
        this.shape36.addBox(0.0F, -2.0F, -5.5F, 0, 2, 11, 0.0F);

        this.RightFrontRadius = new ModelRenderer(this, 30, 0);
        this.RightFrontRadius.setRotationPoint(-1.0F, 3.0F, 0.0F);
        this.RightFrontRadius.addBox(-0.5F, 0.0F, -1.0F, 1, 3, 2, 0.0F);
        this.setRotateAngle(RightFrontRadius, -1.0821041362364843F, 0.0F, 0.0F);

        this.shape37 = new ModelRenderer(this, 0, -3);
        this.shape37.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.shape37.addBox(0.0F, -2.0F, -0.5F, 0, 3, 5, 0.0F);

        this.Leftbackfoot = new ModelRenderer(this, 19, 0);
        this.Leftbackfoot.setRotationPoint(0.0F, 3.0F, 0.0F);
        this.Leftbackfoot.addBox(-1.0F, -0.5F, -2.0F, 2, 1, 3, 0.0F);
        this.setRotateAngle(Leftbackfoot, -0.3839724354387525F, 0.0F, 0.0F);

        this.neck = new ModelRenderer(this, 16, 21);
        this.neck.setRotationPoint(0.0F, 2.5F, -5.5F);
        this.neck.addBox(-1.5F, -5.0F, -6.0F, 3, 5, 6, 0.0F);
        this.setRotateAngle(neck, -0.31869712141416456F, 0.0F, 0.0F);

        this.Leftbacktibia = new ModelRenderer(this, 30, 0);
        this.Leftbacktibia.setRotationPoint(0.5F, 3.0F, 0.0F);
        this.Leftbacktibia.addBox(-0.5F, 0.0F, -1.0F, 1, 3, 2, 0.0F);
        this.setRotateAngle(Leftbacktibia, 1.0821041362364843F, 0.0F, 0.0F);

        this.shape38 = new ModelRenderer(this, 4, 4);
        this.shape38.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.shape38.addBox(0.0F, -2.0F, 0.0F, 0, 2, 4, 0.0F);

        this.head = new ModelRenderer(this, 0, 24);
        this.head.setRotationPoint(0.0F, -2.5F, -6.0F);
        this.head.addBox(-2.0F, -2.5F, -2.0F, 4, 5, 3, 0.0F);
        this.setRotateAngle(head, 0.31869712141416456F, 0.0F, 0.0F);

        this.Teeth = new ModelRenderer(this, 0, 9);
        this.Teeth.setRotationPoint(1.51F, 0.0F, -1.85F);
        this.Teeth.addBox(0.0F, -1.0F, -1.5F, 0, 1, 3, 0.0F);
        this.setRotateAngle(Teeth, 0.0F, 0.0F, 0.17453292519943295F);

        this.shape39 = new ModelRenderer(this, 0, 6);
        this.shape39.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.shape39.addBox(0.0F, -2.0F, -0.5F, 0, 2, 4, 0.0F);

        this.mouth = new ModelRenderer(this, 50, 10);
        this.mouth.setRotationPoint(0.0F, 3.8F, -0.3F);
        this.mouth.addBox(-1.5F, 0.0F, -3.5F, 3, 1, 4, 0.0F);

        this.LeftFrontLeg = new ModelRenderer(this, 37, 0);
        this.LeftFrontLeg.setRotationPoint(1.5F, -1.0F, -4.0F);
        this.LeftFrontLeg.addBox(0.0F, 0.0F, -1.0F, 2, 4, 2, 0.0F);
        this.setRotateAngle(LeftFrontLeg, 0.6981317007977318F, 0.0F, 0.0F);

        this.snout = new ModelRenderer(this, 50, 0);
        this.snout.setRotationPoint(0.0F, -2.5F, -2.0F);
        this.snout.addBox(-1.5F, 0.0F, -4.0F, 3, 4, 4, 0.0F);
        this.setRotateAngle(snout, 0.091106186954104F, 0.0F, 0.0F);

        this.RightbackLeg = new ModelRenderer(this, 37, 0);
        this.RightbackLeg.setRotationPoint(-1.5F, -1.0F, 4.5F);
        this.RightbackLeg.addBox(-2.0F, 0.0F, -1.0F, 2, 4, 2, 0.0F);
        this.setRotateAngle(RightbackLeg, -0.6981317007977318F, 0.0F, 0.0F);

        this.RightFrontFoot = new ModelRenderer(this, 19, 4);
        this.RightFrontFoot.setRotationPoint(0.0F, 3.0F, 0.0F);
        this.RightFrontFoot.addBox(-1.0F, -0.5F, -1.5F, 2, 1, 3, 0.0F);
        this.setRotateAngle(RightFrontFoot, 0.3839724354387525F, 0.0F, 0.0F);

        this.shape40 = new ModelRenderer(this, 2, -1);
        this.shape40.setRotationPoint(0.0F, -5.0F, -6.0F);
        this.shape40.addBox(0.0F, -2.0F, 0.0F, 0, 2, 5, 0.0F);

        this.BipedBody = new ModelRenderer(this, 34, 16);
        this.BipedBody.setRotationPoint(0.0F, 19.45F, 0.0F);
        this.BipedBody.addBox(-2.0F, -2.5F, -5.5F, 4, 5, 11, 0.0F);

        this.Rightbacktibia = new ModelRenderer(this, 30, 0);
        this.Rightbacktibia.setRotationPoint(-1.0F, 3.0F, 0.0F);
        this.Rightbacktibia.addBox(-0.5F, 0.0F, -1.0F, 1, 3, 2, 0.0F);
        this.setRotateAngle(Rightbacktibia, 1.0821041362364843F, 0.0F, 0.0F);

        this.LeftFrontRadius = new ModelRenderer(this, 30, 0);
        this.LeftFrontRadius.setRotationPoint(1.0F, 3.0F, 0.0F);
        this.LeftFrontRadius.addBox(-0.5F, 0.0F, -1.0F, 1, 3, 2, 0.0F);
        this.setRotateAngle(LeftFrontRadius, -1.0821041362364843F, 0.0F, 0.0F);

        this.Teeth_1 = new ModelRenderer(this, 0, 9);
        this.Teeth_1.setRotationPoint(-1.51F, 0.0F, -1.85F);
        this.Teeth_1.addBox(0.0F, -1.0F, -1.5F, 0, 1, 3, 0.0F);
        this.setRotateAngle(Teeth_1, 0.0F, 0.0F, -0.17453292519943295F);

        this.head.addChild(this.shape41);
        this.BipedBody.addChild(this.LeftbackLeg);
        this.Rightbacktibia.addChild(this.Rightbackfoot);
        this.LeftFrontRadius.addChild(this.LeftFrontFoot);
        this.BipedBody.addChild(this.Tail);
        this.Tail2.addChild(this.Tail3);
        this.BipedBody.addChild(this.RightFrontLeg);
        this.Tail.addChild(this.Tail2);
        this.BipedBody.addChild(this.shape36);
        this.RightFrontLeg.addChild(this.RightFrontRadius);
        this.Tail.addChild(this.shape37);
        this.Leftbacktibia.addChild(this.Leftbackfoot);
        this.BipedBody.addChild(this.neck);
        this.LeftbackLeg.addChild(this.Leftbacktibia);
        this.Tail2.addChild(this.shape38);
        this.neck.addChild(this.head);
        this.mouth.addChild(this.Teeth);
        this.Tail3.addChild(this.shape39);
        this.snout.addChild(this.mouth);
        this.BipedBody.addChild(this.LeftFrontLeg);
        this.head.addChild(this.snout);
        this.BipedBody.addChild(this.RightbackLeg);
        this.RightFrontRadius.addChild(this.RightFrontFoot);
        this.neck.addChild(this.shape40);
        this.RightbackLeg.addChild(this.Rightbacktibia);
        this.LeftFrontLeg.addChild(this.LeftFrontRadius);
        this.mouth.addChild(this.Teeth_1);


        LeftFrontLegRotation = LeftFrontLeg.rotateAngleX;
        LeftbackLegRotation = LeftbackLeg.rotateAngleX;

        RightFrontLegRotation = RightFrontLeg.rotateAngleX;
        RightbackLegRotation = RightbackLeg.rotateAngleX;
    }

    @Override
    public void render(Entity entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scale)
    {
        super.render(entityIn, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scale);
        this.setRotationAngles(limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scale, entityIn);

        if (this.isChild)
        {
            GlStateManager.pushMatrix();
            GlStateManager.scale(0.5F, 0.5F, 0.5F);
            GlStateManager.translate(0.0F, 24.0F * scale, 0.0F);
            render(scale);
            GlStateManager.popMatrix();
        }
        else
        {
            //Render
            render(scale);
        }
    }

    public void render(float scale)
    {
        this.BipedBody.render(scale);
    }

    @Override
    public void setRotationAngles(float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scaleFactor, Entity entityIn)
    {
        super.setRotationAngles(limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scaleFactor, entityIn);

        //Head rotations
        head.rotateAngleY = netHeadYaw * 0.017453292F;
        head.rotateAngleX = headPitch * 0.017453292F;

        //Reset animation
        Tail.rotateAngleY = 0;
        Tail2.rotateAngleY = 0;
        Tail3.rotateAngleY = 0;

        //Apply animation
        if (entityIn instanceof EntityLizard)
        {
            EntityLizard entityLizard = (EntityLizard) entityIn;

            final float progress = ageInTicks / 10;
            final float angle = (float) Math.toRadians(progress * 360);
            if (entityLizard.isSitting() || limbSwingAmount < 0.1)
            {
                final float moveDistance = (float)Math.toRadians(15);
                Tail.rotateAngleY = MathHelper.cos(angle) * moveDistance;
                Tail2.rotateAngleY = MathHelper.cos(angle) * moveDistance;
                Tail3.rotateAngleY = MathHelper.cos(angle) * moveDistance;
            }
            else
            {
                final float moveDistance = (float)Math.toRadians(7);
                Tail.rotateAngleY = MathHelper.cos(angle) * moveDistance * limbSwingAmount;
                Tail2.rotateAngleY = MathHelper.cos(angle) * moveDistance * limbSwingAmount;
                Tail3.rotateAngleY = MathHelper.cos(angle) * moveDistance * limbSwingAmount;
            }
        }
    }

    @Override
    public void setLivingAnimations(EntityLivingBase entitylivingbaseIn, float limbSwing, float limbSwingAmount, float partialTickTime)
    {
        if (!(entitylivingbaseIn instanceof EntityLizard))
        {
            //TODO reset animations/rotations
            return;
        }

        EntityLizard entityLizard = (EntityLizard) entitylivingbaseIn;

        if (entityLizard.isTamed())
        {
            mouth.rotateAngleX = 15 * 0.017453292F;
        }
        else
        {
            mouth.rotateAngleX = 0;
        }

        //Animation reset
        LeftFrontLeg.rotateAngleX = LeftFrontLegRotation;
        LeftbackLeg.rotateAngleX = LeftbackLegRotation;
        RightFrontLeg.rotateAngleX = RightFrontLegRotation;
        RightbackLeg.rotateAngleX = RightbackLegRotation;

        if (entityLizard.isSitting())
        {

        }
        else
        {
            final float angle = limbSwing * 0.6662F;
            final float moveDistance = 1.4F;
            float motionA = MathHelper.cos(angle) * moveDistance * limbSwingAmount;
            float motionB = MathHelper.cos(angle + (float) Math.PI) * moveDistance * limbSwingAmount;


            LeftFrontLeg.rotateAngleX = motionA + LeftFrontLegRotation;
            LeftbackLeg.rotateAngleX = motionB + LeftbackLegRotation;

            RightFrontLeg.rotateAngleX = motionB + RightFrontLegRotation;
            RightbackLeg.rotateAngleX = motionA + RightbackLegRotation;
        }
    }

    /**
     * This is a helper function from Tabula to set the rotation of model parts
     */
    public void setRotateAngle(ModelRenderer modelRenderer, float x, float y, float z)
    {
        modelRenderer.rotateAngleX = x;
        modelRenderer.rotateAngleY = y;
        modelRenderer.rotateAngleZ = z;
    }
}
