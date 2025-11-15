#import "AppRestartModule.h"
#import <React/RCTReloadCommand.h>
#import <React/RCTDevMenu.h>

@implementation AppRestartModule

RCT_EXPORT_MODULE(AppRestartModule);

RCT_EXPORT_METHOD(reload)
{
  dispatch_async(dispatch_get_main_queue(), ^{
#if DEBUG
    NSLog(@"[AppRestart] Debug → DevMenu reload");
    RCTTriggerReloadCommandListeners(@"Manual reload"); //* debug reload
#else
    NSLog(@"[AppRestart] Release → Reload JS bundle");
    RCTTriggerReloadCommandListeners(@"Manual reload");
#endif
  });
}

@end

