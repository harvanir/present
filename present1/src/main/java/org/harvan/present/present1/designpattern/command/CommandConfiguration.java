package org.harvan.present.present1.designpattern.command;

import java.util.ArrayList;
import java.util.List;
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
  public List<Command> allResendCommand(ApplicationContext applicationContext) {
    Map<String, ResendCommand> resendCommandMap = applicationContext
        .getBeansOfType(ResendCommand.class);

    return new ArrayList<>(resendCommandMap.values());
  }

  @Bean
  public Command resendAllCommand(List<Command> allResendCommand) {
    return new ResendBulkCommand(allResendCommand);
  }
}