#import "RnTurboLocationEnabler.h"

@implementation RnTurboLocationEnabler
RCT_EXPORT_MODULE()

- (std::shared_ptr<facebook::react::TurboModule>)getTurboModule:
    (const facebook::react::ObjCTurboModule::InitParams &)params
{
    return std::make_shared<facebook::react::NativeRnTurboLocationEnablerSpecJSI>(params);
}

- (nonnull NSNumber *)isLocationEnabled { 
  return @(YES);
}

- (void)requestLocationEnabled:(nonnull RCTPromiseResolveBlock)resolve reject:(nonnull RCTPromiseRejectBlock)reject { 
  resolve(@(YES));
}

@end
