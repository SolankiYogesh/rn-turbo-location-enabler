import { useState } from 'react';
import { View, StyleSheet, Button } from 'react-native';
import {
  isLocationEnabled,
  requestLocationEnabled,
} from 'rn-turbo-location-enabler';

const App = () => {
  const [isEnabled, setIsEnabled] = useState(isLocationEnabled());

  return (
    <View style={styles.container}>
      <Button
        title={!isEnabled ? 'Click to Request' : 'Already Enabled'}
        disabled={isEnabled}
        onPress={() => {
          requestLocationEnabled().then(setIsEnabled);
        }}
      />
    </View>
  );
};

export default App;

const styles = StyleSheet.create({
  container: {
    flex: 1,
    alignItems: 'center',
    justifyContent: 'center',
  },
});
