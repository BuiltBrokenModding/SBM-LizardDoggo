package com.builtbroken.lizarddogo.entity;

import java.util.UUID;

import javax.annotation.Nullable;

import com.builtbroken.lizarddogo.LizardDogo;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityAgeable;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIAttackMelee;
import net.minecraft.entity.ai.EntityAIFollowOwner;
import net.minecraft.entity.ai.EntityAIHurtByTarget;
import net.minecraft.entity.ai.EntityAILeapAtTarget;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAIMate;
import net.minecraft.entity.ai.EntityAIOwnerHurtByTarget;
import net.minecraft.entity.ai.EntityAIOwnerHurtTarget;
import net.minecraft.entity.ai.EntityAISit;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAIWanderAvoidWater;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.monster.EntityCreeper;
import net.minecraft.entity.monster.EntityGhast;
import net.minecraft.entity.passive.AbstractHorse;
import net.minecraft.entity.passive.EntityAnimal;
import net.minecraft.entity.passive.EntityTameable;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumHand;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

/**
 * Created by Dark(DarkGuardsman, Robert) on 1/12/2019.
 */
public class EntityLizard extends EntityTameable
{
    public EntityLizard(World worldIn)
    {
        super(LizardDogo.LIZARD_ENTITY_TYPE, worldIn);
        this.setSize(0.5F, 0.4F);
        this.setTamed(false);
        //TODO override lookhelper to allow angled head animation
    }

    @Override
    protected void initEntityAI()
    {
        this.aiSit = new EntityAISit(this);
        this.tasks.addTask(1, new EntityAISwimming(this));
        this.tasks.addTask(2, this.aiSit);
        this.tasks.addTask(4, new EntityAILeapAtTarget(this, 0.4F)); //TODO toy with idea
        this.tasks.addTask(5, new EntityAIAttackMelee(this, 1.0D, true));
        this.tasks.addTask(6, new EntityAIFollowOwner(this, 1.0D, 10.0F, 2.0F));
        this.tasks.addTask(7, new EntityAIMate(this, 1.0D));
        this.tasks.addTask(8, new EntityAIWanderAvoidWater(this, 1.0D));
        this.tasks.addTask(10, new EntityAIWatchClosest(this, EntityPlayer.class, 8.0F));
        this.tasks.addTask(10, new EntityAILookIdle(this));

        this.targetTasks.addTask(1, new EntityAIOwnerHurtByTarget(this));
        this.targetTasks.addTask(2, new EntityAIOwnerHurtTarget(this));
        this.targetTasks.addTask(3, new EntityAIHurtByTarget(this, true, new Class[0]));
    }

