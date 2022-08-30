/*     */ package xyz.pixelatedw.mineminenomi.abilities.mogu;
/*     */ import java.lang.invoke.SerializedLambda;
/*     */ import java.util.List;
/*     */ import net.minecraft.block.BlockState;
/*     */ import net.minecraft.block.Blocks;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.LivingEntity;
/*     */ import net.minecraft.entity.player.PlayerEntity;
/*     */ import net.minecraft.entity.player.ServerPlayerEntity;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.network.IPacket;
import net.minecraft.network.play.server.SEntityVelocityPacket;
/*     */ import net.minecraft.potion.EffectInstance;
/*     */ import net.minecraft.potion.Effects;
/*     */ import net.minecraft.util.DamageSource;
/*     */ import net.minecraft.util.IItemProvider;
/*     */ import net.minecraft.util.math.BlockPos;
/*     */ import net.minecraft.util.math.Vec3d;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.IFallDamageBlockingAbility;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.IFormRequiredAbility;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.IMultiTargetAbility;
/*     */ import xyz.pixelatedw.mineminenomi.api.helpers.FactionHelper;
/*     */ import xyz.pixelatedw.mineminenomi.api.helpers.abilities.AbilityHelper;
/*     */ import xyz.pixelatedw.mineminenomi.api.morph.ZoanInfo;
/*     */ import xyz.pixelatedw.mineminenomi.api.protection.DefaultProtectionRules;
/*     */ import xyz.pixelatedw.mineminenomi.entities.zoan.MoguHeavyZoanInfo;
/*     */ import xyz.pixelatedw.mineminenomi.particles.effects.ParticleEffect;
/*     */ import xyz.pixelatedw.mineminenomi.particles.effects.baku.BakuMunchParticleEffect;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.abilities.Ability;
/*     */ 
/*     */ public class MoguraTonpoAbility extends Ability implements IMultiTargetAbility, IFallDamageBlockingAbility, IFormRequiredAbility {
/*  32 */   public static final MoguraTonpoAbility INSTANCE = new MoguraTonpoAbility();
/*     */   
/*  34 */   private static final ParticleEffect PARTICLES = (ParticleEffect)new BakuMunchParticleEffect();
/*     */   
/*     */   private int initialY;
/*     */   private boolean hasFallDamage = true;
/*     */   
/*     */   public MoguraTonpoAbility() {
/*  40 */     super("Mogura Tonpo", AbilityHelper.getDevilFruitCategory());
/*  41 */     setDescription("Digs a massive tunnel forwards while in mole form\n\n§2SHIFT-USE§r: Digs a tunnel straight below the user");
/*  42 */     setMaxCooldown(12.0D);
/*     */     
/*  44 */     this.onUseEvent = this::onUseEvent;
/*  45 */     this.duringCooldownEvent = this::duringCooldown;
/*     */   }
/*     */ 
/*     */   
/*     */   private boolean onUseEvent(PlayerEntity player) {
/*  50 */     clearTargets();
/*     */     
/*  52 */     this.initialY = (int)player.getPosY();
/*     */     
/*  54 */     if (player.isSneaking()) {
/*     */       
/*  56 */       this.hasFallDamage = false;
/*  57 */       for (int x = -1; x < 1; x++) {
/*     */         
/*  59 */         for (int y = 0; y < 10; y++) {
/*     */           
/*  61 */           for (int z = -1; z < 1; z++) {
/*     */             
/*  63 */             int posX = (int)player.getPosX() + x;
/*  64 */             int posY = (int)player.getPosY() - y;
/*  65 */             int posZ = (int)player.getPosZ() + z;
/*  66 */             BlockPos pos = new BlockPos(posX, posY, posZ);
/*     */             
/*  68 */             player.addPotionEffect(new EffectInstance(Effects.RESISTANCE, 50, 4, false, false));
/*  69 */             player.addPotionEffect(new EffectInstance(Effects.HASTE, 400, 2, false, false));
/*     */             
/*  71 */             BlockState tempBlock = player.world.getBlockState(pos);
/*  72 */             if (AbilityHelper.placeBlockIfAllowed(player.world, posX, posY, posZ, Blocks.AIR, DefaultProtectionRules.CORE_FOLIAGE_ORE))
/*     */             {
/*  74 */               player.inventory.addItemStackToInventory(new ItemStack((IItemProvider)tempBlock.getBlock()));
/*  75 */               PARTICLES.spawn(player.world, posX, posY, posZ, 0.0D, 0.0D, 0.0D);
/*     */             }
/*     */           
/*     */           } 
/*     */         } 
/*     */       } 
/*     */     } else {
/*     */       
/*  83 */       Vec3d speed = WyHelper.propulsion((LivingEntity)player, 4.0D, 4.0D);
/*  84 */       player.setMotion(speed.x, 0.3D, speed.z);
/*  85 */       ((ServerPlayerEntity)player).connection.sendPacket((IPacket)new SEntityVelocityPacket((Entity)player));
/*     */     } 
/*     */     
/*  88 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   private void duringCooldown(PlayerEntity player, int cooldownTimer) {
/*  93 */     if (canDealDamage() && player.getPosY() >= this.initialY) {
/*     */       
/*  95 */       List<LivingEntity> list = WyHelper.getEntitiesNear(player.getPosition(), player.world, 1.6D, FactionHelper.getOutsideGroupPredicate((LivingEntity)player), new Class[] { LivingEntity.class });
/*  96 */       list.remove(player);
/*  97 */       list.forEach(entity -> {
/*     */             if (isTarget(entity)) {
/*     */               entity.attackEntityFrom(DamageSource.causePlayerDamage(player), 15.0F);
/*     */             }
/*     */           });
/*     */ 
/*     */       
/* 104 */       for (BlockPos location : WyHelper.getNearbyBlocks((LivingEntity)player, 3)) {
/*     */         
/* 106 */         if (location.getY() >= player.getPosY()) {
/*     */           
/* 108 */           BlockState tempBlock = player.world.getBlockState(new BlockPos(location.getX(), location.getY(), location.getZ()));
/* 109 */           if (AbilityHelper.placeBlockIfAllowed(player.world, location.getX(), location.getY(), location.getZ(), Blocks.AIR, DefaultProtectionRules.CORE_FOLIAGE_ORE)) {
/*     */             
/* 111 */             player.inventory.addItemStackToInventory(new ItemStack((IItemProvider)tempBlock.getBlock()));
/* 112 */             PARTICLES.spawn(player.world, location.getX(), location.getY(), location.getZ(), 0.0D, 0.0D, 0.0D);
/*     */           } 
/*     */         } 
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean canDealDamage() {
/* 121 */     return (this.cooldown > getMaxCooldown() * 0.8D);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void resetFallDamage(LivingEntity player) {
/* 127 */     this.hasFallDamage = true;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean hasFallDamage() {
/* 133 */     return this.hasFallDamage;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public ZoanInfo[] getRequiredForms(PlayerEntity player) {
/* 139 */     return new ZoanInfo[] { (ZoanInfo)MoguHeavyZoanInfo.INSTANCE };
/*     */   }
/*     */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\abilities\mogu\MoguraTonpoAbility.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */