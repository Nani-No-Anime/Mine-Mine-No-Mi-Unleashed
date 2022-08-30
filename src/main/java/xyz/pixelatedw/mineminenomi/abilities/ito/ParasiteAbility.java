/*    */ package xyz.pixelatedw.mineminenomi.abilities.ito;
/*    */ 
/*    */ import java.lang.invoke.SerializedLambda;
/*    */ import java.util.List;
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.entity.player.PlayerEntity;
/*    */ import net.minecraft.potion.EffectInstance;
/*    */ import net.minecraft.potion.Effects;
/*    */ import net.minecraft.util.math.EntityRayTraceResult;
/*    */ import net.minecraft.util.math.RayTraceResult;
/*    */ import xyz.pixelatedw.mineminenomi.api.helpers.FactionHelper;
/*    */ import xyz.pixelatedw.mineminenomi.api.helpers.abilities.AbilityHelper;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModEffects;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.abilities.Ability;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.data.ability.AbilityDataCapability;
/*    */ 
/*    */ public class ParasiteAbility
/*    */   extends Ability {
/* 21 */   public static final ParasiteAbility INSTANCE = new ParasiteAbility();
/*    */ 
/*    */   
/*    */   public ParasiteAbility() {
/* 25 */     super("Parasite", AbilityHelper.getDevilFruitCategory());
/* 26 */     setDescription("By attaching your strings to nearby enemies, they get completely immobilized");
/* 27 */     setMaxCooldown(30.0D);
/*    */     
/* 29 */     this.onUseEvent = this::onUseEvent;
/*    */   }
/*    */ 
/*    */   
/*    */   private boolean onUseEvent(PlayerEntity player) {
/* 34 */     TorikagoAbility ability = (TorikagoAbility)AbilityDataCapability.get((LivingEntity)player).getEquippedAbility((Ability)TorikagoAbility.INSTANCE);
/*    */     
/* 36 */     if (ability != null && ability.isEntityInThisTorikago((Entity)player)) {
/*    */       
/* 38 */       List<LivingEntity> targets = WyHelper.getEntitiesNear(player.getPosition(), player.world, 30.0D, FactionHelper.getOutsideGroupPredicate((LivingEntity)player), new Class[] { LivingEntity.class });
/* 39 */       targets.remove(player);
/* 40 */       for (LivingEntity target : targets) {
/*    */         
/* 42 */         if (!(target instanceof xyz.pixelatedw.mineminenomi.entities.mobs.ability.BlackKnightEntity) && ability.isEntityInThisTorikago((Entity)target)) {
/*    */           
/* 44 */           target.addPotionEffect(new EffectInstance(Effects.BLINDNESS, 300, 0, false, false));
/* 45 */           target.addPotionEffect(new EffectInstance(ModEffects.MOVEMENT_BLOCKED, 300, 0, false, false));
/*    */         } 
/*    */       } 
/* 48 */       setMaxCooldown(30.0D);
/*    */     }
/*    */     else {
/*    */       
/* 52 */       EntityRayTraceResult trace = WyHelper.rayTraceEntities((Entity)player, 16.0D);
/* 53 */       if (trace.getType().equals(RayTraceResult.Type.ENTITY) && trace.getEntity() instanceof LivingEntity) {
/*    */         
/* 55 */         LivingEntity target = (LivingEntity)trace.getEntity();
/* 56 */         if (!(target instanceof xyz.pixelatedw.mineminenomi.entities.mobs.ability.BlackKnightEntity)) {
/*    */           
/* 58 */           target.addPotionEffect(new EffectInstance(Effects.BLINDNESS, 300, 0, false, false));
/* 59 */           target.addPotionEffect(new EffectInstance(ModEffects.MOVEMENT_BLOCKED, 300, 0, false, false));
/* 60 */           setMaxCooldown(15.0D);
/*    */         } 
/*    */       } else {
/*    */         
/* 64 */         return false;
/*    */       } 
/*    */     } 
/* 67 */     return true;
/*    */   }
/*    */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\abilities\ito\ParasiteAbility.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */