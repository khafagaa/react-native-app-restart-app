# Installation Example - Copy & Paste Commands

## 🚀 Complete Installation (Copy these commands)

### Step 1: Verify Library Exists

```bash
ls -la /Users/mis/Documents/ADIO_mobile/libraries/react-native-app-restart/
```

You should see the library files.

### Step 2: Add to Your package.json

Open your main `package.json` and add this line to dependencies:

```json
"react-native-app-restart": "file:./libraries/react-native-app-restart"
```

Or use this command to add it automatically:

```bash
cd /Users/mis/Documents/ADIO_mobile
npm install ./libraries/react-native-app-restart --save
```

### Step 3: Install Dependencies

```bash
cd /Users/mis/Documents/ADIO_mobile
npm install
```

### Step 4: iOS - Install Pods

```bash
cd /Users/mis/Documents/ADIO_mobile/ios
pod install
cd ..
```

### Step 5: Android - Add Native Module

Open: `/Users/mis/Documents/ADIO_mobile/android/app/src/main/java/com/linkdev/adio/MainApplication.kt`

Add this import at the top (after other imports):

```kotlin
import com.reactnativeapprestart.AppRestartPackage
```

Then find the `getPackages()` method and add the package:

```kotlin
override fun getPackages(): List<ReactPackage> {
    return PackageList(this).packages.apply {
        // Your existing packages...
        add(AppRestartPackage())  // ← Add this line
    }
}
```

### Step 6: Rebuild Your App

For iOS:

```bash
cd /Users/mis/Documents/ADIO_mobile
npx react-native run-ios
```

For Android:

```bash
cd /Users/mis/Documents/ADIO_mobile
npx react-native run-android
```

## ✅ Verify Installation

Create a test file or add to an existing screen:

```typescript
// Test.tsx
import React from 'react';
import { View, Button } from 'react-native';
import AppRestart from 'react-native-app-restart';

const TestRestart = () => {
  const handleRestart = () => {
    console.log('Restarting app...');
    AppRestart.restart();
  };

  return (
    <View style={{ flex: 1, justifyContent: 'center', alignItems: 'center' }}>
      <Button title="Test Restart" onPress={handleRestart} />
    </View>
  );
};

export default TestRestart;
```

## 🔄 Update Your Language Selector

Find your language selector component (likely in `src/components/bottomSheet/AppSelectLanguage/`):

**Before:**

```typescript
import {NativeModules} from 'react-native';
const {JSRestartModule} = NativeModules;

// ...
JSRestartModule.reload();
```

**After:**

```typescript
import AppRestart from 'react-native-app-restart';

// ...
AppRestart.restart();
```

## 🧹 Clean Up Old Implementation (Optional)

Once verified working, you can remove:

1. `/Users/mis/Documents/ADIO_mobile/android/app/src/main/java/com/jsrestartmodule/JSRestartModule.kt`
2. `/Users/mis/Documents/ADIO_mobile/android/app/src/main/java/com/jsrestartmodule/JSRestartModulePackage.kt`
3. `/Users/mis/Documents/ADIO_mobile/ios/RCTJSRestartModule.h`
4. `/Users/mis/Documents/ADIO_mobile/ios/RCTJSRestartModule.m`

And remove from `MainApplication.kt`:

```kotlin
import com.jsrestartmodule.JSRestartModulePackage  // ← Remove
// ...
add(JSRestartModulePackage())  // ← Remove
```

## 🐛 Troubleshooting

### Issue: "Module not found" on iOS

**Solution:**

```bash
cd /Users/mis/Documents/ADIO_mobile/ios
rm -rf Pods build
pod install
cd ..
npx react-native run-ios
```

### Issue: "Module not found" on Android

**Solution:**

```bash
cd /Users/mis/Documents/ADIO_mobile/android
./gradlew clean
cd ..
npx react-native run-android
```

### Issue: TypeScript errors

**Solution:**

```bash
cd /Users/mis/Documents/ADIO_mobile/libraries/react-native-app-restart
npm run build
cd ../..
```

### Issue: Duplicate module after removing old implementation

**Solution:**

1. Make sure you removed all old JSRestartModule files
2. Remove JSRestartModulePackage from MainApplication.kt
3. Clean and rebuild:

```bash
cd android && ./gradlew clean && cd ..
npx react-native run-android
```

## ✨ Complete Example Usage

```typescript
// src/components/bottomSheet/AppSelectLanguage/AppSelectLanguage.tsx
import React from 'react';
import { View, TouchableOpacity, Text } from 'react-native';
import AppRestart from 'react-native-app-restart';
import I18n from 'react-native-i18n';
import AsyncStorage from '@react-native-async-storage/async-storage';

const AppSelectLanguage = () => {
  const changeLanguage = async (languageCode: string) => {
    try {
      // 1. Save language preference
      await AsyncStorage.setItem('userLanguage', languageCode);

      // 2. Update i18n locale
      I18n.locale = languageCode;

      // 3. Restart app to apply RTL/LTR changes
      AppRestart.restart();
    } catch (error) {
      console.error('Failed to change language:', error);
    }
  };

  return (
    <View>
      <TouchableOpacity onPress={() => changeLanguage('en')}>
        <Text>English</Text>
      </TouchableOpacity>

      <TouchableOpacity onPress={() => changeLanguage('ar')}>
        <Text>العربية</Text>
      </TouchableOpacity>
    </View>
  );
};

export default AppSelectLanguage;
```

## 🎉 Done!

You now have the app restart library integrated and working!

### What You Get:

✅ Type-safe API with TypeScript
✅ Reliable app restart on both platforms
✅ Clean, maintainable code
✅ Reusable across projects
✅ Well-documented library

### Next Steps:

1. Test the restart functionality
2. Update all code using old JSRestartModule
3. Remove old implementation files
4. Enjoy your new library! 🚀
