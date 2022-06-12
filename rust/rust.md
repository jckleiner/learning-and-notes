# Rust

 * **rustc**: The compiler which takes your Rust code and compiles it into binary code
 * **rustup**: The command line utility to install and update Rust
 * **cargo**: The Rust build system and package manager

https://doc.rust-lang.org/book/ch01-02-hello-world.html

## Vscode Extensions
  - rust-analyzer
  - CodeLLDB
  - Event Better Toml
  - crates: hepls with dependency management (only works for dependencies coming from crates.io)
  - error lense

## Installation
`curl --proto '=https' --tlsv1.2 https://sh.rustup.rs -sSf | sh`

This will download and install the official compiler for the Rust
programming language, and its package manager, Cargo.

Rustup metadata and toolchains will be installed into the Rustup
home directory, located at:  `/Users/kleiner/.rustup`

This can be modified with the `RUSTUP_HOME` environment variable.

The Cargo home directory located at:  `/Users/kleiner/.cargo`

This can be modified with the `CARGO_HOME` environment variable.

The cargo, rustc, rustup and other commands will be added to
Cargo's bin directory, located at:  `/Users/kleiner/.cargo/bin`

  This path will then be added to your PATH environment variable by
modifying the profile files located at:

    /Users/kleiner/.profile
    /Users/kleiner/.zshenv

You can uninstall at any time with rustup self uninstall and
these changes will be reverted.

## Basics

### Cargo 
 * Creating a Project with Cargo: `cargo new hello_cargo`
 * Build: `cargo build` (creates a `target` directory and puts the exec in `target/debug`)
 * Buind and run: `cargo run`
 * Check if compiles without creating an executable: `cargo check` (faster than building)
 * Build for release: `cargo build --release` compiles with opimizations, takes longer but is faster compared to the *DEV* profile. Exec goes to `target/release`.

### Basics
 * `main` is always the first code that runs in every executable Rust program
 * A `!` means it you are calling a **macro** instead of a normal function (macros don’t always follow the same rules as functions).
 * Compiling and running are separate steps: `rustc main.rs` will create an executable called `main` (or `main.exe`)


 * By default variables are *immutable*. The Rust compiler guarantees that when you state a value won’t change, it really won’t change
 * *shadowing* works differently. You can do `let x = 5` and after that `let x = 2 + 1` and the last declaration will be taken. If a `let x = 2` is done in a different scope (for example an inner block), then when the block returns, x will have the old value, and not 2.

### Data Types
Two data type subsets: **scalar** and **compound**.

Scalar types:
 - integers (`u8`, `u16`, `u32`, `u64`, `u128`, `usize`, `i8`, `i16`, `i32`, `i64`, `i128`, `isize`)
 - floating-point numbers (`f32`, `f64`)
 - Booleans (`bool`: true/false)
 - characters (`char`)

`let result = 56.7 * 5` -> cannot multiply `{float}` by `{integer}`

Compont types:
 - **Tuple**: A tuple is a general way of grouping together a number of values with a *variety of types* into one compound type. Tuples have a fixed length: once declared, they cannot grow or shrink in size.
 `let tup: (i32, f64, u8) = (500, 6.4, 1)`
 `let five_hundred = tup.0;`
 `let (x, y, z) = tup;`
 - **Array**: Fixed length. Unlike tuples, all elements must have the same type.
 `let arr: [i32; 5] = [1, 2, 3, 4, 5];`
 `let arr: [&str; 1] = ["hi"];`
 `let a = [3; 5];` -> [3, 3, 3, 3, 3]

### Functions
 * snake_case