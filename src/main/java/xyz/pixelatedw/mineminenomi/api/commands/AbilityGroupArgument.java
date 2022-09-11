package xyz.pixelatedw.mineminenomi.api.commands;

import com.mojang.brigadier.StringReader;
import com.mojang.brigadier.arguments.ArgumentType;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import com.mojang.brigadier.suggestion.Suggestions;
import com.mojang.brigadier.suggestion.SuggestionsBuilder;
import java.util.Collection;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import net.minecraft.command.ISuggestionProvider;
import xyz.pixelatedw.mineminenomi.api.enums.AbilityCommandGroup;



public class AbilityGroupArgument<T extends Enum<T>>
  implements ArgumentType<T>
{
  private final Class<T> enumClass;
  
  public static AbilityGroupArgument<AbilityCommandGroup> abilityGroup() {
    return new AbilityGroupArgument<>(AbilityCommandGroup.class);
  }

  
  public AbilityGroupArgument(Class<T> enumClass) {
    this.enumClass = enumClass;
  }


  
  public T parse(StringReader reader) throws CommandSyntaxException {
    return Enum.valueOf(this.enumClass, reader.readUnquotedString());
  }


  
  public <S> CompletableFuture<Suggestions> listSuggestions(CommandContext<S> context, SuggestionsBuilder builder) {
    return ISuggestionProvider.suggest(Stream.<Enum>of((Enum[])this.enumClass.getEnumConstants()).map(Object::toString), builder);
  }


  
  public Collection<String> getExamples() {
    return (Collection<String>)Stream.<Enum>of((Enum[])this.enumClass.getEnumConstants()).map(Object::toString).collect(Collectors.toList());
  }
}


