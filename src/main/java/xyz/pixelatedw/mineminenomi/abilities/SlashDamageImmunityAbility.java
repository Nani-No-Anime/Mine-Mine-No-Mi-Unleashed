/*    */ package xyz.pixelatedw.mineminenomi.abilities;
/*    */ 
/*    */ import java.lang.invoke.SerializedLambda;
/*    */ import javax.annotation.Nullable;
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.entity.SharedMonsterAttributes;
/*    */ import net.minecraft.inventory.EquipmentSlotType;
/*    */ import net.minecraft.item.ItemStack;
/*    */ import net.minecraft.util.DamageSource;
/*    */ import net.minecraft.util.EntityDamageSource;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.DamagedPassiveAbility;
/*    */ import xyz.pixelatedw.mineminenomi.api.helpers.ItemsHelper;
/*    */ import xyz.pixelatedw.mineminenomi.api.helpers.abilities.AbilityHelper;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModDamageSource;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.APIConfig;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.abilities.Ability;
/*    */ 
/*    */ public class SlashDamageImmunityAbility
/*    */   extends DamagedPassiveAbility {
/* 21 */   public static final SlashDamageImmunityAbility BARA_INSTANCE = new SlashDamageImmunityAbility(AbilityHelper.getDevilFruitCategory());
/*    */   
/*    */   private APIConfig.AbilityCategory category;
/*    */ 
/*    */   
/*    */   public SlashDamageImmunityAbility(APIConfig.AbilityCategory category) {
/* 27 */     super("Slash Immunity", category);
/* 28 */     this.category = category;
/* 29 */     setDescription("Makes the user immune to slash based attacks");
/*    */     
/* 31 */     hideInGUI(false);
/* 32 */     this.onDamagedEvent = this::onDamagedEvent;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean onDamagedEvent(LivingEntity entity, DamageSource damageSource) {
/* 37 */     boolean isSlashDamage = (damageSource instanceof ModDamageSource && ((ModDamageSource)damageSource).isSlashDamage() && !((ModDamageSource)damageSource).isInternalDamage());
/* 38 */     boolean isSwordDamage = false;
/*    */     
/* 40 */     if (damageSource instanceof EntityDamageSource) {
/*    */       
/* 42 */       Entity source = ((EntityDamageSource)damageSource).getTrueSource();
/* 43 */       if (source != null && source.isAlive() && source instanceof LivingEntity) {
/*    */         
/* 45 */         LivingEntity livingSource = (LivingEntity)source;
/*    */         
/* 47 */         isSwordDamage |= checkItemStack(livingSource.getHeldItemMainhand());
/* 48 */         isSwordDamage |= checkItemStack(livingSource.getHeldItemOffhand());
/*    */       } 
/*    */     } 
/*    */     
/* 52 */     if (isSlashDamage || isSwordDamage) {
/* 53 */       return false;
/*    */     }
/* 55 */     return true;
/*    */   }
/*    */ 
/*    */   
/*    */   private boolean checkItemStack(ItemStack stack) {
/* 60 */     if (stack != null && !stack.isEmpty()) {
/*    */       
/* 62 */       boolean isSword = ItemsHelper.isSword(stack);
/* 63 */       boolean hasDamageAttribute = (stack.getAttributeModifiers(EquipmentSlotType.MAINHAND).get(SharedMonsterAttributes.ATTACK_DAMAGE.getName()).size() > 0);
/*    */       
/* 65 */       if (isSword || hasDamageAttribute) {
/* 66 */         return true;
/*    */       }
/*    */     } 
/* 69 */     return false;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   @Nullable
/*    */   public Ability create() {
/*    */     try {
/* 78 */       return getClass().getConstructor(new Class[] { APIConfig.AbilityCategory.class }).newInstance(new Object[] { this.category });
/*    */     }
/* 80 */     catch (Exception ex) {
/*    */       
/* 82 */       System.out.println("Exception raised for " + getDisplayName());
/* 83 */       ex.printStackTrace();
/*    */       
/* 85 */       return null;
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\abilities\SlashDamageImmunityAbility.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */