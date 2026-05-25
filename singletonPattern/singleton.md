# Singleton Design Pattern in Java

The Singleton Design Pattern is a **Creational Design Pattern** that ensures:

- Only **one object** of a class is created.
- A **global access point** is provided to access that object.

---

## Real-Life Example

Think about:

- Database Connection
- Logger
- Configuration Class
- Cache Manager

We usually need only one shared object throughout the application.

**Example:**  
If multiple database connection objects are created unnecessarily:
- memory waste occurs
- resource management becomes difficult

So we use **Singleton**.

---

## Main Idea of Singleton

A Singleton class:
- creates only one object
- prevents outside classes from creating objects directly

---

## Rules to Create Singleton Class

### 1. Make Constructor Private

```java
private User() {
}
```

So no other class can do:

```java
new User();
```

### 2. Create Static Object Reference

```java
private static User user;
```

Static belongs to class, not object.

### 3. Provide Public Static Method

```java
public static User getUser()
```

This method returns the single object.

---

## Basic Singleton Implementation

```java
package singletonPattern;

public class User {

    private static User user;

    private User() {
        System.out.println("Object Created");
    }

    public static User getUser() {

        if(user == null) {
            user = new User();
        }

        return user;
    }
}
```

### How It Works

**First Call**
```java
User u1 = User.getUser();
```
- `user == null`
- object created

**Second Call**
```java
User u2 = User.getUser();
```
- object already exists
- same object returned

### Checking Singleton

```java
public class Main {

    public static void main(String[] args) {

        User u1 = User.getUser();
        User u2 = User.getUser();

        System.out.println(u1.hashCode());
        System.out.println(u2.hashCode());
    }
}
```

> Same hashcode means same object.

---

## Problem with Basic Singleton

It is **NOT thread safe**.

If two threads execute simultaneously:

```java
if(user == null)
```

both threads may create separate objects.

---

## Thread Safe Singleton

### Method 1: Synchronized Method

```java
package singletonPattern;

public class User {

    private static User user;

    private User() {
    }

    public synchronized static User getUser() {

        if(user == null) {
            user = new User();
        }

        return user;
    }
}
```

**Problem with Synchronized Method**

Every thread waits even after object creation. This **decreases performance**.

---

## Double Checked Locking Singleton

Best normal Singleton implementation.

```java
package singletonPattern;

public class User {

    private static volatile User user = null;

    private User() {
        System.out.println("User Created");
    }

    public static User getUser() {

        if(user == null) {

            synchronized(User.class) {

                if(user == null) {
                    user = new User();
                }
            }
        }

        return user;
    }
}
```

### Why Double Check?

**First Check**
```java
if(user == null)
```
Avoids unnecessary locking.

**Second Check**
```java
if(user == null)
```
Ensures another thread has not created object already.

### Why `volatile` is Important?

```java
private static volatile User user;
```

Without `volatile`, JVM may:
1. Allocate memory
2. Assign reference
3. Execute constructor

Another thread may get a **partially initialized object**.

`volatile` prevents this problem.

---

## Advantages of Singleton

- Saves memory
- Global access point
- Shared resource management
- Lazy initialization possible
- Thread-safe implementations possible

## Disadvantages of Singleton

- Difficult unit testing
- Global state problem
- Can violate Single Responsibility Principle
- Difficult to scale in distributed systems

---

## Where to Use Singleton

### 1. Database Connection
One shared DB connection manager.

### 2. Logger
One logging object for whole application.

### 3. Configuration Class
Application settings loaded once.

### 4. Cache Manager
Single cache object in memory.

---

## How to Break Singleton

Even thread-safe singleton can be broken.

Ways to break:
1. Reflection
2. Serialization
3. Cloning

### 1. Breaking Singleton using Reflection

Reflection can access private constructor.

**Example**

```java
package singletonPattern;

import java.lang.reflect.Constructor;

public class BreakSingleton {

    public static void main(String[] args) throws Exception {

        User user1 = User.getUser();

        Constructor<User> constructor =
                User.class.getDeclaredConstructor();

        constructor.setAccessible(true);

        User user2 = constructor.newInstance();

        System.out.println(user1.hashCode());
        System.out.println(user2.hashCode());
    }
}
```

**Output**
```
12345
67890
```

Different hashcodes = different objects. **Singleton broken.**

**How Reflection Breaks It**

```java
constructor.setAccessible(true);
```

bypasses private constructor access.

---

### 2. Breaking Singleton using Serialization

When object is serialized and deserialized using `ObjectInputStream` / `ObjectOutputStream`, a new object may get created.

**Example**
```java
User user2 = (User) inputStream.readObject();
```

This can create another object.

**Fix for Serialization**

Use:
```java
protected Object readResolve() {
    return user;
}
```

---

### 3. Breaking Singleton using Cloning

If class implements `Cloneable`, `clone()` creates a new object.

**Fix for Cloning**

Override clone method:
```java
@Override
protected Object clone() throws CloneNotSupportedException {
    throw new CloneNotSupportedException();
}
```

---

## Unbreakable Singleton Design Pattern

The best implementation is:

### ✅ Enum Singleton

**Why Enum Singleton?**

Java guarantees:
- only one object
- thread safety
- reflection safety
- serialization safety

**Enum Singleton Implementation**

```java
package singletonPattern;

public enum Database {

    INSTANCE;

    public void showMessage() {
        System.out.println("Singleton Object");
    }
}
```

**Using Enum Singleton**

```java
package singletonPattern;

public class Main {

    public static void main(String[] args) {

        Database d1 = Database.INSTANCE;
        Database d2 = Database.INSTANCE;

        System.out.println(d1.hashCode());
        System.out.println(d2.hashCode());

        d1.showMessage();
    }
}
```

### Why Enum Singleton is Unbreakable

#### 1. Reflection Safe
Trying reflection gives:
```
Cannot reflectively create enum objects
```
JVM blocks it.

#### 2. Serialization Safe
JVM ensures same object after deserialization.

#### 3. Thread Safe
JVM handles enum initialization safely.

---

## Comparison Table

| Implementation          | Thread Safe | Reflection Safe | Serialization Safe |
|-------------------------|:-----------:|:---------------:|:------------------:|
| Basic Singleton         | ❌          | ❌              | ❌                 |
| Synchronized Singleton  | ✅          | ❌              | ❌                 |
| Double Checked Locking  | ✅          | ❌              | ❌                 |
| Enum Singleton          | ✅          | ✅              | ✅                 |

---

## Important Interview Questions

**What is Singleton Pattern?**  
A design pattern that ensures only one object of a class exists and provides global access to it.

**Why Constructor is Private?**  
To prevent object creation from outside class.

**Why Static Method?**  
Because object should be accessible without creating class object.

**Why `volatile` in Double Checked Locking?**  
To prevent partially initialized objects due to instruction reordering.

**Best Singleton Implementation?**  
Enum Singleton.

---

## Final Conclusion

- Singleton ensures **single object** creation.
- **Double Checked Locking** is good for multithreading.
- **Reflection**, **Serialization**, and **Cloning** can break Singleton.
- **Enum Singleton** is safest and recommended in production applications.
