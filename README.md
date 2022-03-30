# Discord Music Link Bot

This project was created to make it easier to share music links through Discord. There are a lot of streaming platforms now and it makes it a bit laborious to share song links with your friends. This bot will convert a shared music link to the platform of your choice so that your recipient can immediately play it!

## Getting Started

### Add to your server

Use this link to invite the bot to your server: [Bot Invite Link](https://discord.com/oauth2/authorize?client_id=950171115270070312&scope=applications.commands%20bot&permissions=0)

### How to use once invited

There is only one command for this bot:
```
/convert [platform] [URL]
```
It is implemented with slash commands so Discord will help you fill in the inputs.

## Version History

* 1.1
    * Changes responder from an instant responder with a 3 second time to a respond later. This fixes an issue with the bot not responding when the API call took longer than 3 seconds.
* 1.0
    * Initial Release

## License

This project is licensed under the TBD License - see the LICENSE.md file for details

## Acknowledgments

* [Javacord](https://github.com/Javacord/Javacord)
* [Songlink](https://odesli.co/)
