package mbot;

import org.javacord.api.DiscordApi;
import org.javacord.api.DiscordApiBuilder;

//The MusicBot class is the primary class for running the discord bot

public class MusicBot {

    public static void main(String[] args) {

        String secret = "DISCORD-API-KEY";
        String token = Vault.getValue(secret);
        System.out.println(token);
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


