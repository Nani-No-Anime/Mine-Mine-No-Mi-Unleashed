/*    */ package xyz.pixelatedw.mineminenomi.api.abilities;
/*    */ 
/*    */ import java.util.UUID;
/*    */ import net.minecraft.entity.ai.attributes.AttributeModifier;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.abilities.Ability;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class AbilityAttributeModifier
/*    */   extends AttributeModifier
/*    */ {
/*    */   private Ability ability;
/*    */   
/*    */   public AbilityAttributeModifier(UUID uuid, Ability ability, String name, double amount, AttributeModifier.Operation operation) {
/* 20 */     super(uuid, name, amount, operation);
/* 21 */     this.ability = ability;
/*    */   }
/*    */ 
/*    */   
/*    */   public Ability getAbility() {
/* 26 */     return this.ability;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public AbilityAttributeModifier setSaved(boolean saved) {
/* 33 */     return (AbilityAttributeModifier)super.setSaved(saved);
/*    */   }
/*    */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\api\abilities\AbilityAttributeModifier.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */