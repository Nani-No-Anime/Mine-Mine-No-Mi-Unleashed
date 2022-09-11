package xyz.pixelatedw.mineminenomi.packets.server.ability;

import java.util.function.Supplier;
import net.minecraft.block.Blocks;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.player.ClientPlayerEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.network.PacketBuffer;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.fml.network.NetworkDirection;
import net.minecraftforge.fml.network.NetworkEvent;
import xyz.pixelatedw.mineminenomi.api.helpers.abilities.AbilityHelper;
import xyz.pixelatedw.mineminenomi.api.protection.BlockProtectionRule;
import xyz.pixelatedw.mineminenomi.api.protection.block.AirBlockProtectionRule;
import xyz.pixelatedw.mineminenomi.api.protection.block.ClientBlockProtectionRule;
import xyz.pixelatedw.mineminenomi.api.protection.block.FoliageBlockProtectionRule;
import xyz.pixelatedw.mineminenomi.api.protection.block.LiquidBlockProtectionRule;



public class SViewProtectionPacket
{
  private boolean state;
  private int[] midPoint;
  private int size;
  
  public SViewProtectionPacket() {}
  
  public SViewProtectionPacket(boolean state, int[] midPoint, int size) {
    this.state = state;
    this.midPoint = midPoint;
    this.size = size;
  }

  
  public void encode(PacketBuffer buffer) {
    buffer.writeBoolean(this.state);
    buffer.writeInt(this.midPoint[0]);
    buffer.writeInt(this.midPoint[1]);
    buffer.writeInt(this.midPoint[2]);
    buffer.writeInt(this.size);
  }

  
  public static SViewProtectionPacket decode(PacketBuffer buffer) {
    SViewProtectionPacket msg = new SViewProtectionPacket();
    msg.state = buffer.readBoolean();
    msg.midPoint = new int[] { buffer.readInt(), buffer.readInt(), buffer.readInt() };
    msg.size = buffer.readInt();
    return msg;
  }

  
  public static void handle(SViewProtectionPacket message, Supplier<NetworkEvent.Context> ctx) {
    if (((NetworkEvent.Context)ctx.get()).getDirection() == NetworkDirection.PLAY_TO_CLIENT)
    {
      ((NetworkEvent.Context)ctx.get()).enqueueWork(() -> ClientHandler.handle(message));
    }



    
    ((NetworkEvent.Context)ctx.get()).setPacketHandled(true);
  }
  
  public static class ClientHandler
  {
    private static final BlockProtectionRule GRIEF_RULE1 = (new BlockProtectionRule(new BlockProtectionRule[] { (BlockProtectionRule)AirBlockProtectionRule.INSTANCE, (BlockProtectionRule)LiquidBlockProtectionRule.INSTANCE, (BlockProtectionRule)FoliageBlockProtectionRule.INSTANCE })).setBypassGriefRule();
    private static final BlockProtectionRule GRIEF_RULE2 = (new BlockProtectionRule(new BlockProtectionRule[] { (BlockProtectionRule)ClientBlockProtectionRule.INSTANCE })).setBypassGriefRule();

    
    @OnlyIn(Dist.CLIENT)
    public static void handle(SViewProtectionPacket message) {
      ClientPlayerEntity clientPlayerEntity = (Minecraft.getInstance()).player;
      
      if (message.state) {
        
        AbilityHelper.createEmptyCube(((PlayerEntity)clientPlayerEntity).world, message.midPoint[0], message.midPoint[1], message.midPoint[2], message.size, message.size, message.size, Blocks.BLUE_STAINED_GLASS, GRIEF_RULE1);
        AbilityHelper.createEmptyCube(((PlayerEntity)clientPlayerEntity).world, message.midPoint[0], message.midPoint[1], message.midPoint[2], 1, 1, 1, Blocks.RED_STAINED_GLASS, GRIEF_RULE1);
      }
      else {
        
        AbilityHelper.createEmptyCube(((PlayerEntity)clientPlayerEntity).world, message.midPoint[0], message.midPoint[1], message.midPoint[2], message.size, message.size, message.size, Blocks.AIR, GRIEF_RULE2);
        AbilityHelper.createEmptyCube(((PlayerEntity)clientPlayerEntity).world, message.midPoint[0], message.midPoint[1], message.midPoint[2], 1, 1, 1, Blocks.AIR, GRIEF_RULE2);
      } 
    }
  }
}


