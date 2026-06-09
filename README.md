# 🌱 EcoTrace

**EcoTrace** is a premium, data-driven **Carbon Footprint Awareness Platform** designed to help individuals **track, understand, and reduce** their environmental impact in real time.

Built on a unified, multi-platform architecture, EcoTrace delivers a seamless, high-performance experience across both:

* 🌐 **Web (via WebAssembly)**
* 📱 **Native Android**

---

## 🚀 Live Links & Artifacts

* 🌐 **Live Web App**
  Launch the interactive dashboard:
  👉 https://ecotrace-ebzth3t4u-sanjana-londhe-s-projects.vercel.app/

* 🤖 **Android APK**
  Download and install the app directly:
  👉 `./androidApp-debug.apk`

* 💼 **Build Journey & Validation**
  Read the technical deep-dive and public showcase:
  👉 https://www.linkedin.com/posts/sanjana-londhe-9ab383334_kotlin-jetpackcompose-multiplatform-ugcPost-7470071038240993280-KYjG/?utm_source=share&utm_medium=member_desktop&rcm=ACoAAFQPhdMBKmNQiJ0n7H6xn8Zalr3Lfg6grTA

---

## ✨ Key Features

### 📊 Real-Time Carbon Insights

EcoTrace uses a **unidirectional data flow (UDF)** system to instantly recalculate emissions as users interact—no forms, no friction.

### 🎨 Premium UI/UX System

A thoughtfully crafted interface featuring:

* Eco-inspired visual language
* High-contrast typography
* Smooth, responsive layout grids
* Material-based iconography

### ⚡ Reactive Component Engine

Custom-built UI components with:

* Micro-interaction feedback
* Dynamic color-state transitions
* Seamless responsiveness across devices

### 💡 Actionable Intelligence

An intelligent analysis engine that:

* Identifies your **top emission contributors**
* Provides **context-aware reduction strategies**
* Encourages meaningful behavioral change

---

## 🛠️ Tech Stack & Architecture

EcoTrace is built using a **modern multi-target architecture**, maximizing code reuse while maintaining native performance.

* **Language:** Kotlin (100%)
* **UI Framework:** Jetpack Compose Multiplatform
* **Web Target:** Kotlin/Wasm (WebAssembly)
* **Mobile Target:** Native Android
* **Hosting & CI/CD:** Vercel + GitHub

---

## 🏗️ Local Development Guide

### ✅ Prerequisites

* Android Studio (latest version recommended)
* JDK 17+

---

### 🌐 Run Web (Development Mode)

Start the local development server with live reload:

```bash
./gradlew :webApp:wasmJsBrowserDevelopmentRun
```

---

### 📦 Build Web (Production)

Generate optimized, deployment-ready assets:

```bash
./gradlew :webApp:wasmJsBrowserDistribution
```

📁 Output directory:

```
webApp/build/dist/wasmJs/productionExecutable
```

---

### 📱 Build Android APK

Compile and generate a debug APK:

```bash
./gradlew :androidApp:assembleDebug
```

📁 Output file:

```
androidApp/build/outputs/apk/debug/androidApp-debug.apk
```

---

## 💚 Acknowledgements

Developed as part of the **Hack2Skill Vibe Coding Virtual Event Challenge**.

Built with a focus on sustainability, performance, and meaningful user impact 🌍

---

## 🎯 Final Submission Steps

After updating this README:

```bash
git add README.md
git commit -m "docs: refine README with improved structure and clarity"
git push origin main
```

---
