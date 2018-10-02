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
public class WhatsAppServiceImpl implements WhatsAppService {

  private static final Logger LOGGER = LoggerFactory.getLogger(WhatsAppServiceImpl.class);

  @Override
  public Mono<Reactor> resendWhatsApp() {
    // concrete operation.
    LOGGER.debug("Loading whatsApp message from database...");
    LOGGER.debug("Iterate resend whatsApp message...");

    return Mono.just(Reactor.EXECUTED);
  }
}