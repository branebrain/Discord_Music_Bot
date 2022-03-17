package mbot;

import com.azure.core.credential.BasicAuthenticationCredential;
import com.azure.identity.DefaultAzureCredentialBuilder;
import com.azure.identity.EnvironmentCredentialBuilder;
import com.azure.security.keyvault.secrets.SecretClient;
import com.azure.security.keyvault.secrets.SecretClientBuilder;
import com.azure.security.keyvault.secrets.models.KeyVaultSecret;

import java.lang.module.Configuration;

//retrieves Discord API key from Azure secret vault
public class Vault {

    public static String getValue(String inputSecretName){

        String keyVaultName = "discord-musicbot-vault";
        String keyVaultUri = "https://" + keyVaultName + ".vault.azure.net";

        String userName = System.getenv("AZURE_USERNAME");
        String password = System.getenv("AZURE_PASSWORD");
        System.out.println(userName + " " + password);

        SecretClient secretClient = new SecretClientBuilder()
                .vaultUrl(keyVaultUri)
                .credential(new EnvironmentCredentialBuilder().build())
               // .credential(new DefaultAzureCredentialBuilder().build())
                .buildClient();

        KeyVaultSecret retrievedSecret = secretClient.getSecret(inputSecretName);
        return retrievedSecret.getValue();

    }

/*   public static void main(String[] args){
        String secret = "DISCORD-API-KEY";
        String token = Vault.getValue(secret);
        System.out.println(token);
    }*/

}
