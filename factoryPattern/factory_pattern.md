# Introduction to Factory Design Pattern

The Factory Design Pattern is a **Creational Design Pattern** used to create objects in an organized and flexible manner.

Instead of creating objects directly using:

```java
new Car();
```

we use a **factory** to create the object.

The factory decides:

- Which object to create
- When to create
- How to create

The client only **requests** the object.

---

## Why Do We Need Factory Pattern?

Suppose we write code like this:

```java
Car c = new Car();
Bike b = new Bike();
Truck t = new Truck();
```

**Problems:**

- Client is tightly coupled with concrete classes
- If new vehicles are added, client code changes
- Difficult to maintain large projects

Factory Pattern solves this problem by separating:

```
Object Creation Logic
        FROM
Business Logic
```

---

## Main Advantages of Factory Pattern

### 1. Loose Coupling
Client does not know actual implementation classes.

### 2. Better Maintainability
Object creation logic is centralized.

### 3. Easy Scalability
New classes can be added easily.

### 4. Cleaner Code
Business logic becomes simple and readable.

---

## Types of Factory Pattern

There are mainly **3 types**:

1. Simple Factory
2. Factory Method
3. Abstract Factory

---

## 1. SIMPLE FACTORY PATTERN

### Definition
A single factory class creates different objects based on input.

### Main Idea
```
One Factory
Creates Multiple Objects
```

### Vehicle Example

Suppose we have:
- Car
- Bike

One factory will create both objects.

### Structure
```
Client
   ↓
VehicleFactory
   ↓
Car / Bike
```

### Step-by-Step Code

#### Step 1: Create Vehicle Interface

```java
interface Vehicle {

    void start();
}
```

**Explanation**

All vehicles must implement:
- `start()`

This provides common behavior.

#### Step 2: Create Concrete Classes

**Car Class**

```java
class Car implements Vehicle {

    public void start() {
        System.out.println("Car Started");
    }
}
```

**Bike Class**

```java
class Bike implements Vehicle {

    public void start() {
        System.out.println("Bike Started");
    }
}
```

**Explanation**

These are actual implementations.
- Car object
- Bike object

Both implement `Vehicle` interface.

#### Step 3: Create Factory Class

```java
class VehicleFactory {

    public static Vehicle getVehicle(String type) {

        if(type.equalsIgnoreCase("CAR")) {
            return new Car();
        }

        else if(type.equalsIgnoreCase("BIKE")) {
            return new Bike();
        }

        return null;
    }
}
```

**Explanation**

Factory checks input:

| Input | Object Created |
|-------|---------------|
| CAR   | Car object    |
| BIKE  | Bike object   |

The client does **NOT** create objects directly.  
Factory creates them.

#### Step 4: Main Method

```java
public class Main {

    public static void main(String[] args) {

        Vehicle v =
                VehicleFactory.getVehicle("CAR");

        v.start();
    }
}
```

#### Output
```
Car Started
```

### Flow of Execution
```
Client asks factory
        ↓
Factory decides object
        ↓
Factory returns object
```

### Advantages

#### 1. Centralized Object Creation
All creation logic exists in one place.

#### 2. Cleaner Client Code
Client only requests object.

#### 3. Easy to Understand
Good for beginners and small projects.

### Disadvantages

#### 1. Violates Open-Closed Principle
If new vehicle comes (e.g., Truck), factory must be modified.

#### 2. Large Factory Class
As products increase, factory becomes huge.

### Best Use Cases

Use Simple Factory when:
- Few object types exist
- Logic is simple
- Small applications

### Real-Life Example

**Restaurant Counter**

You request Pizza → Counter creates and gives pizza.  
You do not cook it yourself.

---

## 2. FACTORY METHOD PATTERN

### Definition
Object creation responsibility is **delegated to subclasses**.

Instead of one big factory:
- Each product has its own factory

### Main Idea
```
One Factory → One Product
```

### Vehicle Example

We create:
- `CarFactory`
- `BikeFactory`

Each factory creates only one vehicle.

### Structure
```
VehicleFactory
      ↓
CarFactory / BikeFactory
      ↓
Car / Bike
```

### Step-by-Step Code

#### Step 1: Vehicle Interface

```java
interface Vehicle {

    void start();
}
```

#### Step 2: Concrete Classes

**Car**

```java
class Car implements Vehicle {

    public void start() {
        System.out.println("Car Started");
    }
}
```

**Bike**

```java
class Bike implements Vehicle {

    public void start() {
        System.out.println("Bike Started");
    }
}
```

#### Step 3: Abstract Factory

```java
abstract class VehicleFactory {

    abstract Vehicle createVehicle();
}
```

**Explanation**

This class says: Every factory must create a vehicle.  
But it does **NOT** specify which vehicle.

#### Step 4: Concrete Factories

**Car Factory**

```java
class CarFactory extends VehicleFactory {

    Vehicle createVehicle() {
        return new Car();
    }
}
```

**Bike Factory**

```java
class BikeFactory extends VehicleFactory {

    Vehicle createVehicle() {
        return new Bike();
    }
}
```

**Explanation**

Each subclass decides object creation.

| Factory     | Object Created |
|-------------|---------------|
| CarFactory  | Car           |
| BikeFactory | Bike          |

#### Step 5: Main Method

```java
public class Main {

    public static void main(String[] args) {

        VehicleFactory factory =
                new CarFactory();

        Vehicle v =
                factory.createVehicle();

        v.start();
    }
}
```

#### Output
```
Car Started
```

### Flow of Execution
```
Client
   ↓
CarFactory
   ↓
Car Object
```

### Why Better than Simple Factory?

Suppose new vehicle comes: **Truck**

