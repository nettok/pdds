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

    pub fn insertion_sort<T: PartialOrd>(arr: &mut [T]) {
        for i in 1..arr.len() {
            let mut j = i;
            while j > 0 && arr[j - 1] > arr[j] {
                arr.swap(j - 1, j);
                j -= 1;
            }
        }
    }

    pub fn merge_sort<T: Copy + Ord>(arr: &mut [T]) {
        if arr.len() <= 1 {
            return;
        }

        let mid = arr.len() / 2;
        let (left, right) = arr.split_at_mut(mid);
        
        merge_sort(left);
        merge_sort(right);
        
        let merged = merge(left, right);
        arr.copy_from_slice(&merged);
    }

    fn merge<T: Copy + Ord>(left: &[T], right: &[T]) -> Vec<T> {
        let mut result = Vec::with_capacity(left.len() + right.len());
        let (mut i, mut j) = (0, 0);

        while i < left.len() && j < right.len() {
            if left[i] <= right[j] {
                result.push(left[i]);
                i += 1;
            } else {
                result.push(right[j]);
                j += 1;
            }
        }
        
        result.extend_from_slice(&left[i..]);
        result.extend_from_slice(&right[j..]);
        result
    }

    pub fn random_array<const N: usize>() -> [i32; N] {
        let mut rng = rand::rng();
        let mut arr = [0; N];

        for elem in arr.iter_mut() {
            *elem = rng.random();
        }

        arr
    }

    pub fn quicksort<T: PartialOrd>(arr: &mut [T]) {
        if arr.len() <= 1 {
            return;
        }

        let pivot_index = quicksort_partition(arr);
        quicksort(&mut arr[..pivot_index]);
        quicksort(&mut arr[pivot_index + 1..]);
    }

    fn quicksort_partition<T: PartialOrd>(arr: &mut [T]) -> usize {
        let pivot_index = arr.len() - 1;
        let mut i = 0;

        for j in 0..pivot_index {
            if arr[j] <= arr[pivot_index] {
                arr.swap(i, j);
                i += 1;
            }
        }

        arr.swap(i, pivot_index);
        i
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

        #[test]
        fn insertion_sort_random() -> Result<(), String> {
            test_sort_algorithm(insertion_sort, random_array::<10000>())
        }

        #[test]
        fn merge_sort_random() -> Result<(), String> {
            test_sort_algorithm(merge_sort, random_array::<10000>())
        }

        #[test]
        fn quicksort_random() -> Result<(), String> {
            test_sort_algorithm(quicksort, random_array::<10000>())
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
