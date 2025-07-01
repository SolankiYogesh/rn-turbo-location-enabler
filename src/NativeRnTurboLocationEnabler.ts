import type { TurboModule } from 'react-native';
import { TurboModuleRegistry } from 'react-native';

export interface Spec extends TurboModule {
  requestLocationEnabled(): Promise<boolean>;
  isLocationEnabled(): boolean;
}

export default TurboModuleRegistry.getEnforcing<Spec>('RnTurboLocationEnabler');
