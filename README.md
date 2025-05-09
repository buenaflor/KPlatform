# KPlatform

[![Kotlin Multiplatform](https://img.shields.io/badge/Kotlin-Multiplatform-7F52FF?logo=kotlin&logoColor=white" alt="Kotlin Multiplatform")](https://kotlinlang.org/docs/multiplatform.html)
[![Maven Central](https://img.shields.io/maven-central/v/com.giancarlobuenaflor/kplatform")](https://central.sonatype.com/artifact/com.giancarlobuenaflor/kplatform)
[![License](https://img.shields.io/github/license/buenaflor/kplatform?color=blue")](https://github.com/buenaflor/kplatform/blob/main/LICENSE)

**KPlatform** is a lightweight, pluggable Kotlin Multiplatform (KMP) library that exposes runtime
information about _where_ your code is running so **you can write platform-specific logic right
from `commonMain`**—without scattering `expect/actual` declarations or creating extra source-sets
when you don’t actually need platform APIs.

---

## 🚀 Features

- **Zero-config** — Works out-of-the-box with all major Kotlin targets.
- **No Boilerplate** — Keep platform branches close to your domain code.

---

## 📑 Table of Contents

1. [Installation](#-installation)
2. [Quick Start](#-quick-start)
3. [Testing](#-testing)
4. [Supported Targets](#-supported-targets)
5. [License](#-license)

---

## 📦 Installation

Add the dependency to the **`commonMain`** source-set in your Multiplatform `build.gradle(.kts)`
file:
