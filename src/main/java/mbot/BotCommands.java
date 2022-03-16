package mbot;

import org.javacord.api.DiscordApi;
import org.javacord.api.DiscordApiBuilder;
import org.javacord.api.interaction.SlashCommand;
import org.javacord.api.interaction.SlashCommandOption;
import org.javacord.api.interaction.SlashCommandOptionChoice;
import org.javacord.api.interaction.SlashCommandOptionType;

import java.util.Arrays;

//updates the bot commands. Not necessary for the continued running of the bot
public class BotCommands {

    public static void main(String[] args){

        String token = "OTUwMTcxMTE1MjcwMDcwMzEy.YiVBzw.IsfyVOTopCVL1VuhHyX05eWToDc";

        DiscordApi api = new DiscordApiBuilder()
                .setToken(token)
                .login().join();

        SlashCommand command =
                SlashCommand.with("convert", "Command to convert music sharing links",
                                Arrays.asList(
                                        SlashCommandOption.createWithChoices(SlashCommandOptionType.STRING, "platform", "The platform to convert to", true,
                                                Arrays.asList(
                                                        SlashCommandOptionChoice.create("Spotify", "spotify"),
                                                        SlashCommandOptionChoice.create("Youtube Music", "youtubeMusic"),
                                                        SlashCommandOptionChoice.create("Youtube", "Youtube"),
                                                        SlashCommandOptionChoice.create("Apple Music", "appleMusic"),
                                                        SlashCommandOptionChoice.create("Tidal", "tidal"),
                                                        SlashCommandOptionChoice.create("Amazon Music", "amazonMusic")
                                                )
                                        ),
                                        SlashCommandOption.createStringOption("link", "The link that you want converted", true)
                                )
                        )
                        .createGlobal(api)
                        .join();

    }
}
