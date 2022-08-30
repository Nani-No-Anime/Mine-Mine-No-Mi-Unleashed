/*     */ package xyz.pixelatedw.mineminenomi.commands;
/*     */ import com.mojang.brigadier.CommandDispatcher;
/*     */ import com.mojang.brigadier.builder.LiteralArgumentBuilder;
/*     */ import com.mojang.brigadier.context.CommandContext;
/*     */ import com.mojang.brigadier.exceptions.CommandSyntaxException;
/*     */ import net.minecraft.command.CommandSource;
/*     */ import net.minecraft.command.Commands;
/*     */ import net.minecraft.command.arguments.EntityArgument;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.LivingEntity;
/*     */ import net.minecraft.entity.ai.attributes.AttributeModifier;
/*     */ import net.minecraft.entity.ai.attributes.IAttributeInstance;
/*     */ import net.minecraft.entity.player.PlayerEntity;
/*     */ import net.minecraft.entity.player.ServerPlayerEntity;
/*     */ import net.minecraft.network.IPacket;
/*     */ import net.minecraft.network.play.server.SPlayerAbilitiesPacket;
import net.minecraft.util.text.ITextComponent;
/*     */ import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TextFormatting;
/*     */ import net.minecraftforge.common.MinecraftForge;
/*     */ import net.minecraftforge.event.entity.EntityEvent;
import xyz.pixelatedw.mineminenomi.abilities.SpecialFlyAbility;
/*     */ import xyz.pixelatedw.mineminenomi.api.helpers.abilities.AbilityHelper;
/*     */ import xyz.pixelatedw.mineminenomi.config.CommonConfig;
/*     */ import xyz.pixelatedw.mineminenomi.data.entity.devilfruit.DevilFruitCapability;
/*     */ import xyz.pixelatedw.mineminenomi.data.entity.devilfruit.IDevilFruit;
/*     */ import xyz.pixelatedw.mineminenomi.data.world.ExtendedWorldData;
/*     */ import xyz.pixelatedw.mineminenomi.packets.server.SSyncDevilFruitPacket;
/*     */ import xyz.pixelatedw.mineminenomi.packets.server.ability.SRecalculateEyeHeightPacket;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.APIConfig;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.abilities.Ability;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.abilities.ChargeableAbility;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.abilities.ContinuousAbility;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.data.ability.AbilityDataCapability;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.data.ability.IAbilityData;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.debug.WyDebug;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.network.WyNetwork;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.network.packets.server.SSyncAbilityDataPacket;
/*     */ 
/*     */ public class RemoveDFCommand {
/*     */   public static void register(CommandDispatcher<CommandSource> dispatcher) {
/*  39 */     LiteralArgumentBuilder<CommandSource> builder = (LiteralArgumentBuilder<CommandSource>)Commands.literal("removedf").requires(source -> source.hasPermissionLevel(2));
/*     */     
/*  41 */     ((LiteralArgumentBuilder)builder
/*  42 */       .executes(context -> removesDF(context, ((CommandSource)context.getSource()).asPlayer())))
/*  43 */       .then(Commands.argument("target", EntityArgument.player())
/*  44 */         .executes(context -> removesDF(context, EntityArgument.getPlayer(context, "target"))));
/*     */     
/*  46 */     dispatcher.register(builder);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private static int removesDF(CommandContext<CommandSource> context, ServerPlayerEntity player) throws CommandSyntaxException {
/*     */     try {
/*  53 */       IDevilFruit devilFruitProps = DevilFruitCapability.get((LivingEntity)player);
/*  54 */       IAbilityData abilityDataProps = AbilityDataCapability.get((LivingEntity)player);
/*  55 */       ExtendedWorldData worldData = ExtendedWorldData.get(player.world);
/*     */       
/*  57 */       worldData.removeAteDevilFruit((PlayerEntity)player);
/*  58 */       worldData.removeDevilFruitInWorld(devilFruitProps.getDevilFruit());
/*     */       
/*  60 */       devilFruitProps.setDevilFruit("");
/*  61 */       devilFruitProps.setLogia(false);
/*  62 */       devilFruitProps.setZoanPoint("");
/*  63 */       devilFruitProps.setYamiPower(false);
/*     */       
/*  65 */       for (Ability ability : abilityDataProps.getEquippedAbilities(APIConfig.AbilityCategory.ALL)) {
/*     */         
/*  67 */         if (ability != null) {
/*     */           
/*  69 */           if (ability instanceof ContinuousAbility)
/*  70 */             ((ContinuousAbility)ability).stopContinuity((PlayerEntity)player); 
/*  71 */           if (ability instanceof ChargeableAbility) {
/*  72 */             ((ChargeableAbility)ability).stopCharging((PlayerEntity)player);
/*     */           } else {
/*  74 */             ability.stopCooldown((PlayerEntity)player);
/*     */           } 
/*     */         } 
/*     */       } 
/*  78 */       if (CommonConfig.INSTANCE.isSpecialFlyingEnabled() && abilityDataProps.hasUnlockedAbility((Ability)SpecialFlyAbility.INSTANCE) && !player.isCreative() && !player.isSpectator()) {
/*     */         
/*  80 */         player.abilities.allowFlying = false;
/*  81 */         player.abilities.isFlying = false;
/*  82 */         player.connection.sendPacket((IPacket)new SPlayerAbilitiesPacket(player.abilities));
/*     */       } 
/*     */       
/*  85 */       abilityDataProps.clearUnlockedAbilities(AbilityHelper.getDevilFruitCategory());
/*  86 */       abilityDataProps.clearEquippedAbilities(AbilityHelper.getDevilFruitCategory());
/*     */       
/*  88 */       if (WyDebug.isDebug()) {
/*  89 */         player.clearActivePotions();
/*     */       }
/*  91 */       WyNetwork.sendToAllTrackingAndSelf(new SSyncDevilFruitPacket(player.getEntityId(), devilFruitProps), (LivingEntity)player);
/*  92 */       WyNetwork.sendToAllTrackingAndSelf(new SSyncAbilityDataPacket(player.getEntityId(), abilityDataProps), (LivingEntity)player);
/*     */       
/*  94 */       MinecraftForge.EVENT_BUS.post(new EntityEvent.EyeHeight((Entity)player, player.getPose(), player.getSize(player.getPose()), player.getHeight()));
/*  95 */       WyNetwork.sendToAllTrackingAndSelf(new SRecalculateEyeHeightPacket(player.getEntityId()), (LivingEntity)player);
/*  96 */       player.recalculateSize();
/*     */ 
/*     */       //[todo : figure this out]
/*  99 */       //player.getAttributes().getAllAttributes().stream().forEach(attr -> attr.func_225505_c_().stream().forEach(()->{}));
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 105 */       ((CommandSource)context.getSource()).sendFeedback((ITextComponent)new StringTextComponent(TextFormatting.GREEN + "Removed Devil Fruit for " + player.getDisplayName().getFormattedText()), true);
/*     */     }
/* 107 */     catch (Exception e) {
/*     */       
/* 109 */       e.printStackTrace();
/*     */     } 
/*     */     
/* 112 */     return 1;
/*     */   }
/*     */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\commands\RemoveDFCommand.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */