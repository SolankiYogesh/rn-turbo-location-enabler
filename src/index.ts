import RnTurboLocationEnabler from './NativeRnTurboLocationEnabler';
import { Platform } from 'react-native';
export function isLocationEnabled(): boolean {
  if (Platform.OS === 'ios') {
    return true;
  }
  return RnTurboLocationEnabler.isLocationEnabled();
}
export function requestLocationEnabled(): Promise<boolean> {
  if (Platform.OS === 'ios') {
    return Promise.resolve(true);
  }
  return RnTurboLocationEnabler.requestLocationEnabled();
}
