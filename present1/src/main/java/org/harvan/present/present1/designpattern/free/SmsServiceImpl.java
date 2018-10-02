package org.harvan.present.present1.designpattern.free;

import org.harvan.present.present1.designpattern.Reactor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

/**
 * @author Harvan Irsyadi
 */
@Service
public class SmsServiceImpl implements SmsService {

  private static final Logger LOGGER = LoggerFactory.getLogger(SmsServiceImpl.class);

  @Override
  public Mono<Reactor> resendSms() {
    // concrete operation.
    LOGGER.debug("Loading sms message from database...");
    LOGGER.debug("Iterate resend sms message...");

    return Mono.just(Reactor.EXECUTED);
  }
}
