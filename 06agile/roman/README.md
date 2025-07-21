# Roman Numeral Converter

A simple Rust program to convert numbers to Roman numerals.

## Features

- Converts integers between 1 and 3999 to Roman numerals
- Handles edge cases like 0 (returns empty string)
- Validates input range
- Interactive command-line interface

## Requirements

- Rust 1.70 or later (edition 2024)
- Cargo (Rust's package manager)

## Installation

1. Clone the repository:
   ```bash
   git clone git@github.com:nettok/pdds.git
   cd pdds/06agile/roman
   ```

2. Build the project:
   ```bash
   cargo build --release
   ```

## Usage

Run the program:
```bash
cargo run
```

Then enter numbers at the prompt to see their Roman numeral equivalents. Type 'q' or 'quit' to exit.

Example session:
```
> 42
XLII

> 1984
MCMLXXXIV

> q
```

## Running Tests

To run the included unit tests:
```bash
cargo test
```

## How It Works

The program uses a simple algorithm that:
1. Takes a number as input
2. Validates it's within the acceptable range (0-3999)
3. Converts it to Roman numerals using standard subtractive notation
4. Returns the result or an appropriate error message

## License

GPLv3.
