package com.witcherywalls.entity.ai;

import com.witcherywalls.entity.EntityVillageGuard;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.ai.EntityAITarget;
import net.minecraft.village.Village;

public class EntityAIDefendVillageGeneric extends EntityAITarget
{
    private final EntityVillageGuard defender;
    private EntityLivingBase villageAggressorTarget;

    public EntityAIDefendVillageGeneric(EntityVillageGuard guard)
    {
        super(guard, false, true);
        defender = guard;
        setMutexBits(1);
    }

    @Override
    public boolean shouldExecute()
    {
        Village village = defender.getVillage();

        if (village == null)
        {
            return false;
        }

        villageAggressorTarget = village.findNearestVillageAggressor(defender);

        if (!isSuitableTarget(villageAggressorTarget, false))
        {
            if (taskOwner.getRNG().nextInt(20) == 0)
            {
                villageAggressorTarget = defender.world.getClosestPlayerToEntity(defender, 32.0D);
                return isSuitableTarget(villageAggressorTarget, false);
            }
            return false;
        }

        return true;
    }

    @Override
    public void startExecuting()
    {
        defender.setAttackTarget(villageAggressorTarget);
        super.startExecuting();
    }
}
