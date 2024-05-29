package ia.math;

public class Vecteur{
	private double x;
	private double y;

	public double getX(){
		return this.x;
	}
	public void setX(double x){
		this.x = x;
	}
	public double getY(){
		return this.y;
	}
	public void setY(double y){
		this.y = y;
	}

	public Vecteur(double x , double y){
		this.setX(x);
		this.setY(y);
	}

	public Vecteur add(Vecteur vecteur){
		return new Vecteur(this.getX() + vecteur.getX() , this.getY() + vecteur.getY());
	}
	public Vecteur substract(Vecteur vecteur){
		return new Vecteur(this.getX() - vecteur.getX() , this.getY() - vecteur.getY());
	}
	public Vecteur scale(double scalar){
		return new Vecteur(this.getX() * scalar , this.getY() * scalar);
	}
	public double magnitude(){
		return Math.sqrt(this.getX() * this.getX() + this.getY() + this.getY());
	}
	public double homothetie(double value){
		if (this.magnitude() != 0){
			return value / this.magnitude();
		}
		return 0;
	}
	public Vecteur unit(){
		double k = this.homothetie(1);
		return this.scale(k);
	}
	public double dotProduct(Vecteur vecteur){
		return this.getX() * vecteur.getX() + this.getY() * vecteur.getY();
	}

}