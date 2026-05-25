# Adapter Design Pattern in Java

The Adapter Design Pattern is a **Structural Design Pattern**.

It allows two **incompatible interfaces** to work together.

The adapter acts like a **bridge** between two different systems.

---

## Real-Life Example

Imagine:

- Your laptop charger has a **Type-C pin**
- But the wall socket only supports a **2-pin plug**

So you use an **adapter/converter**.

> The adapter converts one interface into another compatible interface.

---

## In Your Example

You have:

- **iPhone** expects: `IphoneCharger`
- But you only have: `AndroidCharger`

These two are **incompatible**.

So you create an **Adapter** which converts:

```
AndroidCharger -> IphoneCharger
```

---

## Flow of Your Program

### Step 1: Create iPhone

```java
Iphone iphone17 = new Iphone();
```

The iPhone only accepts `IphoneCharger`.

### Step 2: Create Adapter

```java
IphoneCharger charger = new Adapter(new RealmeCharger());
```

Here:
- `RealmeCharger` is an `AndroidCharger`
- But iPhone cannot use it directly

So the `Adapter` wraps the `RealmeCharger` and **behaves like** an `IphoneCharger`.

### Step 3: Set Charger

```java
iphone17.setCharger(charger);
```

Now iPhone thinks it received a valid iPhone charger.

### Step 4: Charge Phone

```java
iphone17.chargePhone();
```

Internally:

```
adapter.charge()
    calls
androidCharger.charge();
```

So finally:

```
Charging with Realme Charger
Iphone is Charged
```

---

## Why Adapter Pattern is Needed

Suppose you already have old code/library/service.

But your new system expects a **different interface**.

Instead of modifying existing code, you create an **adapter**.

---

## Problems Solved by Adapter Pattern

### 1. Compatibility Problem
Two classes cannot communicate because interfaces differ. Adapter solves this.

### 2. Reuse Existing Code
You can reuse old classes without changing them.

### 3. Avoid Modifying Existing Classes

Existing code may be:
- Third-party library
- Legacy code
- Already tested

Changing it is **risky**. Adapter avoids modification.

---

## Structure of Adapter Pattern

### 1. Target Interface
The interface expected by client.

**Example:** `IphoneCharger`

### 2. Adaptee
Existing incompatible class/interface.

**Example:** `AndroidCharger`

### 3. Adapter
Converts adaptee into target.

**Example:** `Adapter`

### 4. Client
Uses target interface.

**Example:** `Iphone`

### Visual Representation

```
Iphone -----> IphoneCharger
                  ^
                  |
               Adapter
                  |
                  v
           AndroidCharger
                  ^
                  |
          RealmeCharger
```

---

## Your Adapter Class Explained

```java
public class Adapter implements IphoneCharger {
```

Adapter **behaves like** an iPhone charger.

```java
private AndroidCharger androidCharger;
```

Adapter internally stores Android charger.

This is called:

> **Composition / HAS-A Relationship**
> `Adapter HAS-A AndroidCharger`

```java
public Adapter(AndroidCharger androidCharger) {
    this.androidCharger = androidCharger;
}
```

Constructor injection.

```java
public void charge() {
    androidCharger.charge();
}
```

Adapter **delegates** work to Android charger.

### Important Point

The iPhone **never knows**:
- Realme charger exists
- Samsung charger exists
- Android charger exists

It only knows: `IphoneCharger`

This provides: **Loose Coupling**

---

## Types of Adapter Pattern

### 1. Object Adapter (Your Example)

Uses: **Composition**

```
Adapter HAS-A Adaptee
```

```java
private AndroidCharger androidCharger;
```

This is the **most commonly used** approach.

### 2. Class Adapter

Uses: **Inheritance**

```java
class Adapter extends AndroidCharger implements IphoneCharger
```

> Java does not support multiple class inheritance, so **object adapter is preferred**.

---

## Advantages of Adapter Pattern

### 1. Reuse Existing Classes
No need to rewrite old code.

### 2. Loose Coupling
Client depends only on target interface.

### 3. Better Maintainability
No modification in existing code.

### 4. Follows Open/Closed Principle
Open for extension, closed for modification.

---

## Disadvantages

### 1. More Classes
Extra adapter classes increase complexity.

### 2. Slightly More Indirection
Calls pass through adapter.

---

## Real-World Examples of Adapter Pattern

### 1. Mobile Chargers
Exactly your example.

### 2. Card Reader
Laptop reads memory card using adapter.

### 3. Java `InputStreamReader`

Converts:
```
Byte Stream -> Character Stream
```

### 4. Spring Security
Different authentication providers adapted into common interface.

### 5. Payment Gateway Integration

Different payment APIs adapted to same interface.

Example:
- Paytm
- PhonePe
- Razorpay
- Stripe

All adapted into: `PaymentGateway`

---

## Small Improvement in Your Code

Make constructor **public**.

Currently:
```java
Adapter(AndroidCharger androidCharger)
```

Should be:
```java
public Adapter(AndroidCharger androidCharger)
```

Otherwise constructor has default/package-private access.

### Better Version

```java
public class Adapter implements IphoneCharger {

    private AndroidCharger androidCharger;

    public Adapter(AndroidCharger androidCharger) {
        this.androidCharger = androidCharger;
    }

    @Override
    public void charge() {
        androidCharger.charge();
    }
}
```

### Output

```
Charging with Realme Charger
Iphone is Charged
```

---

## Difference Between Adapter and Decorator Pattern

| Feature           | Adapter             | Decorator                  |
|-------------------|---------------------|----------------------------|
| Purpose           | Convert interface   | Add new behavior           |
| Focus             | Compatibility       | Functionality enhancement  |
| Changes behavior? | No                  | Yes                        |
| Real Example      | Charger converter   | Coffee toppings            |

---

## Key Interview Definition

> Adapter Design Pattern converts one interface into another interface that the client expects, allowing incompatible classes to work together.

---

## One-Line Summary

> Adapter pattern is used when existing class functionality is needed, but its interface is **incompatible** with the client.
