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
    if number == 0 {
        Ok("".to_owned())
    }
    else if number == 1 {
        Ok("I".to_owned())
    } else if number == 5 {
        Ok("V".to_owned())
    } else if number == 10 {
        Ok("X".to_owned())
    } else if number == 50 {
        Ok("L".to_owned())
    } else if number == 100 {
        Ok("C".to_owned())
    } else if number == 500 {
        Ok("D".to_owned())
    } else if number == 1000 {
        Ok("M".to_owned())
    } else {
        Err(anyhow!("Cannot represent {} as a roman number", number))
    }
}

#[cfg(test)]
mod tests {
    use super::*;

    #[test]
    fn test_basic_conversion() {
        assert_eq!(to_roman(1).unwrap(), "I");
        assert_eq!(to_roman(5).unwrap(), "V");
        assert_eq!(to_roman(10).unwrap(), "X");
        assert_eq!(to_roman(50).unwrap(), "L");
        assert_eq!(to_roman(100).unwrap(), "C");
        assert_eq!(to_roman(500).unwrap(), "D");
        assert_eq!(to_roman(1000).unwrap(), "M");
    }

    #[test]
    fn test_composite_numbers() {
        assert_eq!(to_roman(3).unwrap(), "III");
        assert_eq!(to_roman(4).unwrap(), "IV");
        assert_eq!(to_roman(9).unwrap(), "IX");
        assert_eq!(to_roman(58).unwrap(), "LVIII");
        assert_eq!(to_roman(1994).unwrap(), "MCMXCIV");
    }

    #[test]
    fn test_edge_cases() {
        assert_eq!(to_roman(0).unwrap(), "");
        assert_eq!(to_roman(3999).unwrap(), "MMMCMXCIX");
    }

    #[test]
    fn test_invalid_numbers() {
        assert!(to_roman(-1).is_err());
        assert!(to_roman(4000).is_err());
        assert!(to_roman(32767).is_err());
    }
}
