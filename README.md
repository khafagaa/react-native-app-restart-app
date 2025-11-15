# react-native-app-restart

A simple React Native library to restart your app on both iOS and Android platforms.

## Features

- ✅ Completely restart the app on Android
- ✅ Reload JS bundle on iOS
- ✅ TypeScript support
- ✅ Simple API
- ✅ Works with both debug and release builds

## Installation

### 1. Install the package

If using as a local library in your monorepo:

```bash
# Add to your package.json
"react-native-app-restart": "file:./libraries/react-native-app-restart"
```

Then run:

```bash
npm install
# or
yarn install
```

### 2. iOS Setup

Navigate to your iOS folder and install pods:

```bash
cd ios
pod install
cd ..
```

**Manual Linking (if autolinking doesn't work):**

1. Add the following to your `ios/Podfile`:

```ruby
pod 'react-native-app-restart', :path => '../libraries/react-native-app-restart'
```

2. Run `pod install` again

### 3. Android Setup

**For React Native 0.60+ (Autolinking):**

The library should be automatically linked. Just rebuild your app.

**Manual Linking (if needed):**

1. Add the library to `android/settings.gradle`:

```gradle
include ':react-native-app-restart'
project(':react-native-app-restart').projectDir = new File(rootProject.projectDir, '../libraries/react-native-app-restart/android')
```

2. Add the dependency to `android/app/build.gradle`:

```gradle
dependencies {
    implementation project(':react-native-app-restart')
}
```

3. Add the package to `MainApplication.kt` (or `MainApplication.java`):

```kotlin
import com.reactnativeapprestart.AppRestartPackage

// Inside the getPackages() method:
override fun getPackages(): List<ReactPackage> {
    return PackageList(this).packages.apply {
        add(AppRestartPackage())
    }
}
```

### 4. Rebuild your app

```bash
# iOS
npx react-native run-ios

# Android
npx react-native run-android
```

## Usage

```typescript
import AppRestart from 'react-native-app-restart';

// Restart the app
const handleLanguageChange = () => {
  // Save your settings first
  await saveLanguagePreference('ar');

  // Then restart the app
  AppRestart.restart();
};
```

### Example with Language Change

```typescript
import React from 'react';
import { View, Button } from 'react-native';
import AppRestart from 'react-native-app-restart';
import I18n from 'react-native-i18n';

const LanguageSelector = () => {
  const changeLanguage = async (language: string) => {
    // Update your i18n settings
    I18n.locale = language;

    // Persist the language preference
    await AsyncStorage.setItem('userLanguage', language);

    // Restart the app to apply RTL/LTR changes
    AppRestart.restart();
  };

  return (
    <View>
      <Button title="English" onPress={() => changeLanguage('en')} />
      <Button title="العربية" onPress={() => changeLanguage('ar')} />
    </View>
  );
};
```

## API

### `restart()`

Restarts the application.

- **iOS**: Triggers a JS bundle reload using React Native's reload command
- **Android**: Completely restarts the app by relaunching the main activity and exiting the current process

```typescript
AppRestart.restart(): void
```

## Platform Differences

### iOS

- Performs a JS bundle reload
- Faster than a full app restart
- Preserves some native state
- Works in both debug and release modes

### Android

- Performs a complete app restart
- Clears all activities and creates a new task
- Ensures a fresh app state
- Forces an immediate process exit for instant restart

## Troubleshooting

### iOS

**Issue**: Module not found or linking error

**Solution**:

1. Clean build folder: `cd ios && rm -rf build && cd ..`
2. Reinstall pods: `cd ios && pod install && cd ..`
3. Rebuild: `npx react-native run-ios`

### Android

**Issue**: Module cannot be found

**Solution**:

1. Clean gradle: `cd android && ./gradlew clean && cd ..`
2. Check that the package is added to `MainApplication.kt`
3. Rebuild: `npx react-native run-android`

**Issue**: Kotlin version mismatch

**Solution**: Make sure your project's `android/build.gradle` has a compatible Kotlin version:

```gradle
buildscript {
    ext {
        kotlinVersion = "1.8.0" // or higher
    }
}
```

## License

MIT

## Contributing

Pull requests are welcome! Feel free to open issues for bugs or feature requests.
