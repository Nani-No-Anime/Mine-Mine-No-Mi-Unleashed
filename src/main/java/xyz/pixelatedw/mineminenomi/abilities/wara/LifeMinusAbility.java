/*    */ package xyz.pixelatedw.mineminenomi.abilities.wara;
/*    */ import java.lang.invoke.SerializedLambda;
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.entity.player.PlayerEntity;
/*    */ import net.minecraft.item.ItemStack;
/*    */ import net.minecraft.particles.IParticleData;
/*    */ import net.minecraft.particles.ParticleTypes;
/*    */ import net.minecraft.world.server.ServerWorld;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.HurtPassiveAbility;
/*    */ import xyz.pixelatedw.mineminenomi.api.damagesource.AbilityDamageSource;
/*    */ import xyz.pixelatedw.mineminenomi.api.helpers.DevilFruitHelper;
/*    */ import xyz.pixelatedw.mineminenomi.api.helpers.abilities.AbilityHelper;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModEffects;
import xyz.pixelatedw.mineminenomi.init.ModItems;
/*    */ import xyz.pixelatedw.mineminenomi.items.StrawDollItem;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.abilities.Ability;
/*    */ 
/*    */ public class LifeMinusAbility extends HurtPassiveAbility {
/* 20 */   public static final LifeMinusAbility INSTANCE = new LifeMinusAbility();
/*    */ 
/*    */   
/*    */   public LifeMinusAbility() {
/* 24 */     super("Life Minus", AbilityHelper.getDevilFruitCategory());
/* 25 */     setDescription("Transfers the damage taken to a Straw Doll in your inventory");
/* 26 */     hideInGUI(false);
/*    */     
/* 28 */     this.onHurtEvent = this::onHurtEvent;
/*    */   }
/*    */ 
/*    */   
/*    */   private boolean onHurtEvent(LivingEntity entity, Entity source) {
/* 33 */     if (!(entity instanceof PlayerEntity)) {
/* 34 */       return true;
/*    */     }
/* 36 */     boolean canUseDoll = (!AbilityHelper.isAffectedByWater(entity) && !entity.isPotionActive(ModEffects.ABILITY_OFF) && !DevilFruitHelper.kairosekiChecks(entity));
/* 37 */     if (!canUseDoll) {
/* 38 */       return true;
/*    */     }
/* 40 */     PlayerEntity attacked = (PlayerEntity)entity;
/*    */     
/* 42 */     for (int i = 0; i < attacked.inventory.mainInventory.size(); i++) {
/*    */       
/* 44 */       ItemStack stack = attacked.inventory.getStackInSlot(i);
/*    */       
/* 46 */       if (stack.getItem() == ModItems.STRAW_DOLL) {
/*    */         
/* 48 */         LivingEntity strawDollOwner = ((StrawDollItem)stack.getItem()).getOwner(attacked.world, attacked.getPosition(), stack);
/* 49 */         if (attacked == strawDollOwner) {
/* 50 */           return true;
/*    */         }
/* 52 */         if (strawDollOwner == null) {
/*    */           
/* 54 */           attacked.inventory.deleteStack(stack);
/*    */         }
/*    */         else {
/*    */           
/* 58 */           boolean attack = strawDollOwner.attackEntityFrom(AbilityDamageSource.causeAbilityDamage((LivingEntity)attacked, (Ability)this).setInternalDamage().setDamageIsAbsolute().setDamageBypassesArmor(), getAmount());
/*    */           
/* 60 */           if (attack && strawDollOwner.getHealth() <= 0.0F) {
/*    */             
/* 62 */             spawnParticles((ServerWorld)attacked.world, attacked.getPosX(), attacked.getPosY(), attacked.getPosZ());
/* 63 */             spawnParticles((ServerWorld)strawDollOwner.world, strawDollOwner.getPosX(), strawDollOwner.getPosY(), strawDollOwner.getPosZ());
/* 64 */             attacked.inventory.deleteStack(stack);
/*    */           } 
/* 66 */           return false;
/*    */         } 
/*    */       } 
/*    */     } 
/* 70 */     return true;
/*    */   }
/*    */ 
/*    */   
/*    */   private void spawnParticles(ServerWorld world, double posX, double posY, double posZ) {
/* 75 */     for (int i = 0; i < 5; i++) {
/*    */       
/* 77 */       double offsetX = WyHelper.randomDouble() / 2.0D;
/* 78 */       double offsetY = WyHelper.randomDouble() / 2.0D;
/* 79 */       double offsetZ = WyHelper.randomDouble() / 2.0D;
/* 80 */       world.spawnParticle((IParticleData)ParticleTypes.DRAGON_BREATH, posX + offsetX, posY + offsetY, posZ + offsetZ, 25, 0.0D, 0.0D, 0.0D, 0.1D);
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\abilities\wara\LifeMinusAbility.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */