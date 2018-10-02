package org.harvan.present.present1.designpattern.command;

import org.harvan.present.present1.designpattern.Reactor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

/**
 * @author Harvan Irsyadi
 */
@Service
public class SmsReceiver implements Receiver {

  private static final Logger LOGGER = LoggerFactory.getLogger(SmsReceiver.class);

  @Override
  public Mono<Reactor> doResend() {
    // concrete operation.
    LOGGER.debug("Loading sms message from database...");
    LOGGER.debug("Iterate resend sms message...");

    return Mono.just(Reactor.EXECUTED);
  }
}