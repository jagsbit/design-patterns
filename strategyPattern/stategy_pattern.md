# Strategy Design Pattern

The Strategy Design Pattern is a **Behavioral Design Pattern**.

It is used when:

- A task can be performed in **multiple ways**
- and we want to **switch behaviors dynamically** at runtime.

Instead of writing:

```java
if(type.equals("CAR")) {
   // logic
}
else if(type.equals("BIKE")) {
   // logic
}
```

we move each behavior into **separate classes**.

---

## Main Idea of Strategy Pattern

```
Encapsulate algorithms/behaviors
into separate classes
and make them interchangeable.
```

---

## Real-Life Example

Think of **Google Maps**.

You can travel using:
- Car
- Bike
- Walking

The destination is same. Only **strategy changes**.

---

## Vehicle Example (Best Example)

Suppose vehicles can move differently.

- **Car** → Moves on road.
- **Boat** → Moves on water.
- **Airplane** → Flies in air.

If we directly write logic inside `Vehicle` class:

```java
class Vehicle {

   void move() {
      // many if else conditions
   }
}
```

This becomes difficult to maintain.

So we use **Strategy Pattern**.

---

## Structure of Strategy Pattern

```
Context
   ↓
Strategy Interface
   ↓
Concrete Strategies
```

---

## Step-by-Step Vehicle Example

### Step 1: Create Strategy Interface

```java
interface DriveStrategy {

    void drive();
}
```

**Explanation**

This interface represents different driving behaviors.

---

### Step 2: Create Concrete Strategies

**Normal Drive Strategy**

```java
class NormalDriveStrategy
        implements DriveStrategy {

    public void drive() {
        System.out.println("Driving Normally on Road");
    }
}
```

**Sports Drive Strategy**

```java
class SportsDriveStrategy
        implements DriveStrategy {

    public void drive() {
        System.out.println("Driving at High Speed");
    }
}
```

**OffRoad Drive Strategy**

```java
class OffRoadDriveStrategy
        implements DriveStrategy {

    public void drive() {
        System.out.println("Driving on Rough Roads");
    }
}
```

**Explanation**

Each class represents one algorithm/behavior.

| Strategy             | Behavior         |
|----------------------|------------------|
| NormalDriveStrategy  | Normal driving   |
| SportsDriveStrategy  | Fast driving     |
| OffRoadDriveStrategy | Off-road driving |

---

### Step 3: Create Vehicle Class (Context)

```java
class Vehicle {

    private DriveStrategy driveStrategy;

    public Vehicle(DriveStrategy driveStrategy) {
        this.driveStrategy = driveStrategy;
    }

    public void drive() {
        driveStrategy.drive();
    }
}
```

**Explanation**

Vehicle **HAS-A** strategy. This is **composition**.

```
Vehicle HAS-A DriveStrategy
```

Vehicle **delegates** work to strategy object.

---

### Step 4: Create Vehicle Types

**Sports Car**

```java
class SportsCar extends Vehicle {

    public SportsCar() {
        super(new SportsDriveStrategy());
    }
}
```

**OffRoad Vehicle**

```java
class OffRoadVehicle extends Vehicle {

    public OffRoadVehicle() {
        super(new OffRoadDriveStrategy());
    }
}
```

**Passenger Vehicle**

```java
class PassengerVehicle extends Vehicle {

    public PassengerVehicle() {
        super(new NormalDriveStrategy());
    }
}
```

**Explanation**

Each vehicle chooses its own strategy.

| Vehicle          | Strategy Used        |
|------------------|----------------------|
| SportsCar        | SportsDriveStrategy  |
| OffRoadVehicle   | OffRoadDriveStrategy |
| PassengerVehicle | NormalDriveStrategy  |

---

### Step 5: Main Method

```java
public class Main {

    public static void main(String[] args) {

        Vehicle v1 = new SportsCar();
        v1.drive();

        Vehicle v2 = new OffRoadVehicle();
        v2.drive();

        Vehicle v3 = new PassengerVehicle();
        v3.drive();
    }
}
```

### Output

```
Driving at High Speed
Driving on Rough Roads
Driving Normally on Road
```

---

## Main Understanding

The **behavior changes dynamically**.  
But the `Vehicle` class **remains same**.

---

## Why Strategy Pattern?

Without strategy pattern:

```java
class Vehicle {

   void drive() {

      if(type.equals("SPORTS")) {
      }

      else if(type.equals("OFFROAD")) {
      }

      else if(type.equals("NORMAL")) {
      }
   }
}
```

**Problems:**
- Huge if-else
- Duplicate code
- Hard to extend
- Violates Open-Closed Principle

### Strategy Pattern Solves This

We separate behaviors into classes. Now:
- Easy to add new strategy
- Easy to change behavior
- No large conditions

---

## Advantages

### 1. Follows Open-Closed Principle
Add new strategies without modifying old code.

### 2. Removes Duplicate Code
Common logic stays separate.

### 3. Runtime Behavior Change
Behavior can change dynamically.

### 4. Cleaner Code
No giant if-else blocks.

---

## Disadvantages

### 1. Many Classes
Each strategy needs separate class.

### 2. Slightly Complex
More abstraction involved.

---

## Real-World Uses

Strategy Pattern is used in:
- Payment systems
- Sorting algorithms
- Navigation systems
- Compression algorithms
- Authentication methods

---

## Difference Between Strategy and Factory Pattern

> This is **VERY IMPORTANT**. Students usually confuse them.

### Factory Pattern

**Purpose:** Used to **CREATE** objects.

Factory pattern focuses on: **Object Creation**

### Strategy Pattern

**Purpose:** Used to **CHANGE BEHAVIOR/ALGORITHM**.

Strategy pattern focuses on: **Behavior Selection**

### Simple Understanding

| Pattern  | Focus                   |
|----------|-------------------------|
| Factory  | Which object to create  |
| Strategy | Which behavior to use   |

---

### Vehicle Example Comparison

**Factory Pattern Example**

```java
Vehicle v = VehicleFactory.getVehicle("CAR");
```

Factory decides: Create `Car` object.  
Main goal: **Object creation**

**Strategy Pattern Example**

```java
Vehicle v =
      new Vehicle(new SportsDriveStrategy());
```

Strategy decides: How vehicle should drive.  
Main goal: **Behavior selection**

---

### Core Difference

```
Factory Pattern  →  Creates object
Strategy Pattern →  Changes behavior
```

---

### Another Easy Analogy

**Factory Pattern**

> Restaurant creates food.  
> `Pizza Factory → Pizza`  
> Focus: **Food creation**

**Strategy Pattern**

> How you eat food changes.
> - Spicy Strategy
> - Sweet Strategy
> - Healthy Strategy
>
> Focus: **Behavior/style**

---

### Difference Table

| Feature                | Factory Pattern    | Strategy Pattern     |
|------------------------|--------------------|----------------------|
| Pattern Type           | Creational         | Behavioral           |
| Main Purpose           | Create objects     | Change behavior      |
| Focus                  | Object creation    | Algorithm selection  |
| Uses Inheritance?      | Often yes          | Mostly composition   |
| Runtime Behavior Change| No                 | Yes                  |
| Example                | VehicleFactory     | DriveStrategy        |

---

## Important Interview Point

- **Factory Pattern** → *"What object should be created?"*
- **Strategy Pattern** → *"How should the task be performed?"*

---

## Final Definitions

### Strategy Pattern Definition

> Strategy Pattern defines a family of algorithms, encapsulates them into separate classes, and makes them **interchangeable at runtime**.

### Factory Pattern Definition

> Factory Pattern creates objects without exposing object creation logic to the client.
