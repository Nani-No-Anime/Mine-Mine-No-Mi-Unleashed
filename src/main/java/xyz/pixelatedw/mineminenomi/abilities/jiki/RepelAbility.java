/*     */ package xyz.pixelatedw.mineminenomi.abilities.jiki;
/*     */ 
/*     */ import java.lang.invoke.SerializedLambda;
/*     */ import java.util.List;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.LivingEntity;
/*     */ import net.minecraft.entity.item.ItemEntity;
/*     */ import net.minecraft.entity.player.PlayerEntity;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.util.math.Vec3d;
/*     */ import xyz.pixelatedw.mineminenomi.api.helpers.abilities.AbilityHelper;
/*     */ import xyz.pixelatedw.mineminenomi.data.entity.entitystats.EntityStatsCapability;
/*     */ import xyz.pixelatedw.mineminenomi.entities.projectiles.jiki.PunkGibsonProjectile;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModTags;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.abilities.Ability;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.abilities.ContinuousAbility;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.data.ability.AbilityDataCapability;
/*     */ 
/*     */ public class RepelAbility extends ContinuousAbility {
/*  21 */   public static final RepelAbility INSTANCE = new RepelAbility();
/*     */   
/*     */   private static final int RADIUS = 5;
/*     */ 
/*     */   
/*     */   public RepelAbility() {
/*  27 */     super("Repel", AbilityHelper.getDevilFruitCategory());
/*  28 */     setDescription("§2Range:§r 5 blocks\nRepels all metallic objects or projectiles near the user");
/*     */ 
/*     */     
/*  31 */     setThreshold(5.0D);
/*  32 */     setMaxCooldown(5.0D);
/*     */     
/*  34 */     this.onStartContinuityEvent = this::onStartContinuityEvent;
/*  35 */     this.duringContinuityEvent = this::duringContinuityEvent;
/*     */   }
/*     */ 
/*     */   
/*     */   private boolean onStartContinuityEvent(PlayerEntity player) {
/*  40 */     PunkGibsonAbility ability = (PunkGibsonAbility)AbilityDataCapability.get((LivingEntity)player).getEquippedAbility((Ability)PunkGibsonAbility.INSTANCE);
/*  41 */     boolean isGibsonActive = (ability != null && ability.isContinuous());
/*     */     
/*  43 */     if (isGibsonActive) {
/*     */       
/*  45 */       PunkGibsonProjectile proj = new PunkGibsonProjectile(player.world, (LivingEntity)player, ability.getMagneticItems());
/*  46 */       player.world.addEntity((Entity)proj);
/*  47 */       proj.shoot((Entity)player, player.rotationPitch, player.rotationYaw, 0.0F, 4.0F, 1.0F);
/*  48 */       ability.stopItemDrops();
/*  49 */       ability.endContinuity(player);
/*  50 */       endContinuity(player);
/*  51 */       return false;
/*     */     } 
/*     */     
/*  54 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   public void duringContinuityEvent(PlayerEntity player, int continuousTime) {
/*  59 */     List<Entity> targets = WyHelper.getEntitiesNear(player.getPosition(), player.world, 5.0D, new Class[] { Entity.class });
/*  60 */     targets.remove(player);
/*  61 */     for (Entity target : targets) {
/*     */       
/*  63 */       boolean flag = false;
/*     */       
/*  65 */       if (target.getType().isContained(ModTags.Entities.MAGNETIC)) {
/*  66 */         flag = true;
/*     */       }
/*  68 */       if (target instanceof LivingEntity && (getIronArmorCoverPercentage((LivingEntity)target) >= 0.5F || EntityStatsCapability.get((LivingEntity)target).isCyborg())) {
/*  69 */         flag = true;
/*     */       }
/*  71 */       if (flag) {
/*     */         
/*  73 */         Vec3d dist = target.getPositionVec().subtract(player.getPositionVec()).add(0.0D, -1.0D, 0.0D);
/*  74 */         double speedReduction = 5.0D;
/*  75 */         double minSpeed = 3.0D;
/*  76 */         double xSpeed = Math.min(minSpeed, -dist.x / speedReduction);
/*  77 */         double ySpeed = Math.min(minSpeed, -dist.y / speedReduction);
/*  78 */         double zSpeed = Math.min(minSpeed, -dist.z / speedReduction);
/*  79 */         target.setMotion(-xSpeed, ySpeed, -zSpeed);
/*  80 */         target.velocityChanged = true;
/*     */       } 
/*     */     } 
/*     */ 
/*     */     
/*  85 */     List<ItemEntity> items = WyHelper.getEntitiesNear(player.getPosition(), player.world, 5.0D, new Class[] { ItemEntity.class });
/*  86 */     for (ItemEntity item : items) {
/*     */       
/*  88 */       if (item.getItem().isEmpty() || !item.getItem().getItem().isIn(ModTags.Items.MAGNETIC))
/*     */         continue; 
/*  90 */       Vec3d vec = item.getPositionVec().subtract(player.getPositionVec()).add(0.0D, -1.0D, 0.0D);
/*  91 */       double speedReduction = 8.0D;
/*  92 */       double speed = 2.0D;
/*  93 */       double xSpeed = Math.min(speed, -vec.x / speedReduction);
/*  94 */       double zSpeed = Math.min(speed, -vec.z / speedReduction);
/*  95 */       item.setMotion(-xSpeed, 0.1D, -zSpeed);
/*  96 */       item.velocityChanged = true;
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public float getIronArmorCoverPercentage(LivingEntity target) {
/* 102 */     Iterable<ItemStack> iterable = target.getArmorInventoryList();
/* 103 */     int i = 0;
/* 104 */     int j = 0;
/*     */     
/* 106 */     for (ItemStack itemstack : iterable) {
/*     */       
/* 108 */       if (!itemstack.isEmpty() && itemstack.getItem().isIn(ModTags.Items.MAGNETIC))
/*     */       {
/* 110 */         j++;
/*     */       }
/*     */       
/* 113 */       i++;
/*     */     } 
/*     */     
/* 116 */     return (i > 0) ? (j / i) : 0.0F;
/*     */   }
/*     */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\abilities\jiki\RepelAbility.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */