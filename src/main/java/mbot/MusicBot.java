package mbot;

import org.javacord.api.DiscordApi;
import org.javacord.api.DiscordApiBuilder;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

//The MusicBot class is the primary class for running the discord bot

public class MusicBot {

    private static final Logger logger = LogManager.getLogger(MusicBot.class);

    public static void main(String[] args) {

        String token = System.getenv("DISCORD_API_TOKEN");
        DiscordApi api = new DiscordApiBuilder()
                .setToken(token)
                .login().join();
        //add listener
        api.addSlashCommandCreateListener(new Responder());

        /*
         Print the invite URL of bot
         System.out.println(api.createBotInvite());
        applications.commands%20 needs to be added to the permissions link
        The permission link for this bot:
        https://discord.com/oauth2/authorize?client_id=950171115270070312&scope=applications.commands%20bot&permissions=0
        */
    }
}


