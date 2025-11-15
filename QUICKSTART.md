# Quick Start Guide

## 🚀 5-Minute Integration

### Step 1: Install (2 minutes)

```bash
# From your project root: /Users/mis/Documents/ADIO_mobile
npm install
```

### Step 2: iOS Setup (1 minute)

```bash
cd ios
pod install
cd ..
```

### Step 3: Android Setup - Verify MainApplication.kt (1 minute)

Open: `android/app/src/main/java/com/linkdev/adio/MainApplication.kt`

Add import:

```kotlin
import com.reactnativeapprestart.AppRestartPackage
```

Add to packages:

```kotlin
override fun getPackages(): List<ReactPackage> {
    return PackageList(this).packages.apply {
        add(AppRestartPackage())  // Add this line
    }
}
```

### Step 4: Rebuild (1 minute)

```bash
# iOS
npx react-native run-ios

# Android
npx react-native run-android
```

## ✨ Usage

### Import

```typescript
import AppRestart from 'react-native-app-restart';
```

### Restart App

```typescript
AppRestart.restart();
```

### Example: Language Change

```typescript
import AppRestart from 'react-native-app-restart';
import I18n from 'react-native-i18n';

const changeLanguage = async (lang: string) => {
  // Save preference
  await AsyncStorage.setItem('language', lang);

  // Update i18n
  I18n.locale = lang;

  // Restart app
  AppRestart.restart();
};
```

## 📝 Add to package.json

If not already added, add this to your main `package.json`:

```json
{
  "dependencies": {
    "react-native-app-restart": "file:./libraries/react-native-app-restart"
  }
}
```

## 🐛 Troubleshooting

### Module not found (iOS)

```bash
cd ios && rm -rf Pods build && pod install && cd ..
npx react-native run-ios
```

### Module not found (Android)

```bash
cd android && ./gradlew clean && cd ..
npx react-native run-android
```

### TypeScript errors

```bash
cd libraries/react-native-app-restart
npm run build
cd ../..
```

## 📚 More Info

- **Full Documentation**: See `README.md`
- **Integration Guide**: See `INTEGRATION.md`
- **Library Structure**: See `LIBRARY_STRUCTURE.md`

## ✅ That's it!

You now have a reusable, type-safe app restart library integrated into your project.
