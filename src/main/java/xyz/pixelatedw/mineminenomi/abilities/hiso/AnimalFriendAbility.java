/*    */ package xyz.pixelatedw.mineminenomi.abilities.hiso;
/*    */ 
/*    */ import java.lang.invoke.SerializedLambda;
/*    */ import java.lang.reflect.Method;
/*    */ import java.util.List;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.entity.passive.OcelotEntity;
/*    */ import net.minecraft.entity.passive.TameableEntity;
/*    */ import net.minecraft.entity.passive.horse.AbstractHorseEntity;
/*    */ import net.minecraft.entity.player.PlayerEntity;
/*    */ import net.minecraft.particles.BasicParticleType;
/*    */ import net.minecraft.particles.IParticleData;
/*    */ import net.minecraft.particles.ParticleTypes;
/*    */ import net.minecraft.world.server.ServerWorld;
/*    */ import net.minecraftforge.fml.common.ObfuscationReflectionHelper;
/*    */ import xyz.pixelatedw.mineminenomi.api.helpers.abilities.AbilityHelper;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.abilities.Ability;
/*    */ 
/*    */ public class AnimalFriendAbility extends Ability {
/* 21 */   public static final AnimalFriendAbility INSTANCE = new AnimalFriendAbility();
/*    */ 
/*    */   
/*    */   public AnimalFriendAbility() {
/* 25 */     super("Animal Friend", AbilityHelper.getDevilFruitCategory());
/* 26 */     setDescription("Allows the user to tame all nearby tameable entities");
/* 27 */     setMaxCooldown(10.0D);
/*    */     
/* 29 */     this.onUseEvent = this::onUseEvent;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   private boolean onUseEvent(PlayerEntity player) {
/* 35 */     List<TameableEntity> targets = WyHelper.getEntitiesNear(player.getPosition(), player.world, 10.0D, new Class[] { TameableEntity.class });
/* 36 */     for (TameableEntity target : targets) {
/*    */       
/* 38 */       target.setTamedBy(player);
/* 39 */       spawnHearts((LivingEntity)target);
/*    */     } 
/*    */ 
/*    */     
/* 43 */     List<AbstractHorseEntity> horses = WyHelper.getEntitiesNear(player.getPosition(), player.world, 10.0D, new Class[] { AbstractHorseEntity.class });
/* 44 */     for (AbstractHorseEntity target : horses) {
/*    */       
/* 46 */       target.setTamedBy(player);
/* 47 */       spawnHearts((LivingEntity)target);
/*    */     } 
/*    */ 
/*    */     
/* 51 */     List<OcelotEntity> ocelots = WyHelper.getEntitiesNear(player.getPosition(), player.world, 10.0D, new Class[] { OcelotEntity.class });
/* 52 */     for (OcelotEntity target : ocelots) {
/*    */       
/* 54 */       Method method = ObfuscationReflectionHelper.findMethod(OcelotEntity.class, "setTrusting", new Class[] { boolean.class });
/*    */       
/*    */       try {
/* 57 */         method.setAccessible(true);
/* 58 */         method.invoke(target, new Object[] { Boolean.valueOf(true) });
/*    */       }
/* 60 */       catch (Exception e) {
/*    */         
/* 62 */         e.printStackTrace();
/*    */       } 
/* 64 */       spawnHearts((LivingEntity)target);
/*    */     } 
/*    */     
/* 67 */     return true;
/*    */   }
/*    */ 
/*    */   
/*    */   private void spawnHearts(LivingEntity target) {
/* 72 */     BasicParticleType basicParticleType = ParticleTypes.HEART;
/*    */     
/* 74 */     for (int i = 0; i < 7; i++)
/* 75 */       WyHelper.spawnParticles(basicParticleType, (ServerWorld)target.world, target.getPosXRandom(1.0D), target.getPosYRandom() + 0.5D, target.getPosZRandom(1.0D)); 
/*    */   }
/*    */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\abilities\hiso\AnimalFriendAbility.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */