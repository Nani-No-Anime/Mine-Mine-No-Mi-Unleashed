package xyz.pixelatedw.mineminenomi.api.abilities;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import javax.annotation.Nullable;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import xyz.pixelatedw.mineminenomi.api.morph.ZoanInfo;







public interface IFormRequiredAbility
{
  ZoanInfo[] getRequiredForms(PlayerEntity paramPlayerEntity);
  
  @Nullable
  default ITextComponent addFormRequirement() {
    TranslationTextComponent translationTextComponent=null;
    int forms = (getRequiredForms(null)).length;
    List<String> names = (List<String>)Arrays.<ZoanInfo>stream(getRequiredForms(null)).map(zoan -> zoan.getDisplayName()).collect(Collectors.toList());
    ITextComponent requirement = null;
    if (forms == 1)
      translationTextComponent = new TranslationTextComponent("\n\n× Can only be used while §2%s§r is active", new Object[] { names.get(0) }); 
    if (forms >= 2)
      translationTextComponent = new TranslationTextComponent("\n\n× Can only be used while §2%s§r or §2%s§r is active", new Object[] { names.get(0), names.get(1) }); 

    return (ITextComponent)translationTextComponent;
  }
}


