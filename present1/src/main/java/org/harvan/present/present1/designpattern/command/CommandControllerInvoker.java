package org.harvan.present.present1.designpattern.command;

import org.harvan.present.present1.designpattern.Reactor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

/**
 * @author Harvan Irsyadi
 */
@RestController
@RequestMapping("/command")
public class CommandControllerInvoker {

  private ResendCommand resendWhatsAppCommand;

  private ResendCommand resendSmsCommand;

  @Autowired
  public void setResendWhatsAppCommand(ResendCommand resendWhatsAppCommand) {
    this.resendWhatsAppCommand = resendWhatsAppCommand;
  }

  @Autowired
  public void setResendSmsCommand(ResendCommand resendSmsCommand) {
    this.resendSmsCommand = resendSmsCommand;
  }

  @GetMapping(path = "/resendWhatsApp")
  public Mono<Reactor> resendWhatsApp() {
    return resendWhatsAppCommand.execute();
  }

  @GetMapping(path = "/resendSms")
  public Mono<Reactor> resendSms() {
    return resendSmsCommand.execute();
  }
}
