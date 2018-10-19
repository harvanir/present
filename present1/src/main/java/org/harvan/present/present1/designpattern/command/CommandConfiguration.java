package org.harvan.present.present1.designpattern.command;

import java.util.ArrayList;
import java.util.Map;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author Harvan Irsyadi
 */
@Configuration
public class CommandConfiguration {

  @Bean
  public Command resendWhatsAppCommand(Receiver whatsAppReceiver) {
    return new ResendCommand(whatsAppReceiver);
  }

  @Bean
  public Command resendSmsCommand(Receiver smsReceiver) {
    return new ResendCommand(smsReceiver);
  }

  @Bean
  public Command resendAllCommand(ApplicationContext applicationContext) {
    Map<String, ResendCommand> resendCommandMap = applicationContext
        .getBeansOfType(ResendCommand.class);

    return new ResendBulkCommand(new ArrayList<>(resendCommandMap.values()));
  }

  @Bean
  public Command healthCheckWhatsAppCommand(Receiver whatsAppReceiver) {
    return new HealthCheckCommand(whatsAppReceiver);
  }

  @Bean
  public Command healthCheckSmsCommand(Receiver smsReceiver) {
    return new HealthCheckCommand(smsReceiver);
  }

  @Bean
  public Command healthCheckAllCommand(ApplicationContext applicationContext) {
    Map<String, HealthCheckCommand> resendCommandMap = applicationContext
        .getBeansOfType(HealthCheckCommand.class);

    return new HealthCheckBulkCommand(new ArrayList<>(resendCommandMap.values()));
  }
}