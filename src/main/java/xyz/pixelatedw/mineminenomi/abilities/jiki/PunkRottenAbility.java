/*    */ package xyz.pixelatedw.mineminenomi.abilities.jiki;
/*    */ 
/*    */ import java.util.ArrayList;
/*    */ import java.util.List;
/*    */ import java.util.UUID;
/*    */ import net.minecraft.entity.SharedMonsterAttributes;
/*    */ import net.minecraft.entity.ai.attributes.AttributeModifier;
/*    */ import net.minecraft.item.ItemStack;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityAttributeModifier;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.ZoanAbility;
/*    */ import xyz.pixelatedw.mineminenomi.api.helpers.abilities.AbilityHelper;
/*    */ import xyz.pixelatedw.mineminenomi.api.morph.ZoanInfo;
/*    */ import xyz.pixelatedw.mineminenomi.entities.zoan.PunkCornaDioZoanInfo;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModAttributes;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.abilities.Ability;
/*    */ 
/*    */ public class PunkRottenAbility
/*    */   extends ZoanAbility {
/* 19 */   public static final PunkRottenAbility INSTANCE = new PunkRottenAbility();
/*    */   
/*    */   private static final int MAX_ITEMS = 500;
/* 22 */   private static final AbilityAttributeModifier DAMAGE_REDUCTION = (new AbilityAttributeModifier(UUID.fromString("e86edafe-c7a7-4e92-a4d2-84ad975660d6"), (Ability)INSTANCE, "Punk Rotten Damage Reduction Modifier", 0.800000011920929D, AttributeModifier.Operation.ADDITION)).setSaved(false);
/* 23 */   private static final AbilityAttributeModifier DEFENSE_MODIFIER = (new AbilityAttributeModifier(UUID.fromString("6995759e-2fcf-4ae7-a760-e8127de1cced"), (Ability)INSTANCE, "Punk Rotten Defense Modifier", 20.0D, AttributeModifier.Operation.ADDITION)).setSaved(false);
/* 24 */   private static final AbilityAttributeModifier ATTACK_MODIFIER = (new AbilityAttributeModifier(UUID.fromString("4141adbf-5f80-4c54-aa85-13c0c5f57619"), (Ability)INSTANCE, "Punk Rotten Attack Modifier", 25.0D, AttributeModifier.Operation.ADDITION)).setSaved(false);
/* 25 */   private static final AbilityAttributeModifier SPEED_MODIFIER = (new AbilityAttributeModifier(UUID.fromString("4141adbf-5f80-4c54-aa85-13c0c5f57619"), (Ability)INSTANCE, "Punk Rotten Speed Modifier", -0.5D, AttributeModifier.Operation.ADDITION)).setSaved(false);
/*    */   
/* 27 */   private List<ItemStack> magneticItems = new ArrayList<>();
/*    */ 
/*    */   
/*    */   public PunkRottenAbility() {
/* 31 */     super("Punk Rotten", AbilityHelper.getDevilFruitCategory());
/* 32 */     setDescription("Uses §2500§r magnetic items from the user's inventory to create a tall iron mech which will heavily increase the user's offensive as well as defensive capabilities.");
/* 33 */     addZoanModifier(ModAttributes.DAMAGE_REDUCTION, (AttributeModifier)DAMAGE_REDUCTION);
/* 34 */     addZoanModifier(SharedMonsterAttributes.ARMOR, (AttributeModifier)DEFENSE_MODIFIER);
/* 35 */     addZoanModifier(SharedMonsterAttributes.ARMOR_TOUGHNESS, (AttributeModifier)DEFENSE_MODIFIER);
/* 36 */     addZoanModifier(SharedMonsterAttributes.ATTACK_DAMAGE, (AttributeModifier)ATTACK_MODIFIER);
/* 37 */     addZoanModifier(SharedMonsterAttributes.MOVEMENT_SPEED, (AttributeModifier)SPEED_MODIFIER);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public ZoanInfo getTransformation() {
/* 43 */     return (ZoanInfo)PunkCornaDioZoanInfo.INSTANCE;
/*    */   }
/*    */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\abilities\jiki\PunkRottenAbility.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */