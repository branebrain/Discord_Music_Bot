package mbot;

import com.azure.identity.DefaultAzureCredentialBuilder;
import com.azure.security.keyvault.secrets.SecretClient;
import com.azure.security.keyvault.secrets.SecretClientBuilder;
import com.azure.security.keyvault.secrets.models.KeyVaultSecret;

//retrieves Discord API key from Azure secret vault
public class Vault {

    public static String getValue(String inputSecretName){

        String keyVaultName = "discord-musicbot-vault";
        String keyVaultUri = "https://" + keyVaultName + ".vault.azure.net";

        SecretClient secretClient = new SecretClientBuilder()
                .vaultUrl(keyVaultUri)
                .credential(new DefaultAzureCredentialBuilder().build())
                .buildClient();

        String secretName = inputSecretName;
        KeyVaultSecret retrievedSecret = secretClient.getSecret(secretName);
        return retrievedSecret.getValue();

    }

/*   public static void main(String[] args){
        String secret = "DISCORD-API-KEY";
        String token = Vault.getValue(secret);
        System.out.println(token);
    }*/

}
