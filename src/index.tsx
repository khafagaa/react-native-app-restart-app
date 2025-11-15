import {NativeModules, Platform} from 'react-native';

const LINKING_ERROR =
  `The package 'react-native-instant-restart' doesn't seem to be linked. Make sure: \n\n` +
  Platform.select({
    ios: "- Run 'pod install' in the ios/ directory\n",
    default: '',
  }) +
  '- You rebuilt the app after installing the package\n' +
  '- If you are using Expo, the module is not available in Expo Go\n';

const AppRestart = NativeModules.AppRestartModule
  ? NativeModules.AppRestartModule
  : new Proxy(
      {},
      {
        get() {
          throw new Error(LINKING_ERROR);
        },
      },
    );

/**
 * Restarts the application
 *
 * On iOS: Triggers a JS bundle reload
 * On Android: Completely restarts the whole app by relaunching the main activity
 *
 * @example
 * ```typescript
 * import AppRestart from 'react-native-instant-restart';
 *
 * // Restart the app
 * AppRestart.restart();
 * ```
 */
export function restart(): void {
  AppRestart.reload();
}

export default {
  restart,
};
