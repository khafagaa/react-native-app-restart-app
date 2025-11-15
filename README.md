<div align="center">
  <img src="https://raw.githubusercontent.com/khafagaa/react-native-app-restart-app/main/assets/we-stand-with-palestine.avif" alt="We Stand With Palestine" width="300"/>
</div>

# react-native-app-restart

[![npm version](https://badge.fury.io/js/react-native-app-restart.svg)](https://badge.fury.io/js/react-native-app-restart)
[![License: MIT](https://img.shields.io/badge/License-MIT-blue.svg)](https://opensource.org/licenses/MIT)

A lightweight and reliable React Native library to restart your application on both iOS and Android platforms. Perfect for applying language changes, theme switches, or any configuration that requires a fresh app start.

## Table of Contents

- [Features](#features)
- [Requirements](#requirements)
- [Installation](#installation)
- [Usage](#usage)
- [API](#api)
- [Platform Differences](#platform-differences)
- [Troubleshooting](#troubleshooting)
- [Why Use This Library?](#why-use-this-library)
- [License](#license)
- [Contributing](#contributing)

## Features

- ✅ Completely restart the app on Android
- ✅ Reload JS bundle on iOS
- ✅ TypeScript support
- ✅ Simple API
- ✅ Works with both debug and release builds
- ✅ Auto-linking support (React Native 0.60+)

## Requirements

- React Native >= 0.60.0
- iOS 11.0 or higher
- Android minSdkVersion 21 or higher
- Java 17 or higher (for Android)

## Installation

### 1. Install the package

```bash
npm install react-native-app-restart
# or
yarn add react-native-app-restart
```

### 2. iOS Setup

Navigate to your iOS folder and install pods:

```bash
cd ios
pod install
cd ..
```

**That's it!** The library will be automatically linked via CocoaPods autolinking.

### 3. Android Setup

**For React Native 0.60+ (Autolinking):**

✅ **No manual setup required!** The library is automatically discovered and linked.

Just rebuild your app - autolinking handles everything for you.

### 4. Rebuild your app

```bash
# iOS
npx react-native run-ios

# Android
npx react-native run-android
```

---

## ✅ Verify Autolinking

To confirm the library is properly auto-linked:

```bash
npx react-native config
```

Look for `react-native-app-restart` in the output with both iOS and Android configurations.

---

## 🚨 Important: Manual Linking NOT Required

**React Native 0.60+ includes autolinking.** You do **NOT** need to:

- ❌ Manually add the package to `MainApplication.kt`
- ❌ Edit `android/settings.gradle`
- ❌ Edit `android/app/build.gradle`
- ❌ Manually configure Podfile

The library will be automatically discovered and linked on both platforms.

### If Autolinking Fails (Rare Cases)

Only if autolinking doesn't work (very rare), you can manually link:

<details>
<summary><strong>Manual Linking for iOS (Click to expand)</strong></summary>

Add to your `ios/Podfile`:

```ruby
pod 'react-native-app-restart', :path => '../node_modules/react-native-app-restart'
```

Then run:

```bash
cd ios
pod install
cd ..
```

</details>

<details>
<summary><strong>Manual Linking for Android (Click to expand)</strong></summary>

If autolinking doesn't work, you only need to add the module to your gradle files. **React Native's autolinking will automatically detect and register the package** - no need to modify `MainApplication.kt`.

1. Add to `android/settings.gradle`:

```gradle
include ':react-native-app-restart'
project(':react-native-app-restart').projectDir = new File(rootProject.projectDir, '../node_modules/react-native-app-restart/android')
```

2. Add to `android/app/build.gradle`:

```gradle
dependencies {
    implementation project(':react-native-app-restart')
}
```

That's it! The package will be automatically registered via autolinking. **Do NOT manually add it to `MainApplication.kt`** - React Native's autolinking handles this automatically.

</details>

---

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

**Issue**: JVM target compatibility error

```
Inconsistent JVM-target compatibility detected for tasks 'compileDebugJavaWithJavac' (17) and 'compileDebugKotlin'
```

**Solution**: This library requires Java 17. Make sure your project is configured for Java 17:

In your project's `android/gradle.properties`:

```properties
# Ensure you're using Java 17
org.gradle.jvmargs=-Xmx2048m -XX:MaxMetaspaceSize=512m -XX:+HeapDumpOnOutOfMemoryError
```

And in your project's `android/build.gradle`:

```gradle
buildscript {
    ext {
        buildToolsVersion = "34.0.0"
        minSdkVersion = 21
        compileSdkVersion = 34
        targetSdkVersion = 34
        kotlinVersion = "1.8.0" // or higher
    }
}
```

**Issue**: Kotlin version mismatch

**Solution**: Make sure your project has a compatible Kotlin version (1.8.0 or higher) in `android/build.gradle`.

## Why Use This Library?

When building React Native apps with features like:

- 🌍 Language switching (especially RTL/LTR changes)
- 🎨 Theme changes requiring native updates
- ⚙️ Configuration changes that need a fresh start
- 🔄 User preference updates

A simple restart provides the best user experience to apply these changes immediately.

## License

MIT

## Contributing

Contributions are welcome! Please feel free to submit a Pull Request.

1. Fork the repository
2. Create your feature branch (`git checkout -b feature/AmazingFeature`)
3. Commit your changes (`git commit -m 'Add some AmazingFeature'`)
4. Push to the branch (`git push origin feature/AmazingFeature`)
5. Open a Pull Request

## Support

If you find this library helpful, please give it a ⭐️ on [GitHub](https://github.com/khafagaa/react-native-app-restart-app)!

For issues and feature requests, please [open an issue](https://github.com/khafagaa/react-native-app-restart-app/issues).
