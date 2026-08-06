# pac4j-oauth-ext

[English](./README.md) | [简体中文](./README.zh-CN.md)

[![Java](https://img.shields.io/badge/Java-21-orange)](https://github.com/easy-4-java/pac4j-oauth-ext) [![License](https://img.shields.io/badge/license-Apache%202.0-green)](./LICENSE)

pac4j OAuth extension: Baidu, OSChina and Yiban OAuth 2.0 clients with profiles, scribe API bindings and services
[简体中文](./README.zh-CN.md)

> **Current branch**: `feature/3.0.x`
> **Version**: `3.0.x.x.20260630-SNAPSHOT`
> **JDK baseline**: 8
> **Project status**: maintenance (1.0.x line). Not yet published to Maven Central; artifacts are distributed via the Aliyun Maven repository and GitHub Releases.

## Table of Contents

- [1. Project Overview](#1-project-overview)
- [2. Features & Status](#2-features--status)
- [3. Requirements & Compatibility](#3-requirements--compatibility)
- [4. Architecture & Modules](#4-architecture--modules)
- [5. Installation](#5-installation)
- [6. Quick Start](#6-quick-start)
- [7. Configuration](#7-configuration)
- [8. Core Usage](#8-core-usage)
- [9. Testing & Build](#9-testing--build)
- [10. Versioning & Branches](#10-versioning--branches)
- [11. Contributing & License](#11-contributing--license)

## 1. Project Overview

### 1.1 What it is

**pac4j-oauth-ext** extends pac4j's OAuth support (pac4j 4.5.7) with OAuth 2.0 clients for Chinese and Russian ecosystem providers:

- **Baidu** — `BaiduClient` + `BaiduProfile` (display name, username, picture, portrait, gender, birthday, ...);
- **OSChina** — `OschinaClient` + `OschinaProfile`;
- **Yiban** (Tencent) — `YibanClient` + `YibanProfile`, with a custom scribe API (`YibanApi20`, `YibanToken`, `YibanService`, `YibanJsonExtractor`).

Each provider includes profile creators/definitions and scribe API bindings (`BaiduApi20`, `OschinaApi20`).

### 1.2 What it is not

- Not a pac4j fork — it builds on `pac4j-oauth` / `pac4j-core`.
- Not a full OAuth framework — only the three providers above are covered in this line.

### 1.3 Typical scenarios

| Scenario | Recommended entry | Result |
|---|---|---|
| Baidu account login | `BaiduClient` + `BaiduProfile` | OAuth 2.0 profile with Baidu user attributes |
| OSChina account login | `OschinaClient` + `OschinaProfile` | OSChina OAuth 2.0 profile |
| Yiban account login | `YibanClient` + `YibanProfile` | Yiban OAuth 2.0 profile (custom scribe service) |
| Scope selection | `addScope(...)` on each client | Scoped authorization request |

<a id="2-features--status"></a>
## 2. Features & Status

| Capability | Status | Notes |
|---|:---:|---|
| Baidu OAuth 2.0 client | Available | `BaiduClient` (`OAuth20Client`), scopes: `SNSAPI_LOGIN`, `SNSAPI_BASE`, `SNSAPI_USERINFO` |
| Baidu profile | Available | `BaiduProfile`: `getDisplayName`, `getUsername`, `getPictureUrl`, `getPortraitLargeUrl`, `getGender`, `getBirthday`, `getMarriage`, `getBlood`, `getFigure`, `getConstellation`, `getEducation`, `getUserdetail`, ...; `BaiduGenderConverter` |
| OSChina client + profile | Available | `OschinaClient`, `OschinaProfile`, creator + definition |
| Yiban client + profile | Available | `YibanClient`, `YibanProfile`, creator + definition |
| Scribe API bindings | Available | `BaiduApi20`, `OschinaApi20`, `YibanApi20`, `YibanService`, `YibanToken`, `YibanJsonExtractor` |
| Helper utilities | Available | `MyCommonHelper` |

<a id="3-requirements--compatibility"></a>
## 3. Requirements & Compatibility

| Component | Version | Notes |
|---|---:|---|
| JDK | 21+ | Enforced by `maven-enforcer-plugin` |
| Maven | 3.0+ | Enforcer minimum |
| pac4j-core / config / http / oauth | 4.5.7 | Pinned |
| SLF4J | 2.0.18 | Logging facade |

Version-line matrix:

| Version line | Branch | JDK | Version pattern | Purpose |
|---|---|---:|---|---|
| 1.0.x | `feature/3.0.x` (this branch) | 8 | `1.0.x.*` | Legacy projects, Boot 2.x starter line |
| 2.0.x | `feature/2.0.x` | 17 | `2.0.x.*` | JDK 17 line |
| 3.0.x | `feature/3.0.x` | 21 | `3.0.x.*` | New projects |

<a id="4-architecture--modules"></a>
## 4. Architecture & Modules

```text
[ Web Application ]
        |
        | pac4j-oauth-ext + pac4j-oauth/core
        v
+------------------------------------------+
| Clients    BaiduClient / OschinaClient /  |
|            YibanClient (OAuth20Client)    |
| Profiles   BaiduProfile / OschinaProfile /|
|            YibanProfile + creators +      |
|            definitions                    |
| Scribe     BaiduApi20 / OschinaApi20 /    |
|            YibanApi20 + YibanService /    |
|            YibanToken / YibanJsonExtractor|
+------------------------------------------+
        |
        v
[ Provider OAuth 2.0 endpoints ]
```

Single-module library (packaging `jar`). Package layout:

| Package | Responsibility |
|---|---|
| `org.pac4j.oauth.client` | `BaiduClient`, `OschinaClient`, `YibanClient` (with scope enums) |
| `org.pac4j.oauth.profile.baidu` | `BaiduProfile`, `BaiduProfileCreator`, `BaiduProfileDefinition`, `BaiduGenderConverter` |
| `org.pac4j.oauth.profile.oschina` | `OschinaProfile`, creator, definition |
| `org.pac4j.oauth.profile.yiban` | `YibanProfile`, creator, definition |
| `org.pac4j.scribe.builder.api` | `BaiduApi20`, `OschinaApi20`, `YibanApi20` |
| `org.pac4j.scribe.model` / `.service` / `.extractors` | `YibanToken`, `YibanService`, `YibanJsonExtractor` |
| `org.pac4j.util` | `MyCommonHelper` |

<a id="5-installation"></a>
## 5. Installation

Maven:

```xml
<dependency>
    <groupId>io.github.easy4j</groupId>
    <artifactId>pac4j-oauth-ext</artifactId>
    <version>3.0.x.x.20260630-SNAPSHOT</version>
</dependency>
```

Gradle:

```groovy
implementation 'io.github.easy4j:pac4j-oauth-ext:3.0.x.x.20260630-SNAPSHOT'
```

Snapshot builds require an enabled snapshot repository (Aliyun Maven snapshot repository per `distributionManagement` in `pom.xml`).

<a id="6-quick-start"></a>
## 6. Quick Start

```java
// Baidu OAuth 2.0 client
BaiduClient client = new BaiduClient("your-api-key", "your-secret-key");
client.setCallbackUrl("http://localhost:8080/callback");
client.addScope(BaiduClient.BaiduScope.SNSAPI_BASE);
client.addScope(BaiduClient.BaiduScope.SNSAPI_USERINFO);
```

Register the client in the pac4j `Config` (e.g. `new Config("http://localhost:8080/callback", client)`).

**Expected result**: visiting the authorization URL redirects to Baidu's consent page with the requested scopes; after the callback, pac4j produces a `BaiduProfile` with the user's display name, avatar and (depending on scope) personal info.

<a id="7-configuration"></a>
## 7. Configuration

Configured through constructors and setters (no configuration properties):

| Entry | Configuration surface |
|---|---|
| `BaiduClient` / `OschinaClient` / `YibanClient` | `(key, secret)` constructors; `setCallbackUrl(...)`; `addScope(...)` / `setScopes(...)`; OAuth 2.0 settings inherited from `OAuth20Client` |
| Scope enums | `BaiduScope`, `OschinaScope`, `YibanScope` (`SNSAPI_LOGIN`, `SNSAPI_BASE`, `SNSAPI_USERINFO`) |

<a id="8-core-usage"></a>
## 8. Core Usage

### 8.1 Yiban profile data

```java
YibanClient client = new YibanClient("your-api-key", "your-secret-key");
client.setCallbackUrl("http://localhost:8080/callback");

// After authentication, profiles carry provider data:
YibanProfile profile = (YibanProfile) profiles.get(0); // from the pac4j flow
```

### 8.2 OSChina client with scopes

```java
OschinaClient client = new OschinaClient("your-api-key", "your-secret-key");
client.setCallbackUrl("http://localhost:8080/callback");
client.addScope(OschinaClient.OschinaScope.SNSAPI_USERINFO);
```

<a id="9-testing--build"></a>
## 9. Testing & Build

```bash
mvn clean verify
```

- Test sources include scribe API examples (`BaiduExample`, `YibanExample`, `OsChinaExample` under `src/test/java/com/github/scribejava/apis/examples`).
- JaCoCo runs `prepare-agent`, `report` and `check` on the `verify` phase with a **90% line-coverage** rule (`haltOnFailure=false`).
- Release packaging (`mvn -Prelease deploy`) attaches sources and javadoc jars, GPG-signs artifacts and is wired for Sonatype Central Publishing; plain `mvn deploy` routes SNAPSHOT/release artifacts to the Aliyun Maven repository per `distributionManagement`.

<a id="10-versioning--branches"></a>
## 10. Versioning & Branches

| Branch | Version pattern | JDK | Maintenance policy |
|---|---|---|---|
| `feature/1.0.x` (this branch) | `1.0.x.*` | 8 | Compatibility fixes and JDK-8-safe dependency upgrades only |
| `feature/2.0.x` | `2.0.x.*` | 17 | JDK 17 line |
| `feature/3.0.x` | `3.0.x.*` | 21 | JDK 21 line |

<a id="11-contributing--license"></a>
## 11. Contributing & License

Contributions are welcome. Run `mvn clean verify` before opening a pull request and describe compatibility, testing and migration impact. This project is licensed under the [Apache License 2.0](LICENSE).
