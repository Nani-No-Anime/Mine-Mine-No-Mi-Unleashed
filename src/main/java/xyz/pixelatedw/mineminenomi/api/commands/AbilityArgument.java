package xyz.pixelatedw.mineminenomi.api.commands;

import com.mojang.brigadier.StringReader;
import com.mojang.brigadier.arguments.ArgumentType;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import com.mojang.brigadier.suggestion.Suggestions;
import com.mojang.brigadier.suggestion.SuggestionsBuilder;
import java.util.concurrent.CompletableFuture;
import net.minecraft.command.ISuggestionProvider;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.registry.GameRegistry;
import xyz.pixelatedw.mineminenomi.wypi.abilities.Ability;




public class AbilityArgument
  implements ArgumentType<Ability>
{
  public Ability parse(StringReader reader) throws CommandSyntaxException {
    ResourceLocation resourcelocation = ResourceLocation.read(reader);
    Ability ability = (Ability)GameRegistry.findRegistry(Ability.class).getValue(resourcelocation);
    return ability;
  }

  
  public static AbilityArgument ability() {
    return new AbilityArgument();
  }

  
  public static <S> Ability getAbility(CommandContext<S> context, String name) {
    return (Ability)context.getArgument(name, Ability.class);
  }


  
  public <S> CompletableFuture<Suggestions> listSuggestions(CommandContext<S> context, SuggestionsBuilder builder) {
    StringReader stringreader = new StringReader(builder.getInput());
    stringreader.setCursor(builder.getStart());
    
    return suggestAbility(builder);
  }

  
  private CompletableFuture<Suggestions> suggestAbility(SuggestionsBuilder builder) {
    return ISuggestionProvider.suggestIterable(GameRegistry.findRegistry(Ability.class).getKeys(), builder);
  }
}


