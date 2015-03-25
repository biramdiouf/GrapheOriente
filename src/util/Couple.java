package util;

public class Couple<T, U> {
	private T x;
	private U y;

	public Couple(T x, U y) {
		super();
		this.x = x;
		this.y = y;
	}

	public T x() {
		return x;
	}

	public U y() {
		return y;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (!(obj instanceof Couple)) {
			return false;
		}
		@SuppressWarnings("unchecked")
		Couple<T, U> other = (Couple<T, U>) obj;
		if (x == null) {
			if (other.x != null) {
				return false;
			}
		} else if (!x.equals(other.x)) {
			return false;
		}
		if (y == null) {
			if (other.y != null) {
				return false;
			}
		} else if (!y.equals(other.y)) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return String.format("Couple [x=%s, y=%s]", x, y);
	}
}
