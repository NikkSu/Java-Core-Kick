# Arrays Processing - Part I

## Overview
This is a small, structured Java application designed to read, validate, parse, and process arrays
of integers and doubles from a text file.

Instead of writing everything in a single script, the project is built using enterprise-level
architecture patterns to ensure scalability and maintainability.

## Key Features
* Pipeline Architecture: Data flows smoothly through `Reader` -> `Validator` -> `Parser` -> `Factory` -> `Services`.
* Robust Validation: Uses Regular Expressions (Regex) to filter out garbage data.
* Factory Pattern: Clean object creation without exposing internal structures.
* Logging: Fully configured Log4j2 (outputs to both console and file).
* Testing: High test coverage using JUnit 5.

## Tech Stack
* Java 21
* Maven
* JUnit 5
* Log4j2

## How to run
1. Ensure you have the `data/arrays.txt` file in the correct directory.
2. Run the `Main` class.
3. Check the console or `logs/app.log` to see the parsed data, math calculations, and sorting results!