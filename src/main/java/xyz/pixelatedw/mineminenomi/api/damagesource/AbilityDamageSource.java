/*    */ package xyz.pixelatedw.mineminenomi.api.damagesource;
/*    */ 
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.util.text.ITextComponent;
/*    */ import net.minecraft.util.text.TranslationTextComponent;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.abilities.Ability;
/*    */ 
/*    */ public class AbilityDamageSource
/*    */   extends ModEntityDamageSource
/*    */ {
/*    */   private Ability ability;
/*    */   
/*    */   public AbilityDamageSource(String damageType, Entity source, Ability ability) {
/* 15 */     super(damageType, source);
/* 16 */     this.ability = ability;
/*    */   }
/*    */ 
/*    */   
/*    */   public Ability getAbilitySource() {
/* 21 */     return this.ability;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public ITextComponent getDeathMessage(LivingEntity entityLivingBaseIn) {
/* 27 */     String s = "death.attack." + this.damageType;
/* 28 */     return (ITextComponent)new TranslationTextComponent(s, new Object[] { entityLivingBaseIn.getDisplayName(), this.damageSourceEntity.getDisplayName(), this.ability.getDisplayName() });
/*    */   }
/*    */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\api\damagesource\AbilityDamageSource.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */