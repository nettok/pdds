use opt01::sort;

fn main() {
    let arr = sort::random_array::<1000>();
    
    let mut arr_bubble = arr.clone();
    let mut arr_selection = arr.clone();
    
    sort::bubble_sort(&mut arr_bubble);
    sort::selection_sort(&mut arr_selection);

    println!("Original array (first 10 elements): {:?}", &arr[..10]);
    println!("Bubble sorted (first 10 elements): {:?}", &arr_bubble[..10]);
    println!("Selection sorted (first 10 elements): {:?}", &arr_selection[..10]);
    println!("Are bubble and selection sorts equal? {}", arr_bubble == arr_selection);
}