We simply create:
- `Truck`
- `TruckFactory`

Existing factories remain **unchanged**.

This follows:
```
Open-Closed Principle
Open for Extension
Closed for Modification
```

### Advantages

#### 1. Extensible
Easy to add new products.

#### 2. Loose Coupling
Client depends on abstraction.

#### 3. Better Design
Follows SOLID principles.

### Disadvantages

#### 1. More Classes
Every product needs separate factory.

#### 2. Increased Complexity
Not suitable for very small projects.

### Best Use Cases

Use when:
- New object types are added frequently
- Large scalable systems
- Framework design

### Real-Life Example

**Different Showrooms**
- Car showroom creates cars
- Bike showroom creates bikes

---

## 3. ABSTRACT FACTORY PATTERN

### Definition
Creates **families of related objects** together.

### Main Idea
```
One Factory
Creates Multiple Related Objects
```

### Vehicle Example

Vehicle families:

- **Electric Vehicles**
  - ElectricCar
  - ElectricBike
- **Petrol Vehicles**
  - PetrolCar
  - PetrolBike

### Important Rule

Electric factory must create:
- ✅ ElectricCar
- ✅ ElectricBike

Petrol factory must create:
- ✅ PetrolCar
- ✅ PetrolBike

We should **never** mix families.

### Structure
```
Abstract Factory
       ↓
ElectricFactory / PetrolFactory
       ↓
Car + Bike
```

### Step-by-Step Code

#### Step 1: Product Interfaces

**Car Interface**

```java
interface Car {

    void manufacture();
}
```

**Bike Interface**

```java
interface Bike {

    void manufacture();
}
```

**Explanation**

Every car and bike must implement `manufacture()`.

#### Step 2: Electric Vehicle Classes

**Electric Car**

```java
class ElectricCar implements Car {

    public void manufacture() {
        System.out.println("Electric Car Manufactured");
    }
}
```

**Electric Bike**

```java
class ElectricBike implements Bike {

    public void manufacture() {
        System.out.println("Electric Bike Manufactured");
    }
}
```

#### Step 3: Petrol Vehicle Classes

**Petrol Car**

```java
class PetrolCar implements Car {

    public void manufacture() {
        System.out.println("Petrol Car Manufactured");
    }
}
```

**Petrol Bike**

```java
class PetrolBike implements Bike {

    public void manufacture() {
        System.out.println("Petrol Bike Manufactured");
    }
}
```

#### Step 4: Abstract Factory

```java
interface VehicleFactory {

    Car createCar();

    Bike createBike();
}
```

**Explanation**

Every vehicle factory must create:
- One Car
- One Bike

#### Step 5: Electric Vehicle Factory

```java
class ElectricVehicleFactory
        implements VehicleFactory {

    public Car createCar() {
        return new ElectricCar();
    }

    public Bike createBike() {
        return new ElectricBike();
    }
}
```

#### Step 6: Petrol Vehicle Factory

```java
class PetrolVehicleFactory
        implements VehicleFactory {

    public Car createCar() {
        return new PetrolCar();
    }

    public Bike createBike() {
        return new PetrolBike();
    }
}
```

**Explanation**

Each factory creates related products.

| Factory         | Car         | Bike         |
|-----------------|-------------|--------------|
| ElectricFactory | ElectricCar | ElectricBike |
| PetrolFactory   | PetrolCar   | PetrolBike   |

#### Step 7: Main Method

```java
public class Main {

    public static void main(String[] args) {

        VehicleFactory factory =
                new ElectricVehicleFactory();

        Car car = factory.createCar();

        Bike bike = factory.createBike();

        car.manufacture();
        bike.manufacture();
    }
}
```

#### Output
```
Electric Car Manufactured
Electric Bike Manufactured
```

### Flow of Execution
```
Client chooses factory
        ↓
Factory creates related objects
        ↓
Compatible objects returned
```

### Why Use Abstract Factory?

Suppose application supports:
- Electric mode
- Petrol mode

You can switch entire family easily:

```java
VehicleFactory factory =
        new PetrolVehicleFactory();
```

Everything automatically becomes petrol.

### Advantages

#### 1. Ensures Compatibility
Related objects work together.

#### 2. Highly Scalable
Easy to add new families.

#### 3. Loose Coupling
Client does not know concrete classes.

#### 4. Better Organization
Groups related products together.

### Disadvantages

#### 1. Many Classes
Large number of factories and products.

#### 2. Complex Design
Harder for beginners initially.

### Best Use Cases

Use when:
- Products belong to same family
- Objects must work together
- Themes or configurations exist

Examples:
- Vehicle systems
- UI themes
- Operating systems
- Database providers

---

## Difference Between All Three

| Feature         | Simple Factory              | Factory Method              | Abstract Factory                    |
|-----------------|-----------------------------|-----------------------------|-------------------------------------|
| Main Idea       | One factory creates all objects | Separate factory for each object | One factory creates related objects |
| Objects Created | Single object               | Single object               | Family of objects                   |
| Complexity      | Low                         | Medium                      | High                                |
| Flexibility     | Low                         | High                        | Very High                           |
| Extensibility   | Low                         | High                        | High                                |
| Example         | Car/Bike                    | CarFactory/BikeFactory      | ElectricFactory/PetrolFactory       |

---

## Easy Memory Tricks

### Simple Factory
> One shop sells everything

### Factory Method
> Separate shop for each product

### Abstract Factory
> One complete family/package together

---

## Final Interview Definitions

### Simple Factory
A single factory class creates objects based on input conditions.

### Factory Method
Subclasses decide which object should be created.

### Abstract Factory
Creates families of related objects without exposing concrete classes.
