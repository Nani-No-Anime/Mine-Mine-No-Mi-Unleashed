/*    */ package xyz.pixelatedw.mineminenomi.abilities.ope;
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.entity.player.PlayerEntity;
/*    */ import net.minecraft.util.text.ITextComponent;
/*    */ import net.minecraft.util.text.TranslationTextComponent;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModI18n;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.abilities.Ability;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.data.ability.AbilityDataCapability;
/*    */ 
/*    */ public class OpeHelper {
/*    */   public static boolean hasRoomActive(PlayerEntity entity, Ability inst) {
/* 13 */     RoomAbility ability = (RoomAbility)AbilityDataCapability.get((LivingEntity)entity).getEquippedAbility((Ability)RoomAbility.INSTANCE);
/* 14 */     if (ability == null || !ability.isEntityInThisRoom((Entity)entity)) {
/*    */       
/* 16 */       entity.sendMessage((ITextComponent)new TranslationTextComponent(ModI18n.ABILITY_MESSAGE_ONLY_IN_ROOM, new Object[] { inst.getName() }));
/* 17 */       return false;
/*    */     } 
/* 19 */     return true;
/*    */   }
/*    */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\abilities\ope\OpeHelper.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */