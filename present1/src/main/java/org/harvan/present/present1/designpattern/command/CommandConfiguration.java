package org.harvan.present.present1.designpattern.command;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author Harvan Irsyadi
 */
@Configuration
public class CommandConfiguration {

  @Bean
  public ResendCommand resendWhatsAppCommand(Receiver whatsAppReceiver) {
    return new ResendCommand(whatsAppReceiver);
  }

  @Bean
  public ResendCommand resendSmsCommand(Receiver smsReceiver) {
    return new ResendCommand(smsReceiver);
  }
}