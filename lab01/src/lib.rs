pub mod sort {
    use rand::Rng;

    pub fn bubble_sort<T: PartialOrd>(arr: &mut [T]) {
        let mut n = arr.len();
        let mut swapped = true;
    
        while swapped {                     // n - 1
            swapped = false;                    
            for i in 1..n {        // (n - 1) + (n - 2) + ... +  1 = (n^2 - n)/2
                if arr[i - 1] > arr[i] {    // ... * c1
                    arr.swap(i - 1, i);  // ... * c2 (swap occurs more often)
                    swapped = true;
                }
            }
            n -= 1;
        }
    }

    // selection sort

    pub fn selection_sort<T: PartialOrd>(arr: &mut [T]) {
        let len = arr.len();
        for i in 0..len {          // n
            let mut min_idx = i;
            for j in i + 1..len {  // (n - 1) + (n - 2) + ... +  1 = (n^2 - n)/2
                if arr[j] < arr[min_idx] {  // ... * c1
                    min_idx = j;
                }
            }
            if i != min_idx {               // ... * c2
                arr.swap(i, min_idx);       // ... * c3 (swap occurs less often)
            }
        }
    }

    pub fn random_array<const N: usize>() -> [i32; N] {
        let mut rng = rand::rng();
        let mut arr = [0; N];

        for elem in arr.iter_mut() {
            *elem = rng.random();
        }

        arr
    }

    #[cfg(test)]
    mod tests {
        use super::*;

        #[test]
        fn bubble_sort_random() -> Result<(), String> {
            test_sort_algorithm(bubble_sort, random_array::<10000>())
        }

        #[test]
        fn selection_sort_random() -> Result<(), String> {
            test_sort_algorithm(selection_sort, random_array::<10000>())
        }

        fn test_sort_algorithm<F, const N: usize>(
            sort_fn: F,
            mut arr: [i32; N],
        ) -> Result<(), String>
        where
            F: Fn(&mut [i32]),
        {
            sort_fn(&mut arr);

            for i in 0..arr.len() - 1 {
                if arr[i] > arr[i + 1] {
                    return Err(format!("Unordered at {}", i));
                }
            }
            Ok(())
        }
    }
}
