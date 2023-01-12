package Model;

import java.io.Serializable;
import java.util.Arrays;

public class Set<T> implements Serializable{
	private T[] arr;
	private int size;
	private final int ENLARGE_FACTOR = 2;

	public Set() {
		arr = (T[]) new Object[ENLARGE_FACTOR];
		size = 0;
	}

	public int size() {
		return size;
	}

	public boolean add(T o) {
		if (arr.length == size) {
			enlargeArray();
		}
		for (int i = 0; i < size; i++) {
			if (arr[i].equals(o)) // Check!
				return false;
		}
		arr[size++] = o;
		return true;
	}

	private void enlargeArray() {
		T[] temp = (T[]) new Object[size * ENLARGE_FACTOR];
		for (int i = 0; i < size; i++) {
			temp[i] = arr[i];
		}
		arr = temp;
	}

	public boolean set(T o, int num) {
		for (int i = 0; i < size; i++) {
			if (arr[i].equals(o)) // Check!
				return false;
		}
		arr[num] = o;
		return true;
	}

	public T get(int index) {
		return arr[index];
	}

	public boolean remove(int index) {
		for (int i = index; i < size - 1; i++) {
			arr[i] = arr[i + 1];
		}
		arr[--size] = null;
		return true;
	}
}
