# Decorator Design Pattern Notes

The Decorator Design Pattern is a **Structural Design Pattern** used to add new functionality or behavior to an object **dynamically at runtime** without modifying the existing class.

Instead of changing the original object, we **wrap** it inside another object called a **Decorator**.

---

## Definition

> Decorator Pattern attaches additional responsibilities to an object dynamically by placing it inside special wrapper objects called **decorators**.

---

## Why Do We Need Decorator Pattern?

Suppose we have a coffee shop application.

We may need combinations like:

- Simple Coffee
- Coffee + Milk
- Coffee + Sugar
- Coffee + Milk + Sugar
- Coffee + Chocolate

If we use inheritance, we must create many classes:

- `MilkCoffee`
- `SugarCoffee`
- `MilkSugarCoffee`
- `ChocolateMilkCoffee`

As features increase, the number of classes increases.

This problem is called:

> **Class Explosion Problem**

Decorator pattern solves this problem by **combining features dynamically**.

---

## Real-Life Analogy

Think of ordering a pizza.

- Base Pizza
- Add Cheese
- Add Corn
- Add Paneer

You are not changing the original pizza.  
You are **wrapping extra toppings** around it.

Decorator works the same way.

---

## Main Idea

Decorator pattern uses:

```
Composition Instead of Inheritance
```

Meaning:

> **Decorator HAS-A Component**

instead of:

> Decorator IS-A Component only

---

## Structure of Decorator Pattern

### 1. Component Interface
Common interface for original objects and decorators.

### 2. Concrete Component
The actual base object.

### 3. Decorator Abstract Class
Contains reference of component object.

### 4. Concrete Decorators
Add extra functionality.

### UML Structure

```
          Component
              ↑
      ----------------
      |              |
ConcreteComponent   Decorator
                        ↑
               ----------------
               |              |
      ConcreteDecoratorA  ConcreteDecoratorB
```

---

## Coffee Example

### Step 1: Component Interface

```java
interface Coffee {
    String getDescription();
    int getCost();
}
```

All coffee objects must implement these methods.

### Step 2: Concrete Component

```java
class SimpleCoffee implements Coffee {

    public String getDescription() {
        return "Simple Coffee";
    }

    public int getCost() {
        return 50;
    }
}
```

This is the original object.

### Step 3: Abstract Decorator

```java
abstract class CoffeeDecorator implements Coffee {

    protected Coffee coffee;

    public CoffeeDecorator(Coffee coffee) {
        this.coffee = coffee;
    }
}
```

Decorator stores another coffee object.

### Step 4: Concrete Decorators

**Milk Decorator**

```java
class MilkDecorator extends CoffeeDecorator {

    public MilkDecorator(Coffee coffee) {
        super(coffee);
    }

    public String getDescription() {
        return coffee.getDescription() + " + Milk";
    }

    public int getCost() {
        return coffee.getCost() + 10;
    }
}
```

**Sugar Decorator**

```java
class SugarDecorator extends CoffeeDecorator {

    public SugarDecorator(Coffee coffee) {
        super(coffee);
    }

    public String getDescription() {
        return coffee.getDescription() + " + Sugar";
    }

    public int getCost() {
        return coffee.getCost() + 20;
    }
}
```

### Step 5: Main Class

```java
public class Main {

    public static void main(String[] args) {

        Coffee coffee = new SimpleCoffee();

        coffee = new MilkDecorator(coffee);

        coffee = new SugarDecorator(coffee);

        System.out.println(coffee.getDescription());
        System.out.println(coffee.getCost());
    }
}
```

### Output

```
Simple Coffee + Milk + Sugar
80
```

---

## Internal Working

**Step 1**
```java
new SimpleCoffee()
```
Creates base coffee.

**Step 2**
```java
new MilkDecorator(coffee)
```
Wraps milk feature around simple coffee.

**Step 3**
```java
new SugarDecorator(coffee)
```
Wraps sugar feature around milk coffee.

### Object Structure

```
SugarDecorator
      ↓
MilkDecorator
      ↓
SimpleCoffee
```

Each decorator adds its own behavior.

---

## Key Characteristics

### 1. Runtime Feature Addition
Features are added dynamically.

### 2. Uses Composition
Decorator stores component object.

### 3. Open/Closed Principle
- Open for extension
- Closed for modification

We can add new decorators without changing old code.

---

## Advantages

### 1. Flexible
Features can be added or removed dynamically.

### 2. Avoids Class Explosion
No need for many subclasses.

### 3. Reusable Decorators
Decorators can be combined in many ways.

### 4. Follows SOLID Principles
Especially **Open/Closed Principle**.

---

## Disadvantages

### 1. Many Small Classes
Every feature requires a new decorator class.

### 2. Complex Object Creation
Nested decorators may become difficult to read.

**Example:**
```java
new SugarDecorator(
    new MilkDecorator(
        new SimpleCoffee()
    )
)
```

---

## Decorator vs Inheritance

| Inheritance          | Decorator              |
|----------------------|------------------------|
| Static behavior      | Dynamic behavior       |
| Compile-time changes | Runtime changes        |
| Many subclasses      | Few reusable decorators|
| Less flexible        | More flexible          |

---

## Real-World Examples in Java

Decorator pattern is widely used in **Java I/O classes**.

**Example:**

```java
BufferedReader br =
    new BufferedReader(
        new InputStreamReader(System.in));
```

Here:
```
System.in
   wrapped by InputStreamReader
   wrapped by BufferedReader
```

Each wrapper adds new functionality.

---

## Common Use Cases

Decorator pattern is used when:

- Features need to be added dynamically
- Too many subclasses are being created
- Behavior combinations are required
- We want flexible and reusable design

---

## Important Interview Points

### Decorator Pattern Uses
- Composition
- Runtime wrapping
- Dynamic feature addition

### Problem Solved
- Class explosion problem

### Principle Followed
- Open/Closed Principle

### Short Interview Definition

> Decorator Design Pattern is a structural design pattern that dynamically adds additional functionality to objects by wrapping them inside decorator classes without modifying the original object.
