/*    */ package xyz.pixelatedw.mineminenomi.api.commands;
/*    */ 
/*    */ import com.mojang.brigadier.StringReader;
/*    */ import com.mojang.brigadier.arguments.ArgumentType;
/*    */ import com.mojang.brigadier.context.CommandContext;
/*    */ import com.mojang.brigadier.exceptions.CommandSyntaxException;
/*    */ import com.mojang.brigadier.suggestion.Suggestions;
/*    */ import com.mojang.brigadier.suggestion.SuggestionsBuilder;
/*    */ import java.util.concurrent.CompletableFuture;
/*    */ import net.minecraft.command.ISuggestionProvider;
/*    */ import net.minecraft.util.ResourceLocation;
/*    */ import net.minecraftforge.fml.common.registry.GameRegistry;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.abilities.Ability;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class AbilityArgument
/*    */   implements ArgumentType<Ability>
/*    */ {
/*    */   public Ability parse(StringReader reader) throws CommandSyntaxException {
/* 22 */     ResourceLocation resourcelocation = ResourceLocation.read(reader);
/* 23 */     Ability ability = (Ability)GameRegistry.findRegistry(Ability.class).getValue(resourcelocation);
/* 24 */     return ability;
/*    */   }
/*    */ 
/*    */   
/*    */   public static AbilityArgument ability() {
/* 29 */     return new AbilityArgument();
/*    */   }
/*    */ 
/*    */   
/*    */   public static <S> Ability getAbility(CommandContext<S> context, String name) {
/* 34 */     return (Ability)context.getArgument(name, Ability.class);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public <S> CompletableFuture<Suggestions> listSuggestions(CommandContext<S> context, SuggestionsBuilder builder) {
/* 40 */     StringReader stringreader = new StringReader(builder.getInput());
/* 41 */     stringreader.setCursor(builder.getStart());
/*    */     
/* 43 */     return suggestAbility(builder);
/*    */   }
/*    */ 
/*    */   
/*    */   private CompletableFuture<Suggestions> suggestAbility(SuggestionsBuilder builder) {
/* 48 */     return ISuggestionProvider.suggestIterable(GameRegistry.findRegistry(Ability.class).getKeys(), builder);
/*    */   }
/*    */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\api\commands\AbilityArgument.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */