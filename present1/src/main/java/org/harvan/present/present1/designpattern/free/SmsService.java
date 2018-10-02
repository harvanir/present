package org.harvan.present.present1.designpattern.free;

import org.harvan.present.present1.designpattern.Reactor;
import reactor.core.publisher.Mono;

/**
 * @author Harvan Irsyadi
 */
public interface SmsService {

  Mono<Reactor> resendSms();
}