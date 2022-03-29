package mbot;

import org.javacord.api.event.interaction.SlashCommandCreateEvent;
import org.javacord.api.interaction.SlashCommandInteraction;
import org.javacord.api.listener.interaction.SlashCommandCreateListener;
import java.util.HashMap;
import static mbot.LinkConverter.getNewLink;

/*
creates a custom responder class that watches for the commands established
in BotCommands.java
*/

public class Responder  implements SlashCommandCreateListener {

    @Override
    public void onSlashCommandCreate(SlashCommandCreateEvent event){
        SlashCommandInteraction interaction = event.getSlashCommandInteraction();
        if (interaction.getCommandName().equals("convert")){
            String newLink = getNewLink(
                    interaction.getOptionStringValueByName("platform").orElse(null),
                    interaction.getOptionStringValueByName("link").orElse(null));
            interaction.createImmediateResponder()
                    .setContent(newLink)
                    .respond();
        }
    }


}
