# Integration Guide for ADIO Mobile

This guide explains how to integrate the `react-native-app-restart` library into your ADIO mobile app.

## Quick Start

### Step 1: Add to package.json

Add the library to your main project's `package.json`:

```json
{
  "dependencies": {
    "react-native-app-restart": "file:./libraries/react-native-app-restart"
  }
}
```

Then run:

```bash
npm install
# or
yarn install
```

### Step 2: iOS Setup

1. Navigate to iOS folder:

```bash
cd ios
```

2. Install pods:

```bash
pod install
```

3. Go back to root:

```bash
cd ..
```

The library should be automatically linked via CocoaPods autolinking.

### Step 3: Android Setup (Manual - if autolinking doesn't work)

1. **Update `android/settings.gradle`:**

Add after the existing includes:

```gradle
include ':react-native-app-restart'
project(':react-native-app-restart').projectDir = new File(rootProject.projectDir, '../libraries/react-native-app-restart/android')
```

2. **Update `android/app/build.gradle`:**

Add to dependencies section:

```gradle
dependencies {
    // ... other dependencies
    implementation project(':react-native-app-restart')
}
```

3. **Update `android/app/src/main/java/com/linkdev/adio/MainApplication.kt`:**

Add the import at the top:

```kotlin
import com.reactnativeapprestart.AppRestartPackage
```

Then add the package to the packages list in `getPackages()`:

```kotlin
override fun getPackages(): List<ReactPackage> {
    return PackageList(this).packages.apply {
        // Add your packages here
        add(AppRestartPackage())
    }
}
```

### Step 4: Rebuild the App

```bash
# iOS
npx react-native run-ios

# Android
npx react-native run-android
```

## Usage in Your App

### Basic Usage

```typescript
import AppRestart from 'react-native-app-restart';

// Restart the app
AppRestart.restart();
```

### Replace Existing Implementation

You can now replace your existing `JSRestartModule` usage with the new library.

**Before:**

```typescript
import {NativeModules} from 'react-native';
const {JSRestartModule} = NativeModules;

// Restart
JSRestartModule.reload();
```

**After:**

```typescript
import AppRestart from 'react-native-app-restart';

// Restart
AppRestart.restart();
```

### Example: Language Change Handler

Update your language change component (`AppSelectLanguage.tsx`):

```typescript
import AppRestart from 'react-native-app-restart';

const handleLanguageSelect = async (languageCode: string) => {
  // Save language preference
  await saveLanguagePreference(languageCode);

  // Update i18n
  I18n.locale = languageCode;

  // Restart app to apply RTL/LTR changes
  AppRestart.restart();
};
```

## Cleanup (Optional)

Once you've integrated the library and verified it works, you can remove the old implementations:

### Files to Remove:

1. `android/app/src/main/java/com/jsrestartmodule/JSRestartModule.kt`
2. `android/app/src/main/java/com/jsrestartmodule/JSRestartModulePackage.kt`
3. `ios/RCTJSRestartModule.h`
4. `ios/RCTJSRestartModule.m`

### Remove from MainApplication.kt:

Remove these lines from `android/app/src/main/java/com/linkdev/adio/MainApplication.kt`:

```kotlin
import com.jsrestartmodule.JSRestartModulePackage

// And remove from packages list:
add(JSRestartModulePackage())
```

### Remove from iOS Xcode Project:

1. Open `ios/ADIO_mobile.xcodeproj` in Xcode
2. Remove `RCTJSRestartModule.h` and `RCTJSRestartModule.m` from the project
3. Clean build folder (Cmd + Shift + K)
4. Rebuild

## Troubleshooting

### Common Issues

**1. Module not found (iOS)**

```bash
cd ios
rm -rf Pods build
pod install
cd ..
npx react-native run-ios
```

**2. Duplicate module error (Android)**

Make sure you've removed the old `JSRestartModule` files and references from `MainApplication.kt`.

**3. Build errors after integration**

Clean and rebuild:

```bash
# iOS
cd ios
rm -rf build
cd ..

# Android
cd android
./gradlew clean
cd ..

# Rebuild
npx react-native run-ios
# or
npx react-native run-android
```

## Benefits of the Library Approach

1. **Reusability**: Can be used across multiple projects
2. **Maintainability**: Centralized code, easier to update
3. **Type Safety**: TypeScript definitions included
4. **Documentation**: Built-in docs and examples
5. **Testing**: Can be tested independently
6. **Versioning**: Can version and publish if needed

## Next Steps

- Test the restart functionality with language changes
- Remove old implementation files after verification
- Update any documentation referencing the old `JSRestartModule`
