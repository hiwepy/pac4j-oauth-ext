# pac4j-oauth-ext

[English](./README.md) | [简体中文](./README.zh-CN.md)

[![Java](https://img.shields.io/badge/Java-8-orange)](https://github.com/easy-4-java/pac4j-oauth-ext) [![License](https://img.shields.io/badge/license-Apache%202.0-green)](./LICENSE)

pac4j OAuth 扩展：百度、OSChina 与易班 OAuth 2.0 客户端、档案与 scribe API 绑定

> **当前分支**：`feature/1.0.x`
> **版本**：`1.0.x.20260630-SNAPSHOT`
> **JDK 基线**：8
> **项目状态**：维护中（1.0.x 线）。尚未发布 Maven Central；制品通过 Aliyun Maven 仓库与 GitHub Releases 分发。

## 目录

- [1. 项目概述](#1-项目概述)
- [2. 能力与状态](#2-features--status)
- [3. 运行要求与兼容性](#3-requirements--compatibility)
- [4. 架构与模块](#4-architecture--modules)
- [5. 引入依赖](#5-installation)
- [6. 快速开始](#6-quick-start)
- [7. 配置](#7-configuration)
- [8. 核心用法](#8-core-usage)
- [9. 测试与构建](#9-testing--build)
- [10. 版本线与分支](#10-versioning--branches)
- [11. 贡献与许可证](#11-contributing--license)

## 1. 项目概述

### 1.1 是什么

**pac4j-oauth-ext** 在 pac4j 的 OAuth 支持（本线 pac4j 4.5.7）之上扩展面向国内与俄语生态服务商的 OAuth 2.0 客户端：

- **百度**——`BaiduClient` + `BaiduProfile`（昵称、用户名、头像、肖像、性别、生日等）；
- **OSChina**——`OschinaClient` + `OschinaProfile`；
- **易班**（腾讯）——`YibanClient` + `YibanProfile`，并配套自定义 scribe API（`YibanApi20`、`YibanToken`、`YibanService`、`YibanJsonExtractor`）。

每家服务商均包含档案 creator / definition 与 scribe API 绑定（`BaiduApi20`、`OschinaApi20`）。

### 1.2 不是什么

- 不是 pac4j 的分支——它构建于 `pac4j-oauth` / `pac4j-core` 之上。
- 不是完整的 OAuth 框架——本线仅覆盖上述三家服务商。

### 1.3 典型使用场景

| 场景 | 推荐入口 | 结果 |
|---|---|---|
| 百度账号登录 | `BaiduClient` + `BaiduProfile` | 携带百度用户属性的 OAuth 2.0 档案 |
| OSChina 账号登录 | `OschinaClient` + `OschinaProfile` | OSChina OAuth 2.0 档案 |
| 易班账号登录 | `YibanClient` + `YibanProfile` | 易班 OAuth 2.0 档案（自定义 scribe service） |
| Scope 选择 | 各客户端 `addScope(...)` | 带 scope 的授权请求 |

<a id="2-features--status"></a>
## 2. 能力与状态

| 能力 | 状态 | 说明 |
|---|:---:|---|
| 百度 OAuth 2.0 客户端 | 可用 | `BaiduClient`（`OAuth20Client`），scopes：`SNSAPI_LOGIN`、`SNSAPI_BASE`、`SNSAPI_USERINFO` |
| 百度档案 | 可用 | `BaiduProfile`：`getDisplayName`、`getUsername`、`getPictureUrl`、`getPortraitLargeUrl`、`getGender`、`getBirthday`、`getMarriage`、`getBlood`、`getFigure`、`getConstellation`、`getEducation`、`getUserdetail` 等；`BaiduGenderConverter` |
| OSChina 客户端 + 档案 | 可用 | `OschinaClient`、`OschinaProfile`、creator + definition |
| 易班客户端 + 档案 | 可用 | `YibanClient`、`YibanProfile`、creator + definition |
| Scribe API 绑定 | 可用 | `BaiduApi20`、`OschinaApi20`、`YibanApi20`、`YibanService`、`YibanToken`、`YibanJsonExtractor` |
| 工具类 | 可用 | `MyCommonHelper` |

<a id="3-requirements--compatibility"></a>
## 3. 运行要求与兼容性

| 组件 | 版本 | 说明 |
|---|---:|---|
| JDK | 8+ | 由 `maven-enforcer-plugin` 强制校验 |
| Maven | 3.0+ | Enforcer 下限 |
| pac4j-core / config / http / oauth | 4.5.7 | 固定版本 |
| SLF4J | 2.0.18 | 日志门面 |

版本线矩阵：

| 版本线 | 分支 | JDK | 版本模式 | 用途 |
|---|---|---:|---|---|
| 1.0.x | `feature/1.0.x`（当前分支） | 8 | `1.0.x.*` | 存量项目、Boot 2.x Starter 线 |
| 2.0.x | `feature/2.0.x` | 17 | `2.0.x.*` | JDK 17 线 |
| 3.0.x | `feature/3.0.x` | 21 | `3.0.x.*` | 新项目 |

<a id="4-architecture--modules"></a>
## 4. 架构与模块

```text
[ Web 应用 ]
        |
        | pac4j-oauth-ext + pac4j-oauth/core
        v
+------------------------------------------+
| 客户端   BaiduClient / OschinaClient /    |
|          YibanClient（OAuth20Client）     |
| 档案     BaiduProfile / OschinaProfile /  |
|          YibanProfile + creators +        |
|          definitions                      |
| Scribe   BaiduApi20 / OschinaApi20 /      |
|          YibanApi20 + YibanService /      |
|          YibanToken / YibanJsonExtractor  |
+------------------------------------------+
        |
        v
[ 服务商 OAuth 2.0 端点 ]
```

单模块库（打包类型 `jar`）。包结构：

| 包 | 职责 |
|---|---|
| `org.pac4j.oauth.client` | `BaiduClient`、`OschinaClient`、`YibanClient`（含 scope 枚举） |
| `org.pac4j.oauth.profile.baidu` | `BaiduProfile`、`BaiduProfileCreator`、`BaiduProfileDefinition`、`BaiduGenderConverter` |
| `org.pac4j.oauth.profile.oschina` | `OschinaProfile`、creator、definition |
| `org.pac4j.oauth.profile.yiban` | `YibanProfile`、creator、definition |
| `org.pac4j.scribe.builder.api` | `BaiduApi20`、`OschinaApi20`、`YibanApi20` |
| `org.pac4j.scribe.model` / `.service` / `.extractors` | `YibanToken`、`YibanService`、`YibanJsonExtractor` |
| `org.pac4j.util` | `MyCommonHelper` |

<a id="5-installation"></a>
## 5. 引入依赖

Maven：

```xml
<dependency>
    <groupId>io.github.easy4j</groupId>
    <artifactId>pac4j-oauth-ext</artifactId>
    <version>1.0.x.20260630-SNAPSHOT</version>
</dependency>
```

Gradle：

```groovy
implementation 'io.github.easy4j:pac4j-oauth-ext:1.0.x.20260630-SNAPSHOT'
```

快照版本需要启用对应快照仓库（`pom.xml` 中 `distributionManagement` 指向 Aliyun Maven 仓库）。

<a id="6-quick-start"></a>
## 6. 快速开始

```java
// 百度 OAuth 2.0 客户端
BaiduClient client = new BaiduClient("your-api-key", "your-secret-key");
client.setCallbackUrl("http://localhost:8080/callback");
client.addScope(BaiduClient.BaiduScope.SNSAPI_BASE);
client.addScope(BaiduClient.BaiduScope.SNSAPI_USERINFO);
```

将客户端注册到 pac4j `Config`（如 `new Config("http://localhost:8080/callback", client)`）。

**预期结果**：访问授权 URL 后重定向至百度授权页（携带所请求的 scope）；回调后 pac4j 产出 `BaiduProfile`，包含用户昵称、头像及（依 scope）个人信息。

<a id="7-configuration"></a>
## 7. 配置

通过构造器与 setter 配置（无配置属性）：

| 入口 | 配置面 |
|---|---|
| `BaiduClient` / `OschinaClient` / `YibanClient` | `(key, secret)` 构造器；`setCallbackUrl(...)`；`addScope(...)` / `setScopes(...)`；其余继承 `OAuth20Client` 的 OAuth 2.0 设置 |
| Scope 枚举 | `BaiduScope`、`OschinaScope`、`YibanScope`（`SNSAPI_LOGIN`、`SNSAPI_BASE`、`SNSAPI_USERINFO`） |

<a id="8-core-usage"></a>
## 8. 核心用法

### 8.1 易班档案数据

```java
YibanClient client = new YibanClient("your-api-key", "your-secret-key");
client.setCallbackUrl("http://localhost:8080/callback");

// 认证完成后，档案携带服务商数据：
YibanProfile profile = (YibanProfile) profiles.get(0); // 来自 pac4j 流程
```

### 8.2 带 scope 的 OSChina 客户端

```java
OschinaClient client = new OschinaClient("your-api-key", "your-secret-key");
client.setCallbackUrl("http://localhost:8080/callback");
client.addScope(OschinaClient.OschinaScope.SNSAPI_USERINFO);
```

<a id="9-testing--build"></a>
## 9. 测试与构建

```bash
mvn clean verify
```

- 测试源码包含 scribe API 示例（`src/test/java/com/github/scribejava/apis/examples` 下的 `BaiduExample`、`YibanExample`、`OsChinaExample`）。
- JaCoCo 在 `verify` 阶段执行 `prepare-agent`、`report` 与 `check`，行覆盖率规则为 **90%**（`haltOnFailure=false`）。
- 发布打包（`mvn -Prelease deploy`）附带 sources 与 javadoc 构件并执行 GPG 签名，对接 Sonatype Central Publishing；普通 `mvn deploy` 按版本后缀路由到 Aliyun Maven 仓库（见 `distributionManagement`）。

<a id="10-versioning--branches"></a>
## 10. 版本线与分支

| 分支 | 版本模式 | JDK | 维护策略 |
|---|---|---|---|
| `feature/1.0.x`（当前分支） | `1.0.x.*` | 8 | 仅接受兼容性修复与 JDK 8 安全的依赖升级 |
| `feature/2.0.x` | `2.0.x.*` | 17 | JDK 17 线 |
| `feature/3.0.x` | `3.0.x.*` | 21 | JDK 21 线 |

<a id="11-contributing--license"></a>
## 11. 贡献与许可证

欢迎贡献。提交 Pull Request 前请执行 `mvn clean verify`，并说明兼容性、测试与迁移影响。本项目采用 [Apache License 2.0](LICENSE) 许可证。
