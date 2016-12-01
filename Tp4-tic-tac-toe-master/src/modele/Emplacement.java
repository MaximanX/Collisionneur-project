package modele;

public class Emplacement {
	private int x;
	private int y;

	public Emplacement(int x, int y) {
		setX(x);
		setY(y);
	}

	public int getX() {
		return x;
	}

	private void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	private void setY(int y) {
		this.y = y;
	}

	@Override
	public String toString() {
		return "[" + x + ", " + y + "]";
	}

	public boolean isEqual(Emplacement e) {
		return this.getX() == e.getX() && this.getY() == e.getY();
	}
}
