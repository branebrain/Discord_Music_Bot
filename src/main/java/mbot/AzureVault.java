package mbot;

import com.azure.core.util.polling.SyncPoller;
import com.azure.identity.CredentialUnavailableException;
import com.azure.identity.DefaultAzureCredentialBuilder;
import com.azure.security.keyvault.secrets.SecretClient;
import com.azure.security.keyvault.secrets.SecretClientBuilder;
import com.azure.security.keyvault.secrets.models.DeletedSecret;
import com.azure.security.keyvault.secrets.models.KeyVaultSecret;

import java.util.Scanner;


public class AzureVault {
    public static void main(String[] args) throws InterruptedException, IllegalArgumentException {

        String keyVaultName = "discord-musicbot-vault";
        String keyVaultUri = "https://" + keyVaultName + ".vault.azure.net";

        System.out.printf("key vault name = %s and key vault URI = %s \n", keyVaultName, keyVaultUri);

        SecretClient secretClient = new SecretClientBuilder()
                .vaultUrl(keyVaultUri)
                .credential(new DefaultAzureCredentialBuilder().build())
                .buildClient();

        Scanner reader = new Scanner(System.in);

        String secretName = "DISCORD-API-KEY";

        System.out.println("Please provide the value of your secret > ");

        String secretValue = reader.next();

        System.out.print("Creating a secret in " + keyVaultName + " called '" + secretName + "' with value '" + secretValue + "` ... ");

        try{
            secretClient.setSecret(new KeyVaultSecret(secretName, secretValue));
        }catch (CredentialUnavailableException e){
            System.out.println(e.getMessage());
        }


        System.out.println("done.");
        System.out.println("Forgetting your secret.");

        secretValue = "";
        System.out.println("Your secret's value is '" + secretValue + "'.");

/*        System.out.println("Retrieving your secret from " + keyVaultName + ".");

        KeyVaultSecret retrievedSecret = secretClient.getSecret(secretName);

        System.out.println("Your secret's value is '" + retrievedSecret.getValue() + "'.");
        //System.out.print("Deleting your secret from " + keyVaultName + " ... ");

        SyncPoller<DeletedSecret, Void> deletionPoller = secretClient.beginDeleteSecret(secretName);
        deletionPoller.waitForCompletion();

        System.out.println("done.");*/
    }

}
