# 📍 **rn-turbo-location-enabler**

A lightweight library to check and manage the device’s location services in React Native! 🚀

With **rn-turbo-location-enabler**, you can quickly detect whether device location services are enabled, and if not, prompt the user to enable them. Perfect for apps that rely on geolocation! 🌍

---

## ✨ Key Features

- 🌐 **Cross-Platform Support** — Works on both Android and iOS
- ⚡ **Lightweight and Fast** — Minimal footprint for optimal performance
- 🚀 **Compatible with New Architectures** — Ready for the latest React Native updates

---

## 📦 Installation

Install the library using **npm** or **yarn**.

### Using npm

```bash
npm install rn-turbo-location-enabler
```

### Using yarn

```bash
yarn add rn-turbo-location-enabler
```

> **Note:** This package currently supports the new React Native architecture only.

---

## 📝 Usage

Here’s a simple example showing how to use **`isLocationEnabled()`** and **`requestLocationEnabled()`** from **rn-turbo-location-enabler**:

```tsx
import React, { useState } from 'react';
import { View, Button, StyleSheet } from 'react-native';
import {
  isLocationEnabled,
  requestLocationEnabled,
} from 'rn-turbo-location-enabler';

const App = () => {
  const [isEnabled, setIsEnabled] = useState(isLocationEnabled());

  return (
    <View style={styles.container}>
      <Button
        title={isEnabled ? 'Location Enabled' : 'Enable Location'}
        disabled={isEnabled}
        onPress={() => {
          requestLocationEnabled().then(setIsEnabled);
        }}
      />
    </View>
  );
};

const styles = StyleSheet.create({
  container: {
    flex: 1,
    justifyContent: 'center',
    alignItems: 'center',
  },
});

export default App;
```

---

### 🔍 API Reference

- **`isLocationEnabled()`** → `boolean`
  Checks whether the device’s location services are currently enabled.

- **`requestLocationEnabled()`** → `Promise<boolean>`
  Prompts the user to enable location services. Resolves to `true` if the user enables location, or `false` if they decline.
