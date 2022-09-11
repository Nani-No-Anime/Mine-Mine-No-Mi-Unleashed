package xyz.pixelatedw.mineminenomi.entities.mobs.goals.abilities.donkrieg;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.goal.Goal;
import net.minecraft.util.math.MathHelper;
import xyz.pixelatedw.mineminenomi.entities.mobs.OPEntity;
import xyz.pixelatedw.mineminenomi.entities.mobs.goals.CooldownGoal;
import xyz.pixelatedw.mineminenomi.entities.mobs.pirates.kriegpirates.DonKriegEntity;
import xyz.pixelatedw.mineminenomi.entities.projectiles.extra.NormalBulletProjectile;
import xyz.pixelatedw.mineminenomi.init.ModEffects;
import xyz.pixelatedw.mineminenomi.wypi.WyHelper;

public class GunArrayGoal extends CooldownGoal {
  private float animationTimer = 0.0F;
  
  private DonKriegEntity entity;
  
  public GunArrayGoal(DonKriegEntity entity, int timer, int random) {
    super((OPEntity)entity, timer, random);
    this.entity = entity;
  }


  
  public boolean shouldExecute() {
    boolean shouldExecute = super.shouldExecute();
    boolean hasTarget = (this.entity.getAttackTarget() != null);
    boolean hasHighHP = (this.entity.getHealth() > this.entity.getMaxHealth() / 2.0F);
    boolean canSeeTarget = (hasTarget && this.entity.getEntitySenses().canSee((Entity)this.entity.getAttackTarget()));
    boolean hasDifferentPhase = (this.entity.getPreviousGoal() != this);
    boolean hasChance = (Math.abs(WyHelper.randomDouble()) < 0.5D);
    boolean hasMovementUnblocked = !this.entity.isPotionActive(ModEffects.MOVEMENT_BLOCKED);
    
    if (shouldExecute && hasHighHP && canSeeTarget && hasDifferentPhase && hasChance && hasMovementUnblocked) {
      
      execute();
      return true;
    } 
    
    return false;
  }


  
  public void endCooldown() {
    super.endCooldown();
    this.entity.setCurrentGoal(null);
    this.entity.setPreviousGoal((Goal)this);
    this.animationTimer = 0.0F;
  }

  
  public void execute() {
    this.entity.setAnimation(120);
    this.animationTimer += 0.05F;
    
    if (this.entity.getAttackTarget() == null) {
      
      this.entity.setCurrentGoal((Goal)this);
      setOnCooldown(true);
      this.entity.setAnimation(OPEntity.Animation.NONE.ordinal());
      
      return;
    } 
    if (this.animationTimer >= 1.4F) {
      
      for (int i = 0; i < 6; i++) {
        
        NormalBulletProjectile proj = new NormalBulletProjectile(this.entity.world, (LivingEntity)this.entity);
        proj.setDamage(4.0F);
        
        LivingEntity target = this.entity.getAttackTarget();
        
        double velX = target.getPosX() - this.entity.getPosX();
        double velY = (target.getBoundingBox()).minY + (target.getHeight() / 3.0F) - proj.getPosY();
        double velZ = target.getPosZ() - this.entity.getPosZ();
        double x = MathHelper.sqrt(velX * velX + velZ * velZ);
        
        proj.shoot(velX, velY + x * 0.10000000149011612D, velZ, 1.6F, (15 - this.entity.world.getDifficulty().getId() * 4));
        this.entity.world.addEntity((Entity)proj);
      } 
      
      this.entity.setCurrentGoal((Goal)this);
      setOnCooldown(true);
      this.entity.setAnimation(OPEntity.Animation.NONE.ordinal());
    } 
  }
}


