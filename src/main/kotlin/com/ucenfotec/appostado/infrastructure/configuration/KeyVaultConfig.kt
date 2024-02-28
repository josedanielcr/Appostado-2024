package com.ucenfotec.appostado.infrastructure.configuration

import com.azure.identity.ClientSecretCredentialBuilder
import com.azure.identity.DefaultAzureCredentialBuilder
import com.azure.security.keyvault.secrets.SecretClient
import com.azure.security.keyvault.secrets.SecretClientBuilder
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class KeyVaultConfig {

    @Bean
    fun secretClient(): SecretClient {
        return SecretClientBuilder()
            .vaultUrl("https://appostadojdversionkey.vault.azure.net/")
            .credential(DefaultAzureCredentialBuilder().build())
            .buildClient()
    }
}