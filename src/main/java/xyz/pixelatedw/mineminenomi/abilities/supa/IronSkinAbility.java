/*    */ package xyz.pixelatedw.mineminenomi.abilities.supa;
/*    */ 
/*    */ import java.lang.invoke.SerializedLambda;
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.potion.EffectInstance;
/*    */ import net.minecraft.util.DamageSource;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.DamagedPassiveAbility;
/*    */ import xyz.pixelatedw.mineminenomi.api.helpers.ItemsHelper;
/*    */ import xyz.pixelatedw.mineminenomi.api.helpers.abilities.AbilityHelper;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModDamageSource;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModEffects;
/*    */ 
/*    */ 
/*    */ public class IronSkinAbility
/*    */   extends DamagedPassiveAbility
/*    */ {
/* 18 */   public static final IronSkinAbility INSTANCE = new IronSkinAbility();
/*    */ 
/*    */   
/*    */   public IronSkinAbility() {
/* 22 */     super("Iron Skin", AbilityHelper.getDevilFruitCategory());
/* 23 */     setDescription("Makes the user immune to slash based attacks until a certain level");
/* 24 */     this.onDamagedEvent = this::onDamagedEvent;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean onDamagedEvent(LivingEntity entity, DamageSource damageSource) {
/* 29 */     boolean isSlashDamage = (damageSource instanceof ModDamageSource && ((ModDamageSource)damageSource).isSlashDamage());
/* 30 */     boolean isSwordDamage = false;
/*    */     
/* 32 */     if (damageSource instanceof net.minecraft.util.EntityDamageSource) {
/*    */       
/* 34 */       Entity source = damageSource.getTrueSource();
/* 35 */       if (source != null && source.isAlive() && source instanceof LivingEntity) {
/*    */         
/* 37 */         LivingEntity livingSource = (LivingEntity)source;
/* 38 */         boolean mainHand = (!livingSource.getHeldItemMainhand().isEmpty() && ItemsHelper.isSword(livingSource.getHeldItemMainhand()));
/* 39 */         boolean offHand = (!livingSource.getHeldItemOffhand().isEmpty() && ItemsHelper.isSword(livingSource.getHeldItemOffhand()));
/* 40 */         if (mainHand || offHand)
/* 41 */           isSwordDamage = true; 
/*    */       } 
/* 43 */       if (damageSource.getImmediateSource() instanceof xyz.pixelatedw.mineminenomi.entities.projectiles.extra.NormalBulletProjectile) {
/*    */         
/* 45 */         damageSource.getImmediateSource().remove();
/* 46 */         return true;
/*    */       } 
/*    */     } 
/* 49 */     float weaponDamage = Math.max(ItemsHelper.getItemDamage(entity.getHeldItemMainhand()), ItemsHelper.getItemDamage(entity.getHeldItemMainhand()));
/* 50 */     boolean result = (!isSlashDamage && !isSwordDamage && 14.0F > weaponDamage);
/*    */     
/* 52 */     if (!result) {
/* 53 */       entity.addPotionEffect(new EffectInstance(ModEffects.PHYSICAL_MOVING_GUARD, 2, 3, false, false));
/*    */     }
/* 55 */     return result;
/*    */   }
/*    */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\abilities\supa\IronSkinAbility.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */