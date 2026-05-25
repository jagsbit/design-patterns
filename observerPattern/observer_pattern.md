# Observer Design Pattern

The Observer Design Pattern is a **Behavioral Design Pattern**.

It is used when one object changes its state and multiple other objects need to be informed automatically.

It creates a **one-to-many relationship** between objects.

---

## Real-Life Example

### YouTube Channel Example
- A YouTube Channel uploads videos.
- Subscribers want notifications whenever a new video is uploaded.
- Instead of subscribers checking manually again and again, the channel automatically sends notifications.

| Role | Example |
|------|---------|
| Subject / Observable | YouTube Channel |
| Observer | Subscribers |
| Notification | New video uploaded |

---

## Main Components of Observer Pattern

### 1. Subject (Observable)

The object being observed.

**Responsibilities:**
- Add observers
- Remove observers
- Notify observers

### 2. Observer

Objects that want updates from the subject.

**Responsibilities:**
- Receive notifications
- React to updates

---

## Flow of Observer Pattern

```
Subscriber subscribes to channel
        ↓
Channel stores subscriber in list
        ↓
New video uploaded
        ↓
Channel notifies all subscribers
        ↓
Subscribers receive notification
```

---

## Your Example Explanation

You created a YouTube notification system.

### Step 1: Subject Interface

```java
public interface YoutubeChannel {
    void register(Subscriber subscriber);
    void deregister(Subscriber subscriber);
    void notifySubscribers(String title);
}
```

**Purpose**

This interface defines:
- how observers subscribe
- how they unsubscribe
- how notifications are sent

---

### Step 2: Concrete Subject

```java
public class PelamPelCoding implements YoutubeChannel {
```

This class is the actual YouTube channel.

#### Variables

```java
private String name;
private List<Subscriber> subscribers = new ArrayList<>();
```

- **`name`** — Stores channel name.
- **`subscribers`** — Stores all subscribers.

> This is very important in Observer Pattern.  
> The subject always maintains a **collection of observers**.

#### Constructor

```java
PelamPelCoding(String name){
     this.name = name;
}
```

Sets channel name.

#### register()

```java
@Override
public void register(Subscriber subscriber) {
    subscribers.add(subscriber);
}
```

Adds subscriber to the list.

**Example:**
```java
pelamPelCoding.register(harsh);
```
Now Harsh will receive notifications.

#### deregister()

```java
@Override
public void deregister(Subscriber subscriber) {
     subscribers.remove(subscriber);
}
```

Removes subscriber from list. Now that subscriber will not receive updates.

#### notifySubscribers()

```java
@Override
public void notifySubscribers(String title) {
      for(Subscriber subscriber : subscribers){
            subscriber.notified(title, name);
      }
}
```

This is the **heart of Observer Pattern**.

What happens here?
- Loop through all subscribers and notify each one.
- If 1000 subscribers exist, all 1000 will get updates automatically.

---

### Step 3: Observer Class

```java
public class Subscriber {
```

This class represents observers.

#### Variable

```java
private String name;
```

Stores subscriber name.

#### Constructor

```java
Subscriber(String name){
    this.name = name;
}
```

#### notified()

```java
public void notified(String title, String channelName){
     System.out.println(
        "Hyy " + name + " :" + title + ": upload by " + channelName
     );
}
```

This method is called by the subject whenever a new video is uploaded.

---

### Step 4: Main Class

```java
PelamPelCoding pelamPelCoding =
      new PelamPelCoding("Nitish Modi");
```

Creates YouTube channel.

#### Create Subscribers

```java
Subscriber harsh = new Subscriber("Harsh");
Subscriber anshuman = new Subscriber("Anshuman");
```

#### Register

```java
pelamPelCoding.register(anshuman);
pelamPelCoding.register(harsh);
```

Both users subscribed.

#### Notify

```java
pelamPelCoding.notifySubscribers("Read Repair in distributed system");
```

**Output:**
```
Hyy Anshuman :Read Repair in distributed system: upload by Nitish Modi
Hyy Harsh :Read Repair in distributed system: upload by Nitish Modi
```

---

## Complete Working

### Step-by-Step Execution

1. Channel created
2. Subscribers created
3. Subscribers subscribe
4. Channel uploads video
5. `notifySubscribers()` called
6. Loop runs on subscriber list
7. Each subscriber gets notification

---

## Why Observer Pattern is Used

**Without Observer Pattern:**

Subscriber repeatedly checks:
```
"Any new video?"
"Any new video?"
"Any new video?"
```

This is called **Polling**.

**Problem:**
- Wastes resources
- Inefficient

**With Observer Pattern:**

Channel directly informs subscribers → **Efficient and event-driven.**

---

## Advantages of Observer Pattern

### 1. Loose Coupling

Channel only knows:
- Subscriber has `notified()` method

It does NOT care:
- who subscriber is
- how subscriber works internally

### 2. Dynamic Subscription

Observers can join or leave anytime.
- `register()`
- `deregister()`

### 3. Scalable

Can notify:
- 10 users
- 10,000 users
- millions of users

using same logic.

### 4. Event Driven

Actions happen automatically when state changes.

---

## Disadvantages

### 1. Too Many Notifications

If many observers exist: performance issues may occur.

### 2. Hard Debugging

Sometimes difficult to track: who notified whom.

---

## Where Observer Pattern is Used

1. YouTube Notifications
2. Instagram Followers
3. Stock Market Apps
4. Weather Apps
5. Chat Applications
6. Event Systems
7. Java Swing Event Handling
8. Angular Observables
9. Spring Event Listeners

---

## Observer Pattern Structure

```
        Subject
           |
   ----------------
   |      |       |
Observer Observer Observer
```

---

## Important Interview Points

Observer Pattern is:
- **Behavioral** Design Pattern
- **One-to-Many** Relationship
- **Event Driven**
- **Loosely Coupled**

### Important Methods

**Subject Side**
- `register()`
- `deregister()`
- `notifySubscribers()`

**Observer Side**
- `update()`
- `notify()`

---

## Simple Definition for Exams

> Observer Design Pattern is a behavioral design pattern where one object (subject) automatically notifies multiple dependent objects (observers) whenever its state changes.

---

## Key Revision Points

- Subject maintains list of observers
- Observers register themselves
- Subject notifies all observers on state change
- Reduces polling
- Creates loose coupling
- Very useful in event systems and notifications
