use std::io;
use std::io::Write;
use anyhow::{anyhow, Result};

fn main() {
    println!("Enter a number to convert it a roman numeral.  Enter 'q' to quit the program.");
    loop {
        print!("> ");
        io::stdout().flush().expect("Could not flush stdout");

        let mut input = String::new();
        match io::stdin().read_line(&mut input) {
            Ok(_bytes) => {
                let input = input.trim();
                if input == "q" || input == "quit" {
                    break;
                }

                let number: i16 = match input.parse() {
                    Ok(parsed) => { parsed },
                    Err(_error) => {
                        println!("Error: Could not parse `{}` as an i16\n", input);
                        continue;
                    }
                };
                match to_roman(number) {
                    Ok(roman) => {
                        println!("{}", roman);
                    },
                    Err(error) => println!("Error: {}", error)
                }
            }
            Err(error) => println!("Error: {}", error),
        }
        println!();
    }
}

fn to_roman(number: i16) -> Result<String> {
    if number == 1 {
        Ok("I".to_owned())
    } else {
        Err(anyhow!("Cannot represent {} as a roman number", number))
    }
}
