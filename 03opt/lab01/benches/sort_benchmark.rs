use criterion::measurement::WallTime;
use criterion::{BenchmarkGroup, BenchmarkId, Criterion, criterion_group, criterion_main, BatchSize};
use lab01::sort::{
    bubble_sort, insertion_sort, merge_sort, quicksort, random_vec, selection_sort,
};

fn bench_sort(c: &mut Criterion) {
    let mut group = c.benchmark_group("Sorting");
    group.sample_size(10);
    bench_sort_functions_with_input_size::<10>(&mut group);
    bench_sort_functions_with_input_size::<100>(&mut group);
    bench_sort_functions_with_input_size::<1_000>(&mut group);
    bench_sort_functions_with_input_size::<10_000>(&mut group);
    bench_sort_functions_with_input_size::<50_000>(&mut group);
    bench_sort_functions_with_input_size::<100_000>(&mut group);
    // bench_sort_functions_with_input_size::<1_000_000>(&mut group);
    // bench_sort_functions_with_input_size::<10_000_000>(&mut group);
    group.finish();
}

fn bench_sort_functions_with_input_size<const N: usize>(group: &mut BenchmarkGroup<WallTime>) {
    let arr = random_vec::<N>();
    let display_size = format!("{}", N);

    group.bench_with_input(
        BenchmarkId::new("bubble_sort", &display_size),
        &display_size,
        |b, _| {
            b.iter_batched(
                || arr.clone(),
                |mut arr| bubble_sort(&mut arr),
                BatchSize::PerIteration
            )
        },
    );
    group.bench_with_input(
        BenchmarkId::new("selection_sort", &display_size),
        &display_size,
        |b, _| {
            b.iter_batched(
                || arr.clone(),
                |mut arr| selection_sort(&mut arr),
                BatchSize::PerIteration
            )
        },
    );
    group.bench_with_input(
        BenchmarkId::new("insertion_sort", &display_size),
        &display_size,
        |b, _| {
            b.iter_batched(
                || arr.clone(),
                |mut arr| insertion_sort(&mut arr),
                BatchSize::PerIteration
            )
        },
    );
    group.bench_with_input(
        BenchmarkId::new("merge_sort", &display_size),
        &display_size,
        |b, _| {
            b.iter_batched(
                || arr.clone(),
                |mut arr| merge_sort(&mut arr),
                BatchSize::PerIteration
            )
        },
    );
    group.bench_with_input(
        BenchmarkId::new("quicksort", &display_size),
        &display_size,
        |b, _| {
            b.iter_batched(
                || arr.clone(),
                |mut arr| quicksort(&mut arr),
                BatchSize::PerIteration
            )
        },
    );
}

criterion_group!(benches, bench_sort);
criterion_main!(benches);
