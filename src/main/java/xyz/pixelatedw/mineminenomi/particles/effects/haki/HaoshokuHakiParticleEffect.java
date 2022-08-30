/*     */ package xyz.pixelatedw.mineminenomi.particles.effects.haki;
/*     */ 
/*     */ import java.util.Random;
/*     */ import net.minecraft.particles.IParticleData;
/*     */ import net.minecraft.world.World;
/*     */ import net.minecraft.world.server.ServerWorld;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModParticleTypes;
/*     */ import xyz.pixelatedw.mineminenomi.particles.data.GenericParticleData;
/*     */ import xyz.pixelatedw.mineminenomi.particles.effects.ParticleEffect;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
/*     */ 
/*     */ public class HaoshokuHakiParticleEffect
/*     */   extends ParticleEffect
/*     */ {
/*     */   private int level;
/*     */   
/*     */   public HaoshokuHakiParticleEffect(int level) {
/*  18 */     this.level = level;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void spawn(World world, double posX, double posY, double posZ, double motionX, double motionY, double motionZ) {
/*  24 */     double phi = 0.0D;
/*     */     
/*  26 */     double radius = 1.0D;
/*  27 */     Random rand = world.rand;
/*     */     
/*  29 */     int i = 0;
/*  30 */     while (phi < 1.0D) {
/*     */       
/*  32 */       phi += 1.5707963267948966D;
/*     */       double theta;
/*  34 */       for (theta = 0.0D; theta <= 6.283185307179586D; theta += 0.04908738521234052D) {
/*     */         GenericParticleData data;
/*  36 */         double x = phi * Math.cos(theta);
/*  37 */         double y = WyHelper.randomDouble() / 2.0D;
/*  38 */         double z = phi * Math.sin(theta);
/*     */         
/*  40 */         motionX = x * 1.2D;
/*  41 */         motionY = 0.2D + rand.nextDouble() / 8.0D;
/*  42 */         motionZ = z * 1.2D;
/*     */ 
/*     */         
/*  45 */         if (i % 3 == 0) {
/*  46 */           data = new GenericParticleData(ModParticleTypes.GASU2);
/*     */         } else {
/*  48 */           data = new GenericParticleData(ModParticleTypes.MOKU);
/*  49 */         }  data.setLife(20);
/*  50 */         data.setSize(8.0F);
/*  51 */         data.setMotion(motionX, motionY / 2.0D, motionZ);
/*  52 */         data.setColor(0.7F, 0.0F, 0.7F, 0.5F);
/*  53 */         WyHelper.spawnParticles(data, (ServerWorld)world, posX + x, posY - 0.2D + y, posZ + z);
/*     */         
/*  55 */         if (this.level >= 2) {
/*     */           
/*  57 */           if (i % 3 == 0) {
/*  58 */             data = new GenericParticleData(ModParticleTypes.GASU2);
/*     */           } else {
/*  60 */             data = new GenericParticleData(ModParticleTypes.MOKU);
/*  61 */           }  data.setLife(20);
/*  62 */           data.setSize(7.0F);
/*  63 */           data.setMotion(motionX, motionY / 1.5D, motionZ);
/*  64 */           data.setColor(0.7F, 0.0F, 0.7F, 0.5F);
/*  65 */           WyHelper.spawnParticles(data, (ServerWorld)world, posX + x * 1.35D, posY + 0.2D + y, posZ + z * 1.35D);
/*     */           
/*  67 */           if (i % 3 == 0) {
/*  68 */             data = new GenericParticleData(ModParticleTypes.GASU2);
/*     */           } else {
/*  70 */             data = new GenericParticleData(ModParticleTypes.MOKU);
/*  71 */           }  data.setLife(20);
/*  72 */           data.setSize(7.0F);
/*  73 */           data.setMotion(motionX, motionY, motionZ);
/*  74 */           data.setColor(0.7F, 0.0F, 0.7F, 0.5F);
/*  75 */           WyHelper.spawnParticles(data, (ServerWorld)world, posX + x * 1.25D, posY + 1.2D + y, posZ + z * 1.25D);
/*     */           
/*  77 */           if (i % 3 == 0) {
/*  78 */             data = new GenericParticleData(ModParticleTypes.GASU2);
/*     */           } else {
/*  80 */             data = new GenericParticleData(ModParticleTypes.MOKU);
/*  81 */           }  data.setLife(20);
/*  82 */           data.setSize(8.0F);
/*  83 */           data.setMotion(motionX, motionY, motionZ);
/*  84 */           data.setColor(0.7F, 0.0F, 0.7F, 0.5F);
/*  85 */           WyHelper.spawnParticles(data, (ServerWorld)world, posX + x * 0.85D, posY + 2.2D + y, posZ + z * 0.85D);
/*     */         } 
/*     */         
/*  88 */         if (this.level >= 3)
/*     */         {
/*  90 */           for (int n = 0; n < 12; n++) {
/*     */             
/*  92 */             x = radius * Math.cos(theta) * Math.sin(phi) + WyHelper.randomDouble();
/*  93 */             y = radius * Math.cos(phi) + WyHelper.randomDouble() * 2.0D;
/*  94 */             z = radius * Math.sin(theta) * Math.sin(phi) + WyHelper.randomDouble();
/*     */             
/*  96 */             if (i % 3 == 0) {
/*  97 */               data = new GenericParticleData(ModParticleTypes.GASU);
/*     */             } else {
/*  99 */               data = new GenericParticleData(ModParticleTypes.YUKI);
/* 100 */             }  data.setLife(18);
/* 101 */             data.setSize(5.0F);
/* 102 */             data.setMotion(motionX / 1.1D, 0.3D, motionZ / 1.1D);
/* 103 */             data.setColor(0.7F, 0.0F, 0.7F);
/* 104 */             WyHelper.spawnParticles(data, (ServerWorld)world, posX + x, posY - 0.5D + y, posZ + z);
/*     */             
/* 106 */             motionY = Math.abs(y * 1.01D);
/*     */             
/* 108 */             if (i % 3 == 0) {
/* 109 */               data = new GenericParticleData(ModParticleTypes.GASU);
/*     */             } else {
/* 111 */               data = new GenericParticleData(ModParticleTypes.YUKI);
/* 112 */             }  data.setLife(18);
/* 113 */             data.setSize(5.0F);
/* 114 */             data.setMotion(motionX / 1.1D, motionY / 1.5D, motionZ / 1.1D);
/* 115 */             data.setColor(0.7F, 0.0F, 0.7F);
/* 116 */             WyHelper.spawnParticles(data, (ServerWorld)world, posX + x, posY - 0.5D + y, posZ + z);
/*     */           } 
/*     */         }
/* 119 */         i++;
/*     */       } 
/*     */     } 
/*     */   }
/*     */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\particles\effects\haki\HaoshokuHakiParticleEffect.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */