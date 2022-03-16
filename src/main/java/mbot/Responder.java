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
public HashMap<String, String> commandInputs = new HashMap<>();

    @Override
    public void onSlashCommandCreate(SlashCommandCreateEvent event){
        SlashCommandInteraction interaction = event.getSlashCommandInteraction();
        if (interaction.getCommandName().equals("convert")){
             commandInputs.put("platform", interaction.getOptionStringValueByName("platform").orElse(null));
             commandInputs.put("link", interaction.getOptionStringValueByName("link").orElse(null));
            String newLink = getNewLink(commandInputs.get("platform"), commandInputs.get("link"));
            interaction.createImmediateResponder()
                    .setContent(newLink)
                    .respond();
        }
    }


}
