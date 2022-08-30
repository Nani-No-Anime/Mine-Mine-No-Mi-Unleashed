/*    */ package xyz.pixelatedw.mineminenomi.api.abilities;
/*    */ 
/*    */ import java.util.Arrays;
/*    */ import java.util.List;
/*    */ import java.util.stream.Collectors;
/*    */ import javax.annotation.Nullable;
/*    */ import net.minecraft.entity.player.PlayerEntity;
/*    */ import net.minecraft.util.text.ITextComponent;
/*    */ import net.minecraft.util.text.TranslationTextComponent;
/*    */ import xyz.pixelatedw.mineminenomi.api.morph.ZoanInfo;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public interface IFormRequiredAbility
/*    */ {
/*    */   ZoanInfo[] getRequiredForms(PlayerEntity paramPlayerEntity);
/*    */   
/*    */   @Nullable
/*    */   default ITextComponent addFormRequirement() {
/*    */     TranslationTextComponent translationTextComponent=null;
/* 25 */     int forms = (getRequiredForms(null)).length;
/* 26 */     List<String> names = (List<String>)Arrays.<ZoanInfo>stream(getRequiredForms(null)).map(zoan -> zoan.getDisplayName()).collect(Collectors.toList());
/* 27 */     ITextComponent requirement = null;
/* 28 */     if (forms == 1)
/* 29 */       translationTextComponent = new TranslationTextComponent("\n\n× Can only be used while §2%s§r is active", new Object[] { names.get(0) }); 
/* 30 */     if (forms >= 2)
/* 31 */       translationTextComponent = new TranslationTextComponent("\n\n× Can only be used while §2%s§r or §2%s§r is active", new Object[] { names.get(0), names.get(1) }); 

/* 32 */     return (ITextComponent)translationTextComponent;
/*    */   }
/*    */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\api\abilities\IFormRequiredAbility.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */