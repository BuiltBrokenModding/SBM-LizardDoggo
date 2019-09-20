package com.builtbroken.lizarddoggo.entity;

import java.util.UUID;

import javax.annotation.Nullable;

import com.builtbroken.lizarddoggo.ConfigMain;
import com.builtbroken.lizarddoggo.LizardDoggo;

import net.minecraft.entity.AgeableEntity;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.Pose;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.goal.BreedGoal;
import net.minecraft.entity.ai.goal.FollowOwnerGoal;
import net.minecraft.entity.ai.goal.HurtByTargetGoal;
import net.minecraft.entity.ai.goal.LeapAtTargetGoal;
import net.minecraft.entity.ai.goal.LookAtGoal;
import net.minecraft.entity.ai.goal.LookRandomlyGoal;
import net.minecraft.entity.ai.goal.MeleeAttackGoal;
import net.minecraft.entity.ai.goal.OwnerHurtByTargetGoal;
import net.minecraft.entity.ai.goal.OwnerHurtTargetGoal;
import net.minecraft.entity.ai.goal.SitGoal;
import net.minecraft.entity.ai.goal.SwimGoal;
import net.minecraft.entity.ai.goal.WaterAvoidingRandomWalkingGoal;
import net.minecraft.entity.monster.CreeperEntity;
import net.minecraft.entity.monster.GhastEntity;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.entity.passive.TameableEntity;
import net.minecraft.entity.passive.horse.AbstractHorseEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.ArrowEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraft.util.Hand;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

/**
 * Created by Dark(DarkGuardsman, Robert) on 1/12/2019.
 */
public class EntityLizard extends TameableEntity
{
    public EntityLizard(EntityType<EntityLizard> type, World worldIn)
    {
        super(type, worldIn);
        this.setTamed(false);
        //TODO override lookhelper to allow angled head animation
    }

    public EntityLizard(World world)
    {
        this(LizardDoggo.LIZARD_ENTITY_TYPE, world);
    }

    @Override
    protected void registerGoals()
    {
        this.sitGoal = new SitGoal(this);
        this.goalSelector.addGoal(1, new SwimGoal(this));
        this.goalSelector.addGoal(2, this.sitGoal);
        this.goalSelector.addGoal(4, new LeapAtTargetGoal(this, 0.4F)); //TODO toy with idea
        this.goalSelector.addGoal(5, new MeleeAttackGoal(this, 1.0D, true));
        this.goalSelector.addGoal(6, new FollowOwnerGoal(this, 1.0D, 10.0F, 2.0F));
        this.goalSelector.addGoal(7, new BreedGoal(this, 1.0D));
        this.goalSelector.addGoal(8, new WaterAvoidingRandomWalkingGoal(this, 1.0D));
        this.goalSelector.addGoal(10, new LookAtGoal(this, PlayerEntity.class, 8.0F));
        this.goalSelector.addGoal(10, new LookRandomlyGoal(this));

        this.targetSelector.addGoal(1, new OwnerHurtByTargetGoal(this));
        this.targetSelector.addGoal(2, new OwnerHurtTargetGoal(this));
        this.targetSelector.addGoal(3, new HurtByTargetGoal(this));
    }

    @Override
    protected void registerAttributes()
    {
        super.registerAttributes();
        this.getAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.30000001192092896D);
        this.getAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(20.0D);
        this.getAttributes().registerAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(2.0D);
    }

    @Override
    public float getEyeHeight(Pose pose)
    {
        return this.getHeight() * 0.8F;
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

            if (this.sitGoal != null)
            {
                this.sitGoal.setSitting(false);
            }

            if (entity != null && !(entity instanceof PlayerEntity) && !(entity instanceof ArrowEntity))
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
    public boolean processInteract(PlayerEntity player, Hand hand)
    {
        ItemStack itemstack = player.getHeldItem(hand);

        if (this.isTamed())
        {
            if (this.isOwner(player) && !this.world.isRemote && !this.isBreedingItem(itemstack))
            {
                boolean wasSitting = this.isSitting();
                this.sitGoal.setSitting(!wasSitting);
                this.isJumping = false;
                this.navigator.clearPath();
                this.setAttackTarget(null);
                player.sendStatusMessage(new StringTextComponent("follow: " + wasSitting), true);
            }
        }
        else if (eats(itemstack.getItem()))
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
                    this.setAttackTarget(null);
                    this.sitGoal.setSitting(true);
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
        return ConfigMain.CONFIG.tamingItems.get().contains(item.getRegistryName().toString()); //a little hacky, but it works
    }

    @Override
    public boolean isBreedingItem(ItemStack stack)
    {
        return ConfigMain.CONFIG.breedingItems.get().contains(stack.getItem().getRegistryName().toString()); //a little hacky, but it works
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
    public AgeableEntity createChild(AgeableEntity ageable)
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
    public boolean canMateWith(AnimalEntity otherAnimal)
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
    public boolean shouldAttackEntity(LivingEntity target, LivingEntity owner)
    {
        if (!(target instanceof CreeperEntity) && !(target instanceof GhastEntity))
        {
            if (target instanceof TameableEntity)
            {
                TameableEntity entitywolf = (TameableEntity)target;

                if (entitywolf.isTamed() && entitywolf.getOwner() == owner)
                {
                    return false;
                }
            }

            if (target instanceof PlayerEntity && owner instanceof PlayerEntity && !((PlayerEntity)owner).canAttackPlayer((PlayerEntity)target))
            {
                return false;
            }
            else
            {
                return !(target instanceof AbstractHorseEntity) || !((AbstractHorseEntity)target).isTame();
            }
        }
        else
        {
            return false;
        }
    }
}
