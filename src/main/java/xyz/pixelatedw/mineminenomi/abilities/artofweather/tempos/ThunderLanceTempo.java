/*     */ package xyz.pixelatedw.mineminenomi.abilities.artofweather.tempos;
/*     */ import java.awt.Color;
/*     */ import java.lang.invoke.SerializedLambda;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.player.PlayerEntity;
/*     */ import net.minecraft.network.IPacket;
import net.minecraft.network.play.server.SAnimateHandPacket;
/*     */ import net.minecraft.particles.IParticleData;
/*     */ import net.minecraft.particles.ParticleType;
/*     */ import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.RayTraceResult;
/*     */ import net.minecraft.util.text.ITextComponent;
/*     */ import net.minecraft.util.text.TranslationTextComponent;
/*     */ import net.minecraft.world.World;
/*     */ import net.minecraft.world.server.ServerWorld;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.TempoAbility;
import xyz.pixelatedw.mineminenomi.api.helpers.abilities.AbilityHelper;
/*     */ import xyz.pixelatedw.mineminenomi.entities.projectiles.goro.LightningEntity;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModI18n;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModParticleTypes;
/*     */ import xyz.pixelatedw.mineminenomi.items.weapons.ClimaTactItem;
/*     */ import xyz.pixelatedw.mineminenomi.particles.data.GenericParticleData;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
/*     */ 
/*     */ public class ThunderLanceTempo extends TempoAbility {
/*  23 */   public static final ThunderLanceTempo INSTANCE = new ThunderLanceTempo();
/*     */   private boolean canShoot; public boolean canUseCheck(PlayerEntity player, TempoAbility.ICanUse check) { if (player.getHeldItemMainhand().getItem() instanceof ClimaTactItem) {
/*     */       ClimaTactItem climaTact = (ClimaTactItem)player.getHeldItemMainhand().getItem(); String tempoCombo = climaTact.checkCharge(player.getHeldItemMainhand()); return tempoCombo.equalsIgnoreCase("TTT");
/*     */     } 
/*  27 */     return false; } public ThunderLanceTempo() { super("Thunder Lance Tempo", AbilityHelper.getStyleCategory());
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  66 */     this.canShoot = false; setDescription("3 Charged Thunder Balls\nCreates a lighting bolt that goes directly to the area the user is pointing at, exploding on impact and hurting entities in its path"); setCustomTexture("tempo"); setMaxCooldown(25.0D);
/*     */     this.onUseEvent = this::onUseEvent;
/*     */     this.canUseCheck = this::canUseCheck;
/*  69 */     this.duringCooldownEvent = this::duringCooldownEvent; } private void duringCooldownEvent(PlayerEntity player, int cooldown) { if (player.getHeldItemMainhand().getItem() instanceof ClimaTactItem) {
/*     */       
/*  71 */       BlockRayTraceResult blockRayTraceResult = WyHelper.rayTraceBlocks((Entity)player, 100.0D);
/*  72 */       if (blockRayTraceResult.getType().equals(RayTraceResult.Type.BLOCK) && player
/*  73 */         .getHeldItemMainhand().getItem() instanceof ClimaTactItem && this.canShoot)
/*     */       {
/*  75 */         if (cooldown >= 0) {
/*     */           
/*  77 */           if (cooldown >= getMaxCooldown() * 0.67D && cooldown <= getMaxCooldown() * 0.67D + 1.0D) {
/*     */             
/*  79 */             this.canShoot = false;
/*  80 */             ((ServerWorld)player.world).getChunkProvider().sendToTrackingAndSelf((Entity)player, (IPacket)new SAnimateHandPacket((Entity)player, 0));
/*  81 */             int beamLength = 2;
/*  82 */             LightningEntity bolt = new LightningEntity((Entity)player, (beamLength + 100), 6.0F);
/*  83 */             bolt.setAliveTicks(10);
/*  84 */             bolt.setTargetTimeToReset(20);
/*  85 */             bolt.setDamage(12.0F);
/*  86 */             bolt.disableLightningMimic();
/*  87 */             bolt.disableExplosionKnockback();
/*  88 */             bolt.setColor(new Color(253, 208, 35, 205));
/*  89 */             bolt.setExplosion(1, false, 1.0F);
/*  90 */             bolt.setBoxSizeDivision(0.22499999403953552D);
/*  91 */             bolt.setAngle(20);
/*  92 */             bolt.setBranches(2);
/*  93 */             int segments = 5;
/*  94 */             bolt.setSegments((int)(segments + WyHelper.randomWithRange(-segments / 2, segments / 2)));
/*  95 */             player.world.addEntity((Entity)bolt);
/*     */           } 
/*     */           
/*  98 */           if (this.canShoot) {
/*     */             
/* 100 */             double i = (blockRayTraceResult.getHitVec()).x;
/* 101 */             double j = (blockRayTraceResult.getHitVec()).y;
/* 102 */             double k = (blockRayTraceResult.getHitVec()).z;
/*     */             
/* 104 */             if (player.ticksExisted % 2 == 0) {
/* 105 */               spawn(player.world, i, j, k, 0.0D, 0.0D, 0.0D);
/*     */             }
/*     */           } 
/*     */         } 
/*     */       }
/*     */     } else {
/*     */       
/* 112 */       player.sendMessage((ITextComponent)new TranslationTextComponent(ModI18n.ABILITY_MESSAGE_NEED_SORCERY_CLIMA_TACT, new Object[0]));
/* 113 */       stopCooldown(player);
/*     */     }  } private boolean onUseEvent(PlayerEntity player) { if (player.getHeldItemMainhand().getItem() instanceof ClimaTactItem) {
/*     */       ClimaTactItem climaTact = (ClimaTactItem)player.getHeldItemMainhand().getItem(); setMaxCooldown(((4 - climaTact.getLevel()) * 9)); if (climaTact.getLevel() < 2) {
/*     */         player.sendMessage((ITextComponent)new TranslationTextComponent(ModI18n.ABILITY_MESSAGE_NEED_SORCERY_CLIMA_TACT, new Object[0])); return false;
/*     */       }  this.canShoot = true;
/*     */     }  return true; }
/* 119 */   public void spawn(World world, double posX, double posY, double posZ, double motionX, double motionY, double motionZ) { int i = 0;
/* 120 */     double phi = 0.0D;
/* 121 */     double phi2 = 0.0D;
/* 122 */     double radius = 2.0D;
/*     */ 
/*     */     
/* 125 */     while (phi < Math.PI) {
/*     */       
/* 127 */       phi += 2.0943951023931953D;
/*     */       double theta;
/* 129 */       for (theta = 0.0D; theta <= 6.283185307179586D; theta += 0.7853981633974483D) {
/*     */         float red, green, blue; ParticleType<GenericParticleData> particle;
/* 131 */         double x = radius * Math.cos(theta) * Math.sin(phi) + WyHelper.randomDouble() * radius;
/* 132 */         double y = radius * Math.tan(theta) * Math.sin(phi) + WyHelper.randomDouble() * radius;
/* 133 */         double z = radius * Math.sin(theta) * Math.sin(phi) + WyHelper.randomDouble() * radius;
/*     */ 
/*     */         
/* 136 */         if (i % 2 == 0) {
/*     */           
/* 138 */           red = 0.5F;
/* 139 */           green = 0.5F;
/* 140 */           blue = 0.5F;
/* 141 */           particle = ModParticleTypes.MOKU2;
/*     */         }
/*     */         else {
/*     */           
/* 145 */           red = 0.3F;
/* 146 */           green = 0.3F;
/* 147 */           blue = 0.3F;
/* 148 */           particle = ModParticleTypes.MOKU;
/*     */         } 
/*     */         
/* 151 */         GenericParticleData data = new GenericParticleData(particle);
/* 152 */         data.setLife(25);
/* 153 */         data.setSize(8.0F);
/* 154 */         data.setColor(red, green, blue);
/*     */         
/* 156 */         WyHelper.spawnParticles(data, (ServerWorld)world, posX + x, posY + y, posZ + z);
/* 157 */         i++;
/*     */       } 
/*     */     } 
/*     */     
/* 161 */     while (phi2 < Math.PI) {
/*     */       
/* 163 */       phi2 += 2.0943951023931953D;
/*     */       double theta;
/* 165 */       for (theta = 0.0D; theta <= 6.283185307179586D; theta += 0.7853981633974483D) {
/*     */         ParticleType<GenericParticleData> particle;
/* 167 */         double x = radius * Math.cos(theta) * Math.sin(phi2) + WyHelper.randomDouble() * radius;
/* 168 */         double y = radius * Math.tan(theta) * Math.sin(phi2) + WyHelper.randomDouble() * radius;
/* 169 */         double z = radius * Math.sin(theta) * Math.sin(phi2) + WyHelper.randomDouble() * radius;
/*     */ 
/*     */         
/* 172 */         if (i % 4 == 0) {
/* 173 */           particle = ModParticleTypes.GORO_YELLOW;
/*     */         } else {
/* 175 */           particle = ModParticleTypes.GORO2_YELLOW;
/*     */         } 
/* 177 */         GenericParticleData data = new GenericParticleData(particle);
/* 178 */         data.setLife(15);
/* 179 */         data.setSize(6.0F);
/* 180 */         WyHelper.spawnParticles(data, (ServerWorld)world, posX + x, posY - 2.0D + y, posZ + z);
/* 181 */         i++;
/*     */       } 
/*     */     }  }
/*     */ 
/*     */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\abilities\artofweather\tempos\ThunderLanceTempo.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */