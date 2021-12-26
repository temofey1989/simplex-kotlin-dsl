![example event parameter](https://github.com/temofey1989/simplex-kotlin-dsl/actions/workflows/build.yml/badge.svg?branch=main)
[![Quality Gate Status](https://sonarcloud.io/api/project_badges/measure?project=temofey1989_simplex-kotlin-dsl&metric=alert_status)](https://sonarcloud.io/summary/new_code?id=temofey1989_simplex-kotlin-dsl)
[![GitHub license](https://img.shields.io/badge/license-Apache%20License%202.0-blue.svg?style=flat)](https://www.apache.org/licenses/LICENSE-2.0)

# Overview

Kotlin DSL for Simplex Algorithm implementation of Apache Commons Math.

## Setup

### Maven

```xml

<dependency>
    <groupId>io.justdevit.math</groupId>
    <artifactId>simplex-kotlin-dsl</artifactId>
    <version>${simplex-kotlin-dsl.version}</version>
</dependency>
```

### Gradle

```groovy
implementation group: 'io.justdevit.math', name: 'simplex-kotlin-dsl', version: $simplexDslVersion
```

### Gradle (kt)

```kotlin
implementation("io.justdevit.math:simplex-kotlin-dsl:$simplexDslVersion")
```

---

## Usage

To calculate optimal solution you need to declare system of linear equations.  
You can do it like this:

```kotlin
val result = simplex {
    // Linear Constraint Set
    0.3 * x1 + 0.5 * x2 + 0.4 * x3 le 20
    8.x1 + 7.x2 + 12.x3 le 480
    x1 + x2 eq 16
    x3 ge 25

    // Linear Objective Function
    20.x1 + 55.x2 + 60.x3 to MAX
}
```

In builder block the set of linear constraint equations and linear objective function should be defined.  
The result of the function will be an optimal solution. In case of no optimal solution, an exception will be thrown.

### Parameters

To declare a parameter you can use function `x(index)`. Index should be from range `0..Int.MAX_VALUE`.  
Also, you can use predefined parameters singletons `x1, x2, ..., x9`. In case no coefficient defined, `1.0` will be used as a default value.  
For `Short`, `Int` and `Long` types. For decimal types this approach is not used for better readability (due to dots).

### Negative Parameters

In many cases Simplex Algorithm works with non-negative parameters. However, you can configure the run of the Simplex Algorithm to use negative values. To do so, just define
a `strictNonNegativeParameters` parameter of the `simplex()` function.

```kotlin
simplex(strictNonNegativeParameters = false) {
    ...
}
```

Default value is `true`.

### Operators

There are 4 types of operator infix functions:

| Operator | Description                                                                      |
|----------|----------------------------------------------------------------------------------|
| `le`     | Less or equals (`<=`). Used for declaring linear constraint.                     |
| `ge`     | Great or equals (`>=`). Used for declaring linear constraint.                    |
| `eq`     | Equals (`==`). Used for declaring linear constraint.                             |
| `to`     | Used for declaration of the goal (`MIN`/`MAX`) of the linear objective function. |

### Goals

There are two types of the goals:

* `MIN` - stands for `MINIMIZE` goal.
* `MAX` - stands for `MAXIMIZE` goal.
