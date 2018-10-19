package org.harvan.present.present1.designpattern.command;

import java.util.List;
import org.harvan.present.present1.designpattern.Reactor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * @author Harvan Irsyadi
 */
public class HealthCheckBulkCommand implements Command {

  private List<Command> commands;

  HealthCheckBulkCommand(List<Command> commands) {
    this.commands = commands;
  }

  @Override
  public Mono<Reactor> execute() {
    Flux<Command> commandFlux = Flux.fromIterable(commands);

    return commandFlux.map(Command::execute
    ).collectList().map(monos ->
        Reactor.EXECUTED
    );
  }
}