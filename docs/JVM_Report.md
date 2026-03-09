# JVM Report -- MediTrack Project

This document explains the internal working of the Java Virtual Machine
(JVM) and how it executes Java programs.

------------------------------------------------------------------------

# 1. What is JVM?

The **Java Virtual Machine (JVM)** is an abstract computing machine
responsible for executing Java bytecode.

Java programs follow the workflow:

    Java Source Code (.java)
            ↓
    Java Compiler (javac)
            ↓
    Bytecode (.class)
            ↓
    JVM Execution

Because bytecode runs on any JVM, Java achieves **platform
independence**.

This principle is known as:

    Write Once, Run Anywhere (WORA)

------------------------------------------------------------------------

# 2. Class Loader

The **Class Loader subsystem** loads compiled class files into the JVM
memory.

Types of class loaders:

### Bootstrap Class Loader

Loads core Java libraries such as:

    java.lang
    java.util

### Extension Class Loader

Loads extension libraries from the JRE.

### Application Class Loader

Loads application classes from the classpath.

------------------------------------------------------------------------

# 3. Runtime Data Areas

The JVM organizes memory into several runtime areas.

## Heap

The **Heap** stores all objects created during program execution.

Example:

    new Patient()
    new Doctor()

The heap is shared among all threads.

------------------------------------------------------------------------

## Stack

Each thread has its own **stack memory**.

The stack stores:

-   method calls
-   local variables
-   intermediate results

Example:

    main()
      → createAppointment()
      → generateBill()

------------------------------------------------------------------------

## Method Area

Stores:

-   class metadata
-   static variables
-   method bytecode

Example:

    Constants.TAX_RATE
    static blocks

------------------------------------------------------------------------

## Program Counter (PC Register)

The **PC Register** keeps track of the currently executing instruction.

Each thread has its own PC register.

------------------------------------------------------------------------

# 4. Execution Engine

The **Execution Engine** executes bytecode instructions.

It contains:

### Interpreter

Reads and executes bytecode line by line.

Pros: - quick startup

Cons: - slower execution

------------------------------------------------------------------------

### JIT Compiler (Just-In-Time Compiler)

The JIT compiler improves performance by compiling frequently executed
bytecode into native machine code.

Benefits:

-   faster execution
-   optimized code

------------------------------------------------------------------------

# 5. Garbage Collector

Java automatically manages memory using **Garbage Collection**.

Unused objects are removed from the heap.

Example:

    Appointment appointment = null;

The object becomes eligible for garbage collection.

Benefits:

-   prevents memory leaks
-   simplifies memory management

------------------------------------------------------------------------

# 6. JVM in MediTrack

When running MediTrack:

    java com.airtribe.meditrack.Main

The JVM performs:

1.  Class loading
2.  Bytecode verification
3.  Memory allocation
4.  Execution of application logic

Entities like:

    Patient
    Doctor
    Appointment

are created in **heap memory**, while method calls run on the **stack**.

------------------------------------------------------------------------

# 7. Summary

The JVM enables Java programs to be:

-   portable
-   secure
-   memory managed
-   platform independent

This architecture allows MediTrack to run on **Windows, Linux, or macOS
without code changes**.
