package org.harvan.present.present1.designpattern.free;

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
@RequestMapping("/free")
public class FreePatternControllerInvoker {

  private WhatsAppService whatsAppService;

  private SmsService smsService;

  private BulkService bulkService;

  @Autowired
  public void setWhatsAppService(WhatsAppService whatsAppService) {
    this.whatsAppService = whatsAppService;
  }

  @Autowired
  public void setSmsService(SmsService smsService) {
    this.smsService = smsService;
  }

  @Autowired
  public void setBulkService(BulkService bulkService) {
    this.bulkService = bulkService;
  }

  @GetMapping(path = "/resendWhatsApp")
  public Mono<Reactor> resendWhatsApp() {
    return whatsAppService.resendWhatsApp();
  }

  @GetMapping(path = "/resendSms")
  public Mono<Reactor> resendSms() {
    return smsService.resendSms();
  }

  @GetMapping(path = "/resendAll")
  public Mono<Reactor> resendAll() {
    return bulkService.resendAll();
  }
}