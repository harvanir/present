package org.harvan.present.present1.designpattern.command;

import org.harvan.present.present1.designpattern.Reactor;
import reactor.core.publisher.Mono;

/**
 * @author Harvan Irsyadi
 */
public interface Receiver {

  Mono<Reactor> doResend();
}
