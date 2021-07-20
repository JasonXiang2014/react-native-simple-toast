import { NativeModules, Platform } from 'react-native';

const RCTToast = Platform.select({
  ios: NativeModules.LRDRCTSimpleToast,
  android: NativeModules.ToastModule,
});

const DEFAULT_SHORT = 1.5

export default {
  // Toast duration constants
  SHORT: RCTToast.SHORT,
  LONG: RCTToast.LONG,

  // Toast gravity constants
  TOP: RCTToast.TOP,
  BOTTOM: RCTToast.BOTTOM,
  CENTER: RCTToast.CENTER,

  show(message, duration, viewControllerBlacklist) {
    Platform.OS == 'android'
    ? RCTToast.showWithGravity(
      message,
      duration === undefined ? DEFAULT_SHORT : duration,
      RCTToast.CENTER
    )
    : RCTToast.showWithGravity(
      message,
      duration === undefined ? DEFAULT_SHORT : duration,
      RCTToast.CENTER,
      viewControllerBlacklist
    );
  },

  showWithGravity(message, duration, gravity, viewControllerBlacklist) {
    Platform.OS == 'android'
    ? RCTToast.showWithGravity(
      message,
      duration === undefined ? RCTToast.SHORT : duration,
      gravity
    )
    : RCTToast.showWithGravity(
      message,
      duration === undefined ? RCTToast.SHORT : duration,
      gravity,
      viewControllerBlacklist
    );
  },
};
