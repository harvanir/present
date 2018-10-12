package org.harvan.present.present1.designpattern.free;

import org.harvan.present.present1.designpattern.Reactor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * @author Harvan Irsyadi
 */
@Service
public class BulkServiceImpl implements BulkService {

  private SmsService smsService;

  private WhatsAppService whatsAppService;

  @Autowired
  public void setSmsService(SmsService smsService) {
    this.smsService = smsService;
  }

  @Autowired
  public void setWhatsAppService(WhatsAppService whatsAppService) {
    this.whatsAppService = whatsAppService;
  }

  @Override
  public Mono<Reactor> resendAll() {
    Flux<Reactor> reactorFlux = Flux.empty();
    reactorFlux = Flux.concat(reactorFlux, smsService.resendSms());
    reactorFlux = Flux.concat(reactorFlux, whatsAppService.resendWhatsApp());

    return reactorFlux.collectList().map(reactors ->
        Reactor.EXECUTED
    );
  }
}