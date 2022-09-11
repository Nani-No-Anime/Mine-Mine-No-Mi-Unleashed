package xyz.pixelatedw.mineminenomi.mixins.client;

import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.player.ClientPlayerEntity;
import net.minecraft.client.gui.screen.DeathScreen;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.entity.LivingEntity;
import net.minecraft.util.text.ITextComponent;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import xyz.pixelatedw.mineminenomi.data.entity.devilfruit.DevilFruitCapability;
import xyz.pixelatedw.mineminenomi.data.entity.devilfruit.IDevilFruit;
import xyz.pixelatedw.mineminenomi.entities.zoan.YomiZoanInfo;
import xyz.pixelatedw.mineminenomi.init.ModAbilities;

@Mixin({DeathScreen.class})
public class DeathScreenMixin
  extends Screen
{
  @Shadow
  @Final
  public boolean isHardcoreMode;
  
  public DeathScreenMixin(ITextComponent title) {
    super(title);
  }




  
  @Inject(method = {"init"}, at = {@At("HEAD")})
  public void init(CallbackInfo callback) {
    ClientPlayerEntity clientPlayerEntity = (Minecraft.getInstance()).player;
    IDevilFruit props = DevilFruitCapability.get((LivingEntity)clientPlayerEntity);
    
    if (props.hasDevilFruit(ModAbilities.YOMI_YOMI_NO_MI) && !props.getZoanPoint().equalsIgnoreCase(YomiZoanInfo.INSTANCE.getForm()))
    {
      this.isHardcoreMode = false;
    }
  }
}


