package org.example.starvault;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication(scanBasePackages = {"org.example.starvault", "stark.dataworks.boot.autoconfig"})
@EnableTransactionManagement
public class StarVaultApplication
{
    public static void main(String[] args)
    {
        SpringApplication.run(StarVaultApplication.class, args);
    }
}
