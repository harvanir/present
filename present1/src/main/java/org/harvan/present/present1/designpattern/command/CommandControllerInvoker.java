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

  private Command resendWhatsAppCommand;

  private Command resendSmsCommand;

  private Command resendAllCommand;

  @Autowired
  public void setResendWhatsAppCommand(Command resendWhatsAppCommand) {
    this.resendWhatsAppCommand = resendWhatsAppCommand;
  }

  @Autowired
  public void setResendSmsCommand(Command resendSmsCommand) {
    this.resendSmsCommand = resendSmsCommand;
  }

  @Autowired
  public void setResendAllCommand(Command resendAllCommand) {
    this.resendAllCommand = resendAllCommand;
  }

  @GetMapping(path = "/resendWhatsApp")
  public Mono<Reactor> resendWhatsApp() {
    return resendWhatsAppCommand.execute();
  }

  @GetMapping(path = "/resendSms")
  public Mono<Reactor> resendSms() {
    return resendSmsCommand.execute();
  }

  @GetMapping(path = "/resendAll")
  public Mono<Reactor> resendAll() {
    return resendAllCommand.execute();
  }
}
