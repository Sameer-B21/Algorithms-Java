package a1;

import java.util.Random;

public class Sorts {

	public static void swap(int[] a, int i, int j) {
		// swaps the elements in a list
		int tmp = a[i];
		a[i] = a[j];
		a[j] = tmp;

	}

	// shuffles the elements in the list 'a'
	public static void shuffle(int[] a) {
		Random rng = new Random();
		for (int i = a.length - 1; i >= 0; i--) {
			int j = rng.nextInt(0, i + 1);
			swap(a, i, j);
		}
	}

	// checks if a list is in an ascending order
	public static boolean isSorted(int[] a) {
		if (a.length < 2) {
			return true;
		}
		for (int i = 0; i < a.length - 1; i++) {
			if (a[i] > a[i + 1]) {
				return false;
			}
		}
		return true;
	}

	// insertion sort
	public static void iSort(int[] a) {
		int i = 1;
		while (i < a.length) {
			int j = i;
			while (j > 0) {
				if (a[j - 1] > a[j]) {
					swap(a, j - 1, j);
					j--;
				} else {
					break;
				}
			}
			i++;
		}
	}

	// selection sort
	public static void sSort(int[] a) {
		for (int i = 0; i < a.length - 1; i++) {
			int minIndex = i;
			for (int j = i + 1; j < a.length; j++) {
				if (a[j] < a[minIndex]) {
					minIndex = j;
				}
			}
			swap(a, i, minIndex);
		}
	}

	// quick sort
	public static void qSort(int[] a) {
		qSortImpl(a, 0, a.length - 1);
	}

	public static void qSortImpl(int[] a, int lo, int hi) {
		if (lo >= hi || lo < 0) {
			return;
		}
		int p = partition(a, lo, hi);
		qSortImpl(a, lo, p - 1);
		qSortImpl(a, p + 1, hi);
	}

	// partition method for quicksort
	public static int partition(int[] a, int lo, int hi) {
		int pivot = a[hi];
		int i = lo - 1;
		for (int j = lo; j < hi; j++) {
			if (a[j] <= pivot) {
				i++;
				swap(a, i, j);
			}
		}
		i++;
		swap(a, i, hi);
		return i;
	}

	// bogo sort
	public static void bogoSort(int[] a) {
		while (!isSorted(a)) {
			shuffle(a);
		}
	}

	// bozo sort
	public static void bozoSort(int[] a) {
		Random rng = new Random();
		while (!isSorted(a)) {
			int i = rng.nextInt(0, a.length);
			int j = rng.nextInt(0, a.length);
			swap(a, i, j);
		}
	}
}