    @Override
    protected void registerAttributes()
    {
        super.registerAttributes();
        this.getAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.30000001192092896D);
        this.getAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(20.0D);
        this.getAttributeMap().registerAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(2.0D);
    }

    @Override
    public float getEyeHeight()
    {
        return this.height * 0.8F;
    }

    @Override
    public int getVerticalFaceSpeed()
    {
        return this.isSitting() ? 20 : super.getVerticalFaceSpeed();
    }

    @Override
    public boolean attackEntityFrom(DamageSource source, float amount)
    {
        if (this.isInvulnerableTo(source))
        {
            return false;
        }
        else
        {
            Entity entity = source.getTrueSource();

            if (this.aiSit != null)
            {
                this.aiSit.setSitting(false);
            }

            if (entity != null && !(entity instanceof EntityPlayer) && !(entity instanceof EntityArrow))
            {
                amount = (amount + 1.0F) / 2.0F;
            }

            return super.attackEntityFrom(source, amount);
        }
    }

    @Override
    public boolean attackEntityAsMob(Entity entityIn)
    {
        boolean flag = entityIn.attackEntityFrom(DamageSource.causeMobDamage(this), ((int)this.getAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).getValue()));

        if (flag)
        {
            this.applyEnchantments(this, entityIn);
        }

        return flag;
    }

    @Override
    public boolean processInteract(EntityPlayer player, EnumHand hand)
    {
        ItemStack itemstack = player.getHeldItem(hand);

        if (this.isTamed())
        {
            if (this.isOwner(player) && !this.world.isRemote && !this.isBreedingItem(itemstack))
            {
                boolean wasSitting = this.isSitting();
                this.aiSit.setSitting(!wasSitting);
                this.isJumping = false;
                this.navigator.clearPath();
                this.setAttackTarget((EntityLivingBase)null);
                player.sendStatusMessage(new TextComponentString("follow: " + wasSitting), true);
            }
        }
        else if (eats(itemstack.getItem())) //TODO maybe several foods
        {
            if (!player.isCreative())
            {
                itemstack.shrink(1);
            }

            if (!this.world.isRemote)
            {
                //TODO add config for random chance
                if (this.rand.nextInt(3) == 0 && !net.minecraftforge.event.ForgeEventFactory.onAnimalTame(this, player))
                {
                    this.setTamedBy(player);
                    this.navigator.clearPath();
                    this.setAttackTarget((EntityLivingBase)null);
                    this.aiSit.setSitting(true);
                    this.setHealth(20.0F); //TODO match HP
                    this.playTameEffect(true);
                    this.world.setEntityState(this, (byte)7); //TODO cache states in named vars
                }
                else
                {
                    this.playTameEffect(false);
                    this.world.setEntityState(this, (byte)6); //TODO cache states in named vars
                }
            }

            return true;
        }

        return super.processInteract(player, hand);
    }

    public boolean eats(Item item)
    {
        return item == Items.COD || item == Items.SALMON || item == Items.TROPICAL_FISH;
    }

    @Override
    public boolean isBreedingItem(ItemStack stack)
    {
        return stack.getItem() == Items.GLOWSTONE_DUST;
    }

    @Override
    @OnlyIn(Dist.CLIENT)
    public void handleStatusUpdate(byte id)
    {
        super.handleStatusUpdate(id);
    }

    @Override
    public int getMaxSpawnedInChunk()
    {
        return 2;
    }

    @Nullable
    @Override
    public EntityAgeable createChild(EntityAgeable ageable)
    {
        EntityLizard entitywolf = new EntityLizard(this.world);
        UUID uuid = this.getOwnerId();

        if (uuid != null)
        {
            entitywolf.setOwnerId(uuid);
            entitywolf.setTamed(true);
        }

        return entitywolf;
    }

    @Override
    public boolean canMateWith(EntityAnimal otherAnimal)
    {
        if (otherAnimal == this)
        {
            return false;
        }
        else if (!this.isTamed())
        {
            return false;
        }
        else if (!(otherAnimal instanceof EntityLizard))
        {
            return false;
        }
        else
        {
            EntityLizard entitywolf = (EntityLizard) otherAnimal;

            if (!entitywolf.isTamed())
            {
                return false;
            }
            else if (entitywolf.isSitting())
            {
                return false;
            }
            else
            {
                return this.isInLove() && entitywolf.isInLove();
            }
        }
    }

    @Override
    public boolean shouldAttackEntity(EntityLivingBase target, EntityLivingBase owner)
    {
        if (!(target instanceof EntityCreeper) && !(target instanceof EntityGhast))
        {
            if (target instanceof EntityTameable)
            {
                EntityTameable entitywolf = (EntityTameable)target;

                if (entitywolf.isTamed() && entitywolf.getOwner() == owner)
                {
                    return false;
                }
            }

            if (target instanceof EntityPlayer && owner instanceof EntityPlayer && !((EntityPlayer)owner).canAttackPlayer((EntityPlayer)target))
            {
                return false;
            }
            else
            {
                return !(target instanceof AbstractHorse) || !((AbstractHorse)target).isTame();
            }
        }
        else
        {
            return false;
        }
    }
}
