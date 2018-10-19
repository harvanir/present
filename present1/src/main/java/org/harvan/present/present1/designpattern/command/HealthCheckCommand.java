package org.harvan.present.present1.designpattern.command;

import org.harvan.present.present1.designpattern.Reactor;
import reactor.core.publisher.Mono;

/**
 * @author Harvan Irsyadi
 */
public class HealthCheckCommand implements Command {

  private Receiver receiver;

  HealthCheckCommand(Receiver receiver) {
    this.receiver = receiver;
  }

  @Override
  public Mono<Reactor> execute() {
    return receiver.doHealthCheck();
  }
}